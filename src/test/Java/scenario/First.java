package scenario;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class First {
    static String token;

    @BeforeClass
    public static void testGetToken() {

        RestAssured.baseURI = "https://prototype.datasynthes.com";

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
    @DisplayName("Create DS")
    @Description("Create DS")
    public void CatalogCreate() {

        File file3 = new File("C:\\Users\\natalia.chaplygina\\UIDS-tests2\\tmp\\data_source.json");


     /*   JSONObject dc = new JSONObject();
        JSONArray DS = new JSONArray();

        dc.put("dataSource", DS);
        dc.put("groupName", "ROOT.dc");
        dc.put("displayName", "Test_scenario1");
        dc.put("name", "Test_scenario1");*/
        // System.out.println(dc);
        //  https://prototype.datasynthes.com/api/internal/meta/dataCatalogs/Bloomberg?draft=true

        given()
                .header("authorization", token)
                .contentType("application/json")
                .body(file3)
                .when().put("/api/internal/meta/dataCatalogs/Bloomberg?draft=true").then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true"));





/*
        given()
                .header("authorization", token)
                //.pathParam("id", "Bloomberg")
                //.pathParam("id", "test")
                .pathParam("id", "Test_scenario1")
                .param("draft", "true")
                .when()
                .get("http://prototype.datasynthes.com/api/internal/meta/dataCatalogs/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true")).body(containsString("\"errors\":null"))
                .body(containsString("\"displayName\":\"Test_scenario1\""));
*/

    }


    @Test
    @DisplayName("Update DS")
    @Description("CReate all attributes+mapping")
    public void UpdateCatalog() {

        File file = new File("C:\\Users\\natalia.chaplygina\\UIDS-tests2\\tmp\\data_source_2.json");


        given()
                .header("authorization", token)
                .contentType("application/json")
                // .body(file.toString())
                .body(file)
                .when().put("http://prototype.datasynthes.com/api/internal/meta/dataCatalogs/Bloomberg?draft=true").then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true"));

// // https://prototype.datasynthes.com/api/internal/meta/dataCatalogs/Bloomberg?draft=true
    }


    @Test
    @DisplayName("Create DQ")
    @Description("Create DQ")

    public void dqPolicyCreate1() {


        JSONObject dq = new JSONObject();
        JSONArray instr = new JSONArray();
        JSONArray mapdef = new JSONArray();


        dq.put("description", "");
        dq.put("displayName", "Test_policy_scenario1");
        dq.put("groupName", "ROOT.dqPolicies");
        dq.put("name", "Test_policy_scenario1");
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
                .pathParam("id", "Test_policy_scenario1")
                .param("draft", "true")
                .when()
                .get("/api/internal/meta/dqPolicies/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true")).body(containsString("\"errors\":null"))
                .body(containsString("\"displayName\":\"Test_policy_scenario1\""));


        File file1 = new File("C:\\Users\\natalia.chaplygina\\UIDS-tests2\\tmp\\policy_1.json");


        given()
                .header("authorization", token)
                .pathParam("id", "Test_policy_scenario1")
                .contentType("application/json")
                // .body(file.toString())
                .body(file1)
                .when().put("/api/internal/meta/dqPolicies/{id}?draft=true").then()
                //https://prototype.datasynthes.com/api/internal/meta/dqPolicies/Test_policy_scenario1?draft=true
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true"));


    }


    @Test
    @DisplayName("/api/v1/spark/packages")
    @Description("Update package")
//@Ignore
    public void PackTest()

    {
        File file4 = new File("C:\\Users\\natalia.chaplygina\\UIDS-tests2\\tmp\\package_1.json");

        String packid =
                given()
                        .header("authorization", token)
                        .contentType("application/json")
                        // .body(file.toString())
                        .body(file4)
                        .when().put("/api/v1/spark/packages").then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .jsonPath().getString("id");
        // .body(containsString("\"success\":true")); //Не содержит она это, надо подумать что вставить


        System.out.println("create a new pckage" + packid);

    }

    @Test
    @DisplayName("/api/v1/spark/packages")
    @Description("Update package")
@Ignore
    public void JobTest1()

    {

        String idjob =
                given()
                        .header("authorization", token)
                        .contentType("multipart/form-data")
       // Content-Type: multipart/form-data; boundary=----WebKitFormBoundaryhoGeI6Lqlfnv1MVY

            .multiPart("file9", new File("C:\\Users\\natalia.chaplygina\\UIDS-tests2\\tmp\\VendorSource_EQ_source.xlsx"))
                        //   .pathParam("packageId ", packid)

                        .pathParam("packageId", "PACK032")
                        .expect()
                        .statusCode(200)
                        .when().post("/api/v1/spark/packages/{packageId}/submit").then()
                        // .when().post("/api/v1/spark/packages/PACK032/submit").then()
                        //   .statusCode(200)
                        .extract()
                        .jsonPath().getString("id");
        System.out.println("create a new pckage" + idjob);


    }


    @Test
    @DisplayName("/api/v1/spark/packages")
    @Description("Update package")
    @Ignore
    public void JobTest()

    {
        File file2 = new File("C:\\Users\\natalia.chaplygina\\UIDS-tests2\\tmp\\package.json");


        given()
                .header("authorization", token)
                .contentType("application/json")
                // .body(file.toString())
                .body(file2)
                .when().put("/api/v1/spark/packages").then()
                .assertThat()
                .statusCode(200)
                .body(containsString("\"success\":true")); //Не содержит она это, надо подумать что вставить


    }


}























    /*
    * File file = new File("/Users/Kbay/IdeaProjects/RESTproj/CreateAccount.json");
String content = null;
Response resss = given().headers(headers).when().contentType(ContentType.JSON).body(file).post("https://api-int.pc.com:444/accessmanagement-api/v1/accounts");
System.out.println(resss);*/


/*
    File file = new File(classLoader.getResource("payment.json").getFile());

    payment_id = given()
.contentType(ContentType.JSON)
.auth()
.oauth2(accessToken)
.when()
.body(file)
.log()
.all()
.post("/payments/payment")
.then()
.log()
.all()
.extract()
.path("id");
*/
