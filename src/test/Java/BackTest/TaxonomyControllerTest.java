package BackTest; /**
 * Created by natalia.chaplygina on 14.01.2019.
 */


import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;


public class TaxonomyControllerTest {


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
    @DisplayName("/api/v1/taxonomies/")
    public void getTaxonomyTest() {
        given()
                .header("authorization", token)
                .when()
                .get("/api/v1/taxonomies")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"group\":\"Risk\",\"name\":\"Low\"")).body(containsString("\"group\":\"Risk\",\"name\":\"Medium\"")).body(containsString("\"group\":\"Risk\",\"name\":\"High\""))
                .body(containsString("\"group\":\"Trading\",\"name\":\"Not Specified\""))
                .body(containsString("\"group\":\"Accounting\",\"name\":\"Liability\"")).body(containsString("\"group\":\"Accounting\",\"name\":\"Asset\""))
                .body(containsString("\"group\":\"Accounting\",\"name\":\"Equity\"")).body(containsString("\"group\":\"Accounting\",\"name\":\"Revenue\"")).body(containsString("\"group\":\"Accounting\",\"name\":\"Expense\""));




    }



    @Test
    @DisplayName("/api/v1/taxonomies/{id}")
    public void getTaxonomybyidTest() {
        given()
                .header("authorization", token)
                .pathParam("id", "5bfd0bddfeb5bb2a7cb7e975")
                .when()
                .get("/api/v1/taxonomies/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"group\":\"Accounting\",\"name\":\"Expense\""));
        //  .getBody().print();

    }














}
