package com.gorest.testbase;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBase {

    @BeforeClass
    public void inIt() {
        //RestAssured is a class ,baseURI is a method
        RestAssured.baseURI = "https://gorest.co.in/public/v2/";
        RestAssured.basePath = "/users";
    }
}
