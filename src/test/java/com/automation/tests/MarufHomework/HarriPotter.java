package com.automation.tests.MarufHomework;

import com.automation.tests.AkbarTests.TestBase;
import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.collection.IsArrayContaining;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


public class HarriPotter extends TestBase {
/*
Verify sorting hat
1. Send a get request to /sortingHat. Request includes :
2. Verify status code 200, content type application/json; charset=utf-8
3. Verify that response body contains one of the following houses:
"Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"
 */
    @Test
    @DisplayName("Verify sorting hat")
    public void VerifySortingHat() {
        List<String> expectedBodyTexts = new ArrayList<>(Arrays.asList("Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"));

        Response response =
                given().
                        accept(ContentType.JSON).
                        when()
                        .get(harriPotterURL + "sortingHat");

        //gotta use replaceAll because the body is returning one letter in each line(corresponding to a key)
        String responseText= response.asString().replaceAll("\"", "");

        assertTrue(expectedBodyTexts.contains(responseText));

    }

    /*
    Verify bad key
1. Send a get request to /characters. Request includes :
• Header Accept with value application/json
• Query param key with value invalid
2. Verify status code 401, content type application/json; charset=utf-8
3. Verify response status line include message Unauthorized
4. Verify that response body says "error": "API Key Not Found"
     */

    @Test
    public void Verify_Bad_Key(){

        given()
                .accept(ContentType.JSON)
                .queryParam("key", "invalid").

        when()
                .get(harriPotterURL + "characters").
        then()
                .statusCode(401)
                .contentType(ContentType.JSON)
                .statusLine(containsString("Unauthorized"))
                .body("error", is("API Key Not Found"))
        ;
    }


    /*
    Verify no key
1. Send a get request to /characters. Request includes :
• Header Accept with value application/json
2. Verify status code 409, content type application/json; charset=utf-8
3. Verify response status line include message Conflict
4. Verify that response body says "error": "Must pass API key for request"
     */

    @Test
    public void Verify_No_Key_Test(){

        given()
                .accept(ContentType.JSON).
        when()
                .get(harriPotterURL + "characters").
        then()
                .statusCode(409)
                .statusLine(containsString("Conflict"))
                .body("error", equalTo("Must pass API key for request"))
                ;
    }

    /*
    Verify number of characters
1. Send a get request to /characters. Request includes :
• Header Accept with value application/json
• Query param key with value {{apiKey}}
2. Verify status code 200, content type application/json; charset=utf-8
3. Verify response contains 194 characters
     */
        @Test
        public void VerifyNumOfChars_Test(){
           Response response =


            given()
                    .accept(ContentType.JSON).
                    queryParam("key", harriPotterAPIKey).
            when()
                    .get(harriPotterURL + "characters");

           assertEquals(200, response.statusCode());
           assertEquals(response.contentType(),  "application/json; charset=utf-8");

           List<Object>objects= response.jsonPath().getList("");

         //total number of objects is 195. therefore this test fails
            assertEquals(194, objects.size());
        }
/*
Verify number of character id and house
1. Send a get request to /characters. Request includes :
• Header Accept with value application/json
• Query param key with value {{apiKey}}
2. Verify status code 200, content type application/json; charset=utf-8
3. Verify all characters in the response have id field which is not empty
4. Verify that value type of the field dumbledoresArmy is a boolean in all characters in the response
5. Verify value of the house in all characters in the response is one of the following:
"Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"
 */

    @Test
    public void Verify_Number_of_Chars_ID_and_House_Test(){


    }

}
