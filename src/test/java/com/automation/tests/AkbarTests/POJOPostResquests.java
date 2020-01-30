package com.automation.tests.AkbarTests;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.baseURI;

public class POJOPostResquests {
    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("spartans_uri");
    }

    @AfterAll
    public static void teardown() {
        RestAssured.reset();
    }


    @Test
    //sending a positive post request w pojo
    public void Add_New_Spartan_With_Pojo_Positive_Test(){

        Spartan spartan = new Spartan ("Cercei Lanister", "Female", 1236549875);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan).
        when()
                .post(baseURI + "spartans/").

        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", equalTo("A Spartan is Born!"))
                .body("data.name", is("Cercei Lanister")).
                body("data.phone", hasToString("1236549875"));
    }

    @Test
    //sending a positive post request w pojo
    public void Add_New_Spartan_With_Pojo_Negative_Test(){

        Spartan spartan = new Spartan ("C", "Female", 1236549875);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan).
        when()
                .post(baseURI + "spartans/").

        then()
                .log().all()
                .statusCode(400)
                .body("error", is("Bad Request"))
                .body("errors[0].defaultMessage", containsString("name should be at least 2 character and max 15 character"))
;
    }

    @Test
    //sending a negative post request w pojo
    public void Add_New_Spartan_With_Pojo_NegativeName_NegativeGender_NegativePhone_Test(){

        Spartan spartan = new Spartan ("C", "F", 123);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan).
         when()
                .post(baseURI + "spartans/").

         then()
                .log().all()
                .statusCode(400)
                .body("error", is("Bad Request"))
                .body("errors.defaultMessage", hasSize(3))
                .body("errors.defaultMessage", hasItem("Gender should be either Male or Female"))
                .body("errors.defaultMessage", hasItems("Gender should be either Male or Female"
                                                        ,"Phone number should be at least 10 digit and UNIQUE!!",
                                                         "name should be at least 2 character and max 15 character"))
                .body("message", containsString("Error count: 3"))
        ;
    }

}
