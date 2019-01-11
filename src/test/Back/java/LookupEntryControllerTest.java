

/**
 * Created by natalia.chaplygina on 09.01.2019.
 */



import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.json.Json;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.*;
import static com.jayway.jsonpath.JsonPath.*;

public class LookupEntryControllerTest {


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
                        //    .path("access_token");
                        .jsonPath().getString("access_token");
       /* extract().
                jsonPath().getLong("user_id");

                extract().
            jsonPath().getLong("user_id"), equalTo(USER_ID)
*/
        System.out.println(token1);
        String token2 = "Bearer " + token1;
        System.out.println(token2);
        token = token2;

    }



    @Test

    public void lookupsTest() {
        given()
        .header("authorization", token)
         .param("size","100000")
        .when()
       // .get("http://prototype.datasynthes.com/api/v1/lookups/?size=100000")
        .get("http://prototype.datasynthes.com/api/v1/lookups/")
        .then()
        .statusCode(200)
        .body(containsString("\"type\":\"Currency\""))
        .body(containsString("\"type\":\"Exchange\""))
        .body(containsString("\"type\":\"Country\""));


    }


    @Test
@Ignore
    public void lookupsTest1()

    {
        RestAssured.baseURI = "http://prototype.datasynthes.com";
        RequestSpecification httpRequest = RestAssured.given().header("authorization", token);
        Response response = httpRequest.get("/api/v1/lookups/");


        // First get the JsonPath object instance from the Response interface
        JsonPath response2 = response.jsonPath();
        System.out.println (response2.get("content"));
        System.out.println (response2.get("type"));
        System.out.println (response2.get( "$..type"));



      //  String pageName = JsonPath.read(json, "$.pageInfo.pageName");

      //  JsonPath.read(json, "$.status");

        // Let us print the city variable to see what we got
      //  System.out.println("City received from Response " + jsonPathEvaluator.get("type"));

        // Print the temperature node
        //   System.out.println("Temperature received from Response " + jsonPathEvaluator.get("Temperature"));



/*
                        given().
                       config(RestAssured.config().xmlConfig(xmlConfig().with().namespaceAware(true))).
                       when().
                       get("/package-db-xml").
                       then().
                       body(hasXPath("/db:package-database", namespaceContext));

        */


        //  assertThat(, hasJsonPath("$.not_there"));

        //  body(containsString("\"type\": \"Exchange\"")).body(containsString("\"type\": \"Currency\"")).body(containsString("\"type\": \"Country\","));

        // .body(containsString("\"type\":\"Currency\"")); /*работает*/
        //  .body(containsString("\"type\":\"Exchange\"")); /*работает*/

        // .body(hasItemInArray("Currency")).body(hasItemInArray("Exchange")).body(hasItemInArray("Country"));


       /* .jsonPath().getString("access_token");
       /* extract().
                jsonPath().getLong("user_id");

                extract().
            jsonPath().getLong("user_id"), equalTo(USER_ID)
*/

    }



    @Test

    public void lookupsbytypeTest_Contry() {
        // String con=
        given()
                .header("authorization", token)
                .pathParam("type", "Country")
                .param("size", "100000")
                .when()
                .get("http://prototype.datasynthes.com/api/v1/lookups/{type}/")
                //.get("http://prototype.datasynthes.com/api/v1/lookups/Currency/?size=100000")
                .then()
                .assertThat()
                .statusCode(200)
                   .body(containsString("\"numberOfElements\":354"));
             //   .extract()
                //    .path("access_token");
            //    .jsonPath().getString("numberOfElements").equals("354");
        //можно выевсти весь content
        //  System.out.println('\n'+ con);

    }
    @Test

    public void lookupsbytypeTest_Currency() {
      //  String con=
        given()
                .header("authorization", token)
                .pathParam("type", "Currency")
                .param("size", "100000")
                .when()
                .get("http://prototype.datasynthes.com/api/v1/lookups/{type}/")
                //.get("http://prototype.datasynthes.com/api/v1/lookups/Currency/?size=100000")
                .then()
                .assertThat()
                .statusCode(200)
                  .body(containsString("\"numberOfElements\":187"));

    }


    @Test

    public void lookupsbytypeTest_Exchage() {
     //   String con=
        given()
                .header("authorization", token)
                .pathParam("type", "Exchange")
                .param("size", "100000")
                .when()
                .get("http://prototype.datasynthes.com/api/v1/lookups/{type}/")
                //.get("http://prototype.datasynthes.com/api/v1/lookups/Currency/?size=100000")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"numberOfElements\":1613"));


    }




}