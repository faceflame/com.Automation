package com.automation.tests.day2;


import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class EurpeanCentralBankTests {
    private String baseURI = "https://api.exchangeratesapi.io/";

    @Test
    public void test1() {

        Response response = given().get(baseURI + "latest");
        assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());
    }


    @Test
    public void test2() {

        Response response = given().get(baseURI + "latest");
        assertEquals(200, response.getStatusCode());
        //verify that data is coming from Json
        assertEquals("application/json", response.getHeader("Content-Type"));
        //or like this
        assertEquals("application/json", response.getContentType());


    }

    @Test
    public void test3() {
        //get currency exchange rate for Dollar. By default it is Euro
        Response response = given().
                baseUri(baseURI + "latest").
                queryParam("base", "USD").get();

        assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    //Task: Verify that response body for the latest currency rates, contains today's date (2020-01-03 | yyyy-MM-dd)
    @Test
    public void test4() {
        Response response = given().
                baseUri(baseURI + "latest").
                queryParam("base", "TRY").
                get();

        String todaysDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("Today's date : " + todaysDate);
        assertEquals(200, response.getStatusCode());

        assertTrue(response.getBody().asString().contains(todaysDate));
    }

    //lets get currency excange rate for year 2000
    //GET https://api.exchangeratesapi.io/history?start_at=2018-01-01&end_at=2018-09-01 HTTP/1.1
    @Test
    public void test5(){
        Response response = given().
                baseUri(baseURI +"history").
                queryParam("start_at", "2000-01-01").
                queryParam("end_at", "2000-01-31").
                queryParam("base", "USD").
                queryParam("symbols", "EUR", "GBP", "JPY").
                get();

        assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    //Given request parameter "base" is "USD"
    //When user sends request to "api.exchangeratesapi.io"
    //Then response code should be 200
    //And response body must contain "base': "USD"

    @Test
    public void test6(){

        Response response = given().
                baseUri(baseURI +"latest").
                queryParam("base", "USD").
                get();

        String body = response.getBody().asString();
        assertEquals(200, response.statusCode());
        assertTrue(response.getBody().asString().contains("\"base\":\"USD\""));

    }
}
