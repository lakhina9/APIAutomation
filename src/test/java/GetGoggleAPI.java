import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;



public class GetGoggleAPI {

	
	//@Test
	public void googleGetRestAPI() {
		
	RestAssured.baseURI="https://maps.googleapis.com";
		
		Response res=given().
		param("location", "-33.8670522,151.1957362").
		param("type", "restaurant").
		param("keyword","cruise").param("radius", "1500").param("key", "AIzaSyBBDLqGN3Ns689md3VSBUCTSZNBMFL9M1I").
		when().get("/maps/api/place/nearbysearch/json").then().assertThat().statusCode(200).
		and().contentType(ContentType.JSON).and().body("results[0].name", equalTo("Cruise Bar")).
		and().body("results[0].place_id",equalTo("ChIJi6C1MxquEmsR9-c-3O48ykI")).
		and().header("server", "scaffolding on HTTPServer2").extract().response();
		
		String rawResponse=res.asString();
		JsonPath path=new JsonPath(rawResponse);
		
		System.out.println("Raw Response:" +rawResponse);
		System.out.println("Json Response:" +path);
		
		System.out.println("Value at given path:"+path.getString("results[0].place_id"));

		
		
		
		/*body("results[0].name",equalTo("Sydney")).and().
	       body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
	       header("Server","pablo");*/
	}





}
