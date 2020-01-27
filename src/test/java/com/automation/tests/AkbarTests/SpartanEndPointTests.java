package com.automation.tests.AkbarTests;

import com.automation.utilities.ConfigurationReader;
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
    And json object id should be 2
    */
    @Test
    public void VerifySingleSpartanSelected() {
        Response response = get(baseURI + "spartans/2");
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json;charset=UTF-8", response.getContentType());
    }

    /*
    Given no headers are provided
    When user send request to spartans
    Then response status should be 200
    And header should have content type/json
    And header should contain Date
    */

    @Test
    public void VerifyHeader(){
        Response response= get(baseURI + "spartans");
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertTrue(response.getHeaders().hasHeaderWithName("Date"));

    }



}
