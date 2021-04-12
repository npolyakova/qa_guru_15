package tests;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ApiTest extends BaseTest{

    @Test
    public void getUserListTest(){
        given().spec(requestSpecificationUL)
                .when()
                .get("users/1")
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("userinfo.schema.json"))
                .body("data.id", equalTo(1));
    }

    @Test
    public void createUserTest(){
        given()
                .spec(requestSpecificationUC)
                .when()
                .post("users")
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body(matchesJsonSchemaInClasspath("usercreate.schema.json"))
                .body("name", is("Andrew"))
                .body("job", is("HR manager"));
    }

    @Test
    public void registerSuccessful(){

        given().spec(requestSpecificationUR)
                .when()
                .post("register")
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id",notNullValue())
                .body("token",notNullValue());
    }

    @Test
    public void loginUnsuccessful(){
        given().spec(requestSpecificationUUA)
                .when()
                .post("login")
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void loginSuccessful(){
        given().spec(requestSpecificationUR)
                .when()
                .post("login")
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("token",notNullValue());
    }
}
