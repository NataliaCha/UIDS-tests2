/**
 * Created by natalia.chaplygina on 27.12.2018.
 */


import static io.restassured.RestAssured.get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


//http://prototype.datasynthes.com/api/swagger-ui.html?urls.primaryName=internal#/authentication-controller/loginUsingPOST

public class Second_Api {

    @BeforeClass
public static void setup ()  {
        RestAssured.baseURI ="http://prototype.datasynthes.com/api/internal/authentication/login";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("password", "UsaeZ9ph");
        requestParams.put("redirectTo", "http://prototype.datasynthes.com");
        requestParams.put("userName", "admin");
        Response response = request.post("");




    }

       @Test
    //(description = "GET")


    public void getRequestExampleTest()  {
        given().
                when().
                get("http://prototype.datasynthes.com/api/v1/regions").
                then().
                assertThat().
                statusCode(200).
                 body(containsString("AMER"));

    }



/*
       // JSONArray jsonResponse = new JSONArray(response.asString());
        JSONArray jsonResponse = response.                JSONArray ja = new JSONArray(jsonResponse);

        // String capital = jsonResponse.getJSONObject(0).getString("capital");
            // Assert.assertEquals(capital, "Moscow");


        JsonArray array = obj.getJsonArray("members");
        System.out.println("Members:");
        for (JsonValue value : array) {
            System.out.println(value.toString());
        }

    } catch (Exception e) {
        System.out.println("Failed: " + e.getMessage());
    }
}
}
    }
*/
}
