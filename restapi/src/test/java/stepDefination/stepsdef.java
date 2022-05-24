package stepDefination;


import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.omg.Messaging.SyncScopeHelper;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;


import basepackage.baseclass;
import dataUtils.dataReadWrite;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class stepsdef extends baseclass{



	static  Response res;

	@Given("^user has generated \"([^\"]*)\" token for Authorization$") 
	public  void authorization(String gtoken){
		setToken(gtoken);
		System.out.println("The authorization for token is = "+token);

		tokenresponse =
				given().
				relaxedHTTPSValidation().
				header("Authorization","Bearer "+token).
				header("Content-Type", "application/json");

	} 	


	@Then("^user retrieves response status code as ([^\"]*)$")
	public void validateStatusCode(int response) {	
		res.
		then().
		assertThat().
		statusCode(response);

	}


	@Then("^user validates whether json body \"([^\"]*)\" has value \"([^\"]*)\"$") 
	public  void validateDetails(String key,String value){
		res.		
		then().
		header("Content-Type", "application/json; charset=utf-8").extract().response();

		System.out.println("output of res as"+res.asString());

		JsonPath js = new JsonPath(res.asString());
		String val = js.getString(key);
		System.out.println(key+" has value "+val);
		Assert.assertEquals(value, val);
	}
	


	@Then("^user validates whether json body \"([^\"]*)\" contains value \"([^\"]*)\"$") 
	public  void validateContainsDetails(String key,String value){
		res.		
		then().
		header("Content-Type", "application/json; charset=utf-8").extract().response();

		System.out.println("output of res as"+res.asString());

		JsonPath js = new JsonPath(res.asString());
		String val = js.getString(key);
		if(value.contains("curdate")) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			LocalDateTime now = LocalDateTime.now();
			value=dtf.format(now);
		}
		System.out.println(key+" contains value "+val);
		if(val.contains(value)){
			Assert.assertTrue(true);
		}else {
			System.out.println( val );
			Assert.assertTrue(false);

		}
	}

	@Then("^user validates whether jsons body \"([^\"]*)\" contains value \"([^\"]*)\"$") 
	public  void validatesContainsDetails(String key,String value){
		res.		
		then().
		header("Content-Type", "application/json").extract().response();

		System.out.println("output of res as"+res.asString());

		JsonPath js = new JsonPath(res.asString());
		String val = js.getString(key);
		if(value.contains("curdate")) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			LocalDateTime now = LocalDateTime.now();
			value=dtf.format(now);
		}
		System.out.println(key+" contains value "+val);
		if(val.contains(value)){
			Assert.assertTrue(true);
		}else {
			System.out.println( val );
			Assert.assertTrue(false);

		}
	}





	@Given("^user has entered json body with below contents$")
	public void setBodyjson(DataTable data){
		HashMap<String,Object> mapdata=new HashMap<String,Object>(); 
		mapdata=getMapfromDataTable(data);

		System.out.println("The contents of body are :"+mapdata.toString());

		tokenresponse=tokenresponse.
				header("Content-Type","application/json").
				contentType(ContentType.JSON).
				body(mapdata);


	}

	@When("^user performs POST \"([^\"]*)\"$") 
	public static void postResponse_login(String urls){
		System.out.println("To perform POST response of ="+urls);
		String getKey=urls.split("/")[0];
		String getVal=setUrl(getKey);
		url=urls.replace(getKey, getVal);
		System.out.println("To perform POST response of ="+url);

		res=tokenresponse.
				when().
				post(url);

	}
	
	@When("^user performs PUT \"([^\"]*)\"$") 
	public static void putResponse_login(String urls){
		System.out.println("To perform PUT response of ="+urls);
		String getKey=urls.split("/")[0];
		String getVal=setUrl(getKey);
		url=urls.replace(getKey, getVal);
		System.out.println("To perform PUT response of ="+url);

		res=tokenresponse.
				when().
				put(url);

	}
	
	@When("^user performs GET \"([^\"]*)\"$") 
	public static void getResponse_login(String urls){
		System.out.println("To perform GET response of ="+urls);
		String getKey=urls.split("/")[0];
		String getVal=setUrl(getKey);
		url=urls.replace(getKey, getVal);
		System.out.println("To perform GET response of ="+url);

		res=tokenresponse.
				when().
				get(url);

	}

	
	@Given("^user has entered below query params$")
	public void setQueryParam(DataTable data){
		HashMap<String,Object> mapdata=new HashMap<String,Object>(); 
		mapdata=getMapfromDataTable(data);

		System.out.println("The contents of query param are :"+mapdata.toString());

		tokenresponse=tokenresponse.
				queryParams(mapdata);
	}

	@Given("^user has entered below header params$")
	public void setHeaderParam(DataTable data){
		HashMap<String,Object> mapdata=new HashMap<String,Object>(); 
		mapdata=getMapfromDataTable(data);

		System.out.println("The contents of header param are :"+mapdata.toString());
		
		tokenresponse=tokenresponse.
				given().
				headers(mapdata);
	}

}