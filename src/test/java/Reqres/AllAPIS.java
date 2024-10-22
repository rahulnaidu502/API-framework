package Reqres;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;



public class AllAPIS {


    public void GetListUsers()
    {

        String url = "https://reqres.in/api/users?page=2";
        Response resp = RestAssured.get(url);
       // System.out.println(resp.asPrettyString());
        String res = resp.asString().toString();
        String responseBody = resp.getBody().asString();
        System.out.println(res);
        System.out.println(res.contains("first_name"));
        System.out.println(resp.getStatusCode());
       // resp.body("total",equalTo(12));

        // Extracting specific value using Jsonpath?
        String value = resp.jsonPath().getString("total");
        System.out.println(value);


        System.out.println("Git practice line ignore ");


    }

    public void getSingleUser()
    {
        String url = "https://reqres.in/api/users/2";
        get(url).then().body("data.id", equalTo(2));
        get(url).then().body("data.first_name", equalTo("Janet"));

    }

    public void deleteUser()
    {
        String url = "https://reqres.in/api/users/2";
        Response resp = RestAssured.delete(url);
        System.out.println(resp.asPrettyString());
        System.out.println(resp.getStatusCode());

    }

    public void createUser()
    {
        String requestBody= "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        String url = "https://reqres.in/api/users";
        Response resp = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url)
                .then()
                .statusCode(201)
                .extract()
                .response();

        System.out.println(resp.asPrettyString());
        System.out.println(resp.getStatusCode());
    }

    public void updateUser() throws IOException {
        //it will pull data from request.json file
        String requestBody = new String(Files.readAllBytes(Paths.get("C:\\Users\\Server\\eclipse-workspace\\APIAutomation\\src\\test\\java\\Reqres\\request.json")));
        String url = "https://reqres.in/api/users/2";
        Response resp = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put(url)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(resp.asPrettyString());
        System.out.println(resp.getStatusCode());
    }
}
