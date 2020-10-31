package restAssured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static restAssured.BaseReq.PATH;

public class Helper {

    public static final String register = "/doregister";

    static RequestSpecification requestSpec;
    ResponseSpecification responseSpec;

    public static Response registration() {

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(PATH)
                .setBasePath(register)
                .setContentType(ContentType.JSON)
                .build();

        String name = Utils.randomIdentifier();

        JSONObject jsonObj = new JSONObject()
                .put("email", name+"@mail.ru")
                .put("name", name)
                .put("password", "12345678");

        Response response = given()
                .spec(requestSpec)
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post()
                .then()
                .extract().response();

        return response;

    }
}
