package com.automation.tests.MarufHomework;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class Github {
    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("github_URL");
    }

    @AfterAll
    public static void teardown() {
        RestAssured.reset();
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
    public void OrganizationsGithubTest() {

        given()
                .log().all()
                .pathParam("org", "cucumber").
        when().
                get(baseURI + "/orgs/{org}").
                prettyPeek().
        then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("login", is("cucumber"))
                .body("name", is("Cucumber"))
                .body("id", is(320565));
    }
/*
Verify error message
1. Send a get request to /orgs/:org. Request includes :
• Header Accept with value application/xml
• Path param org with value cucumber
2. Verify status code 415, content type application/json; charset=utf-8
3. Verify response status line include message Unsupported Media Type
 */

    @Test
    public void VerifyErrorMessage(){
        given()
                .accept(ContentType.XML)
                .pathParam("org", "cucumber").

        when()
                .get(baseURI + "/orgs/{org}").
                prettyPeek().

        then()
                .statusCode(415)
                .contentType(ContentType.JSON)
        .body("message", containsString("Unsupported  'Accept' header"));
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
        public void VerifyNumberOfReposTest(){

            Response response = given().
                    pathParam("org", "cucumber")
                    .get("/orgs/{org}");

            int publicReposField= response.jsonPath().getInt("public_repos");
            System.out.println(publicReposField);

            Response response2 = given().
                    pathParam("org", "cucumber")
                    .get("/orgs/{org}/repos");

            List<Object> repos=response2.jsonPath().getList("");

            assertThat(publicReposField, equalTo(repos.size()));
     /*
            given()
                    .accept(ContentType.XML)
                    .pathParam("org", "cucumber").

            when()
                    .get(baseURI + "/orgs/{org}").
                    prettyPeek().

            then()

                    .body("public_repos", equalTo(83));
*/
        }

        /*
        Repository id information
1. Send a get request to /orgs/:org/repos. Request includes :
• Path param org with value cucumber
2. Verify that id field is unique in every in every object in the response
3. Verify that node_id field is unique in every in every object in the response
         */

        @Test
    public void RepoInfoTest(){
//1. are IDs unique in the response body?
            Response response2 = given().
                    pathParam("org", "cucumber")
                    .get("/orgs/{org}/repos");

            List<Integer> IDs= response2.jsonPath().getList("id");

            boolean idUnique= true;

            for (int i = 0; i <IDs.size() ; i++) {
                int count = 0;
                for (int j = 0; j <IDs.size() ; j++) {
                    if(IDs.get(i) == IDs.get(j)){
                        count++;
                    }
                }
                if( count >1){
                    idUnique= false;
                }
            }
            assertTrue(idUnique);

            List<String>nodes= response2.jsonPath().getList("node_id");

//2. are nodeIDs unique?

            boolean nodeUnique= true;

            for (int i = 0; i <nodes.size() ; i++) {
                int count = 0;
                for (int j = 0; j <nodes.size() ; j++) {
                    if(nodes.get(i).equals(nodes.get(j))){
                        count++;
                    }
                }
                System.out.println(count);
                if( count >1){
                    nodeUnique= false;
                }
            }
            assertTrue(nodeUnique);
        }


}
