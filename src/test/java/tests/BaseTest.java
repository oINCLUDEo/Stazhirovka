package tests;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
    @BeforeClass
    public void setUp() {
        Configuration.baseUrl = "https://www.way2automation.com/";
        Configuration.browser = "chrome";
        Configuration.browserSize = "maximized";
        Configuration.downloadsFolder = "target/downloads";
        Configuration.headless = false;
        Configuration.pageLoadStrategy = "eager";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        Configuration.browserCapabilities = options;
    }


    @AfterClass
    public void tearDown() {
        closeWebDriver();
    }
}
