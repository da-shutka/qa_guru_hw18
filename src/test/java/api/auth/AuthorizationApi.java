package api.auth;

import io.qameta.allure.Step;
import models.LoginRequestModel;
import models.LoginResponseModel;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.LoginSpec.loginRequestSpec;
import static specs.LoginSpec.loginSuccessResponseSpec;

public class AuthorizationApi {

    private static final String login = "petrova_di",
            password = "!DXbnBeb15";

    public static final String USER_ID = "userID",
            TOKEN = "token",
            EXPIRES = "expires";


    @Step("Авторизация пользователя")
    public static LoginResponseModel login() {
        LoginRequestModel loginData = new LoginRequestModel();
        loginData.setUserName(login);
        loginData.setPassword(password);

        return given(loginRequestSpec)
                .body(loginData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(loginSuccessResponseSpec)
                .extract().as(LoginResponseModel.class);
    }

    @Step("Установка cookies")
    public static void addCookies() {
        open("/favicon.ico");
        LoginResponseModel loginResponseData = login();
        getWebDriver().manage().addCookie(new Cookie(USER_ID, loginResponseData.getUserId()));
        getWebDriver().manage().addCookie(new Cookie(TOKEN, loginResponseData.getToken()));
        getWebDriver().manage().addCookie(new Cookie(EXPIRES, loginResponseData.getExpires()));
    }

    @Step("Получение {cookie} из установленных cookies")
    public static String getCookieByName(String cookie) {
        return getWebDriver().manage().getCookieNamed(cookie).getValue();
    }
}
