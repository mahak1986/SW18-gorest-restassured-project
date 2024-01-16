package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .get("/posts")
                .then().statusCode(200);
    }
    //1. Extract the title
    @Test
    public void test001(){
        List<?> title = response.extract().path("title");
        System.out.println("the titles are : " + title);
    }
    //2. Extract the total number of record
    @Test
    public void test002(){
       int total = response.extract().path("total.size()");
        System.out.println("the total number of record : " + total);
    }
    //3. Extract the body of 15th record
    @Test
    public void test003() {
        String body = response.extract().path("body[14]");
        System.out.println("the body of 15th record : " + body);
    }
    //4. Extract the user_id of all the records
    @Test
    public void test004(){
        List<?> user_id = response.extract().path("user_id");
        System.out.println("the user_id of all the records : " + user_id);
    }
    //5. Extract the title of all the records
    @Test
    public void test005(){
        List<?> title = response.extract().path("title");
        System.out.println("the title of all the records : " + title);
    }
    //6. Extract the title of all records whose user_id = 5914150
    @Test
    public void test006(){
        List<HashMap<String, ?>> title1 = response.extract().path("findAll{it.user_id == 5914150}.title");
        System.out.println("the title of all records whose user_id = 5914200 : " + title1);
    }

    //7. Extract the body of all records whose id = 93999
    @Test
    public void test007(){
        List<HashMap<String, ?>> body = response.extract().path("findAll{it.id == 93999}.body");
        System.out.println("the body of all records whose id = 93999 : " + body);
    }
    }

