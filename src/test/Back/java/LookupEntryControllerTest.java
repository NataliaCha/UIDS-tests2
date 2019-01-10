

/**
 * Created by natalia.chaplygina on 09.01.2019.
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
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasValue;



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
*/
        System.out.println(token1);
        String token2 = "Bearer " + token1;
        System.out.println(token2);
        token = token2;

    }



@Test

    public void lookupsTest()
{
    given().
            //header("authorization", "Bearer 31109fdd-2121-416f-bd1e-e43bad9d6708").
            header("authorization", token).
            when().
            get("http://prototype.datasynthes.com/api/v1/lookups/").
            then().
            assertThat().
            statusCode(200);
          //  body(containsString("\"type\": \"Exchange\"")).body(containsString("\"type\": \"Currency\"")).body(containsString("\"type\": \"Country\","));


    // body(hasValue("AMER","APAC"));


}


    @Test

    public void lookupsbytypeTest()
    {
        given().
                header("authorization", token).
                param("type","currency").
                when().
                get("http://prototype.datasynthes.com/api/v1/lookups/").
                then().
                assertThat().
                statusCode(200).
                body(containsString("\"numberOfElements\":20"));
        // body(hasValue("AMER","APAC"));


    }




}
