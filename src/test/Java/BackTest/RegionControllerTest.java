package BackTest; /**
 * Created by natalia.chaplygina on 27.12.2018.
 */


import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;


//http://prototype.datasynthes.com/api/swagger-ui.html?urls.primaryName=internal#/authentication-controller/loginUsingPOST

public class RegionControllerTest {

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
    @DisplayName("/api/v1/regions")
    public void getregionsTest() {
        given().
                header("authorization", token).
                when().
                get("/api/v1/regions").
                then().
                assertThat().
                statusCode(200).
                body(containsString("EMEA")).body(containsString("AMER")).body(containsString("APAC"));
        // body(hasValue("AMER","APAC"));


        // hasItems(23, 54));
        //  .getBody().print();
    }


    @Test
    @DisplayName("/api/v1/regions/{id}")
    public void getregionsbyidTest() {
        given().
                header("authorization", token).
                pathParam("id","5bf3d04da6f7870001aee9b3").
                when().
                get("/api/v1/regions/{id}").
                then().
                assertThat().
                statusCode(200).
                body(containsString("APAC"));
        //  .getBody().print();
    }

/*
    public void v1() {
        given().
                header("authorization", token).
                when().
                get("http://prototype.datasynthes.com/api/v2/api-docs?group=v1").
                then().



*/


}

