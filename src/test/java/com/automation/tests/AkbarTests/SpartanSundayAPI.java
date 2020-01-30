package com.automation.tests.AkbarTests;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class SpartanSundayAPI {

    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("spartans_uri");
    }

    @AfterAll
    public static void teardown() {
        RestAssured.reset();
    }

    @Test
    public void All_Spartans_With_Size_And_Items_Test() {

        given()
                .accept(ContentType.JSON).
                queryParam("gender", "male").
                when().
                get(baseURI + "spartans").prettyPeek().
                then().
                statusCode(200).
                contentType(ContentType.JSON)
                .body("[0].name", equalTo("Salam"))
                //.body("name", hasSize(144))
                .body("[1].gender", is("Male"))
                .header("Transfer-Encoding", "chunked")
                .header("Date", notNullValue());
    }

    @Test
    public void GetSingleSpartanJson_Logging_All_Details() {

        given()
                .accept(ContentType.JSON).
                pathParam("id", "102").
                log().all().
        when()
                .get(baseURI + "spartans/{id}").

        then()
                .statusCode(200)
                .log().ifValidationFails();

    }

}