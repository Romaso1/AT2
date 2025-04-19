package com.example.api.scenarios;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;;

public class GetProjectsTest {

    @Test
    public void shouldReturnProjects() {
        RestAssured.baseURI = "http://127.0.0.1/mantis/api/rest/";

        Response response = given()
                .header("Authorization", "i93i-jXElXNTHH_3U0aXEKz3LhSZQMaW")
                .when()
                .get("/projects/")
                .then()
                .statusCode(200)
                .extract().response();

        String body = response.getBody().asString();
        assertTrue(body.contains("name"));
    }
}
