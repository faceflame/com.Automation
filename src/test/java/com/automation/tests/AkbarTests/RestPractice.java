package com.automation.tests.AkbarTests;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class RestPractice {
    private String baseURL = ConfigurationReader.getProperty("spartans_uri");

    @BeforeClass
    public static void setup() {
        basePath="http://ec2-3-83-242-165.compute-1.amazonaws.com:8000/api/";


        //3.83.242.165 : baseURL | Hostname
        //http://ec2-3-83-242-165.compute-1.amazonaws.com/ baseURL | Hostname
        //8000 is the port
        //entry point | base path to REST API: /api
        //what we most care about /hello the resource we want to get - endpoint
    }
    @Test
    public void test2(){
        Response response = get(ConfigurationReader.getProperty("spartans_uri") + "hello");
        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
        System.out.println(response.getHeader("date"));
    }

    @Test
    public void Hello_End_Point_Test() {
        Response response = get(basePath + "hello");
        assertEquals(200, response.getStatusCode());
        assertEquals("text/plain;charset=UTF-8", response.contentType());
         response.getHeaders().asList();
         assertEquals("Hello from Sparta", response.body().asString());
    }

    /*
    Given no header provided
    When user sends API/hello
    Then the response code should be 200
    And Response Payload should have contentType "text/plain;charset=UTF-8"
    And header should contain "date"
    And Content=Length should be 17
     */

    @Test
    public void test3(){
        Response result = RestAssured.get( baseURI +"hello");
        assertEquals(200, result.getStatusCode());
        assertEquals("text/plain;charset=UTF-8", result.contentType());
        Headers headers= result.getHeaders();

        assertTrue(result.getHeaders().hasHeaderWithName("Date"));
        assertEquals("17", headers.getValue("content-length"));
        assertEquals("17", result.getHeader("content-length"));


        List<String>headersList= new ArrayList<>();

        for (Header head:headers) {
            headersList.add(head.getName());
        }
        System.out.println(headersList);
        assertTrue(headersList.contains("Date"));


    }

}
