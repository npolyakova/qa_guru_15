package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public class BaseTest {

    private static final String BASE_URI = "https://reqres.in/api/";
    public static RequestSpecification requestSpecificationUL;
    public static RequestSpecification requestSpecificationUC;
    public static RequestSpecification requestSpecificationUR;
    public static RequestSpecification requestSpecificationUUA;

    @BeforeAll
    public static void setUp() {
        requestSpecificationUL = given().baseUri(BASE_URI).contentType(ContentType.JSON).filter(new AllureRestAssured());

        String requestParamsUC = "{ \"name\": \"Andrew\", \"job\": \"HR manager\" }";
        requestSpecificationUC = given().baseUri(BASE_URI).contentType(ContentType.JSON).filter(new AllureRestAssured()).body(requestParamsUC);

        String requestParamsUR = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";
        requestSpecificationUR = given().baseUri(BASE_URI).contentType(ContentType.JSON).filter(new AllureRestAssured()).body(requestParamsUR);

        String requestParamsUUA = "{ \"email\": \"eve.holt@reqres.in\"}";
        requestSpecificationUUA = given().baseUri(BASE_URI).contentType(ContentType.JSON).filter(new AllureRestAssured()).body(requestParamsUUA);
    }

}
