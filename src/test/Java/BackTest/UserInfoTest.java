package BackTest;



import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class UserInfoTest {


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


    @Test
    @DisplayName("api/internal/authentication/get-current-user/ _OLD_ENDPOINT")
    @Description("Get-current-user_ old endpoint")

    public void UserInfoGet() {
        //    String con=
        given()
                .header("authorization", token)
                .when()
                .get("/api/internal/authentication/get-current-user/")
                //   .get(baseURI+basePath+"/dqPolicies")

                .then()
                .assertThat()
                .statusCode(200);

    }


    @Test

    @DisplayName("/api/internal/authentication/currentUser _NEW_ENDPOINT")
    @Description("Get-current-user_new endpoint")

    public void UserInfoGetNew() {
        //    String con=
        given()
                .header("authorization", token)
                .when()
                .get("/api/internal/authentication/currentUser/")
                //   .get(baseURI+basePath+"/dqPolicies")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("themePP"))
                .body(containsString("firstName"));

    }


    @Test

    @DisplayName("/internal/authentication/currentUserInfo - PUT")
    @Description("Update user info: theme")
    //fullcycle to create, get and delete dc
    public void userPut() {


         String theme=
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

        System.out.println("Old value " + theme);
        String newtheme;

        if  ( theme.equals("dark") ) {
            newtheme = "light";
        }
        else {
            newtheme  = "dark";
        }

        System.out.println("New value " + newtheme);


        /*         * {"dept":"IT","firstName":"John","lastName":"Doerr","login":"admin","theme":"dark"*/
        JSONObject userdata = new JSONObject();
        userdata.put("dept", "IT");
       userdata.put("firstName", "John");
       // userdata.put("firstName", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        userdata.put("lastName", "Doer");
        userdata.put("login", "admin");
        userdata.put("theme", newtheme);
       // userdata.put("theme", "blue");

        System.out.println(userdata);


        given()
                .header("authorization", token)
                .contentType("application/json")
                .body(userdata.toString())
                .when().put("/api/internal/authentication/currentUserInfo").then()
                //"http://prototype.datasynthes.com/api/internal/meta/lookup-entities?draft=true"
                .assertThat()
                .statusCode(200);


        String newsettheme=
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

        Assert.assertEquals(newsettheme,newtheme);




             //   .extract()
       //         .jsonPath().getString("content");

        //newsettheme.equals(newtheme);
     //   System.out.println(newsettheme);


        System.out.println("New theme was set");


//http://prototype.datasynthes.com/api/internal/authentication/currentUserInfo




    }
}

