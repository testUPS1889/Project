package IBM.Restassured;

import java.io.FileInputStream;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.test.context.TestContext;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.POJONode;
import com.fasterxml.jackson.databind.util.JSONPObject;

import POJOClass.POJOSample;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.apache.commons.io.IOUtils;
import io.restassured.specification.RequestSpecification;


public class Day2 {

	@Test (enabled = false)
	public void JSONtoString()
	{
	RestAssured.baseURI="http://localhost:3000";
	
	String reqbody = "{\"empname\":\"Name22\",\"city\":\"Chennai\",\"address\":\"Street22\"}";
	
	RestAssured.
	given()
	   	.contentType(ContentType.JSON)
	   	.body(reqbody).
	when()
		.post("/apisample").
	then()
		.statusCode(201)
		.log()
		.all();
		
}
	
	@Test (enabled = false)
	public void filemtd() throws IOException
	{
		FileInputStream fis = new FileInputStream(".\\JSON\\PostPayload.json");
		RestAssured.baseURI="http://localhost:3000";
		RestAssured.
		given()
	   		.contentType(ContentType.JSON)
	   		.body(IOUtils.toString(fis,"UTF-8"))
	   		.post("/apisample").
	   	then()
	   		.statusCode(201)
	   		.log()
	   		.all();
		
}

	

	@Test (enabled = false) 
	public void JSONObjExample()
	{
		
		JSONObject obj = new JSONObject();
		obj.put("empname","Name24");
		obj.put("city","Ooty");
		obj.put("address","Street24");
		
		RestAssured.baseURI="http://localhost:3000";
		RestAssured.
		given()
	   		.contentType(ContentType.JSON)
	   		.body(obj.toJSONString()).
	   	when()	
	   		.post("/apisample").
	   	then()
	   		.statusCode(201)
	   		.log()
	   		.all();
		
}

	@Test (enabled = false)
	
	public void AssertionExample()
	//(ITestContext testvariable)
	{
		
		JSONObject obj = new JSONObject();
		obj.put("empname","Name30");
		obj.put("city","Ooty");
		obj.put("address","Street30");
		
		RestAssured.baseURI="http://localhost:3000";
		Response respobj = RestAssured.given()
	   		.contentType(ContentType.JSON)
	   		.body(obj.toJSONString()).
	   	when()	
	   		.post("/apisample").
	   	then()
	   		.statusCode(201)
	   		.log()
	   		.all()
	   		.extract().response();
		
		String city = respobj.jsonPath().getString("city");
	//	String id = respobj.jsonPath().getString("id");
		System.out.println(city);
		
		Assert.assertEquals(city,"Ooty");
//		testvariable.setAttribute("id", id);
		
}

@Test (enabled = false)
	
	public void getdatafromprevstep(ITestContext testvariable)
	{
		
		String id = testvariable.getAttribute("getid").toString();
		RestAssured.baseURI="http://localhost:3000";
		RestAssured.
		given().
			get("/apisample"+id).
		then().
			statusCode(200).
			log().
			all();
			
			
		
} 
	@Test
	public void POJOExample() throws JsonProcessingException
	{
		POJOSample pojoobj = new POJOSample();
		pojoobj.setEmpname("Name31");
		pojoobj.setCity("Karur");
		pojoobj.setAddress("Street31");
		
		System.out.println(pojoobj.getEmpname());
		System.out.println(pojoobj.getCity());
		System.out.println(pojoobj.getAddress());
		
		ObjectMapper obj = new ObjectMapper();
		String body = obj.writerWithDefaultPrettyPrinter().writeValueAsString(pojoobj);
		System.out.println(body);
		
		RestAssured.baseURI="http://localhost:3000";
		RestAssured.given()
		   		.contentType(ContentType.JSON)
		   		.body(body).
		   	when()	
		   		.post("/apisample").
		   	then()
		   		.statusCode(201)
		   		.log()
		   		.all();
	}
	
}