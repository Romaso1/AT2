package trello;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import trello.comparator.OrganizationResponseComparator;
import trello.dto.BoardResponse;
import trello.dto.OrganizationResponse;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrelloTest {

    String key = "";
    String token = "";
    String orgName = "My API Org";
    String boardName = "My API Board";
    String memberId = "romadav";
    String memberType = "admin";

    @Test
    public void fullTrelloScenario() {
        RestAssured.baseURI = "https://api.trello.com/1";

        // === 1. Create Organization ===
        Response orgResp = given()
                .log().all()
                .header("Content-Type", "")
                .queryParam("displayName", orgName)
                .queryParam("key", key)
                .queryParam("token", token)
                .post("/organizations")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        OrganizationResponse actualOrg = orgResp.as(OrganizationResponse.class);
        OrganizationResponse expectedOrg = new OrganizationResponse();
        expectedOrg.setDisplayName(orgName);
        assertTrue(OrganizationResponseComparator.compare(expectedOrg, actualOrg));
        String orgId = actualOrg.getId();

        // === 2. Create Board ===
        Response boardResp = given()
                .log().all()
                .header("Content-Type", "")
                .queryParam("name", boardName)
                .queryParam("idOrganization", orgId)
                .queryParam("key", key)
                .queryParam("token", token)
                .post("/boards")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        BoardResponse board = boardResp.as(BoardResponse.class);
        String boardId = board.getId();

        // === 3. Add Member ===
        given()
                .log().all()
                .header("Content-Type", "")
                .queryParam("type", memberType)
                .queryParam("key", key)
                .queryParam("token", token)
                .put("/boards/" + boardId + "/members/" + memberId)
                .then()
                .log().all()
                .statusCode(200);

        // === 4. Archive Board ===
        Response archiveResp = given()
                .log().all()
                .header("Content-Type", "")
                .queryParam("closed", true)
                .queryParam("key", key)
                .queryParam("token", token)
                .put("/boards/" + boardId)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        BoardResponse archivedBoard = archiveResp.as(BoardResponse.class);
        assertTrue(archivedBoard.isClosed(), "Дошка не була архівована");
    }
}