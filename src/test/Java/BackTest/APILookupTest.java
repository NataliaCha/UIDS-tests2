package BackTest;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
//import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class APILookupTest {




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
    @Story("GET")
    @DisplayName("/internal/meta/lookup-entities/?draft=true -GET")
    @Description("Get all lookups with draft- true +check lookup Country")

    public void entitiesTestAllTrue() {
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
                .body(containsString("\"totalElements\":21"))
                .body(containsString("\"name\":\"Country\""));

        //проверим что, UBS policy на месте

    }
    @Test
    @DisplayName("/internal/meta/lookup-entities/?draft=false -GET")
    @Description("Get all lookups with draft- false +check lookup Country")

    public void entitiesTestAllFalse() {
        //    String con=
        given()
                .header("authorization", token)
                .param("size", "100000")
                .param("draft", "false")
                .when()
                .get("/api/internal/meta/lookup-entities")
                //   .get(baseURI+basePath+"/dqPolicies")

                .then()
                .assertThat()
                .statusCode(200)
                //  .body(containsString("\"totalElements\":7"))
              //  .body(containsString("\"totalElements\":21"))
              .body(containsString("\"name\":\"Country\""));

        //проверим что, UBS policy на месте

    }

    @Test
    @DisplayName("/internal/meta/lookup-entities/{id} -GET")
    @Description("Get real lookup Country")

    public void entitiesTestCountry() {
        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "Country")
                .param("draft", "true")
                .when()
                .get("/api/internal/meta/lookup-entities/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true")).body(containsString("\"errors\":null"))
                .body(containsString("\"displayName\":\"Country\""));

        System.out.println("Find Country");
    }

        @Test
        @DisplayName("/internal/meta/lookup-entities/{id} -GET UPPER letters")
        @Description("Get real lookup COUNTRY -Upper Case ")
        public void entitiesTestCountryUp() {
        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "COUNTRY")
                .param("draft", "true")
                .when()
                .get("/api/internal/meta/lookup-entities/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"Lookup entity is not found."));

        System.out.println("not Find COUNTRY");



    }

    @Test
    @DisplayName("/internal/meta/lookup-entities/{id} -GET_negative")
    @Description("Get not real lookup absence")

    public void entitiesTest() {
        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "Absence")
                .param("draft", "true")
                .when()
                .get("/api/internal/meta/lookup-entities/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":false")).body(containsString("Lookup entity is not found"));


    }




    @Test
    @Ignore
    @DisplayName("/internal/meta/ lookup-entities/${name}?draft=true -POST/empty")
    @Description("Create new lookup with empty attributes. It's succuess and it is bad")
    //fullcycle to create, get and delete dc
    public void lookupCreatedem() {


        JSONObject lookup = new JSONObject();
        JSONArray ar = new JSONArray();


        lookup.put("aliasCodeAttributes", ar);
        lookup.put("aliases", ar);
        lookup.put("categories", ar);
        lookup.put("codeAttribute", JSONObject.NULL);
        lookup.put("dataQualityRules", ar);
        lookup.put("displayName", "");
        lookup.put("description", "");
        lookup.put("glossaries", ar);
        lookup.put("name", "");
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

        System.out.println("New lookup with empty name/display name was created");



    }
////////////
    @Test

    @DisplayName("/internal/meta/ lookup-entities/${name}?draft=true -POST(cat not real")
    @Description("Create new lookup with  category not from list  and  name like 8566 ")
    //fullcycle to create, get and delete dc
    public void lookupCreateddif1() {


        JSONObject lookup = new JSONObject();
        JSONArray ar = new JSONArray();
        JSONArray cat= new JSONArray();

        lookup.put("aliasCodeAttributes", ar);
        lookup.put("aliases", ar);
        lookup.put("categories", cat);
        //cat.put("Issue Classification");
        cat.put("popo");

        lookup.put("codeAttribute", JSONObject.NULL);
        lookup.put("dataQualityRules", ar);
        lookup.put("displayName", "123");
        lookup.put("description", "");
        lookup.put("glossaries", ar);
        lookup.put("name", "8566");
        lookup.put("groupName", "ROOT.referenceTables");
        lookup.put("relatedTerms", ar);
        lookup.put("replacementTerms", ar);
        lookup.put("rights", ar);
        lookup.put("simpleAttributes", ar);
        lookup.put("synonyms", ar);
        lookup.put("customAttributeMetas", ar);

//        System.out.println(lookup);

//
        given()
                .header("authorization", token)
                .contentType("application/json")
                .body(lookup.toString())
                .when().post("/api/internal/meta/lookup-entities?draft=true").then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true"));

        System.out.println("New lookup with empty name/display name was created");
//check
       given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "8566")
                .param("draft", "true")
                .when()
                .get("/api/internal/meta/lookup-entities/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true")).body(containsString("\"errors\":null"))
                .body(containsString("\"displayName\":\"123\""));


    }

