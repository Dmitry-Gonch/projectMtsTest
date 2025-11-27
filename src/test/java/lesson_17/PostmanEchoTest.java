package lesson_17;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTest {

    private static final String URL = "https://postman-echo.com";
    private static final String POST = "/post";

    @Test
    public void testGetRequestMethod() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecOk200());

        given()
                .when()
                .get("/get")
                .then().log().all()
                .assertThat()
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-type", equalTo("application/json"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.user-agent", notNullValue())
                .body("headers.accept-encoding", equalTo("gzip,deflate"));
    }

    @Test
    public void testGetRequestWoopsMethod() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecOk200());

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("foo1", "bar1");
        queryParams.put("foo2", "bar2");

        given()
                .params(queryParams)
                .when()
                .get("/get?foo1=bar1&foo2=bar2")
                .then().log().all()
                .assertThat()
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"));
    }

    @Test
    public void testPostRawTextRequestMethod() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecOk200());

        final String text = "{\n    \"test\": \"value\"\n}";
        Map<String, String> info = Map.of("test", "value");

        given()
                .body(text)
                .when()
                .post(POST)
                .then().log().all()
                .assertThat()
                .body("data", equalTo(info));
    }

    @Test
    public void testPostFormDataRequestMethod() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecOk200());

        Map<String, String> info = new HashMap<>();
        info.put("foo1", "bar1");
        info.put("foo2", "bar2");

        given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .params(info)
                .when()
                .post(POST)
                .then().log().all()
                .assertThat()
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .body("headers.content-length", equalTo("19"))
                .body("json.foo2", equalTo("bar2"));
    }

    @Test
    public void testPutRequestMethod() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecOk200());

        final String text = "This is expected to be sent back as part of response body.";

        given()
                .body(text)
                .when()
                .put("/put")
                .then().log().all()
                .assertThat()
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("headers.content-length", equalTo("58"));
    }

    @Test
    public void testPatchRequestMethod() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecOk200());

        String text = "This is expected to be sent back as part of response body.";

        given()
                .body(text)
                .when()
                .patch("/patch")
                .then().log().all()
                .assertThat()
                .body("data", equalTo(text))
                .body("headers.x-amzn-trace-id", notNullValue());
    }

    @Test
    public void checkMethodDeleteTest() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecOk200());

        String text = "This is expected to be sent back as part of response body.";

        Response response = given()
                .body(text)
                .when()
                .delete("/delete")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String data = jsonPath.get("data");

        Assert.assertEquals(data, text);
    }
}
