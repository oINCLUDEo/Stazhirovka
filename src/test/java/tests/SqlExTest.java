package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import pages.SqlExPage;

import static com.codeborne.selenide.Selenide.*;
import static helpers.CookieManager.*;

public class SqlExTest extends BaseTest {
    private SqlExPage sqlExPage;
    private static final Logger LOG = LoggerFactory.getLogger(SqlExTest.class);
    private static final String BASE_URL = "https://www.sql-ex.ru";

    @BeforeMethod
    public void openLoginPage() {
        open(BASE_URL);
        sqlExPage = page(SqlExPage.class);
    }

    @BeforeClass
    public void alwaysCleanUp() {
        clearCookiesFile();
    }

    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }

    @Test(invocationCount = 2)
    public void cookiesLoginTest() {
        sqlExPage.checkLoginFormVisibility();
        sqlExPage.loginWithCookiesOrCredentials();
        refresh();
        sqlExPage.checkSuccessfulLogin();
    }
}

