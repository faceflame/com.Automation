package com.automation.tests.AkbarTests;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HR_ORDS_REST_API_Test {

    @BeforeAll
    public static void setup() {
        baseURI = "http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/";
    }

    @AfterClass
    public static void teardown() {
        RestAssured.reset();
    }

    @Test
    public void test1() {
        Response response = given().
                accept("application/json").
                when().get("regions");


        List<Map<String, ?>>regions = response.jsonPath().getList("items");
        String firstRegionName = response.jsonPath().getString( "items[0].region_name");
        List<String>regionNames= response.jsonPath().getList( "items.region_name");
        System.out.println(regionNames);

        System.out.println(firstRegionName);
        System.out.println(regions.get(0).get("region_name"));

        System.out.println(response.jsonPath().getString("items[2].links[0].href"));

        List<String>allHrefs = response.jsonPath().getList("items.links.rel");
        System.out.println(allHrefs);

        String lastRel= response.jsonPath().getString("links[3].rel");

        assertEquals("first", lastRel);
    }

    @Test
    public void TestSingleRegion (){
        Response response = given().accept(ContentType.JSON).
                pathParam("id", 6).
                when().get("regions/{id}");

        //response.prettyPrint();

        List<Map<String, ?>>objects = response.jsonPath().getList("links");
        System.out.println("objects = " + objects);

        Map<String, Object> JsonMap = response.jsonPath().getMap("");
        System.out.println(JsonMap.get("region_name"));


    }
}