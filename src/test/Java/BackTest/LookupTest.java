package BackTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class LookupTest {




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
    @DisplayName(" /internal/meta/lookup-entities/?draft=true -GET")
    @Description("Get all lookups")

    public void entitiesTestAll() {
        //    String con=
        given()
                .header("authorization", token)
                .param("size", "100000")
                .param("draft", "true")
                .when()
                .get("/api/internal/meta/lookup-entities")
                //   .get(baseURI+basePath+"/dqPolicies")

                .then()
                .assertThat()
                .statusCode(200)
                //  .body(containsString("\"totalElements\":7"))
                .body(containsString("\"totalElements\":32"));  //проверим что, UBS policy на месте

    }

///!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!%!!!!!!!!!!!!!!!!!!!!!!!!

    @Test
    @DisplayName("/internal/meta/ lookup-entities/${name}?draft=true -POST,GET,DUT,DELETE")
    @Description("Create new lookup, get it,update and delete")
    //fullcycle to create, get and delete dc
    public void lookupCreate() {


        JSONObject lookup = new JSONObject();
        JSONArray ar = new JSONArray();


        lookup.put("aliasCodeAttributes", ar);
        lookup.put("aliases", ar);
        lookup.put("categories", ar);
        lookup.put("codeAttribute", JSONObject.NULL);
        lookup.put("dataQualityRules", ar);
        lookup.put("displayName", "test");
        lookup.put("description", "");
        lookup.put("glossaries", ar);
        lookup.put("name", "test");
        lookup.put("groupName", "ROOT.referenceTables");
        lookup.put("relatedTerms", ar);
        lookup.put("replacementTerms", ar);
        lookup.put("rights", ar);
        lookup.put("simpleAttributes", ar);
        lookup.put("synonyms", ar);
        lookup.put("customAttributeMetas", ar);


        System.out.println(lookup);


        given()
                .header("authorization", token)
                .contentType("application/json")
                .body(lookup.toString())
                .when().post("/api/internal/meta/lookup-entities?draft=true").then()
                //"http://prototype.datasynthes.com/api/internal/meta/lookup-entities?draft=true"
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true"));

        System.out.println("New lookuo was created");

        //check new entity
        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "test")
                .param("draft", "true")
                .when()
                .get("/api/internal/meta/lookup-entities/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true")).body(containsString("\"errors\":null"))
                .body(containsString("\"displayName\":\"test\""));


        //update new lookup

        JSONObject lookup1 = new JSONObject();
        JSONArray ar1 = new JSONArray();


        JSONArray codedattr = new JSONArray();
        JSONObject codedattr1 = new JSONObject();

        JSONArray rightsarr = new JSONArray();
        JSONObject rights = new JSONObject();


        lookup1.put("createdBy", "");
        lookup1.put("updatedBy", "");
        lookup1.put("createdAt", "");
        lookup1.put("updatedAt", "");
        lookup1.put("version", "");

        lookup1.put("customAttributeMetas", ar1);
        lookup1.put("simpleAttributes", ar1);
        lookup1.put("arrayAttributes", ar1);
        lookup1.put("entityDependency", ar1);
        lookup1.put("customProperties", ar1);
        //lookup1.put("version", ar1);
        lookup1.put("name", "test");
        lookup1.put("displayName", "testtest");
        lookup1.put("description", "");
        lookup1.put("order", 0);
        lookup1.put("classifiers", ar1);
        lookup1.put("categories", ar1);
        lookup1.put("hasData", false);
        lookup1.put("modelName", JSONObject.NULL);


        /*
        {"createdBy":null,"updatedBy":null,"createdAt":null,"updatedAt":null,"version":null,"customAttributeMetas":[],
        "simpleAttributes":[],"arrayAttributes":[],"entityDependency":[],"customProperties":[],"name":"test","displayName":"test5",
        "description":"","order":0,"classifiers":[],"categories":[],"hasData":false,"modelName":null,

         */
        lookup1.put("codeAttribute", codedattr1);
        //************************************

        codedattr1.put("name", "Code");
        codedattr1.put("displayName", "Code");
        codedattr1.put("description", JSONObject.NULL);
        codedattr1.put("readOnly", false);
        codedattr1.put("hidden", false);
        //codedattr1.put("rights", &&&);//?????????????????????

        rights.put("createdAt", "2019-01-29T08:26:56.437+0000");
        rights.put("updatedAt", "2019-01-29T08:26:56.437+0000");
        rights.put("createdBy", "SYSTEM");
        rights.put("updatedBy", "SYSTEM");
        rights.put("securedResource", JSONObject.NULL);
        rights.put("create", true);
        rights.put("update", true);
        rights.put("delete", true);
        rights.put("read", true);

        rightsarr.put(rights);
        codedattr1.put("rights", rights);

        /*
         * "rights":{"createdAt":"2019-01-29T08:26:56.437+0000","updatedAt":"2019-01-29T08:26:56.437+0000","createdBy":"SYSTEM","updatedBy":"SYSTEM",
         * "securedResource":null,"create":true,"update":true,"delete":true,"read":true},
         *
         * */


        codedattr1.put("definition", JSONObject.NULL);
        codedattr1.put("acronym", JSONObject.NULL);
        codedattr1.put("category", JSONObject.NULL);
        codedattr1.put("status", JSONObject.NULL);
        codedattr1.put("statusUpdateDate", JSONObject.NULL);
        codedattr1.put("example", JSONObject.NULL);
        codedattr1.put("dataSteward", JSONObject.NULL);
        codedattr1.put("phone", JSONObject.NULL);
        codedattr1.put("email", JSONObject.NULL);
        codedattr1.put("location", JSONObject.NULL);
        codedattr1.put("businessUnit", JSONObject.NULL);
        codedattr1.put("applicator", JSONObject.NULL);
        codedattr1.put("businessFunc", JSONObject.NULL);
        codedattr1.put("useCases", JSONObject.NULL);
        codedattr1.put("format", JSONObject.NULL);
        codedattr1.put("required", JSONObject.NULL);
        codedattr1.put("computeFunction", JSONObject.NULL);
        codedattr1.put("numericPrecision", JSONObject.NULL);
        codedattr1.put("numericScale", JSONObject.NULL);
        codedattr1.put("relatedTerms", ar1);
        codedattr1.put("replacementTerms", ar1);
        codedattr1.put("synonyms", ar1);
        codedattr1.put("aliases", ar1);
        codedattr1.put("assets", ar1);
        codedattr1.put("glossaries", ar1);
        codedattr1.put("customProperties", ar1);
        codedattr1.put("nullable", false);
        codedattr1.put("unique", false);
        codedattr1.put("searchable", false);
        codedattr1.put("simpleDataType", "String");
        codedattr1.put("length", JSONObject.NULL);
        codedattr1.put("assetsClasses", ar1);
        codedattr1.put("taxonomyGroup", JSONObject.NULL);
        codedattr1.put("taxonomyName", JSONObject.NULL);
        codedattr1.put("displayable", false);
        codedattr1.put("mainDisplayable", false);
        codedattr1.put("mask", "");
        codedattr1.put("externalIdGenerationStrategy", JSONObject.NULL);


        codedattr.put(codedattr1);

        /*
"codeAttribute":{"name":"Code","displayName":"Code","description":null,"readOnly":false,"hidden":false,
"rights":{"createdAt":"2019-01-29T08:26:56.437+0000","updatedAt":"2019-01-29T08:26:56.437+0000","createdBy":"SYSTEM","updatedBy":"SYSTEM","securedResource":null,"create":true,"update":true,"delete":true,"read":true},
"definition":null,"acronym":null,"category":null,"status":null,"statusUpdateDate":null,"example":null,"dataSteward":null,"phone":null,
"email":null,"location":null,"businessUnit":null,"applicator":null,"businessFunc":null,"useCases":null,
"format":null,"required":null,"computeFunction":null,
"numericPrecision":null,"numericScale":null,"relatedTerms":[],
"replacementTerms":[],"synonyms":[],"aliases":[],"assets":[],"glossaries":[],"customProperties":[],"nullable":false,"unique":true,"searchable":false,
"simpleDataType":"String","length":null,"assetsClasses":[],"taxonomyGroup":null,"taxonomyName":null,"displayable":false,"mainDisplayable":false,"mask":"","externalIdGenerationStrategy":null},


 */

        lookup1.put("aliasCodeAttributes", ar1);
        lookup1.put("dataQualityRules", ar1);
        lookup1.put("attributeGroups", ar1);
        lookup1.put("mergeSettings", JSONObject.NULL);
        lookup1.put("dashboardVisible", false);
        lookup1.put("groupName", "ROOT.referenceTables");


        /*
         * "aliasCodeAttributes":[],"dataQualityRules":[],"attributeGroups":[],"mergeSettings":null,"validityPeriod":null,
         * "externalIdGenerationStrategy":null,"dashboardVisible":false,"groupName":"ROOT.referenceTables"}
         * */
        System.out.println(lookup1);


