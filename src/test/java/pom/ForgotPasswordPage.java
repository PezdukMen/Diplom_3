package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {

    private WebDriver driver;

    // Текст "Восстановление пароля"
    private By passwordRecovery = By.xpath(".//h2[text()='Восстановление пароля']");

    // Ссылка "Войти"
    private By logIn = By.xpath(".//a[text()='Войти']");

    // Анимация
    private By animation =
            By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание текст Восстановление пароля")
    public void visibilityPasswordRecovery() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordRecovery));
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