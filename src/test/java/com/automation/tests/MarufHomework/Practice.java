package com.automation.tests.MarufHomework;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.text.IsEmptyString;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class Practice {

    String baseURI= "https://uinames.com/api";
    String gitHubURI= "https://api.github.com";


    @Test
    public void test(){
        Response response = get(baseURI);

        assertEquals(200, response.statusCode());

        assertEquals("application/json; charset=utf-8", response.contentType());

        Map<String, ?>object1=response.jsonPath().getMap("");

        assertNotNull((object1.get("name")));

    }

    /*
    Send a get request to /orgs/:org/repos. Request includes :
• Path param org with value cucumber
4. Verify that number of objects in the response is equal to value from step 2
     */

    @Test
    public void GithubTest(){

        Response response = given().
                                pathParam("franco", "cucumber").
                            get( gitHubURI + "/orgs/{franco}/repos");

        System.out.println(response.jsonPath().getList("name"));


    }

}
