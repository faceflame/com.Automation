package com.automation.tests.MarufHomework;

import com.automation.utilities.ConfigurationReader;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Uinames {
    String URL = ConfigurationReader.getProperty("uinames_URL");

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ConfigurationReader.getProperty("uinames_URL");

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
        assertNotNull(response.path("name"));
        assertNotNull(response.path("surname"));
        assertNotNull(response.path("gender"));
        assertNotNull(response.path("region"));

    }

    /* 1.Create a request by providing query parameter: gender, male or female
       2. Verify status code 200, content type application/json; charset=utf-8
       3. Verify that value of gender field is same from step 1
    */
    @Test
    public void GenderTest() {
        Response response = given().queryParam("gender", "male").get(baseURI);
        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
        assertEquals("male", response.body().path("gender"));

        System.out.println(response.path("gender").toString());
    }
    /*
       2 params test
1. Create a request by providing query parameters: a valid region and gender
    NOTE: Available region values are given in the documentation
2. Verify status code 200, content type application/json; charset=utf-8
3. Verify that value of gender field is same from step 1
4. Verify that value of region field is same from step 1*/

    @Test
    public void TwoParamTest() {
        Response response = given().
                queryParam("region", "Turkey").
                queryParam("gender", "female").
                when().get(baseURI);
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getContentType());
        assertEquals("female", response.path("gender"));
        assertEquals("Turkey", response.path("region"));
        assertNotNull(response.path("region"));
    }

    /*
    Invalid gender test
1. Create a request by providing query parameter: invalid gender
2. Verify status code 400 and status line contains Bad Request
3. Verify that value of error field is Invalid gender
*/
    @Test
    public void InvalidGenderTest() {
        Response response = given().
                queryParam("gender", "maleFE").
                when().get(baseURI);
        assertEquals(400, response.statusCode());
        assertEquals("Invalid gender", response.path("error"));
    }
 /*
Invalid region test
1. Create a request by providing query parameter: invalid region
2. Verify status code 400 and status line contains Bad Request
3. Verify that value of error field is Region or language not found
  */

    @Test
    public void InvalidRegionTest() {
        Response response = given().
                queryParam("region", "Tanzania").
                when().get(baseURI);
        assertEquals(400, response.statusCode());
        assertTrue(response.path("error").toString().equals("Region or language not found"));
    }

    /*
    Amount and regions test
1. Create request by providing query parameters: a valid region and amount (must be bigger than 1)
2. Verify status code 200, content type application/json; charset=utf-8
3. Verify that all objects have different name+surname combination
     */
    @Test
    public void PositiveAmountAndRegionTest() {
        Response response = given().
                queryParam("region", "Turkey").
                queryParam("amount", 25).
                when().get(baseURI);
        assertEquals(200, response.statusCode());

        List<String> names = Collections.singletonList(response.path("name").toString());
        List<String> sirnames = Collections.singletonList(response.path("surname").toString());
        List<String> fullNames = new ArrayList<>();



        String[] namesArray = names.get(0).replace("[", "").replace("]", "").replace(",", "").split(" ");
        ;

        String[] sirnamesArray = sirnames.get(0).replace("[", "").replace("]", "").replace(",", "").split(" ");


        for (int i = 0; i < names.size(); i++) {
            fullNames.add(namesArray[i] + " " + sirnamesArray[i]);
        }

        System.out.println(fullNames);
        boolean nameSirnameCombinationsEqual = true;

        for (int i = 0; i < fullNames.size()-1; i++) {
            if (fullNames.get(i).equals(fullNames.get(i + 1))) {
                nameSirnameCombinationsEqual = false;
            }
        }

        assertTrue(nameSirnameCombinationsEqual);
        System.out.println(fullNames);

    }
}
