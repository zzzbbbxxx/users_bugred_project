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
import static org.hamcrest.core.IsEqual.equalTo;

public class RegisterTests extends BaseReq {

    @DataProvider(name = "DataProvider1")
    public Object[][] parameterTestProvider_test14() {
        return new Object[][] {
                {""},
                {"12"},
        };
    }

    // Tests for page_size = empty, 1.5, one
    // ...status code = 200
    // ...structure of json = errorscheme
    @Test(dataProvider = "DataProvider1")
    public void registerByValidRole(String email) {

        RestAssured.baseURI = BaseReq.PATH;
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("email", email);
        requestParams.put("name", "...");
        requestParams.put("password", "...");

        request.header("Content-Type", "application/json");

        request.body(requestParams.toString());

        Response response = request.post("/doregister");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        System.out.println(response.getStatusCode());


    }


    @Test(dataProvider = "DataProvider1")
    public void getUserDetails(String email) {

        RestAssured.baseURI = PATH;

        // use org.json JSONObject to define your json
        JSONObject jsonObj = new JSONObject()
                .put("email", email)
                .put("name", email+"...")
                .put("password", "...");

        given()
     // port number
                .contentType("application/json")  //another way to specify content type
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post("/doregister")
                .then()
                .assertThat()
                .statusCode(200)
                .body("type", equalTo("error"))
                .body("message",equalTo("Некоректный  email"+" "+email));

    }

}
