/**
 * Created by natalia.chaplygina on 14.01.2019.
 */


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasValue;


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
    public void getTaxonomyTest() {
        given()
                .header("authorization", token)
                .when()
                .get("http://prototype.datasynthes.com/api/v1/taxonomies")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"group\":\"Risk\",\"name\":\"Low\"")).body(containsString("\"group\":\"Risk\",\"name\":\"Medium\"")).body(containsString("\"group\":\"Risk\",\"name\":\"High\""))
                .body(containsString("\"group\":\"Trading\",\"name\":\"Not Specified\""))
                .body(containsString("\"group\":\"Accounting\",\"name\":\"Liability\"")).body(containsString("\"group\":\"Accounting\",\"name\":\"Asset\""))
                .body(containsString("\"group\":\"Accounting\",\"name\":\"Equity\"")).body(containsString("\"group\":\"Accounting\",\"name\":\"Revenue\"")).body(containsString("\"group\":\"Accounting\",\"name\":\"Expense\""));




    }



    @Test
    public void getTaxonomybyidTest() {
        given()
                .header("authorization", token)
                .pathParam("id", "5bfd0bddfeb5bb2a7cb7e975")
                .when()
                .get("http://prototype.datasynthes.com/api/v1/taxonomies/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"group\":\"Accounting\",\"name\":\"Expense\""));
        //  .getBody().print();

    }














}