package jsonpack;

import org.apache.commons.io.FileUtils;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.json.JSONException;

public class Jsonread {
	
	Gson gson;
	Reader read1;
	Reader read2;
	JsonElement json1;
	JsonElement json2;
	String user1;
	String user2;
	
	public void compareFiles() throws IOException{
        String expectedJson = FileUtils.readFileToString(new File("src/main/resources/user1.json"),StandardCharsets.UTF_8);
        String actualJson = FileUtils.readFileToString(new File("src/main/resources/user2.json"),StandardCharsets.UTF_8);
        try {
          JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);
      } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      }
	public void compareGson() throws FileNotFoundException, JSONException {
		
		gson = new GsonBuilder().setPrettyPrinting().create();

		read1 = new FileReader("src/main/resources/user1.json");
		
	    json1 = gson.fromJson(read1, JsonElement.class);

	    user1 = gson.toJson(json1);
	    
	    read2 = new FileReader("src/main/resources/user2.json");
		
	    json2 = gson.fromJson(read2, JsonElement.class);

	    user2 = gson.toJson(json2);
	    
	    JSONAssert.assertEquals(user1, user2, JSONCompareMode.STRICT);
	    
	    if(user1.equals(user2))
	    {
	    	System.out.println("Json File Matched");
	    	
	    }else {
	    	
	    	System.out.println("Json File not Matched");
	    }	
	    
	    System.out.println(user1);
	    System.out.println(user2);
	}
	
	public void validate() throws JSONException
	{
		 JSONAssert.assertEquals("{firstname:\"Nikesh\"}", user1, false);
	}
	
    public static void main( String[] args ) throws JSONException, IOException
    {
    	Jsonread ap = new Jsonread();
    	ap.compareGson();
    	ap.validate();
    	ap.compareFiles();
    }

}
