package com.automation.tests.day03;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

class ORDSTestsDay3 {

    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("ords.uri");
    }

    //accept("application/json") shortcut for header("Accept", "application/json")
    //we are asking for json as a response
    @Test
    public void test1() {
        given().
                accept("application/json").
                get("/employees").
                then().
                assertThat().statusCode(200).
                and().assertThat().contentType("application/json").
                log().all(true);
    }

    //path parameter - to point on specific resource /employee/:id/ - id it's a path parameter
    //query parameter - to filter results, or describe new resource :
    // POST /users?name=James&age=60&job-title=SDET
    //or to filter GET /employee?name=Jamal get all employees with name Jamal
    @Test
    public void test2() {
        given().
                accept("application/json").
                pathParam("id", 100).
                when().get("/employees/{id}").
                then().assertThat().statusCode(200).
                and().assertThat().body("employee_id", is(100),
                "department_id", is(90),
                "last_name", is("King")).
                log().all(true);
        //body ("phone_number") --> 515.123.4567
        //is is coming from ---> import static org.hamcrest.Matchers.*;
    }

    /**
     * given path parameter is "/regions/{id}"
     * when user makes get request
     * and region id is equals to 1
     * then assert that status code is 200
     * and assert that region name is Europe
     */

    @Test
    public void test3() {
                given().
                        accept("application/json").
                        pathParam("id", 1).
                when().
                        get(baseURI + "/regions/{id}").
                then().
                       assertThat().statusCode(200).
                        assertThat().body("region_name", is("Europe")).
                        time(lessThan(10L), TimeUnit.SECONDS).
                        log().body(true);
    }

    @Test
    public void test4(){
        JsonPath json=
                given().
                    accept("application/json").
                when().
                        get("/employees").
                thenReturn().jsonPath();

                String nameOfFirstEmployee=json.getString("items[0].first_name");
                String nameOfLastEmployee= json.getString("items[-1].first_name");

                System.out.println("first name of the First Employee: " + nameOfFirstEmployee);
                System.out.println("first name of theLast= Employee: " + nameOfLastEmployee);

        Map<String, ?>firstEmployee = json.get("items[0]");
        System.out.println(firstEmployee);

        for (Map.Entry<String, ?> entry : firstEmployee.entrySet())              {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }
    }

    //get info from countries as List<Map<String , ?>>

    @Test
    public void test5(){
        JsonPath json=
                given().
                        accept("application/json").
                        when().
                        get("/countries").prettyPeek()
                        .jsonPath();
        List<HashMap<String, ?>>countryNames= json.get("items");

        for (HashMap<String, ?>map: countryNames){
            System.out.println(map);
        }

        System.out.println(countryNames);

    }
//get collection of employee's salaries
// and the sort it
//and print

@Test
    public void test6(){
    List<Integer>salaries=
            given().
                    accept("application/json").
                    when().
                    get("/employees").
    thenReturn().jsonPath().getList("items.salary");

    Collections.sort(salaries);

    System.out.println(salaries);

    Collections.reverse(salaries);
    System.out.println(salaries);
}

//get collection of phone numbers from employees
//replace all does "." in every phone number w dash "-"

@Test
public void test7(){
        List<String>phoneNumbers =
                given().
                        accept("application/json").
                        when().
                        get("/employees").
                        thenReturn().jsonPath().getList("items.phone_number");

        List<String>phoneNums=new ArrayList<>();

    for (int i = 0; i <phoneNumbers.size() ; i++) {
        phoneNumbers.add(phoneNumbers.get(i).replace(".", "-"));

    }

    System.out.println(phoneNums);
}

/*
Given accept type as JSON
And path parameter is id
When user sends get request to /locations
Then user verifies that status is 200
And user verifies that location id is 1700
And user verifies that postal code is 98199
And user verifies that city is Seattle
And user verifies that state_province is Washington
*/

@Test
    public void test8(){

    given().
            accept("application/json").
            pathParam("id", 1700).
            when().
            get(baseURI + "/locations/{id}").
            then().
            assertThat().statusCode(200).
            assertThat().body("location_id", is(1700)).
            assertThat().body("city", is("Seattle")).
            assertThat().body("state_province", is("Washington")).
            assertThat().body("postal_code", is("98199"))
            ;




}

}
