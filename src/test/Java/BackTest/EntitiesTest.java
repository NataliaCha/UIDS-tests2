package BackTest;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class EntitiesTest {



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
    @DisplayName(" /internal/meta/entities/?draft=true -GET")

    public void entitiesTestAll() {
        //    String con=
        given()
                .header("authorization", token)
                .param("size", "100000")
                .param("draft", "true")
                .when()
                .get("/api/internal/meta/entities")
                //   .get(baseURI+basePath+"/dqPolicies")

                .then()
                .assertThat()
                .statusCode(200)
              //  .body(containsString("\"totalElements\":7"))
                .body(containsString("\"totalElements\":7"));  //проверим что, UBS policy на месте

    }



    @Test
    @DisplayName("/internal/meta/ dqPolicies/${name}?draft=true -POST,GET,DUT,DELETE")
    //fullcycle to create, get and delete dc
    public void entityCreate() {


        JSONObject entity = new JSONObject();
        JSONArray cat = new JSONArray();


        entity.put("aliases", cat);
        entity.put("categories", cat);
        entity.put("complexAttributes",cat);
        entity.put("dataQualityRules",cat);
        entity.put("displayName", "test");
        entity.put("description", "");
        entity.put("glossaries",cat);
        entity.put("groupName", "ROOT.mdm");
        entity.put("name","test");
        entity.put("relatedTerms", "");
        entity.put("replacementTerms",cat);
        entity.put("rights",cat);
        entity.put("simpleAttributes",cat);
        entity.put("synonyms",cat);
        entity.put("customAttributeMetas",cat);
        entity.put("relations",cat);



     System.out.println(entity);


        //create new entity


      given()
                .header("authorization", token)
                .contentType("application/json")
                .body(entity.toString())
                .when().post("/api/internal/meta/entities?draft=true").then()
              //"http://prototype.datasynthes.com/api/internal/meta/entities?draft=true"
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true"));




        //check new entity
        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "test")
                .param("draft", "true")
                .when()
                .get("/api/internal/meta/entities/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true")).body(containsString("\"errors\":null"))
                .body(containsString("\"displayName\":\"test\""));


        //update new dq policy




        JSONObject entity1 = new JSONObject();
        JSONArray cat1 = new JSONArray();


        entity1.put("aliases", cat);
        entity1.put("categories", cat);
        entity1.put("complexAttributes",cat);
        entity1.put("dataQualityRules",cat);
        entity1.put("displayName", "testtest");
        entity1.put("description", "");
        entity1.put("glossaries",cat);
        entity1.put("groupName", "ROOT.mdm");
        entity1.put("name","test");
        entity1.put("relatedTerms", "");
        entity1.put("replacementTerms",cat);
        entity1.put("rights",cat);
        entity1.put("simpleAttributes",cat);
        entity1.put("synonyms",cat);
        entity1.put("customAttributeMetas",cat);
        entity1.put("relations",cat);

// update
        given()
                .header("authorization", token)
                .pathParam("id", "test")
                .param("draft", "true")
                .contentType("application/json")
                .body(entity1.toString())
                .when().put("/api/internal/meta/entities/{id}").then()
                //http://prototype.datasynthes.com/api/internal/meta/dqPolicies/test?draft=true"
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true")).body(containsString("\"displayName\":\"testtest\""));



        //delete new dq policy


        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "test")
                .param("draft", "true")
                .when()
                .delete("/api/internal/meta/entities/{id}")
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
                .get("/api/internal/meta/entities/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":false")).body(containsString("\"userMessage\":\"Entity is not found.\""));


    }






}
