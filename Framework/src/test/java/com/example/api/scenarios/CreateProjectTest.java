package com.example.api.scenarios;

import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;;

public class CreateProjectTest {

    @Test
    public void shouldCreateProject() {
        RestAssured.baseURI = "http://127.0.0.1/mantis/api/rest/";

        JSONObject body = new JSONObject();
        body.put("name", "Test Project " + System.currentTimeMillis());
        body.put("status", new JSONObject().put("id", 10));  // status: development
        body.put("view_state", new JSONObject().put("id", 10));  // public

        int code = given()
                .header("Authorization", "xdg4y5um72ulcNzXU7-l8Xf7pZNPYpve")
                .header("Content-Type", "application/json")
                .body(body.toString())
                .when()
                .post("/projects/")
                .then()
                .extract().statusCode();

        assertEquals(201, code);
    }
}
