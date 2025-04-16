package com.example.api.scenarios;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetProjectsTest {

    @Test
    public void shouldReturnProjects() {
        RestAssured.baseURI = "http://localhost/mantis/api/rest";

        Response response = given()
                .header("Authorization", "API_KEY")
                .when()
                .get("/projects/")
                .then()
                .statusCode(200)
                .extract().response();

        String body = response.getBody().asString();
        assertTrue(body.contains("name"));
    }
}
