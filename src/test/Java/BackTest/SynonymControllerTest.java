package BackTest; /**
 * Created by natalia.chaplygina on 14.01.2019.
 */


import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class SynonymControllerTest {


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
        public void getSynonimTest() {
            given()
                    .header("authorization", token)
                    .when()
                    .get("http://prototype.datasynthes.com/api/v1/synonyms")
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .body(containsString("ADA")).body(containsString("SSS")).body(containsString("BDD"));

        }



    @Test
    public void getSynonimbyidTest() {
        given()
                .header("authorization", token)
                .pathParam("id", "5bfbfcc8feb5bb6b20a12bde")
                .when()
                .get("http://prototype.datasynthes.com/api/v1/synonyms/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("SSS"));
        //  .getBody().print();

    }



    }






