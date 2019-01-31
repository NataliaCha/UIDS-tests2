package BackTest;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;


public class NotificationsTest {

//not commpleted tests


    static String token;




    @BeforeClass
    public  static   void  testGetToken() {

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



    @Test
    @DisplayName("/internal/user/notifications/count - GET")
    @Description ("get all notifications")

    public void testGetUsersCount () {

        given()
                .header("authorization", token)
                .when()
                .get("/api/internal/user/notifications/count")
                //   .get(baseURI+basePath+"/dqPolicies")
                .then()
                .assertThat()
                .statusCode(200)
                //  .body(containsString("\"totalElements\":7"))
                .body(containsString("\"success\":true"));

    }


    @Test
    @DisplayName("/internal/user/notifications/count-DELETE")
    @Description ("delete all notifications")
    @Ignore
    public void testUsersCountDel () {
        given()
                .header("authorization", token)
                .when()
                .delete("/api/internal/user/notifications")
                .then()
                .assertThat()
                .statusCode(200);



    }

    @Test
    @DisplayName("/internal/user/notifications/count-DELETE")
    @Description ("delete  notification by ID")
    @Ignore
    public void testGetUsersCountID () {


    given()
                .header("authorization", token)
                .pathParam("id", "249a5d3f-2307-11e9-92ff-87ba01ce01e0")
                .when()
                .get("/api/internal/user/notifications/{id}")
    //   .get(baseURI+basePath+"/dqPolicies")

                .then()
                .assertThat()
                .statusCode(200)
    //  .body(containsString("\"totalElements\":7"))
              //  .body(containsString("\"totalElements\":21"));
            .body(containsString("\"success\":true"));
    //*249a5d3f-2307-11e9-92ff-87ba01ce01e0

}




    @Test
    @DisplayName("/internal/user/notifications/count-DELETE")
    @Description ("delete  notification by ID")
    @Ignore
    public void testGetUsersCountDelID () {


        given()
                .header("authorization", token)
                .pathParam("id", "249a5d3f-2307-11e9-92ff-87ba01ce01e0")
                .when()
                .delete("/api/internal/user/notifications/{id}")
                //   .get(baseURI+basePath+"/dqPolicies")

                .then()
                .assertThat()
                .statusCode(200)
                //  .body(containsString("\"totalElements\":7"))
                .body(containsString("\"totalElements\":21"));

        //*249a5d3f-2307-11e9-92ff-87ba01ce01e0

    }



}
