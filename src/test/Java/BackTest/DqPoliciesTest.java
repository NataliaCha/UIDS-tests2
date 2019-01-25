package BackTest;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DqPoliciesTest {


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
    @DisplayName("/internal/meta/dqPolicies/${name}?draft=true -GET")
    public void CatalogTestAll() {
        //    String con=
        given()
                .header("authorization", token)
                .param("size", "100000")
                .param("draft", "true")
                .when()
                .get("http://prototype.datasynthes.com/api/internal/meta/dqPolicies")
                //.get("http://prototype.datasynthes.com/api/v1/lookups/Currency/?size=100000")
                .then()
                .assertThat()
                .statusCode(200);
               // .body(containsString("\"name\":\"Bloomberg\""));/// проверим что, Bloomberg на месте

    }




}