//"Lookup entity is not found."

///////////////////

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

        System.out.println("New lookup was created");

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








    @Test
    @DisplayName("/internal/meta/ lookup-entities/${name}?draft=true -DELETE_negative")
    @Description("Delete not real lookup")
    //fullcycle to create, get and delete dc
    public void lookupDeleten() {

        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "absence")
                .param("draft", "true")
                .when()
                .delete("/api/internal/meta/lookup-entities/{id}")
                .then()
                .assertThat()
                .statusCode(200);
        //  .body(containsString("\"success\":true")).body(containsString("\"errors\":null"));
        System.out.println("lookup was deleted");

    }

    @Test
    @DisplayName("/internal/meta/ lookup-entities/${name}?draft=true -DELETE_positive")
    @Description("Delete real lookup")
    //fullcycle to create, get and delete dc
    public void lookupDeletpos() {

        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "8566")
                .param("draft", "true")
                .when()
                .delete("/api/internal/meta/lookup-entities/{id}")
                .then()
                .assertThat()
                .statusCode(200);
        //  .body(containsString("\"success\":true")).body(containsString("\"errors\":null"));
        System.out.println("lookup was deleted");

    }

    @Test
    @DisplayName("/internal/meta/ lookup-entities/${name}?draft=true -DELETE_bad req")
    @Description("Delete lookup with empty id")
    //fullcycle to create, get and delete dc
    public void lookupDeletneg2() {

        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                .pathParam("id", "")
                .param("draft", "true")
                .when()
                .delete("/api/internal/meta/lookup-entities/{id}")
                .then()
                .assertThat()
                .statusCode(405);
        //  .body(containsString("\"success\":true")).body(containsString("\"errors\":null"));
        System.out.println("bad request");

    }



    @Test
    @DisplayName("/v1/lookups/{type}/export")
    @Description("Export lookup and  check availability of attachment and  filename= like Exchange....")
    public void lookupExportTest() {


        JSONObject lookupex = new JSONObject();

        lookupex.put("format", "CSV");
        lookupex.put("csvFieldDelimiter", "|");
        lookupex.put("csvQuoteChar", "\"");
        lookupex.put("csvHeader", true);
        lookupex.put("csvIncludeId", true);
        lookupex.put("displayName", "");



        System.out.println(lookupex);

       Response response =
               given()
                       .header("authorization", token)
                       .pathParam("id", "Exchange")
                       .contentType("application/json")
                       .body(lookupex.toString())
                       .when().post("/api/v1/lookups/{id}/export").then()
                       .assertThat()
                       .statusCode(200)
                     //  .extract().response().getHeader("Content-Disposition").contains("attachment; filename=\"ExchangeSSSS");
                       .extract().response();
     //   System.out.println(response.getHeader("Content-Disposition") );
        Assert.assertTrue(response.getHeader("Content-Disposition").contains("attachment; filename=\"Exchange"));





    }



    @Test
    @DisplayName("/v1/lookups/{type}/export/exportCsvTemplate")
    @Description("Export lookup in exportCsvTemplate and  check availability of attachment and  filename= like Exchange-template.csv")
    public void lookupExportTemplTest() {


        JSONObject lookupex = new JSONObject();

        lookupex.put("format", "CSV");
        lookupex.put("csvFieldDelimiter", "|");
        lookupex.put("csvQuoteChar", "\"");
        lookupex.put("csvHeader", true);
        lookupex.put("csvIncludeId", true);
        lookupex.put("displayName", "");



        System.out.println(lookupex);

        Response response =
                given()
                        .header("authorization", token)
                        .pathParam("id", "Exchange")
                        .contentType("application/json")
                        .body(lookupex.toString())
                        .when().post("/api/v1/lookups/{id}/export").then()
                        .assertThat()
                        .statusCode(200)
                        //  .extract().response().getHeader("Content-Disposition").contains("attachment; filename=\"ExchangeSSSS");
                        .extract().response();
        //   System.out.println(response.getHeader("Content-Disposition") );
        Assert.assertTrue(response.getHeader("Content-Disposition").contains("attachment; filename=\"Exchange-template.csv"));





    }




    //http://prototype.datasynthes.com/api/v1/lookups/Exchange/exportCsvTemplate

}



