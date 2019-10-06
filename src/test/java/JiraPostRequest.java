import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JiraPostRequest {

	// @Test
	public String getSessionCookies() {

		RestAssured.baseURI = "http://localhost:8080";

		Response rs = given().header("Content-Type", "application/json")
				.header("Authorization", "bGFraGluYTlAZ21haWwuY29tOmxhY0gyQ1lvdTJnOWk1b2FhY1kzODkwNQ==").

				body("{ \"username\": \"lakhina9\", \"password\": \"sunilyazan\"}").when().

				post("/rest/auth/1/session").

				then().assertThat().statusCode(200).extract().response();

		String rawData = rs.asString();
		System.out.println("Raw Data:" + rawData);
		JsonPath jData = new JsonPath(rawData);

		System.out.println("Formatted Data:" + jData);

		String sessionId = jData.get("session.value");

		System.out.println("Session Id:" + sessionId);

		return sessionId;
	}

	// @Test
	public String createIssueInJIRA() {

		String sessionId = getSessionCookies();

		RestAssured.baseURI = "http://localhost:8080";

		Response reponseCreateIssue = given().header("Cookie", "JSESSIONID=" + sessionId + "").

				header("Content-Type", "application/json").

				body("{" + " \"fields\": {" + " \"project\":" + " {" + "          \"key\": \"AG1\"" + "       },"
						+ "       \"summary\": \"Defect using Automation\","
						+ "       \"description\": \"This is an API For Creating Issue in Jira-Using Automationb\",\r\n"
						+ "       \"issuetype\": {" + "          \"name\": \"Bug\"" + "       }" + "   }" + "}")
				.

				when().post("/rest/api/2/issue/").then().statusCode(201).extract().response();
		System.out.println("Get the reponse code:" + reponseCreateIssue.statusCode());

		String rawResponse = reponseCreateIssue.asString();
		JsonPath JsonData = new JsonPath(rawResponse);
		System.out.println("Raw Response:" + rawResponse);

		// response after creating an issue in jira.
		String responseId = JsonData.get("id");
		System.out.println("Response Value Id:/IssueId" + responseId);

		System.out.println("Response Value Key:" + JsonData.get("Key"));
		return responseId;

	}

	@Test
	public void updateCommentsInAlreadyCreatedJIRAIssue() {

		
		RestAssured.baseURI = "http://localhost:8080";
	Response res=	given().headers("Cookie","JSESSIONID=FAEE6FD94D1EBF53D67BD0003454F46A").
	header("Content-Type","application/json").
	body("{" + 
			"    \"body\": \"This is a comment rest comment api Automation.\"," + 
			"    \"visibility\": {" + 
			"        \"type\": \"role\"," + 
			"        \"value\": \"Administrators\"" + 
			"    }" + 
			"}").
		when().post("/rest/api/2/issue/"+createIssueInJIRA()+"/comment"). 
	
	//when().post("/rest/api/2/issue/10102/comment").
	then().assertThat().statusCode(201).extract().response();
	
	System.out.println("Response Code:" +res.asString());
	//comment 
	
	}

	@Test
	public void deleteTheCreatedIssue() {
		
		//URL:http://localhost:8080/rest/api/2/issue/{IssueId}
		
		// take the issue id from create issue api and then pass as a parameter
		
		RestAssured.baseURI = "http://localhost:8080";
		given().header("Cookie", "JSESSIONID=" + getSessionCookies()).
		header("Content-Type","application/json").pathParam("issueId", ""+createIssueInJIRA()).when().
		delete("/rest/api/2/issue/{issueId}").then().statusCode(204);
		
		//there are 2 ways to pass parameter, as a pathparameter or direct in the url
		//when().delete("/rest/api/2/issue/"+createIssueInJIRA()+"").then().assertThat().statusCode(204);
	

		
	}
	
	@Test
	public void JiraAPIUpdate()
	{
		//Creating Issue/Defect
	
		RestAssured.baseURI= "http://localhost:8080";
		Response res=given().header("Content-Type", "application/json").
		header("Cookie","JSESSIONID="+getSessionCookies()).
		pathParams("commentid","10103").
		
		body("{ \"body\": \"Updating comment from the automation code\","+
    "\"visibility\": {"+
        "\"type\": \"role\","+
        "\"value\": \"Administrators\" }"+
"}").when().
		put("/rest/api/2/issue/10207/comment/{commentid}").then().statusCode(200).extract().response();
	
		
		
		
		
		
		
		
		
		  
	}
				

}
