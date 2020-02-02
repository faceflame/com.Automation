package com.automation.tests.StepDefs;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

public class GetSingleSpartanStepDefs {

    RequestSpecification requestSpec;
    Response response;

    @Given("credentilas with USER_role is provided")
    public void credentilas_with_USER_role_is_provided() {
        requestSpec = given().
                log().all().
                accept(ContentType.JSON).
                auth().basic("user", "user");
    }

    @When("user trys to send a get request on {string}")
    public void user_trys_to_send_a_get_request_on(String spartanID) {
        response = requestSpec.when()
                .get(spartanID);
    }

    @Then("user should get status code {int}")
    public void user_should_get_status_code(Integer statusCode) {
        response.then().statusCode(statusCode);
    }
}
