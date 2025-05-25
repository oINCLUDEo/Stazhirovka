package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Selenide.*;
import static helpers.AllureHelper.attachPageSourceToAllure;
import static helpers.AllureHelper.attachScreenshotToAllure;

public class BaseTest {
    private static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);

    @BeforeClass
    public void setUp() {
        Configuration.baseUrl = System.getProperty("baseUrl", "https://www.way2automation.com/");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.downloadsFolder = "target/downloads";
        Configuration.headless = false;
        Configuration.pageLoadStrategy = "eager";
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

    @AfterClass
    public void tearDown() {
        closeWebDriver();
    }

    @AfterMethod
    public void afterTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            LOG.info("Тест {} упал, создаем скриншот", result.getName());
            attachScreenshotToAllure();
            attachPageSourceToAllure();
        }
    }
}
