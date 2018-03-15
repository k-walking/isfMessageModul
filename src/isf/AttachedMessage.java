package isf;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.w3c.dom.NodeList;

public class AttachedMessage {
	
	static String wikiJsonContent;
	static String utf = "Iași";

	static String downloadedWikis = "first.json";
	static NodeList titles;

	public static void main(String[] args) throws JSONException, UnsupportedEncodingException{
		
		wikiJsonContent = URLContentDownloader.getURLContent("https://en.wikipedia.org/w/api.php?action=query&generator=random&grnnamespace=0&prop=extracts&exchars=500&format=json");
		
		
		JSONObject obj = new JSONObject(wikiJsonContent);
		JSONObject query = (JSONObject) obj.get("query");
		JSONObject pages = (JSONObject) query.get("pages");
		
		Iterator<String> keys = pages.keys();
		if( keys.hasNext() ){
		   String key = (String)keys.next(); // First key in your json object
		   JSONObject num = (JSONObject) pages.get(key);
		   
		   Iterator<String> keys2 = num.keys();
			if( keys2.hasNext() ){
			   String key2 = (String)keys2.next(); // First key in your json object
			   String title = (String) num.getString("title");
			   System.out.println(title);
			   String excerpt = (String) num.getString("extract");
			   System.out.println(html2text(excerpt));
			}
		}
	}
	
	public static String html2text(String html) {
	    return Jsoup.parse(html).text();
	}
}
