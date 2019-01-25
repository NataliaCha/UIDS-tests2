package BackTest;



import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


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
@DisplayName("/internal/meta/dataCatalogs?draft=true -GET")
    public void CatalogTestAll() {
     //    String con=
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
                .body(containsString("\"name\":\"Bloomberg\""));/// проверим что, Bloomberg на месте
               // .extract() .jsonPath().getString("content") ;


    //можно выевсти весь content
    //  System.out.println('\n'+ con);
    }


    @Test
    @DisplayName("/internal/meta/dataCatalogs?draft=false -GET")
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
    @DisplayName("/internal/meta/dataCatalogs/${catalogName}?draft=true -POST,GET,DUT,DELETE")
    //fullcycle to create, get and delete dc
    public void CatalogCreate() {

        JSONObject dc = new JSONObject();
        JSONArray DS = new JSONArray();

        dc.put("dataSource",DS);
        dc.put("groupName", "ROOT.dc");
        dc.put("displayName", "test");
        dc.put("name", "test");
       // System.out.println(dc);

              given()
                .header("authorization", token)
                .contentType("application/json")
                .body(dc.toString())
                .when().post("http://prototype.datasynthes.com/api/internal/meta/dataCatalogs?draft=true").then()
                .assertThat()
                .statusCode(200)
               .body(containsString("\"success\":true"));


        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "test")
                .param("draft", "true")
                .when()
                .get("http://prototype.datasynthes.com/api/internal/meta/dataCatalogs/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true")).body(containsString("\"errors\":null"))
                .body(containsString("\"displayName\":\"test\""));


        JSONObject dc1 = new JSONObject();
        JSONArray DS1 = new JSONArray();

        dc1.put("dataSource",DS);
        dc1.put("groupName", "ROOT.dc");
        dc1.put("displayName", "testtest");
        dc1.put("name", "test");
        // System.out.println(dc);

        given()
                .header("authorization", token)
                .contentType("application/json")
                .body(dc1.toString())
                .when().put("http://prototype.datasynthes.com/api/internal/meta/dataCatalogs/test?draft=true").then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true"));


        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "test")
                .param("draft", "true")
                .when()
                .get("http://prototype.datasynthes.com/api/internal/meta/dataCatalogs/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true")).body(containsString("\"errors\":null"))
                .body(containsString("\"displayName\":\"testtest\""));




        given()
                .header("authorization", token)
                .pathParam("id", "test")
                .param("draft", "true")
                .when()
                .delete("http://prototype.datasynthes.com/api/internal/meta/dataCatalogs/{id}")
                .then()
                .assertThat()
                .statusCode(200);


        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "test")
                .param("draft", "true")
                .when()
                .get("http://prototype.datasynthes.com/api/internal/meta/dataCatalogs/{id}")
                .then()
               .assertThat()
                .statusCode(200)
               .body(containsString("\"success\":false"));


    }








    @Test
    @DisplayName("/internal/meta/dataCatalogs/${catalogName}?draft=true -DELETE")
    @Ignore
    public void CatalogDelete() {

        given()
                .header("authorization", token)
                .pathParam("id", "test")
                .param("draft", "true")
                .when()
                .delete("http://prototype.datasynthes.com/api/internal/meta/dataCatalogs/{id}")
                .then()
                .assertThat()
                .statusCode(200);

    }




    @Test
    @DisplayName("/internal/meta/dataCatalogs/${catalogName}?draft=true -GET")
    @Ignore
    public void CatalogTestByID() {
        // String con=
        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "test")
                .param("draft", "true")
                .when()
                .get("http://prototype.datasynthes.com/api/internal/meta/dataCatalogs/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true")).body(containsString("\"errors\":null"));


    }

    @Test
    @DisplayName("/internal/meta/dataCatalogs/${catalogName}/suggestedList?draft=true -GET")
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







}
