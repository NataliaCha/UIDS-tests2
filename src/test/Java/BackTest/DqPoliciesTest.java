package BackTest;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DqPoliciesTest
{


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
    @DisplayName(" /internal/meta/dqPolicies/?draft=true -GET")

    public void CatalogTestAll() {
        //    String con=
        given()
                .header("authorization", token)
                .param("size", "100000")
                .param("draft", "true")
                .when()
                .get("/api/internal/meta/dqPolicies")
             //   .get(baseURI+basePath+"/dqPolicies")

                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"name\":\"UBS Equity policy\""));  //проверим что, UBS policy на месте

    }



    @Test
    @DisplayName("/internal/meta/ dqPolicies/${name}?draft=true -POST,GET,DUT,DELETE")
    //fullcycle to create, get and delete dc
    public void dqPolicyCreate() {


        JSONObject dq = new JSONObject();
        JSONArray instr = new JSONArray();
        JSONArray mapdef = new JSONArray();


        dq.put("description", "");
        dq.put("displayName", "test");
        dq.put("groupName", "ROOT.dqPolicies");
        dq.put("name", "test");
        dq.put("expression", "");
        dq.put("instruments", instr);
        dq.put("dqpolicyMappingDefinitions", mapdef);

        //        System.out.println(dq);

        //create new DQ
        given()
                .header("authorization", token)
                .contentType("application/json")
                .body(dq.toString())
                .when().post("/api/internal/meta/dqPolicies?draft=true").then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true"));



  // get new DQ Policy

        //check new DQ
        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "test")
                .param("draft", "true")
                .when()
                .get("/api/internal/meta/dqPolicies/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true")).body(containsString("\"errors\":null"))
                .body(containsString("\"displayName\":\"test\""));


   //update new dq policy



            JSONObject dq1 = new JSONObject();
            JSONArray instr1 = new JSONArray();
            JSONArray mapdef1 = new JSONArray();


            dq1.put("description", "");
            dq1.put("displayName", "testtest");
            dq1.put("groupName", "ROOT.dqPolicies");
            dq1.put("name", "test");
            dq1.put("expression", "");
            dq1.put("instrumentType", "Auxiliary Data");
            dq1.put("instruments", instr1);
            dq1.put("dqpolicyMappingDefinitions", mapdef1);

// update
            given()
                    .header("authorization", token)
                    .pathParam("id", "test")
                    .param("draft", "true")
                    .contentType("application/json")
                    .body(dq1.toString())
                    .when().put("/api/internal/meta/dqPolicies/{id}").then()
                    //http://prototype.datasynthes.com/api/internal/meta/dqPolicies/test?draft=true"
                    .assertThat()
                    .statusCode(200)
                    .body(containsString("\"success\":true")).body(containsString("\"displayName\":\"testtest\""))
                    .body(containsString("\"instrumentType\":\"Auxiliary Data\""));


           //delete new dq policy


            given()
                    .header("authorization", token)
                    //.pathParam("id", "Bloomberg")
                    .pathParam("id", "test")
                    .param("draft", "true")
                    .when()
                    .delete("/api/internal/meta/dqPolicies/{id}")
                    .then()
                    .assertThat()
                    .statusCode(200);
            //  .body(containsString("\"success\":true")).body(containsString("\"errors\":null"));


        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "test")
                .param("draft", "true")
                .when()
                .get("/api/internal/meta/dqPolicies/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":false")).body(containsString("\"userMessage\":\"DQ Policy is not found.\""));


        }








@Test
@Ignore
@Step ("Get policy by name")
    public void dqPolicybyid1 () {

given()
                .header("authorization", token)
    //.pathParam("id", "Bloomberg")
                .pathParam("id", "test")
                .param("draft", "true")
                .when()
                .get("/api/internal/meta/dqPolicies/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true")).body(containsString("\"errors\":null"))
            .body(containsString("\"displayName\":\"test\""));
        }

 //   "http://prototype.datasynthes.com/api/internal/meta/dqPolicies/test?draft=true"


    @Test
    @Ignore
   // @DisplayName("/internal/meta/ dqPolicies/${name}?draft=true -POST,GET,DUT,DELETE")
    //fullcycle to create, get and delete dc
    public void dqPolicyUpdate1() {

        JSONObject dq1 = new JSONObject();
        JSONArray instr1 = new JSONArray();
        JSONArray mapdef1 = new JSONArray();


        dq1.put("description", "");
        dq1.put("displayName", "testtest");
        dq1.put("groupName", "ROOT.dqPolicies");
        dq1.put("name", "test");
        dq1.put("expression", "");
        dq1.put("instrumentType","Auxiliary Data");
        dq1.put("instruments", instr1);
        dq1.put("dqpolicyMappingDefinitions", mapdef1);


        given()
                .header("authorization", token)
                .pathParam("id", "test")
                .param("draft", "true")
                .contentType("application/json")
                .body(dq1.toString())
                .when().put("/api/internal/meta/dqPolicies/{id}").then()
                //http://prototype.datasynthes.com/api/internal/meta/dqPolicies/test?draft=true"
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true")).body(containsString("\"displayName\":\"testtest\""))
                .body(containsString("\"instrumentType\":\"Auxiliary Data\""));


    }

    @Test
    @Ignore
    public void dqPolicyDelbyid1 () {

        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "test")
                .param("draft", "true")
                .when()
                .delete("/api/internal/meta/dqPolicies/{id}")
                .then()
                .assertThat()
                .statusCode(200);
              //  .body(containsString("\"success\":true")).body(containsString("\"errors\":null"));
    }


    //http://prototype.datasynthes.com/api/internal/meta/dqPolicies/test?draft=true"




 //   {"description":"","displayName":"test123","dqpolicyMappingDefinitions":[],"groupName":"ROOT.dqPolicies","name":"test123","instruments":["g"],"expression":null,"instrumentType":"Auxiliary Data"}
   // {"description":"","displayName":"test","dqpolicyMappingDefinitions":[],"groupName":"ROOT.dqPolicies","name":"test","instruments":[],"expression":null}
//


}
