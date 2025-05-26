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

import static com.codeborne.selenide.Selenide.*;
import static helpers.AllureHelper.attachPageSourceToAllure;
import static helpers.AllureHelper.attachScreenshotToAllure;

@Listeners(TestListener.class)
public class BaseTest {
    private static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(@Optional("") String browserFromXml) {
        Configuration.baseUrl = "https://www.way2automation.com/";
        String browserName = System.getProperty("browser");
        if (browserName == null || browserName.isEmpty()) {
            browserName = browserFromXml;
        }
        if (browserName == null || browserName.isEmpty()) {
            browserName = "chrome";
        }
        browserName = browserName.toUpperCase();;
        Configuration.downloadsFolder = "target/downloads";
        Configuration.headless = false;
        Configuration.pageLoadStrategy = "eager";
        Configuration.remoteConnectionTimeout = 5000;
        Configuration.remoteReadTimeout = 5000;
        Configuration.pageLoadTimeout = 10000;

        try {
            DriverFactory.Browser browser = DriverFactory.Browser.valueOf(browserName);
            WebDriver driver = DriverFactory.createDriver(browser);
            WebDriverRunner.setWebDriver(driver);
            LOG.info("Браузер {} успешно инициализирован", browserName);
        } catch (IllegalArgumentException e) {
            LOG.error("Неподдерживаемый браузер: {}. Используем Chrome по умолчанию", browserName);
            WebDriver driver = DriverFactory.createDriver(DriverFactory.Browser.CHROME);
            WebDriverRunner.setWebDriver(driver);
        }

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @AfterMethod
    public void afterTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            LOG.info("Тест {} упал, создаем скриншот", result.getName());
            attachScreenshotToAllure();
            attachPageSourceToAllure();
        }
        closeWebDriver();
        clearBrowserCookies();
    }
}
