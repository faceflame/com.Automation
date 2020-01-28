package com.automation.tests.MarufHomework;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;



public class WarmUpTaskJan27 {
    String URL= "http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/regions";
    /*
     * given url "http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/regions/{id}"
     * when user makes get request with path param id=1
     * and region id is equals to 1
     * then assert that status code is 200
     * and assert that region name is Europe
     */

    @Test
    public void TestWQuerryAndPathParam(){
        Response response = given().
                pathParam("id", "1").
                when().
                get(URL + "/{id}");

        response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals("Europe", response.jsonPath().get("region_name"));

    }



}
