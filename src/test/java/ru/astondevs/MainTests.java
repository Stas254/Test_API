package ru.astondevs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;


public class MainTests {
    private final static String URL = "https://postman-echo.com/";

    @Test
    @DisplayName("Проверка корректности значений полей в теле ответа GET-запроса при отправке значений foo1, foo2")
    public void checkGetTest() {
        Specifications.installSpecification(Specifications.requestJsonSpec(URL), Specifications.responseSpecOK200());
        given()
                .param("foo1", "bar1")
                .param("foo2", "bar2")
                .when()
                .get("get")
                .then()//.log().all()
                .assertThat().body("args.foo1", equalTo("bar1"))
                .assertThat().body("args.foo1", equalTo("bar1"))
                .assertThat().body("headers.x-forwarded-proto", equalTo("https"))
                .assertThat().body("headers.x-forwarded-port", equalTo("443"))
                .assertThat().body("headers.host", equalTo("postman-echo.com"))
                .assertThat().body("headers.x-amzn-trace-id", notNullValue())
                .assertThat().body("headers.content-type", equalTo("application/json"))
                .assertThat().body("headers.accept", notNullValue())
                .assertThat().body("headers.user-agent", notNullValue())
                .assertThat().body("headers.accept-encoding", notNullValue())
                .assertThat().body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
    }

    @Test
    @DisplayName("Проверка корректности значений полей в теле ответа POST-запроса (Raw Text)")
    public void checkPostRawTextTest() {
        Specifications.installSpecification(Specifications.requestTextSpec(URL), Specifications.responseSpecOK200());
        String text = "Specifications API";
        given()
                .body(text)
                .when()
                .post("post")
                .then()//.log().all()
                .assertThat().body("data", equalTo(text))
                .assertThat().body("headers.x-forwarded-proto", equalTo("https"))
                .assertThat().body("headers.x-forwarded-port", equalTo("443"))
                .assertThat().body("headers.host", equalTo("postman-echo.com"))
                .assertThat().body("headers.x-amzn-trace-id", notNullValue())
                .assertThat().body("headers.content-length", equalTo("18"))
                .assertThat().body("headers.content-type", containsString("text/plain"))
                .assertThat().body("headers.accept", notNullValue())
                .assertThat().body("headers.user-agent", notNullValue())
                .assertThat().body("headers.accept-encoding", notNullValue())
                .assertThat().body("url", equalTo("https://postman-echo.com/post"));
    }

    @Test
    @DisplayName("Проверка корректности значений полей в теле ответа POST-запроса (Form Data)")
    public void checkPostFormDataTest() {
        Specifications.installSpecification(Specifications.requestFormSpec(URL), Specifications.responseSpecOK200());
        given()
                .formParam("name", "John")
                .formParam("year", "30")
                .when()
                .post("post")
                .then()//.log().all()
                .assertThat().body("form.name", equalTo("John"))
                .assertThat().body("form.year", equalTo("30"))
                .assertThat().body("json.name", equalTo("John"))
                .assertThat().body("json.year", equalTo("30"))
                .assertThat().body("headers.x-forwarded-proto", equalTo("https"))
                .assertThat().body("headers.x-forwarded-port", equalTo("443"))
                .assertThat().body("headers.host", equalTo("postman-echo.com"))
                .assertThat().body("headers.x-amzn-trace-id", notNullValue())
                .assertThat().body("headers.content-length", equalTo("17"))
                .assertThat().body("headers.content-type", containsString("application/x-www-form-urlencoded"))
                .assertThat().body("headers.accept", notNullValue())
                .assertThat().body("headers.user-agent", notNullValue())
                .assertThat().body("headers.accept-encoding", notNullValue())
                .assertThat().body("url", equalTo("https://postman-echo.com/post"));
    }

