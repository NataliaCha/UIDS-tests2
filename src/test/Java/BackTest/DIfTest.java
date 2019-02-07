package BackTest;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DIfTest {
    static String token;


    @BeforeClass
    public static void testGetToken() {

        RestAssured.baseURI = "http://prototype.datasynthes.com";
        //     RestAssured.basePath= "api/internal/meta";
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


   // @Test
   // @DisplayName("/internal/user/cleanse-functions/ - GET")
   // @Description("get all cleanse-functions")

    /*public void testGetFunction() {

      String func=
                given()
                        .header("authorization", token)
                        .when()
                        .get("/api/internal/authentication/currentUser/")
                        //   .get(baseURI+basePath+"/dqPolicies")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .jsonPath().getString("theme");


}*/


      /*http://prototype.datasynthes.com/api/internal/meta/cleanse-functions/CompareValues


        /*http://prototype.datasynthes.com/api/internal/meta/cleanse-functions*/

    @Test
    @DisplayName("/internal/meta/cleanse-functions/${id}-GET")
    @Description ("get function by ID -cleanse-functions")

    public void testGetFuncID () {


        given()
                .header("authorization", token)
                .pathParam("id", "CompareValues")
                .when()
                .get("/api/internal/meta/cleanse-functions/{id}")
                //   .get(baseURI+basePath+"/dqPolicies")

                .then()
                .assertThat()
                .statusCode(200)
                //  .body(containsString("\"totalElements\":7"))
                //  .body(containsString("\"totalElements\":21"));
                .body(containsString("\"name\":\"CompareValues\""));
        //*249a5d3f-2307-11e9-92ff-87ba01ce01e0

    }




}