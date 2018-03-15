package isf;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class URLContentDownloader {
	
	String url;
	
	public URLContentDownloader() {
	}
	
	public static String getURLContent(String url) {
		URL oURL;
		URLConnection oConnection;
		BufferedReader oReader;
		String sLine;
		StringBuilder sbResponse;
		String sResponse = null;
		
		try {
			oURL = new URL(url);
			oConnection = oURL.openConnection();
			oReader = new BufferedReader(new InputStreamReader(oConnection.getInputStream()));
			sbResponse = new StringBuilder();
			
			while((sLine = oReader.readLine()) != null) {
				sbResponse.append(sLine);
			}
			
			sResponse = sbResponse.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return sResponse;
	}
	
	public static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        
        fis.close();
        bis.close();
    }

    public static void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }
}