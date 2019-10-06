import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


public class APIExample1 {
	@Test
	public void GetWeatherDetails()
	{   
		
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/Hyderabad");

	    String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
		int statusCode=response.getStatusCode();
		Assert.assertEquals(200, statusCode,"Failed");
				
		/*System.out.println(response.getHeader("Server"));
		System.out.println(response.header("Content-Type"));
		System.out.println(response.header("Content-Encoding"));
		*/
	//print all tne headers from HTTPREsponse
				
		/*Headers allHeaders=response.headers();		
		for(Header all:allHeaders) {			
			System.out.println("Key:"+all.getName()+"Value:" +all.getValue()+"Class:" +all.getClass());
						
		}*/
			
//get the response body
		/*ResponseBody t=response.getBody();
		System.out.println("Response Body:"+t);
			//convert body into string
		System.out.println("String form of body:"+t.asString());
		
		*/
	// In Java JsonPath you do not need to have $ as the root node. You can completely skip that.	
		JsonPath jpath=response.jsonPath();
		String temperature=jpath.get("Temperature");		
		System.out.println("Temperature Value:" +temperature);
		Assert.assertEquals(temperature, "24.3 Degree celsius","Values does not matched");
		
	 }

		
		


		
	}