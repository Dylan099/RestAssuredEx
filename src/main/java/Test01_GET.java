import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Test01_GET {

    @Test
    void test_01(){

        given().get("https://reqres.in/api/users?page=2")
                .then()
                .body("data.id[0]",equalTo(7));
    }

    @Test
    public void test_02(){
        given().get("https://reqres.in/api/users?page=2")
                .then().statusCode(200)
                .body("data.first_name",hasItems("Michael","Lindsay"));
    }
    @Test
    public void test_03_POST(){
        JSONObject request = new JSONObject();

        request.put("name","Dylan");
        request.put("job","Tester");

        System.out.println(request.toString());

        given().
                body(request.toString()).
                when().post("https://reqres.in/api/users").
                then().statusCode(201).log().all();
    }

    @Test
    public void test_04_PUT(){
        JSONObject request = new JSONObject();

        request.put("name","Dylan");
        request.put("job","Tester");

        System.out.println(request.toString());

        given().
                body(request.toString()).
                when().put("https://reqres.in/api/users/2").
                then().statusCode(200);
    }

    @Test
    public void test_05_DELETE(){

        when().delete("https://reqres.in/api/users/2").then()
                .statusCode(204).log().all();
    }
}
