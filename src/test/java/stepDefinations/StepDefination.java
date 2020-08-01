package stepDefinations;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pojo.Addplace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefination extends Utils{
	 RequestSpecification response;
	 ResponseSpecification res;
	 Response newResponse;
	 TestDataBuild data = new TestDataBuild();
	 static String place_id;
	 
	 @Given("Add Place PayLoad with {string} {string} {string}")
	 public void add_Place_PayLoad_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		response= given().spec(requestSpecification()).body(data.addLoadPayLoad(name,language,address)).log().all();
	}

	/*
	 * @When("user calls {string} with POST http request") public void
	 * user_calls_with_POST_http_request(String string) {
	 * 
	 * res= new
	 * ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.
	 * JSON).build();
	 * newResponse=response.when().post("/maps/api/place/add/json").then().log().all
	 * ().spec(res).extract().response(); }
	 */
	 
	 @When("user calls {string} with {string} http request")
	 public void user_calls_with_http_request(String resource, String method) throws IOException{
		 APIResources resEnum= APIResources.valueOf(resource);
		 res= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 //newResponse=response.when().post(resEnum.getResource()).then().log().all().spec(res).extract().response();
		 if(method.equalsIgnoreCase("Post"))
		 {
			 newResponse=response.when().post(resEnum.getResource()); 
		 }
		 else if(method.equalsIgnoreCase("Get"))
		 {
			 newResponse=response.when().get(resEnum.getResource()); 
		 }
	    
	 }
	@Then("the API call is success with status code {string}")
	public void the_API_call_is_success_with_status_code(String string) { 
		assertEquals(newResponse.getStatusCode(),200);
		
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	   
		//String resp = newResponse.asString();
		//js = new JsonPath(resp);
		assertEquals(getJsonPath(newResponse,key),value);
		//Get place_id
			
	}
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    //prepare req spec.
		System.out.println("Expected Name:"+ expectedName);
		place_id=getJsonPath(newResponse,"place_id");
		 response= given().spec(requestSpecification()).queryParam("place_id", place_id);
		 user_calls_with_http_request(resource,"GET");
		 String actualName=getJsonPath(newResponse,"name");
		 System.out.println("Actual Name:"+ actualName);
		 assertEquals(actualName,expectedName);
	}
	

	@Given("DeletePlace PayLoad")
	public void deleteplace_PayLoad() throws IOException {
    // Write code here that turns the phrase above into concrete actions
		response= given().spec(requestSpecification()).body(data.deletePlacePayLoad1(place_id));
    
	}

}
