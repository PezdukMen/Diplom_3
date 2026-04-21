package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pom.*;
import utill.WebDriverFactory;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public abstract class BaseTest { // что бы он не считался как Test

    protected WebDriver driver;

    // pom all
    protected ForgotPasswordPage objForgotPasswordPage;
    protected LoginPage objLoginPage;
    protected MainPage objMainPage;
    protected ProfilePage objProfilePage;
    protected RegisterPage objRegisterPage;

    @Parameterized.Parameter
    public String browserName;

    @Parameterized.Parameters(name = "{index}: Браузер: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"chrome"},
                {"yandex"}
        });
    }

    @Before
    public void setUp() throws InterruptedException {
        driver = WebDriverFactory.getDriver(browserName);
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.education-services.ru");

        // obj all
        objForgotPasswordPage = new ForgotPasswordPage(driver);
        objLoginPage = new LoginPage(driver);
        objMainPage = new MainPage(driver);
        objProfilePage = new ProfilePage(driver);
        objRegisterPage = new RegisterPage(driver);

        Thread.sleep(1500); // избегание флаки-тестов
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}