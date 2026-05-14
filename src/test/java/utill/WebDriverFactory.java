package utill;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.Map;

public class WebDriverFactory {

    private static final Map<String, String> BROWSER_BINARY_PATHS = Map.of(
            "yandex", getYandexBinaryPath()
    );

    public static WebDriver getDriver(String browserName) {
        String browser = browserName.toLowerCase().trim();

        switch (browser) {
            case "chrome":
                return new ChromeDriver();

            case "yandex":
                ChromeOptions options = new ChromeOptions();
                options.setBinary(getBinaryPath("yandex"));

                options.setBrowserVersion("147"); // явно указываем версию браузера
                options.setCapability("browserVersion", "147");

                // для стабильности
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

                return new ChromeDriver(options);

            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browserName);
        }
    }

    private static String getBinaryPath(String browser) {
        String path = BROWSER_BINARY_PATHS.get(browser);
        if (path == null || !new File(path).exists()) {
            throw new RuntimeException("Не найден исполняемый файл для браузера: " + browser +
                    "\nПроверьте, что браузер установлен.");
        }
        return path;
    }

    /**
     * Определяем путь к Яндекс.Браузеру в зависимости от ОС
     */
    private static String getYandexBinaryPath() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            // Самые актуальные пути на Windows (проверяем по порядку)
            String user = System.getProperty("user.name");
            String[] windowsPaths = {
                    "C:\\Users\\" + user + "\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe",
                    "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe",
                    "C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe"
            };

            for (String p : windowsPaths) {
                if (new File(p).exists()) {
                    return p;
                }
            }
        }

        else if (os.contains("mac")) {
            String[] macPaths = {
                    "/Applications/Yandex.app/Contents/MacOS/Yandex",
                    "/Applications/Yandex Browser.app/Contents/MacOS/Yandex"
            };
            for (String p : macPaths) {
                if (new File(p).exists()) return p;
            }
        }

        throw new RuntimeException("Не удалось автоматически определить путь к Yandex Browser. " +
                "Укажите путь вручную в getYandexBinaryPath()");
    }
}