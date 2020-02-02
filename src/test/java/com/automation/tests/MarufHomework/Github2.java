package com.automation.tests.MarufHomework;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;



public class Github2 {

    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("github_URL");
    }

    @AfterAll
    public static void teardown() {
        RestAssured.reset();
    }

    /*

Ascending order by full_name sort
1. Send a get request to /orgs/:org/repos. Request includes :
• Path param org with value cucumber
• Query param sort with value full_name
2. Verify that all repositories are listed in alphabetical order based on the value of the field name
     */


    @Test
    public void Ascending_Order_By_Full_Name_Sort(){

        Response response = given().
                pathParam("org", "cucumber")
                .queryParam("sort", "full_name")
                .get("/orgs/{org}/repos");

        List<String> namesList = response.jsonPath().getList("name");
        System.out.println(namesList);
        List<Map<String, String >>Objects= response.jsonPath().getList("");

        for (int i = 0; i <Objects.size()-1 ; i++) {

            String firstItem= Objects.get(i).get("name");
            String nextItem= Objects.get(i+1).get("name");

            ///Selenium Tables . table contents comparison dersinden bu karsilastirma mantigina bakilabilir.
            assertTrue(firstItem.compareTo(nextItem)<1);

        }



        //  System.out.println(response.jsonPath().getString("[].name"));


    }

    @Test

    public void test4() {

        Response response =
                given()
                        .pathParam("org", "cucumber")
                        .queryParam("sort", "full_name")
                        .when()
                        .get("/orgs/{org}/repos");



        JsonPath json = response.jsonPath();
        List<String> names = json.getList("name");
        List<String> sortedNames = json.getList("name");
        Collections.sort(sortedNames);
        assertEquals(sortedNames,names);
        System.out.println(names);


    }



}
