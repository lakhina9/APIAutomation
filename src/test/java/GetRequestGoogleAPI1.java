
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class GetRequestGoogleAPI1 {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://maps.googleapis.com";
		
		given().
		param("location","-33.8670522,151.1957362").
		param("radius","1500").
		param("key","ChIJP3Sa8ziYEmsRUKgyFmh9AQM").
		
		when().
		get("/maps/api/place/nearbysearch/json").
		
		then().assertThat().statusCode(200).and().
		and().contentType(ContentType.JSON).
		//and().body("results[0].name", equalTo("Cruise Bar"));
		
		//and().body("results[0].place_id",equalTo("ChIJi6C1MxquEmsR9-c-3O48ykI"));
		
		
		and().header("Server", "scaffolding on HTTPServer2");
		
		
	
		
		

	}

}
