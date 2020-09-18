import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
//import static org.hamcrest.core.IsEqual.equalTo;

public class RegisterTests extends BaseReq {



    @Test(description = "success registration")
    public void successRegistration() {

        RestAssured.baseURI = PATH;

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
                .body("$",hasKey("name"))
                .body("$", hasKey("avatar"))
                .body("$", hasKey("password"))
                .body("$", hasKey("birthday"))
                .body("$", hasKey("email"))
                .body("$", hasKey("gender"))
                .body("$", hasKey("date_start"))
                .body("$", hasKey("hobby"));
    }


    @Test(description = "email is required field")
    public void emailIsRequired() {

        RestAssured.baseURI = PATH;

        // use org.json JSONObject to define your json
        JSONObject jsonObj = new JSONObject()
                .put("name", "Машенька")
                .put("password","12345678");

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
                .body("message", equalTo("Параметр email является обязательным!"));

    }

    @Test(description = "name is required field")
    public void nameIsRequired() {

        RestAssured.baseURI = PATH;

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

        RestAssured.baseURI = PATH;

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

        RestAssured.baseURI = PATH;

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

        RestAssured.baseURI = PATH;

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
