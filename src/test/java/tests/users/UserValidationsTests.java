package tests.users;

import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restAssured.BaseReq;
import restAssured.Utils;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class UserValidationsTests extends UserBase {

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
                .spec(requestSpec)
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post()
                .then()
                .spec(getResponseSpec("Параметр email является обязательным!"));

    }

    @Test(description = "name is required field")
    public void nameIsRequired() {

        JSONObject jsonObj = new JSONObject()
                .put("email", "milli@mail.ru")
                .put("password", "12345678");


        given()
                .spec(requestSpec)
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post()
                .then()
                .spec(getResponseSpec("Параметр name является обязательным!"));

    }


    @Test(description = "password is required field")
    public void passwordIsRequired() {

        JSONObject jsonObj = new JSONObject()
                .put("email", "milli@mail.ru")
                .put("name", "Машенька");

        given()
                .spec(requestSpec)
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post()
                .then()
                .spec(getResponseSpec("Параметр password является обязательным!"));

    }



    @Test(description = "mail is already in use")
    public void emailIsExist() {

        String name = Utils.randomIdentifier();

        // use org.json JSONObject to define your json
        JSONObject jsonObj = new JSONObject()
                .put("email", "milli@mail.ru")
                .put("name", name)
                .put("password", "12345678");

        given()
                .spec(requestSpec)
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post("/doregister")
                .then()
                .spec(getResponseSpec(" email milli@mail.ru уже есть в базе"));

    }


    @Test(description = "name is already in use")
    public void nameIsExist() {


        String name = Utils.randomIdentifier();

        JSONObject jsonObj = new JSONObject()
                .put("email", name+"@mail.ru")
                .put("name", "Машенька")
                .put("password", "12345678");

        given()
                .spec(requestSpec)
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post()
                .then()
                .spec(getResponseSpec(" Текущее ФИО Машенька уже есть в базе"));

    }

}
