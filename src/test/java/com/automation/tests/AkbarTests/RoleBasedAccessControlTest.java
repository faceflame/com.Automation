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
import org.junit.jupiter.api.Test;

public class RoleBasedAccessControlTest extends TestBase{
   /* @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("spartans_uri");
    }

    @AfterAll
    public static void teardown() {
        RestAssured.reset();
    }
*/
    @Test
    public void PublicUserRoleCanNotViewData(){
        String username = ConfigurationReader.getProperty("spartan.admin_role.name");
        String password= ConfigurationReader.getProperty("spartan.admin_role.password");

        given().
                log().all().
                auth().basic(username, password)
                .accept(ContentType.JSON).
         when()
                .get(baseURI + "spartans/30").

        then()
                .log().all()
                .statusCode(200);

    }


}
