package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    private WebDriver driver;

    // Кнопка "Профиль"
    private By profile = By.xpath(".//a[text()='Профиль']");

    // Кнопка "Конструктор"
    private By constructor = By.xpath(".//a[@href='/']/p[text()='Конструктор']");

    // Анимация
    private By animation =
            By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание кнопка Профиль")
    public void visibilityProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(profile));
    }

    @Step("Клик кнопка Конструктор")
    public void clickConstructor() {
        driver.findElement(constructor).click();
        invisibilityAnimation();
    }

    // Ожидание проигрывания Анимации
    public void invisibilityAnimation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(animation));
    }

}