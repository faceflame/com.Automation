package com.automation.tests.MarufHomework;

import com.automation.utilities.ConfigurationReader;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Uinames {
    String URL = ConfigurationReader.getProperty("uinames_URL");

    @BeforeAll
    public static void setup() {
      RestAssured.baseURI= "https://uinames.com/api/";

    }
 /*1. Send a get request without providing any parameters
2. Verify status code 200, content type application/json; charset=utf-8
3. Verify that name, surname, gender, region fields have value
*/
    @Test
    public void NoParamTest() {
        Response response = get(baseURI);
        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.header("Content-Type"));
        assertNotNull(response.body());

    }

    @Test
    public void GenderTest() {


        Response response = given().baseUri("").
                queryParam("gender", "Male").
                get();



        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());

    }

}
