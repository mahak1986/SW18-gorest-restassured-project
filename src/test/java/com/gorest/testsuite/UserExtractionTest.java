package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().statusCode(200);
    }

    //1. Extract the All Ids
    @Test
    public void test001() {
        List<?> ids = response.extract().path("id");
        System.out.println("All Ids are : " + ids);
    }

    //2. Extract the all Names
    @Test
    public void test002() {
        List<?> names = response.extract().path("name");
        System.out.println("All Ids are : " + names);
    }

    //3. Extract the name of 5th object
    @Test
    public void test003() {
        String name = response.extract().path("name[4]");
        System.out.println("the name of 5th object : " + name);
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<HashMap<String, ?>> names = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("names of all object whose status = inactive: " + names);
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<HashMap<String, ?>> gender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("the gender of all the object whose status = active: " + gender);
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<HashMap<String, ?>> female = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("the names of the object whose gender = female " + female);
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<HashMap<String, ?>> email = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("the emails of the object where status = inactive: " + email);
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<HashMap<String, ?>> id = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("the ids of the object where gender = male: " + id);
    }

    //9. Get all the status
    @Test
    public void test009() {
        List<?> status = response.extract().path("status");
        System.out.println("all the status : " + status);
    }

    //10. Get email of the object where name = Lal Dwivedi
    @Test
    public void test010() {
        List<HashMap<String, ?>> email1 = response.extract().path("findAll{it.name == 'Abani Butt'}.email");
        System.out.println("email of the object where name = Abani Butt " + email1);
    }

    //11. Get gender of id = 5914136
    @Test
    public void test011() {
        String gender = response.extract().path("find{it.id == 5914136}.gender");
        System.out.println("gender of id = 5914136 = " + gender);
    }
}


