package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import pojo.lc;
import pojo.location;
import resources.TestDataBuild;
import resources.Utils;
import resources.api_res;
public class StepDefination extends Utils {
	ResponseSpecification res3;
	RequestSpecification t;
	Response res2;
	static String place;
	TestDataBuild data  = new TestDataBuild();
	
	@Given("add place payload {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException	{	
		 t =given().spec(reqspecification()).body(data.addplacepayload(name,language,address));
			 		
	}

	@When("user calls {string} with {string} http req")
	public void user_calls_with_http_req(String resource, String method) {
		
		api_res ress=api_res.valueOf(resource);
		System.out.println(ress.getresource());
		
		res3=	new ResponseSpecBuilder().expectStatusCode(200).build();
		if(method.equalsIgnoreCase("POST")) 
		 res2=	t.when().post(ress.getresource()).then().spec(res3)
				.extract().response();			
		else if(method.equals("GET")){
			res2=	t.when().get(ress.getresource()).then().spec(res3)
					.extract().response();}
		
			
			}
	@Then("api call is successfull with status code {string}")
	public void api_call_is_successfull_with_status_code(String string) {
	    assertEquals(res2.getStatusCode(),200);
	}
	@Then("{string} response body {string}")
	public void response_body_to_validate_diff_parameter(String key, String expvalue) {
		
		assertEquals(getJsonPath(res2,key),expvalue);	
		
	}
	@Then("verify placeid created maps to {string} using {string}")
	public void verify_placeid_created_maps_to_using(String expname, String resource) throws IOException {
		
		api_res ress=api_res.valueOf(resource);
		 place = getJsonPath(res2, "place_id");
		 t =given().spec(reqspecification().queryParam("place_id",place));
		 user_calls_with_http_req(resource,"GET");
		 String actname1 = getJsonPath(res2, "name");
		 assertEquals(expname,actname1);	
	}
	@Given("deleteplace  payload")
	public void deleteplace_payload() throws IOException {
		t =given().spec(reqspecification()).body(data.deldata(place));
		
		
		
	}

}
