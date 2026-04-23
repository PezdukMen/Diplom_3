package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    // Текст "Вход"
    private By textEntrance = By.xpath(".//h2[text()='Вход']");

    // Поле "Email"
    private By email = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    // Поле "Пароль"
    private By password = By.xpath(".//div[./label[text()='Пароль']]/input[@name='Пароль']");

    // Кнопка "Войти"
    private By logIn = By.xpath(".//button[text()='Войти']");

    // Ссылка "Зарегистрироваться"
    private By register = By.xpath(".//a[@href='/register' and text()='Зарегистрироваться']");
    // Ссылка "Восстановить пароль"
    private By recoverPassword = By.xpath(".//a[@href='/forgot-password' and text()='Восстановить пароль']");

    // Анимация
    private By animation =
            By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание текст Вход")
    public void visibilityTextEntrance() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(textEntrance));
    }

    @Step("Ввод в поле Email")
    public void sendKeysEmail(String emailUser) {
        driver.findElement(email).sendKeys(emailUser);
    }

    @Step("Ввод в поле Пароль")
    public void sendKeysPassword(String passwordUser) {
        driver.findElement(password).sendKeys(passwordUser);
    }

    @Step("Клик кнопка Войти")
    public void clickLogIn() { // JavaScript-клик
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(logIn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

        invisibilityAnimation();
    }

    /*public void clickLogIn() {
        driver.findElement(logIn).click();
        invisibilityAnimation();
    }
    */

    @Step("Клик по ссылка Зарегистрироваться")
    public void clickRegister() {
        driver.findElement(register).click();
        invisibilityAnimation();
    }

    @Step("Клик по ссылка Восстановить пароль")
    public void clickRecoverPassword() {
        driver.findElement(recoverPassword).click();
        invisibilityAnimation();
    }

    // Ожидание проигрывания Анимации
    public void invisibilityAnimation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(animation));
    }

    // Получения URL страницы
    public String getUrl() {
        return driver.getCurrentUrl();
    }

}