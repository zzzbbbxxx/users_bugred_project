package restAssured;

import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class DataUser {

    User data;


    public String getEmail(){
        return data.email;
    }


    public DataUser(Response response){

        JSONObject json = getJsonUser(response);
        data = getGsonUser(json);

    }


    static JSONObject getJsonUser(Response response){

        JsonPath jsonPathEvaluator = response.jsonPath();
        return new JSONObject((Map<?, ?>) jsonPathEvaluator.get("$"));

    }

    static User getGsonUser(JSONObject json){

        User data = new Gson().fromJson(String.valueOf(json), User.class);
        return data;

    }

    class User {

        public String name;
        public String avatar;
        public String password;
        public long birthday;
        public String email;
        public String gender;
        public long date_start;
        public String hobby;

    }


}