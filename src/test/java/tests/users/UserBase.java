package tests.users;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import restAssured.BaseReq;

import static org.hamcrest.Matchers.equalTo;

public class UserBase extends BaseReq {

    public static final String register = "/doregister";

    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;

    public UserBase (){

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(PATH)
                .setBasePath(register)
                .setContentType(ContentType.JSON)
                .build();

    }

    public ResponseSpecification getResponseSpec(String message) {

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectBody("type", equalTo("error"))
                .expectBody("message", equalTo(message))
                .build();

        return responseSpec;
    }
}
