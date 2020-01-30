package com.automation.tests.AkbarTests;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class DeserializaingJsonToJavaObject {
    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("spartans_uri");
    }

    @AfterAll
    public static void teardown() {
        RestAssured.reset();
    }

    @Test
    public void DeserializeJsonToObject_Test(){

        Spartan sp1=
                get(baseURI + "spartans/10")
                .jsonPath()
                .getObject("", Spartan.class);


        System.out.println("sp1 = " + sp1);

        ;






    }

}
