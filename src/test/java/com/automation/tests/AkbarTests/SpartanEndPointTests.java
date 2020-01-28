package com.automation.tests.AkbarTests;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanEndPointTests {


    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("spartans_uri");
    }

    /*
    Given no headers are provided
    When user send request to /api/spartans/2
    Then response status should be 200
    And header should have content type/json
    And text Nels is displayed in response body
    And json object id should be 2
    */
    @Test
    public void VerifySingleSpartanSelected() {
        Response response = given().pathParam("id", 2).
                when().get(baseURI + "spartans/{id}");


        //      get(baseURI + "spartans/2");
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json;charset=UTF-8", response.getContentType());
        boolean NelsContained = response.asString().contains("Gokhan");
        assertTrue(NelsContained);
        response.getBody().prettyPeek().print();

    }

    /*
    Given no headers are provided
    When user send request to spartans
    Then response status should be 200
    And header should have content type/json
    And header should contain Date
    */
    @Test
    public void VerifyHeader() {
        Response response = get(baseURI + "spartans");
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertTrue(response.getHeaders().hasHeaderWithName("date"));

    }

    /*
    Given no headers are provided
    When user send request to /api/spartans/2002
    Then response status should be 204
    And header should have content type/json
    And response payload should contain "Spartan Not Found"
    */
//200: Success
// 201 Created
// 204 Updated/Deleted

//400 Bad request
//401 Unauthorized
//404 Not Found
//406 Not Acceptable

// 500 Server error

    @Test
    public void SpartanNotFoundTest() {
/*****/
        Response response = //given().pathParam("id", 2002).get(baseURI + "spartans/{id}");
                given().pathParam("id", 2000).
                        when().get(baseURI + "spartans/{id}");


        assertEquals(404, response.getStatusCode());
        assertEquals("application/json;charset=UTF-8", response.getHeader("Content-Type"));
        assertTrue(response.body().asString().contains("Spartan Not Found"));
    }


    /*
    Given Accept header is provided as Json
    When user send request to /api/spartans/2
    Then response status should be 200
    And header should have content type/json
    And text Nels is displayed in response body
    And json object id should be 2
    */

    @Test
    public void VerifySpartanWithHeaderTest() {
        Response response = given().accept(ContentType.JSON).
                given().pathParam("id", 2).
                when().get(baseURI + "spartans/{id}");

        assertEquals(200, response.getStatusCode());
        assertEquals("application/json;charset=UTF-8", response.getContentType());
        assertTrue(response.body().asString().contains("Gokhan"));
    }

    /*
    Given Accept header is provided as xml
    When user send request to /api/spartans/2
    Then response status should be 406
        */

    @Test
    public void VerifySpartanWithHeaderNegativeTest() {
        Response response = given().accept(ContentType.XML).
                given().pathParam("id", 2).
                when().get(baseURI + "spartans/{id}");

        System.out.println(response.statusLine());

        assertEquals(406, response.getStatusCode());
        assertTrue(response.body().asString().contains("Not Acceptable"));
    }

    /*
    Given Accept header is provided as json
    When querry parameter is gender is male
    When user sends request to api/spartans/search
    Then response status code should be 200
    And header should be content type /Json
        */
    @Test
    public void VerifySpartanWithQuerryParameterTest() {
        Response response = given().
                accept(ContentType.JSON).
                // queryParam("gender", "male").
                        params("gender", "male").
                //queryParam("nameContains", "ol").
                        when().
                        get(baseURI + "spartans/search");

        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        response.prettyPrint();

//Simple navigation to the key or keys
        System.out.println(response.path("pageable.sort.unsorted").toString());



        assertTrue(!response.asString().contains("Female"));
        assertTrue(response.asString().contains("ol"));
    }
/*
    Given no headers are provided
    When user send request to /api/spartans/2
    Then response status should be 200
    And header should have content type/json
    And text Nels is displayed in response body
    And json object id should be 2
    */

    @Test
    public void SingleValueTestReadFromJsonField() {

        Response response = given().pathParam("id", 2).
                when().get(baseURI + "spartans/{id}");

        assertEquals(200, response.getStatusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        response.prettyPrint();


        System.out.println(response.path("name").toString()                            );
        assertNotNull(response.path("name", "gender", "id", "phone"));






    }

}
