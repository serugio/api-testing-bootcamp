package academyapi.baseservice;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

/**
 * Base Service TestNG + Rest Assured
 * Core API Methods
 *
 * @author Brayhan.Criollo
 */
public class BaseService {

    public Response requestGetMethod(String url) {
        return given().get(url);
    }
    public Response requestPostMethod(String url, Object body ) {

        return given()
                .contentType("application/json")
                .body(body)
                .post(url);
    }

    public Response requestPostBasicAuthMethod(String url, Object body, String authorization ) {

        return given()
                .contentType("application/json")
                .header("Authorization", authorization)
                .body(body)
                .post(url);
    }

    public Response requestGetBasicAuthMethod(String url, String authorization)
    {
        return given()
                .header("Authorization", authorization)
                .get(url);
    }



    public void requestDeleteMethod(String url, String id) {
        String deleteUrl = url + "/" + id;
        when().delete(deleteUrl);
        System.out.println("deleting id " + id);
    }

    public String getRandomNumber(int min, int max) {
        return Double.toString(Math.floor((Math.random() * (max - min)) + min));
    }
}

