package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
    @BeforeClass
    public void setUp() {
        Configuration.baseUrl = "https://www.way2automation.com/";
        Configuration.browser = "chrome";
        Configuration.browserSize = "maximized";
        Configuration.downloadsFolder = "target/downloads";
        Configuration.headless = false;
        open(Configuration.baseUrl);
    }

    @AfterClass
    public void tearDown() {
        closeWebDriver();
    }
}
