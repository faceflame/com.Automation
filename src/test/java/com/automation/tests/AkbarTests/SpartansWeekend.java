package com.automation.tests.AkbarTests;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import static io.restassured.RestAssured.baseURI;

public class SpartansWeekend {

    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("spartans_uri");
    }

    @Test
    public void test1() {
        RequestSpecification reqSpec = given().accept(ContentType.JSON);
        Response response =
                given().
                        accept(ContentType.JSON).
                        when().
                        get(baseURI + "spartans");
    }


    @Test
    public void test2() {
        RequestSpecification reqSpec = given().accept(ContentType.JSON);

        Response response =
                given().
                        accept(ContentType.JSON).
                        pathParam("id", 2).
                        when().
                        get(baseURI + "spartans/{id}");

        response.prettyPrint();
        assertEquals("Male", response.path("gender").toString());

    }
}
