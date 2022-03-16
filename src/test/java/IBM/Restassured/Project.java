package IBM.Restassured;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import POJOClass.POJOProject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class Project {

	@Test
	public void POST(ITestContext user, ITestContext password) throws JsonProcessingException
	{
		POJOProject pojoobj = new POJOProject();
		pojoobj.setUsername("User1");
		pojoobj.setFirstname("first1");
		pojoobj.setLastname("Last1");
		pojoobj.setEmail("Firstlast@gmail.com");
		pojoobj.setPassword("Firstlast1");
		pojoobj.setPhone("9999999999");
		
		String uname = pojoobj.getUsername();
		String pwd = pojoobj.getPassword();
		
		System.out.println(uname);
		System.out.println(pojoobj.getFirstname());
		System.out.println(pojoobj.getLastname());
		System.out.println(pojoobj.getEmail());
		System.out.println(pwd);
		System.out.println(pojoobj.getPhone());
		
		ObjectMapper obj = new ObjectMapper();
		String body = obj.writerWithDefaultPrettyPrinter().writeValueAsString(pojoobj);
		System.out.println(body);
		
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		RestAssured.given()
		   		.contentType(ContentType.JSON)
		   		.body(body).
		   	when()	
		   		.post("/user").
		   	then()
		   		.statusCode(200)
		   		.log()
		   		.all();
		
		user.setAttribute("Username", uname);
		password.setAttribute("Password", pwd);
				
	}
	
	@Test(dependsOnMethods = "POST")
	public void Login(ITestContext user, ITestContext password) throws JsonProcessingException
	{
		String username = user.getAttribute("Username").toString();
		System.out.println("User Name : " +username);
		String pwd = password.getAttribute("Password").toString();
		System.out.println("Password : " +pwd);
		
		RestAssured.baseURI="https://petstore.swagger.io/v2";
				RestAssured.given()
		   		.get("/user/login?username="+username + "&password"+pwd).
		   	then()
		   		.statusCode(200)
		   		.log()
		   		.all();

		
	}
	
	@Test(dependsOnMethods = "POST")
	public void PUT(ITestContext user) throws JsonProcessingException
	{
		String username = user.getAttribute("Username").toString();
		System.out.println("User Name : " +username);
		POJOProject pojoobj = new POJOProject();
		pojoobj.setUsername("User1");
		pojoobj.setFirstname("First1");
		pojoobj.setLastname("Last1");
		pojoobj.setEmail("Firstlast@gmail.com");
		pojoobj.setPassword("Firstlast1");
		pojoobj.setPhone("9999999999");
		
		ObjectMapper obj = new ObjectMapper();
		
		String body = obj.writerWithDefaultPrettyPrinter().writeValueAsString(pojoobj);
		
		System.out.println(body);
		
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		
		RestAssured.given()
		   		.contentType(ContentType.JSON)
		   		.body(body).
		   	when()	
		   		.put("/user/"+username).
		   	then()
		   		.statusCode(200)
		   		.log()
		   		.all();
	
	}

	
	@Test(dependsOnMethods = "Login")
	public void Logout() throws JsonProcessingException
	{
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			
			RestAssured.given()
		   		.get("/user/logout").
		   	then()
		   		.statusCode(200)
		   		.log()
		   		.all();

		
	}
	
	@Test(dependsOnMethods = "Logout")
	public void Deleteuser(ITestContext username) throws JsonProcessingException
	{
		String user = username.getAttribute("Username").toString();
		System.out.println("User Name : " +user);
		
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		
		RestAssured.given()
		   		.delete("/user/"+user).
		   	then()
		   		.statusCode(200)
		   		.log()
		   		.all();
	
	}

}
	

