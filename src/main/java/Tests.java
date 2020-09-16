import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.core.IsEqual.equalTo;

public class Tests extends BaseReq{

    @Test(description = "success registration")
    public void successRegistration() {

        RestAssured.baseURI = PATH;

        // use org.json JSONObject to define your json
        JSONObject jsonObj = new JSONObject()
                .put("email", "millimillimillimillimilli@mail.ru")
                .put("name", "МашенькаМашенькаМашенька")
                .put("password", "12345678");

        Response response = given()
                // port number
                .contentType("application/json")  //another way to specify content type
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post("/doregister")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        ValidatableResponse validatableResponse = response.then();
        validatableResponse.body("$", (hasKey("name")));
        validatableResponse.body("$", (hasKey("avatar")));
        validatableResponse.body("$", (hasKey("password")));
        validatableResponse.body("$", (hasKey("birthday")));
        validatableResponse.body("$", (hasKey("email")));
        validatableResponse.body("$", (hasKey("gender")));
        validatableResponse.body("$", (hasKey("date_start")));
        validatableResponse.body("$", (hasKey("hobby")));

    //    DataUser dataUser = new DataUser(response);

    //    System.out.println(dataUser.data);

    }




}
