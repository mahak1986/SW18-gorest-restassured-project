package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
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

    //1. Verify if the total record is 25
    @Test
    public void test001() {
        response.body("total.size()", equalTo(25));
    }

    //2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti
    //cohaero libero.”
    @Test
    public void test002() {
        response.body("find{it.id == 93997}.title", equalTo("Demitto conqueror atavus argumentum corrupti cohaero libero."));
    }

    //3. Check the single user_id in the Array list (5914249)
    @Test
    public void test003() {
        response.body("user_id", hasItem(5914249));
    }
    //4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914251)
    @Test
    public void test004() {
        response.body("user_id", hasItems(5914243,5914202,5914251));
    }
    //5. Verify the body of userid = 5914197 is equal “Desidero vorax adsum. Non confero clarus.
    //Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus
    //vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio
    //tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus
    //acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”
    @Test
    public void test005() {
        response.body("find{it.user_id== 5914155}.body", equalTo("Compono tot super. Velum consequuntur conculco. Circumvenio umbra usus. Curis cohaero consectetur. Venia copia delectatio. Cunae tamdiu antepono. Tempus cui attonbitus. Contego derelinquo aut. Asperiores aqua id. Qui vehemens virgo. Perspiciatis sperno explicabo. Tollo contigo advoco. Desparatus delicate tondeo. Cerno abstergo culpo. Verbera occaecati strues. Volo sequi eveniet. Carus tamisium admoneo. Autus addo patruus. Spiritus paens stabilis."));
    }
}


