package com.automation.tests.AkbarTests;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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
                        queryParam("gender", "Male").
                        queryParam("nameContains", "lo").
                        when().
                        get(baseURI + "spartans/search");

        boolean isEmpty = response.jsonPath().getBoolean("pageable.sort.empty");
        assertTrue(isEmpty);


        int totalElements = response.jsonPath().getInt("totalElements");
        Integer numberOfElements = response.jsonPath().getInt("numberOfElements");
    }

    @Test
    public void SearchForJsonArrayItems() {
        RequestSpecification reqSpec = given().accept(ContentType.JSON);

        Response response =
                given().
                        accept(ContentType.JSON).
                        queryParam("gender", "Male").
                        when().
                        get(baseURI + "spartans/search");

        Long phone = response.jsonPath().getLong("content[0].phone");
        System.out.println("phone num is: " + phone);
       // response.body().prettyPrint();

        List<Long> phones= response.jsonPath().getList("content.phone");
        System.out.println(phones);

        List<String>names= response.jsonPath().getList("content.name");
        System.out.println(names);

        List< Map<String, String> >objectlist = response.jsonPath().getList("content");

        System.out.println(objectlist.get(0).get("name"));

    }
}


