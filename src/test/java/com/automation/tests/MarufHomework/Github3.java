package com.automation.tests.MarufHomework;

import com.automation.tests.AkbarTests.TestBase;
import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;


public class Github3 extends TestBase {
    @BeforeAll
    //https://api.github.com
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("github_URL");

    }
/*
Verify organization information
1. Send a get request to /orgs/:org. Request includes :
• Path param org with value cucumber
2. Verify status code 200, content type application/json; charset=utf-8
3. Verify value of the login field is cucumber
4. Verify value of the name field is cucumber
5. Verify value of the id field is 320565
 */
    @Test
    public void Github1(){
    given()
            .pathParam("Seyfo", "cucumber").
    when()
            .get(baseURI + "/orgs/{Seyfo}" ).
    then()
             .statusCode(200)
    .contentType("application/json; charset=utf-8")
    .contentType(ContentType.JSON)
    .body("login", containsString("cucumber"))
    .body("name", equalToIgnoringCase("cucumber"))
    .body("id", equalTo(320565))
   ;
    }
/*

    Verify error message
1. Send a get request to /orgs/:org . Request includes :
 • Header Accept with value application/xml
• Path param org with value cucumber
2. Verify status code 415, content type application/json; charset=utf-8
            3. Verify response status line include message Unsupported Media Type
  */  @Test
    public void test2(){
    given()
            .accept(ContentType.XML)
            .pathParam("Seyfo", "cucumber").
    when()
            .get(baseURI + "/orgs/{Seyfo}" ).
    then()
            .statusCode(415).
            contentType(ContentType.JSON)
            .statusLine(containsString("Unsupported Media Type"));
}
/*
Number of repositories
1. Send a get request to /orgs/:org. Request includes :
• Path param org with value cucumber
2. Grab the value of the field public_repos
3. Send a get request to /orgs/:org/repos. Request includes :
• Path param org with value cucumber
4. Verify that number of objects in the response is equal to value from step 2
 */

@Test
    public void test3(){
  Response response =
          given()
                  .pathParam("Seyfo", "cucumber").
                  when()
                  .get(baseURI + "/orgs/{Seyfo}" );
  assertEquals(200, response.statusCode());


Integer repoCount=    response.getBody().jsonPath().getInt("public_repos");

    System.out.println(repoCount);

    Response response2 =
            given()
                    .pathParam("Seyfo", "cucumber").
             when()
                    .get(baseURI + "/orgs/{Seyfo}/repos" );


    List< Object> Repos= response2.body().jsonPath().get();
    System.out.println(Repos.size());
assertThat(repoCount, equalTo(Repos.size()));

response.getBody().jsonPath().get("[18].owner.login");


   /* given()
            .pathParam("Seyfo", "cucumber").
    when()
            .get(baseURI + "/orgs/{Seyfo}" ).
    then()
            .body("public_repos", 83).
*/
}




}