    @Test
    @DisplayName("Проверка корректности значений полей в теле ответа PUT-запроса")
    public void checkPutTest() {
        Specifications.installSpecification(Specifications.requestFormSpec(URL), Specifications.responseSpecOK200());
        given()
                .formParam("name", "Svetlana")
                .formParam("year", "25")
                .when()
                .put("put")
                .then()//.log().all()
                .assertThat().body("form.name", equalTo("Svetlana"))
                .assertThat().body("form.year", equalTo("25"))
                .assertThat().body("json.name", equalTo("Svetlana"))
                .assertThat().body("json.year", equalTo("25"))
                .assertThat().body("headers.x-forwarded-proto", equalTo("https"))
                .assertThat().body("headers.x-forwarded-port", equalTo("443"))
                .assertThat().body("headers.host", equalTo("postman-echo.com"))
                .assertThat().body("headers.x-amzn-trace-id", notNullValue())
                .assertThat().body("headers.content-length", equalTo("21"))
                .assertThat().body("headers.content-type", containsString("application/x-www-form-urlencoded"))
                .assertThat().body("headers.accept", notNullValue())
                .assertThat().body("headers.user-agent", notNullValue())
                .assertThat().body("headers.accept-encoding", notNullValue())
                .assertThat().body("url", equalTo("https://postman-echo.com/put"));
    }

    @Test
    @DisplayName("Проверка корректности значений полей в теле ответа PATCH-запроса")
    public void checkPatchTest() {
        Specifications.installSpecification(Specifications.requestFormSpec(URL), Specifications.responseSpecOK200());
        given()
                .formParam("FirstName", "Daniel")
                .when()
                .patch("patch")
                .then()//.log().all()
                .assertThat().body("form.FirstName", equalTo("Daniel"))
                .assertThat().body("form.FirstName", equalTo("Daniel"))
                .assertThat().body("headers.x-forwarded-proto", equalTo("https"))
                .assertThat().body("headers.x-forwarded-port", equalTo("443"))
                .assertThat().body("headers.host", equalTo("postman-echo.com"))
                .assertThat().body("headers.x-amzn-trace-id", notNullValue())
                .assertThat().body("headers.content-length", equalTo("16"))
                .assertThat().body("headers.content-type", containsString("application/x-www-form-urlencoded"))
                .assertThat().body("headers.accept", notNullValue())
                .assertThat().body("headers.user-agent", notNullValue())
                .assertThat().body("headers.accept-encoding", notNullValue())
                .assertThat().body("url", equalTo("https://postman-echo.com/patch"));
    }

    @Test
    @DisplayName("Проверка корректности удаления значения поля data DELETE-запросом")
    public void checkDeleteTest() {
        Specifications.installSpecification(Specifications.requestTextSpec(URL), Specifications.responseSpecOK200());
        given()
                .body("")   //чтобы было, что удалить в данном случае
                .when()
                .delete("delete")   //обычно delete/something
                .then()//.log().all()
                .assertThat().body("data", equalTo(""))
                .assertThat().body("headers.x-forwarded-proto", equalTo("https"))
                .assertThat().body("headers.x-forwarded-port", equalTo("443"))
                .assertThat().body("headers.host", equalTo("postman-echo.com"))
                .assertThat().body("headers.x-amzn-trace-id", notNullValue())
                .assertThat().body("headers.content-length", equalTo("0"))
                .assertThat().body("headers.content-type", containsString("text/plain"))
                .assertThat().body("headers.accept", notNullValue())
                .assertThat().body("headers.user-agent", notNullValue())
                .assertThat().body("headers.accept-encoding", notNullValue());
    }

    @Test
    @DisplayName("Негативный тест удаления DELETE-запросом, когда нет или не найдено такого содержимого")
    public void checkNegativeDeleteTest() {
        Specifications.installSpecification(Specifications.requestTextSpec(URL), Specifications.responseSpecERROR404());
        given()
                .when()
                .delete("delete/something")
                .then();//.log().all();
    }
}
