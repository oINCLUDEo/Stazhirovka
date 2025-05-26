package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import listeners.TestListener;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.reporters.FailedReporter;

import static com.codeborne.selenide.Selenide.*;
import static helpers.AllureHelper.attachPageSourceToAllure;
import static helpers.AllureHelper.attachScreenshotToAllure;

@Listeners({TestListener.class, FailedReporter.class})
public class BaseTest {
    private static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);

    @BeforeClass
    public void setUp() {
        Configuration.baseUrl = "https://www.way2automation.com/";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.downloadsFolder = "target/downloads";
        Configuration.headless = false;
        Configuration.pageLoadStrategy = "eager";
        Configuration.remoteConnectionTimeout = 5000;
        Configuration.remoteReadTimeout = 5000;
        Configuration.pageLoadTimeout = 10000;

        String gridUrl = System.getProperty("gridUrl");
        if (gridUrl != null && !gridUrl.isEmpty()) {
            Configuration.remote = gridUrl;
            LOG.info("Используем Selenium Grid по адресу: {}", gridUrl);
        }

        String browser = Configuration.browser.toLowerCase();
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                Configuration.browserCapabilities = chromeOptions;
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                firefoxOptions.addArguments("--disable-notifications");
                firefoxOptions.addArguments("--disable-popup-blocking");
                Configuration.browserCapabilities = firefoxOptions;
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--disable-notifications");
                edgeOptions.addArguments("--disable-popup-blocking");
                Configuration.browserCapabilities = edgeOptions;
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
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
