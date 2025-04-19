package com.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProjectCrudTest {
    private String token;
    private int projectId;
    private String projectName;

    @BeforeClass
    public void setup() throws Exception {
        Properties props = new Properties();
        props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        RestAssured.baseURI = props.getProperty("api.baseUrl");
        token = props.getProperty("api.token");
    }

    @Test(priority = 1, groups = "api")
    public void createProject() {
        projectName = "API Test Project " + System.currentTimeMillis();

        Map<String, Object> status = new HashMap<>();
        status.put("name", "development");

        Map<String, Object> viewState = new HashMap<>();
        viewState.put("name", "private");

        Map<String, Object> body = new HashMap<>();
        body.put("name", projectName);
        body.put("status", status);
        body.put("view_state", viewState);
        body.put("enabled", true);

        Response resp = RestAssured.given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(body)
                .post("projects/")
                .then()
                .statusCode(201)
                .extract().response();

        System.out.println("CREATE RESPONSE:\n" + resp.asPrettyString());

        projectId = resp.jsonPath().getInt("project.id");
        assertThat(resp.jsonPath().getString("project.name"), equalTo(projectName));
    }

    @Test(priority = 2, dependsOnMethods = "createProject", groups = "api")
    public void readProject() {
        Response resp = RestAssured.given()
                .header("Authorization", token)
                .get("projects/" + projectId)
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("READ RESPONSE:\n" + resp.asPrettyString());

        // Беремо перший елемент з масиву "projects"
        String actualName = resp.jsonPath().getString("projects[0].name");
        assertThat(actualName, equalTo(projectName));
    }

    @Test(priority = 3, dependsOnMethods = "createProject", groups = "api")
    public void updateProject() {
        String updatedName = projectName + " Updated";

        Map<String, Object> body = new HashMap<>();
        body.put("name", updatedName);

        Response resp = RestAssured.given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(body)
                .patch("projects/" + projectId)
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("UPDATE RESPONSE:\n" + resp.asPrettyString());

        assertThat(resp.jsonPath().getString("project.name"), equalTo(updatedName));
    }

    @Test(priority = 4, dependsOnMethods = "createProject", groups = "api")
    public void deleteProject() {
        Response resp = RestAssured.given()
                .header("Authorization", token)
                .delete("projects/" + projectId)
                .then()
                .statusCode(204)
                .extract().response();

        System.out.println("DELETE RESPONSE CODE: " + resp.getStatusCode());
    }
}
