package com.example.api.scenarios;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class MantisApiTest {

    @Test
    public void testGetProjects() {
        Response response = RestAssured.given()
            .baseUri("http://127.0.0.1/mantis/api/rest/")
            .header("Authorization", "i93i-jXElXNTHH_3U0aXEKz3LhSZQMaW")
            .when()
            .get("projects/")
            .then()
            .extract().response();

        assertThat(response.asString(), containsString("TestProject"));
    }
}
