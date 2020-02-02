package com.automation.tests.StepDefs;

import com.automation.utilities.ConfigurationReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class hooks {


    @Before
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("spartans_uri");
        String githubURI= ConfigurationReader.getProperty("github_URL");
    }

    @After
    public static void teardown() {
        RestAssured.reset();
    }

    public static String GenerateTokenUtility(){
        String username = ConfigurationReader.getProperty("spartan.admin_role.name");
        String password= ConfigurationReader.getProperty("spartan.admin_role.password");

        String token=

                given().
                        log().all().
                        auth().basic(username, password)
                        .accept(ContentType.JSON).
                        when()
                        .get(baseURI + "spartans/30").
                        jsonPath().getString("accessToken");


        return token;
    }


}
