package com.automation.tests.day2;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSTests {
    //adress of ards web service,that is running no AWS ec2
    //dara is coming from SQL Oracle data base to this web service
    //during back end testing with SQl developer JDBC API
    // we were accessing data base directly
    //now ewe gonna access web servide
    //according to OPP conventions,all instance variable should be private
    //but,if we will make it public,it will not make ant diffrence for us
    //now we gonna access web service

    private String baseURI = "http://ec2-52-91-239-159.compute-1.amazonaws.com:1000/ords/hr";
    //we start from given()
    //then we can specify type of request like: get(),put(),delete(),post()
    //and as parameter,we enter resource location (URI)
    //then we are getting response back,that response we can put into Response object
    //from response object,we can retrive:body,header,status code
    //it works without RestAssured.given() because of static import
    //veify that status code is 200

    @Test
    public void test1() {
        Response response = given().//we dont need to write RestAssureGiven only given is enough we alredy import assuretions
                get(baseURI + "/employees");
        System.out.print(response.getBody().asString());
        assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());
    }
    //get employee with id 100 verify that  response returns status code is 200
    //also,print body

    @Test
    public void test2() {
        //header stands for metadata
        //usually it is used to include cookies
        //in this example,we are specify what kind of response type we need
        //because web service can return lets say json or xml
        //when we put header info "Accept","application/json",we are saying that we need only json as response
        Response response = given().
                header("Accept", "application/json").
                get(baseURI + "/employees/100");
        int actualStatusCode = response.getStatusCode();
        System.out.println(response.prettyPrint());
        assertEquals(200, actualStatusCode);
        //get information about response content type,you can retrieve from response object
        System.out.println("what kind of content server sends to you ,in this response:" + response.getHeader("Content-Type"));
    }


    @Test
    public void test3() {
        Response response = given().get(baseURI + "/regions");
        assertEquals(200, response.getStatusCode());
        Header header = response.getHeaders().get("Content-Type");

        for (Header h : response.getHeaders()) {
            System.out.println(h);

        }
        System.out.println(response.prettyPrint());

    }

}