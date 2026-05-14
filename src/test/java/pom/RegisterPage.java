package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private WebDriver driver;

    // Текст "Регистрация"
    private By textRegister = By.xpath(".//h2[text()='Регистрация']");

    // Поле "Имя"
    private final By name = By.xpath(".//div[./label[text()='Имя']]/input[@name='name']");
    // Поле "Email"
    private final By email = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    // Поле "Пароль"
    private By password = By.xpath(".//div[./label[text()='Пароль']]/input[@name='Пароль']");
    // Поле "Пароль" - текст "Некорректный пароль" (можно просто снять фокус с поля)
    private By textPassword = By.xpath(".//p[text()='Некорректный пароль']");

    // Кнопка "Зарегистрироваться"
    private By register = By.xpath(".//button[text()='Зарегистрироваться']");

    // Ссылка "Войти"
    private By logIn = By.xpath(".//a[text()='Войти']");

    // Анимация
    private By animation =
            By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание текст Регистрация")
    public void visibilityTextRegister() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(textRegister));
    }

    @Step("Ввод в поле Имя")
    public void sendKeysName(String nameUser) {
        driver.findElement(name).sendKeys(nameUser);
    }

    @Step("Ввод в поле Email")
    public void sendKeysEmail(String emailUser) {
        driver.findElement(email).sendKeys(emailUser);
    }


    @Step("Ввод в поле Пароль")
    public void sendKeysPassword(String passwordUser) {
        driver.findElement(password).sendKeys(passwordUser);
    }

    @Step("Ожидание текст Некорректный пароль и возврат")
    public String getTextPassword () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(textPassword)).getText();
    }

    @Step("Клик кнопка Зарегистрироваться")
    public void clickRegister() {
        driver.findElement(register).click();
        invisibilityAnimation();
    }

    @Step("Клик ссылка Войти")
    public void clickLogIn() {
        driver.findElement(logIn).click();
        invisibilityAnimation();
    }

    // Ожидание проигрывания Анимации
    public void invisibilityAnimation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(animation));
    }

}