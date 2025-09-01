package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.DriverFactory;
import io.qameta.allure.selenide.AllureSelenide;
import listeners.TestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static helpers.AllureHelper.attachPageSourceToAllure;
import static helpers.AllureHelper.attachScreenshotToAllure;

@Listeners(TestListener.class)
public class BaseTest {
    private static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(@Optional("") String browser) {
        LOG.info("Запуск метода setUp с браузером: {}", browser);
        
        // Проверяем наличие SELENOID_URL
        String selenoidUrl = System.getenv("SELENOID_URL");
        if (selenoidUrl != null && !selenoidUrl.isEmpty()) {
            LOG.info("Обнаружен SELENOID_URL: {}, используем headless режим", selenoidUrl);
            
            // Настраиваем Selenide для работы с Selenoid
            Configuration.remote = selenoidUrl;
            Configuration.browser = "chrome";
            Configuration.browserVersion = "latest";
            Configuration.baseUrl = "https://www.way2automation.com/";
            
            // Настройки для стабильности
            Configuration.pageLoadStrategy = "eager";
            Configuration.remoteConnectionTimeout = 60000;
            Configuration.remoteReadTimeout = 60000;
            Configuration.pageLoadTimeout = 60000;
            
            // Capabilities для Selenoid
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
            options.setCapability("selenoid:options", Map.of(
                "enableVNC", false,
                "enableVideo", false
            ));
            Configuration.browserCapabilities = options;
            
        } else {
            // Локальная конфигурация
            Configuration.baseUrl = "https://www.way2automation.com/";
            DriverFactory.Browser browserType = browser.isEmpty() ? DriverFactory.Browser.CHROME : DriverFactory.Browser.valueOf(browser.toUpperCase());
            WebDriver driver = DriverFactory.createDriver(browserType);
            WebDriverRunner.setWebDriver(driver);
        }
        
        // Настройка Allure
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        
        // Настройка загрузок
        Configuration.downloadsFolder = "/app/downloads";
        Configuration.reportsFolder = "test-reports";
        
        LOG.info("Браузер {} успешно инициализирован", browser.isEmpty() ? "CHROME" : browser.toUpperCase());
    }

    @AfterMethod
    public void afterTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            LOG.info("Тест {} упал, создаем скриншот", result.getName());
            attachScreenshotToAllure();
            attachPageSourceToAllure();
        }
        clearBrowserCookies();
    }
    
    @AfterClass
    public void tearDown() {
        LOG.info("Закрытие браузера после завершения всех тестов класса");
        closeWebDriver();
    }
}