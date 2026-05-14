package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pom.*;
import utill.ConfigReader;
import utill.WebDriverFactory;

/***
 * mvn test -Dbrowser=chrome
 * mvn test -Dbrowser=yandex
 */
public abstract class BaseTest { // что бы он не считался как Test

    protected WebDriver driver;

    // pom all
    protected ForgotPasswordPage objForgotPasswordPage;
    protected LoginPage objLoginPage;
    protected MainPage objMainPage;
    protected ProfilePage objProfilePage;
    protected RegisterPage objRegisterPage;

    @Before
    public void setUp() throws InterruptedException {
        String browser = System.getProperty("browser",
                ConfigReader.get("browser"));

        String baseUrl = ConfigReader.get("base.url");

        driver = WebDriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.get(baseUrl);

        // obj all
        objForgotPasswordPage = new ForgotPasswordPage(driver);
        objLoginPage = new LoginPage(driver);
        objMainPage = new MainPage(driver);
        objProfilePage = new ProfilePage(driver);
        objRegisterPage = new RegisterPage(driver);

        objMainPage.invisibilityAnimation(); // избегание флаки-тестов
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}