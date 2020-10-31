package tests.company;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restAssured.DataUser;
import restAssured.Helper;
import restAssured.Utils;
import tests.users.UserBase;

import java.awt.image.DataBufferUShort;

import static io.restassured.RestAssured.given;

public class CompanyTests extends CompanyBase {

    @DataProvider(name = "Data-Provider1")
    public Object[][] parameterTestProvider() {
        return new Object[][]{
                {"ИП"},
                {"ОАО"},
                {"ООО"}
        };
    }

    @Test(dataProvider = "Data-Provider1",
            description = "success create company")
    public void successCreation(String company_type) {

        String name = Utils.randomIdentifier();
        DataUser user = new DataUser(Helper.registration());

        JSONArray company_users = new JSONArray();
        company_users.put(user.getEmail());

        JSONObject jsonObj = new JSONObject()
                .put("company_name", name)
                .put("company_type", company_type)
                .put("company_users",  company_users)
                .put("email_owner", user.getEmail());

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
