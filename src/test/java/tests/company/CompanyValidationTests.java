package tests.company;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restAssured.DataUser;
import restAssured.Helper;
import restAssured.Utils;

import static io.restassured.RestAssured.given;

public class CompanyValidationTests extends CompanyBase {

    @DataProvider(name = "Data-Provider-Function_test7")
    public Object[][] parameterTestProvider_test7() {
        return new Object[][] {
                {"company_name", ""},
                {"company_name" , null},
                {"" , ""}

        };
    }


    @Test(dataProvider = "Data-Provider-Function_test7",
            description = "company_name is required field")
    public void companyNameIsRequired(String key, String value) {

        DataUser user = new DataUser(Helper.registration());

        JSONArray company_users = new JSONArray();
        company_users.put(user.getEmail());

        JSONObject jsonObj = new JSONObject()
                .put(key , value)
                .put("company_type", "ООО")
                .put("company_users", company_users)
                .put("email_owner", user.getEmail());

        given()
                .spec(requestSpec)
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post()
                .then()
                .spec(getResponseSpec("Параметр company_name является обязательным!"));

    }



    @DataProvider(name = "Data-Provider-Function_test8")
    public Object[][] parameterTestProvider_test8() {
        return new Object[][] {
                {"company_name", 11},
        };
    }


    @Test(dataProvider = "Data-Provider-Function_test8",
            description = "company_name must be a string")
    public void companyNameIsIncorrect(String key, int value) {

        DataUser user = new DataUser(Helper.registration());

        JSONArray company_users = new JSONArray();
        company_users.put(user.getEmail());

        JSONObject jsonObj = new JSONObject()
                .put(key , value)
                .put("company_type", "ООО")
                .put("company_users", company_users)
                .put("email_owner", user.getEmail());

        given()
                .spec(requestSpec)
                .body(jsonObj.toString())   // use jsonObj toString method
                .when()
                .post()
                .then()
                .spec(getResponseSpec(" company_name некорректный"));

    }


}
