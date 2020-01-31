package com.automation.tests.AkbarTests;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class DeserializingJsonToJavaObject {
    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("spartans_uri");
    }

    @AfterAll
    public static void teardown() {
        RestAssured.reset();
    }

    @Test
    public void DeserializeJsonToObject_Test() {
    //FIRST WAY
        Spartan sp1 =
                get(baseURI + "spartans/10").prettyPeek()
                        .jsonPath()
                        .getObject("", Spartan.class);

        System.out.println("sp1 = " + sp1);

        //SECOND WAY
        Spartan sp2=get(baseURI + "spartans/12").prettyPeek()
        .as(Spartan.class);

        System.out.println("sp2 = " + sp2);
    }

    @Test
    //sending a post request
    public void Add_New_Spartan_With_Map_As_Pojo_Test(){

        //this time, we send all relevant info from a POJO to create Json

        Spartan spartanAydan= new Spartan("Fidel Castro", "Male", 1236549875L);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartanAydan).

        when()
                .post(baseURI + "spartans/").

        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", equalTo("A Spartan is Born!"))
                .body("data.name", is("Fidel Castro"))
                .body("data.phone", hasToString("1236549875"));
    }


}