// update

        given()
                .header("authorization", token)
                .pathParam("id", "test")
                .param("draft", "true")
                .contentType("application/json")
                .body(lookup1.toString())
                .when().put("/api/internal/meta/lookup-entities/{id}").then()
                //http://prototype.datasynthes.com/api/internal/meta/dqPolicies/test?draft=true"
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true")).body(containsString("\"displayName\":\"testtest\""));

        System.out.println("New lookup was updated");

        //check new entity
        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "test")
                .param("draft", "true")
                .when()
                .get("/api/internal/meta/lookup-entities/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true")).body(containsString("\"errors\":null"))
                .body(containsString("\"displayName\":\"testtest\""));


        //delete new dq policy


        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "test")
                .param("draft", "true")
                .when()
                .delete("/api/internal/meta/lookup-entities/{id}")
                .then()
                .assertThat()
                .statusCode(200);
        //  .body(containsString("\"success\":true")).body(containsString("\"errors\":null"));
        System.out.println("New lookup was deleted");

        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "test")
                .param("draft", "true")
                .when()
                .get("/api/internal/meta/lookup-entities/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":false")).body(containsString("\"userMessage\":\"Lookup entity is not found.\""));


    }


}





   /*     JSONObject requestparams = new JSONObject();
JSONArray authArray = new JSONArray();
JSONObject authparam = new JSONObject();

        requestparams.put("FirstName", "Virender");
        requestparams.put("LastName", "Singh");

        authparam.put("Line1", "Flat no 101");
        authparam.put("Area", "Andheri");
        authparam.put("City", "Mumbai");
        authArray.put(authparam);

        requestparams.put("Address", authparam);*/