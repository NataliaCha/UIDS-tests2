package BackTest;



import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


public class CatalogTest {

    static String token;

    @BeforeClass
    public  static   void  testGetToken() {

        RestAssured.baseURI = "http://prototype.datasynthes.com";

        String token1 =
                given()
                        .header("authorization", "Basic V2g2SEJ3VWtZcVBWMzVYYjpNJHdYOVB7bT5hLi92XX5OJS1fXGZcN2NfOylFRm1+OSZ2cEYya0pOYnF2d2d1Mis=")
                        .accept("application/json")
                        .contentType("application/x-www-form-urlencoded")
                        .body("grant_type=password&username=admin&password=UsaeZ9ph")
                        .post("/api/oauth/token")
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath().getString("access_token");

        System.out.println(token1);
        String token2 = "Bearer " + token1;
        System.out.println(token2);
        token = token2;

    }
@Test
    public void CatalogTestAll() {
        // String con=
        given()
                .header("authorization", token)
                .param("size", "100000")
                .param("draft", "true")
                .when()
                .get("http://prototype.datasynthes.com/api/internal/meta/dataCatalogs")
                //.get("http://prototype.datasynthes.com/api/v1/lookups/Currency/?size=100000")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"name\":\"Bloomberg\""));
    }


    @Test
    public void CatalogTestAll_false() {
        // String con=
        given()
                .header("authorization", token)
                .param("size", "100000")
                .param("draft", "false")
                .when()
                .get("http://prototype.datasynthes.com/api/internal/meta/dataCatalogs")
                //.get("http://prototype.datasynthes.com/api/v1/lookups/Currency/?size=100000")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"name\":\"Bloomberg\""));
    }

    @Test
    public void CatalogTestByID() {
        // String con=
        given()
                .header("authorization", token)
                .pathParam("id", "Bloomberg")
                .param("draft", "true")
                .when()
                .get("http://prototype.datasynthes.com/api/internal/meta/dataCatalogs/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                 .body(containsString("Primary Exchange Code"));
            }

    @Test
    public void CatalogTestByIDSugg() {
        // String con=
        given()
                .header("authorization", token)
                .pathParam("id", "Bloomberg")
                .param("draft", "true")
                .when()
                .get("http://prototype.datasynthes.com/api/internal/meta/dataCatalogs/{id}/suggestedList")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"errors\":null"));
    }





//{"dataSource":[],"groupName":"ROOT.dc","displayName":"test","name":"test"}





















}
