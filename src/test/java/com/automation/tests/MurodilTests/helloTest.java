package com.automation.tests.MurodilTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class helloTest {
private String baseURI= "http://ec2-3-83-242-165.compute-1.amazonaws.com:8000/api/";


    @Test
    public void test(){
        //request
        Response response= get(baseURI + "spartans");

        //response
        //verify status code 200
        assertEquals(200, response.statusCode());
        //verify header
        assertTrue(response.contentType().contains("application/json"));

        System.out.println(response.prettyPrint());

    }
}
