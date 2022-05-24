package basepackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;


import dataUtils.dataReadWrite;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class baseclass {


	protected static String token;
	protected static RequestSpecification tokenresponse;
	static Properties p=new Properties();
	protected static String url;
	protected static String username;
	protected static dataReadWrite excel=new dataReadWrite();

	
	public baseclass() {
		try {
			String path="src//test//resources//config.properties";

			FileInputStream file=new FileInputStream(path);
		
			p.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getUrl(){
		 url=p.getProperty("url");
		return url;
	}
	
	public static String setUrl(String url){
		 url=p.getProperty(url);
		 return url;
	}
	
	public static void setProperty(String key,String value){
		 p.setProperty(key, value);
	}
	
	public static String getUserName(){
		 username=p.getProperty("username");
		return username;
	}
	
	
	public void setresponse(){
		Response getres=null;
	}

	public static void setToken(String stoken){
		token=p.getProperty(stoken);

	}
	
	
	public HashMap  getMapfromDataTable(DataTable data){
		HashMap<String,Object> mapdata=new HashMap<String,Object>(); 
		  List<List<String>> ldata = data.asLists();
	      
	      for(int i=0;i<ldata.size();i++){
	    	  List<String> idata=ldata.get(i);
	     
	    	  for(int j=0;j<idata.size()-1;j=j+2){
	    		  mapdata.put(idata.get(j), idata.get(j+1));
	    	  }
	      }
		return mapdata;
	      

	}
	

}