package tests;

import api.User;
import api.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utill.UserGenerator;

public class EntranceTest extends BaseTest {

    private UserClient userClient = new UserClient();
    private User registeredUser;
    private String accessToken;

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
    public void shouldLoginUsingLogAccountButton() {
        objMainPage.invisibilityAnimation();
        objMainPage.clickLogAccount();

        loginAsRegisteredUser(); // шаг

        objMainPage.visibilityTextBuns();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void shouldLoginUsingPersonalAccountButton() {
        objMainPage.invisibilityAnimation();
        objMainPage.clickPersonalAccount();

        loginAsRegisteredUser(); // шаг

        objMainPage.visibilityTextBuns();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void shouldLoginButtonRegistrationForm() {
        objMainPage.invisibilityAnimation();
        objMainPage.clickLogAccount();
        objLoginPage.clickRegister();
        objRegisterPage.clickLogIn();

        loginAsRegisteredUser(); // шаг

        objMainPage.visibilityTextBuns();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void shouldLoginButtonPasswordRecoveryForm() {
        objMainPage.invisibilityAnimation();
        objMainPage.clickLogAccount();
        objLoginPage.clickRecoverPassword();
        objForgotPasswordPage.clickLogIn();

        loginAsRegisteredUser(); // шаг

        objMainPage.visibilityTextBuns();
    }

    // Вспомогательный метод внутри теста для уменьшения дублирования кода
    private void loginAsRegisteredUser() {
        objLoginPage.sendKeysEmail(registeredUser.getEmail());
        objLoginPage.sendKeysPassword(registeredUser.getPassword());
        objLoginPage.clickLogIn();
    }
}