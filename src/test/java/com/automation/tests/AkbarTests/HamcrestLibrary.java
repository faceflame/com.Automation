package com.automation.tests.AkbarTests;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class HamcrestLibrary {


    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("spartans_uri");
    }


    @AfterAll
    public static void teardown() {
        RestAssured.reset();
    }

    @Test
    public void CalculationTest(){
        int a = 10, b=20;
        assertEquals(30, a+b);

        assertThat(30, equalTo(a+b));
        assertThat(40, greaterThan(a+b));
 }

    @Test
    public void VerifySingleSpartanSelectedHamcrest() {

                given()
                    .pathParam("id", 2).
                when()
                    .get(baseURI + "spartans/{id}").
                then()
                     .assertThat()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .body("id", equalTo(2))
                        .body("gender", equalToIgnoringCase("male"))
                        .body("phone", hasToString("4124579201"));

    }








}
