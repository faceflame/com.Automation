package com.automation.tests.AkbarTests;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.baseURI;

public class Serialization_DeSerialization {

    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("spartans_uri");
    }

    @AfterAll
    public static void teardown() {
        RestAssured.reset();
    }

    @Test
    public void GetSingleSpartanJson() {

        given()
                .accept(ContentType.JSON).
                pathParam("id", "102").
                log().all().
        when()
                .get(baseURI + "spartans/{id}").

        then()
                .log().ifValidationFails()
                .statusCode(200);
    }

    @Test
    //sending a post request
    public void Add_New_Spartan_With_String_As_Body_Test(){

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(" {\n" +
//                        "        \"id\": 114,\n" +
                        "        \"name\": \"Danrys Targrian\",\n" +
                        "        \"gender\": \"Female\",\n" +
                        "        \"phone\": 5715511365\n" +
                        "    }").
        when()
                .post(baseURI + "spartans/").

        then()
                .log().all()
                .statusCode(201)
                .body("success", equalTo("A Spartan is Born!"))
                .body("data.name", is("Danrys Targrian")).
                body("data.phone", hasToString("5715511365"));
    }


    @Test
    //sending a post request
    public void Add_New_Spartan_With_Map_As_Body_Test(){

        //this is where we store a java object to be later transformed into single json
        Map<String, Object>bodyMap= new HashMap<>();
            bodyMap.put("name", "Tyrion Lnnister");
            bodyMap.put("gender", "Male");
            bodyMap.put("phone", 6178495632L);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap).
        when()
                .post(baseURI + "spartans/").

        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", equalTo("A Spartan is Born!"))
                .body("data.name", is("Tyrion Lnnister")).
                body("data.phone", hasToString("6178495632"));
    }


    @Test
    //sending a post request
    public void Add_New_Spartan_With_Map_As_Pojo_Test(){

        //this is where we store a java object to be later transformed into single json
        Map<String, Object>bodyMap= new HashMap<>();
        bodyMap.put("name", "Tyrion Lnnister");
        bodyMap.put("gender", "Male");
        bodyMap.put("phone", 6178495632L);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap).
                when()
                .post(baseURI + "spartans/").

                then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", equalTo("A Spartan is Born!"))
                .body("data.name", is("Tyrion Lnnister")).
                body("data.phone", hasToString("6178495632"));
    }





}
