package tests.company;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.asserts.SoftAssert;
import restAssured.BaseReq;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class CompanyBase extends BaseReq {

    public static final String company = "/createcompany";

    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;

    public CompanyBase(){

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(PATH)
                .setBasePath(company)
                .setContentType(ContentType.JSON)
                .build();

    }

    public ResponseSpecification getResponseSpec(String message) {

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("type", equalTo("error"))
                .expectBody("message", equalTo(message))
                .build();

        return responseSpec;
    }

    public ResponseSpecification getSuccessResponseSpec() {

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("$",hasKey("type"))
                .expectBody("$",hasKey("id_company"))
                .expectBody("$",hasKey("company"))
                .build();

        return responseSpec;
    }
}
