package tests.users;

import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.Test;
import restAssured.BaseReq;
import restAssured.Utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class CreateUsersTests extends BaseReq {



    @Test(description = "success registration")
    public void successRegistration() {

        RestAssured.baseURI = BaseReq.PATH;

        Utils x = new Utils();
        String name = x.randomIdentifier();

        // use org.json JSONObject to define your json
        JSONObject jsonObj = new JSONObject()
                .put("email", name+"@mail.ru")
                .put("name", name)
                .put("password", "12345678");

        given()
                // port number
                .contentType("application/json")  //another way to specify content type
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post("/doregister")
                .then()
                .assertThat()
                .statusCode(200)
                .body("$", hasKey("name"))
                .body("$", hasKey("avatar"))
                .body("$", hasKey("password"))
                .body("$", hasKey("birthday"))
                .body("$", hasKey("email"))
                .body("$", hasKey("gender"))
                .body("$", hasKey("date_start"))
                .body("$", hasKey("hobby"));
    }


}
