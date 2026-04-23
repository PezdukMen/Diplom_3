package tests;

import api.User;
import api.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utill.UserGenerator;

public class EntranceTest extends BaseTest {

    private UserClient userClient = new UserClient();
    private User registeredUser;
    private String accessToken;

    private String expectedUrl = "https://stellarburgers.education-services.ru/";

    @Before
    public void prepareUser() {
        // Генерируем данные пользователя
        registeredUser = UserGenerator.getRandomUser();

        // Создаем пользователя и СРАЗУ сохраняем токен из ответа создания
        Response response = userClient.createUser(registeredUser);
        accessToken = userClient.extractToken(response);
    }

    @After
    public void deleteUser() {
        // Удаляем пользователя
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Ожидаем url страницы main")
    public void shouldLoginUsingLogAccountButton() {
        objMainPage.invisibilityAnimation();
        objMainPage.clickLogAccount();

        loginAsRegisteredUser(); // шаг
        objMainPage.visibilityTextBurger();

        String actualUrl = objMainPage.getUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Ожидаем url страницы main")
    public void shouldLoginUsingPersonalAccountButton() {
        objMainPage.clickPersonalAccount();

        loginAsRegisteredUser(); // шаг
        objMainPage.visibilityTextBurger();

        String actualUrl = objMainPage.getUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Ожидаем url страницы main")
    public void shouldLoginButtonRegistrationForm() {
        objMainPage.clickLogAccount();
        objLoginPage.clickRegister();
        objRegisterPage.clickLogIn();

        loginAsRegisteredUser(); // шаг
        objMainPage.visibilityTextBurger();

        String actualUrl = objMainPage.getUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Ожидаем url страницы main")
    public void shouldLoginButtonPasswordRecoveryForm() {
        objMainPage.clickLogAccount();
        objLoginPage.clickRecoverPassword();
        objForgotPasswordPage.clickLogIn();

        loginAsRegisteredUser(); // шаг
        objMainPage.visibilityTextBurger();

        String actualUrl = objMainPage.getUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    // Вспомогательный метод внутри теста для уменьшения дублирования кода
    private void loginAsRegisteredUser() {
        objLoginPage.sendKeysEmail(registeredUser.getEmail());
        objLoginPage.sendKeysPassword(registeredUser.getPassword());
        objLoginPage.clickLogIn();
    }
}