package tests.users;

import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.Test;
import restAssured.BaseReq;
import restAssured.Utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class UserTests extends UserBase {


    @Test(description = "success registration")
    public void successRegistration() {

        String name = Utils.randomIdentifier();

        JSONObject jsonObj = new JSONObject()
                .put("email", name+"@mail.ru")
                .put("name", name)
                .put("password", "12345678");

        given()
                .spec(requestSpec)
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post()
                .then()
                .assertThat()
                .spec(getSuccessResponseSpec());
    }


}
