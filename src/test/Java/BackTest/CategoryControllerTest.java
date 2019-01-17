package BackTest; /**
 * Created by natalia.chaplygina on 11.01.2019.
 */


import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
//import io.qameta.allure.junit4.DisplayName;
//import io.qameta.allure.Severity;
//import io.qameta.allure.SeverityLevel;

public class CategoryControllerTest {

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
//    @DisplayName("Test to get category")
//    @Severity(SeverityLevel.CRITICAL)
    public void getCategoryTest() {
        given()
                .header("authorization", token)
                .when()
                .get("http://prototype.datasynthes.com/api/v1/categories")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("Issue")).body(containsString("Issue Classification")).body(containsString("Other"));
        // body(hasValue("AMER","APAC"));


        // hasItems(23, 54));
        //  .getBody().print();
    }



    @Test
  //  @DisplayName("Test to get category by id")
    public void getCategorybyidTest() {
        given()
                .header("authorization", token)
                .pathParam("id","5bfbfcc8feb5bb6b20a12be1")
                .when()
                .get("http://prototype.datasynthes.com/api/v1/categories/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("Issue Classification"));
        //  .getBody().print();
    }
}
