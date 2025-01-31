package HomeWork;

import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostmanEchoTest {

    private static final String postmanEchoUrl = "https://postman-echo.com";
    RequestBody testRequest = new RequestBody("bar1", "bar2");

    ResponseSpecification getSpec = expect()
            .statusCode(200)
            .body("args.foo1", equalTo("bar1"))
            .body("args.foo2", equalTo("bar2"))
            .body("headers.host", equalTo("postman-echo.com"))
            .body("headers.connection", equalTo("close"))
            .body("headers.x-forwarded-proto", equalTo("https"))
            .body("headers.x-forwarded-port", equalTo("443"))
            .body("headers.content-type", equalTo("application/json"))
            .body("headers.accept", equalTo("*/*"))
            .body("url", equalTo(postmanEchoUrl + "/get?foo1=bar1&foo2=bar2"))
            .body("headers.x-request-start", notNullValue())
            .body("headers.x-amzn-trace-id", notNullValue());

    ResponseSpecification postSpec = expect()
            .statusCode(200)
            .body("json.foo1", equalTo("bar1"))
            .body("json.foo2", equalTo("bar2"))
            .body("headers.host", equalTo("postman-echo.com"))
            .body("headers.connection", equalTo("close"))
            .body("headers.x-forwarded-proto", equalTo("https"))
            .body("headers.x-forwarded-port", equalTo("443"))
            .body("headers.content-type", equalTo("application/json"))
            .body("headers.accept", equalTo("*/*"))
            .body("url", equalTo(postmanEchoUrl + "/post"))
            .body("headers.x-request-start", notNullValue())
            .body("headers.x-amzn-trace-id", notNullValue())
            .body("headers.content-length", notNullValue());

    ResponseSpecification putSpec = expect()
            .statusCode(200)
            .body("json.foo1", equalTo("bar1"))
            .body("json.foo2", equalTo("bar2"))
            .body("headers.host", equalTo("postman-echo.com"))
            .body("headers.connection", equalTo("close"))
            .body("headers.x-forwarded-proto", equalTo("https"))
            .body("headers.x-forwarded-port", equalTo("443"))
            .body("headers.content-type", equalTo("application/json"))
            .body("headers.accept", equalTo("*/*"))
            .body("url", equalTo(postmanEchoUrl + "/put"))
            .body("headers.x-request-start", notNullValue())
            .body("headers.x-amzn-trace-id", notNullValue())
            .body("headers.content-length", notNullValue());

    ResponseSpecification patchSpec = expect()
            .statusCode(200)
            .body("json.foo1", equalTo("bar1"))
            .body("json.foo2", equalTo("bar2"))
            .body("headers.host", equalTo("postman-echo.com"))
            .body("headers.connection", equalTo("close"))
            .body("headers.x-forwarded-proto", equalTo("https"))
            .body("headers.x-forwarded-port", equalTo("443"))
            .body("headers.content-type", equalTo("application/json"))
            .body("headers.accept", equalTo("*/*"))
            .body("url", equalTo(postmanEchoUrl + "/patch"))
            .body("headers.x-request-start", notNullValue())
            .body("headers.x-amzn-trace-id", notNullValue())
            .body("headers.content-length", notNullValue());

    ResponseSpecification deleteSpec = expect()
            .statusCode(200)
            .body("json.foo1", equalTo("bar1"))
            .body("json.foo2", equalTo("bar2"))
            .body("headers.host", equalTo("postman-echo.com"))
            .body("headers.connection", equalTo("close"))
            .body("headers.x-forwarded-proto", equalTo("https"))
            .body("headers.x-forwarded-port", equalTo("443"))
            .body("headers.content-type", equalTo("application/json"))
            .body("headers.accept", equalTo("*/*"))
            .body("url", equalTo(postmanEchoUrl + "/delete"))
            .body("headers.x-request-start", notNullValue())
            .body("headers.x-amzn-trace-id", notNullValue())
            .body("headers.content-length", notNullValue());


    @Test
    @DisplayName("Тест Get метода")
    public void testGet() {
        given()
                .baseUri(postmanEchoUrl)
                .when()
                .contentType(ContentType.JSON)
                .get("/get?foo1=bar1&foo2=bar2")
                .then()
                .spec(getSpec);
    }

    @Test
    @DisplayName("Тест Post метода")
    public void testPost() {
        given()
                .baseUri(postmanEchoUrl)
                .header("Content-Type", "application/json")
                .body(testRequest)
                .when()
                .post("/post")
                .then()
                .spec(postSpec);
    }

    @Test
    @DisplayName("Тест Put метода")
    public void testPut() {
        given()
                .baseUri(postmanEchoUrl)
                .header("Content-Type", "application/json")
                .body(testRequest)
                .when()
                .put("/put")
                .then()
                .spec(putSpec);
    }

    @Test
    @DisplayName("Тест Patch метода")
    public void testPatch() {
        given()
                .baseUri(postmanEchoUrl)
                .header("Content-Type", "application/json")
                .body(testRequest)
                .when()
                .patch("/patch")
                .then()
                .spec(patchSpec);
    }

    @Test
    @DisplayName("Тест Delete метода")
    public void testDelete() {
        given()
                .baseUri(postmanEchoUrl)
                .header("Content-Type", "application/json")
                .body(testRequest)
                .when()
                .delete("/delete")
                .then()
                .spec(deleteSpec);
    }
}
