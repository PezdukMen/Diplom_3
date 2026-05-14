package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru";
    private static final String REGISTER_PATH = "/api/auth/register";
    private static final String LOGIN_PATH = "/api/auth/login";
    private static final String USER_PATH = "/api/auth/user";

    @Step("Создать пользователя по API")
    public Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(user)
                .post(REGISTER_PATH);
    }

    public Response loginUser(User user) {
        // Передаем объект целиком, Jackson сам возьмет нужные поля для JSON
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(user)
                .post(LOGIN_PATH);
    }

    @Step("Удалить пользователя по API")
    public void deleteUser(String token) {
        if (token == null) return;

        given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .auth().oauth2(token)
                .delete(USER_PATH);
    }

    public String extractToken(Response response) {
        String token = response.then().extract().path("accessToken");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }
}