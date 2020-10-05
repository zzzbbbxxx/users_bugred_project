package tests.users;

import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restAssured.BaseReq;
import restAssured.Utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class ValidationsTests extends BaseReq {

    @DataProvider(name = "Data-Provider-Function_test7")
    public Object[][] parameterTestProvider_test7() {
        return new Object[][] {
                {"email", ""},
                {"" , ""}
        };
    }


    @Test(dataProvider = "Data-Provider-Function_test7",
            description = "email is required field")
    public void emailIsRequired(String key, String value) {

        JSONObject jsonObj = new JSONObject()
                .put(key , value)
                .put("name", "Машенька")
                .put("password","12345678");

        given()
                .baseUri(BaseReq.PATH)
                .contentType("application/json")  //another way to specify content type
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post("/doregister")
                .then()
                .assertThat()
                .statusCode(400)
                .body("type", equalTo("error"))
                .body("message", equalTo("Параметр email является обязательным!"));

    }

    @Test(description = "name is required field")
    public void nameIsRequired() {

        RestAssured.baseURI = BaseReq.PATH;

        // use org.json JSONObject to define your json
        JSONObject jsonObj = new JSONObject()
                .put("email", "milli@mail.ru")
                .put("password", "12345678");


        given()
                // port number
                .contentType("application/json")  //another way to specify content type
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post("/doregister")
                .then()
                .assertThat()
                .statusCode(400)
                .body("type", equalTo("error"))
                .body("message", equalTo("Параметр name является обязательным!"));

    }


    @Test(description = "password is required field")
    public void passwordIsRequired() {

        RestAssured.baseURI = BaseReq.PATH;

        // use org.json JSONObject to define your json
        JSONObject jsonObj = new JSONObject()
                .put("email", "milli@mail.ru")
                .put("name", "Машенька");

        given()
                // port number
                .contentType("application/json")  //another way to specify content type
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post("/doregister")
                .then()
                .assertThat()
                .statusCode(400)
                .body("type", equalTo("error"))
                .body("message", equalTo("Параметр password является обязательным!"));

    }



    @Test(description = "mail is already in use")
    public void emailIsExist() {

        RestAssured.baseURI = BaseReq.PATH;

        Utils x = new Utils();
        String name = x.randomIdentifier();

        // use org.json JSONObject to define your json
        JSONObject jsonObj = new JSONObject()
                .put("email", "milli@mail.ru")
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
                .statusCode(400)
                .body("type", equalTo("error"))
                .body("message", equalTo(" email milli@mail.ru уже есть в базе"));

    }


    @Test(description = "name is already in use")
    public void nameIsExist() {

        RestAssured.baseURI = BaseReq.PATH;

        Utils x = new Utils();
        String name = x.randomIdentifier();

        // use org.json JSONObject to define your json
        JSONObject jsonObj = new JSONObject()
                .put("email", name+"@mail.ru")
                .put("name", "Машенька")
                .put("password", "12345678");

        given()
                // port number
                .contentType("application/json")  //another way to specify content type
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post("/doregister")
                .then()
                .assertThat()
                .statusCode(400)
                .body("type", equalTo("error"))
                .body("message",
                        equalTo(" Текущее ФИО Машенька уже есть в базе"));

    }

}
