package data.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParsingJSONFiles {

	public static void main(String[] args) {
		File dataFile = new File("datas/files/Books.json");

		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(dataFile));
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(isr);
			
			JSONObject jsonObj = (JSONObject)obj;

			Object obj2 = jsonObj.get("books");  // StudyingAzae 출력
			
			
			JSONArray jsonArr = (JSONArray)obj2;
			
			if (jsonArr.size() > 0){
			    for(int i=0; i<jsonArr.size(); i++){
			        JSONObject jsonBook = (JSONObject)jsonArr.get(i);
			        
			        System.out.println("Title : " + (String)jsonBook.get("title")); 
			        
			        Object edition = jsonBook.get("edition");
			        if(edition != null) {
			        	System.out.println("edition : " + (Long)edition); 
			        }
			        
			        JSONArray authors = (JSONArray)jsonBook.get("authors");
			        if (authors != null){
					    for(int j=0; j <authors.size(); j++){					        
					        System.out.println("author : " + (String)authors.get(j));
					    }
			        }  
			        
			        Object author = jsonBook.get("author");
			        if(author != null) {
			        	System.out.println("author : " + (String)author); 
			        }
			        
			        System.out.println("publisher : " + (String)jsonBook.get("publisher")); 
			        System.out.println("year : " + (Long)jsonBook.get("year")); 
			        
			        Object isbn = jsonBook.get("isbn");
			        if(isbn != null) {
			        	System.out.println("isbn : " + (String)isbn); 
			        }
			        
			        
			        System.out.println();
			    }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
