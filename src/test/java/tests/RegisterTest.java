package tests;

import api.User;
import api.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utill.UserGenerator;

public class RegisterTest extends BaseTest {

    private final UserClient userClient = new UserClient();
    private User registeredUser;
    private String accessToken;

    @Before
    public void prepareUser() {
        registeredUser = UserGenerator.getRandomUser();
    }

    @After
    public void deleteUser() {
        // Удаляем пользователя
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void shouldSuccessfulRegistration() {
        objMainPage.invisibilityAnimation(); // Ожидание проигрывания анимации
        objMainPage.clickPersonalAccount();
        objLoginPage.clickRegister();
        objRegisterPage.sendKeysName(registeredUser.getName());
        objRegisterPage.sendKeysEmail(registeredUser.getEmail());
        objRegisterPage.sendKeysPassword(registeredUser.getPassword());
        objRegisterPage.clickRegister();
        // Проверка текста "Вход" на страницы login, после успешной регистрации
        objLoginPage.visibilityTextEntrance();
        // Входим под пользователем СРАЗУ сохраняем токен из ответа входа
        Response response = userClient.loginUser(registeredUser);
        accessToken = userClient.extractToken(response);
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля. Минимальный пароль — шесть символов")
    public void shouldErrorNotCorrectPassword() {
        objMainPage.invisibilityAnimation(); // Ожидание проигрывания анимации
        objMainPage.clickPersonalAccount();
        objLoginPage.clickRegister();
        objRegisterPage.sendKeysPassword("12345"); // менее 6 символов
        objRegisterPage.clickRegister();
        // Проверка текста "Некорректный пароль" на страницы register, после введения пароля менее 6 символов
        objRegisterPage.visibilityTextPassword();
    }

}