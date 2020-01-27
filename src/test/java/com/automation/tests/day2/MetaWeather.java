package com.automation.tests.day2;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MetaWeather {
    private String baseURI = "https://www.metaweather.com/api/";

    @Test
    public void test1() {
        Response response = given().baseUri(baseURI + "location/search").
                queryParam("query", "New").
                get();

        assertEquals(200, response.getStatusCode());
//        assertTrue(response.getBody().asString().contains("San Francisco"));
        System.out.println(response.prettyPrint());

    }
/*
/api/location/search/?query=san
/api/location/search/?query=london
/api/location/search/?lattlong=36.96,-122.02
            /api/location/search/?lattlong=50.068,-5.316
//"woeid": 2487956,
*/

//users/100/ - 100 is a path parameter
    //users/255?name=James| name - query parameter key =value, key is a query parameter


    @Test
    public void test2() {
        Response response = given().
                pathParam("woeid", "28743736").
                get(baseURI + "location/{woeid}");

        System.out.println(response.prettyPrint());
    }


}
