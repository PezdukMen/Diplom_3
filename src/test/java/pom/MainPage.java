package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    // Кнопка "Личный кабинет"
    private By personalAccount = By.xpath(".//a[@href='/account']");
    // Кнопка "Конструктор"
    private By constructor = By.xpath(".//p[text()='Конструктор']");

    // Кнопка "Войти в аккаунт"
    private By logAccount = By.xpath(".//button[text()='Войти в аккаунт']");

    // Текст "Соберите бургер"
    private By textBurger = By.xpath(".//h1[text()='Соберите бургер']");

    // Конструктор - кнопка "Булки"
    private By buns = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");
    // Конструктор - текст "Булки"
    private By textBuns = By.xpath(".//h2[text()='Булки']");
    // Конструктор - кнопка "Соусы" (сначала клик по ней - Булки по умолчанию открыты)
    private By sauces = By.cssSelector(".tab_tab__1SPyG:nth-child(2)");
    // Конструктор - текст "Соусы"
    private By textSauces = By.xpath(".//h2[text()='Соусы']");
    // Конструктор - кнопка "Начинки"
    private By fillings = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");
    // Конструктор - текст "Начинки"
    private By textFillings = By.xpath(".//h2[text()='Начинки']");

    // Анимация
    private By animation =
            By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик кнопка Личный кабинет")
    public void clickPersonalAccount() {
        driver.findElement(personalAccount).click();
        invisibilityAnimation();
    }

    @Step("Клик кнопка Конструктор")
    public void clickConstructor() {
        driver.findElement(constructor).click();
        invisibilityAnimation();
    }

    @Step("Клик кнопка Войти в аккаунт")
    public void clickLogAccount() {
        driver.findElement(logAccount).click();
        invisibilityAnimation();
    }

    @Step("Ожидание текст Соберите бургер")
    public void visibilityTextBurger() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(textBuns));
    }

    @Step("Клик кнопка Булки")
    public void clickBuns() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(buns).click();
    }

    @Step("Ожидание текст Булки и возврат")
    public String getTextBuns() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(textBuns)).getText();
    }

    @Step("Клик кнопка Соусы")
    public void clickSauces() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(sauces).click();
    }

    @Step("Ожидание текст Соусы и возврат")
    public String getTextSauces() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(textSauces)).getText();
    }

    @Step("Клик кнопка Начинки")
    public void clickFillings() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(fillings).click();
    }

    @Step("Ожидание текст Начинки и возврат")
    public String getTextFillings() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(textFillings)).getText();
    }

    // Ожидание проигрывания Анимации
    public void invisibilityAnimation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(animation));
    }

    @Step("Переключение раздел Булки")
    public void attributeContainsBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.attributeContains(buns, "class", "current"));
    }

    @Step("Переключение раздел Соусы")
    public void attributeContainsSauces() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.attributeContains(sauces, "class", "current"));
    }

    @Step("Переключение раздел Начинки")
    public void attributeContainsFillings() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.attributeContains(fillings, "class", "current"));
    }

    // Получения URL страницы
    public String getUrl() {
        return driver.getCurrentUrl();
    }

}