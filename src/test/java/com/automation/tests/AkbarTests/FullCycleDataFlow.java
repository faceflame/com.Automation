package com.automation.tests.AkbarTests;

import com.automation.utilities.ConfigurationReader;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FullCycleDataFlow extends TestBase {

    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("spartans_uri");
    }

    @AfterAll
    public static void teardown() {
        RestAssured.reset();
    }

    @Test
    public void getSpartanAndDeleteSpartanTest() {
        int spartanID = createRandomSpartan();
        System.out.println(spartanID);


       given()
                .log().all().
               pathParam("id", spartanID).
        when()
                .get("spartans/{id}" ).
        then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", is(spartanID))
       ;

       given()
               .log().all().
               pathParam("id", spartanID).


       when()
                .delete("spartans/{id}" ).
       then()
               .statusCode(204)
               .body(blankOrNullString())
;

        given()
                .log().all().
                pathParam("id", spartanID).
        when()
                .get("spartans/{id}" ).
        then()
                .statusCode(404)
                .contentType(ContentType.JSON)
                .body("error", is("Not Found"))
                .body("message", is("Spartan Not Found"))
        ;


    }


    public int createRandomSpartan() {

        Faker faker = new Faker();
        int randomIndex = faker.number().numberBetween(0, 2);
        String[] genders = {"Male", "Female"};
        String randomGender = genders[randomIndex];


        Spartan spartan = new Spartan(faker.name().firstName(),
                randomGender,
                Long.parseLong(faker.number().digits(10)));

        return
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(spartan).
                        when()
                        .post(baseURI + "spartans/")
                        .prettyPeek()
                        .jsonPath()
                        .getInt("data.id");

    }

}
