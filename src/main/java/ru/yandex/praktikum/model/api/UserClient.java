package ru.yandex.praktikum.model.api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.model.api.base.BurgerRestClient;

import static io.restassured.RestAssured.given;

public class UserClient extends BurgerRestClient {


    private static final String USER_URI = BASE_URI + "auth/";

    @Step("Create user {user}")
    public ValidatableResponse createUser (User user) {
        return given()
                .spec(getBaseReqSpec())
                .body(user)
                .when()
                .post(USER_URI + "register")
                .then();
    }

    @Step("Login as {courierCredentials}")
    public ValidatableResponse login(UserCredentials userCredentials) {
        return given()
                .spec(getBaseReqSpec())
                .body(userCredentials)
                .when()
                .post(USER_URI+ "login")
                .then();
    }

    @Step("Delete user {token}")
    public ValidatableResponse delete (String accessToken) {
        return given()
                .spec(getBaseReqSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(USER_URI + "user")
                .then();
    }
}
