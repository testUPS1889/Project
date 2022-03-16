package IBM.Restassured;



import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Sampleproject {

	@Test (enabled = false)
	public void testcase()
	{
		Response res = RestAssured.get("http://localhost:3000/apisample/1");
		String responsebody = res.asString();
		System.out.println(responsebody);
		System.out.println(res.getStatusLine());
		//System.out.println(res.getHeaders());
		System.out.println(res.jsonPath().getString("city"));
	}
	
	@Test 
	public void testcase1()
	{
		RestAssured.baseURI= "https://petstore.swagger.io/v2";
		//RestAssured.given().get("/apisample/1").then().statusCode(200).log().all();
		//RestAssured.given().delete("/apisample").then().statusCode(200).log().all();
		//String a = "8663358";
		/*
		 * RestAssured .given() .contentType(ContentType.JSON).queryParam("username",
		 * "User22").queryParam("password", "firstlast22") .when()
		 * .get("/user/login").then().statusCode(200).log().all();
		 *///		
		RestAssured
		.given()
			.contentType(ContentType.JSON)
		.when()
			.get("/user/User22").then().statusCode(200).log().all();
	}
}
