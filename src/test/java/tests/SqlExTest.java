package tests;

import io.qameta.allure.*;
import org.testng.annotations.*;
import pages.SqlExPage;

import static com.codeborne.selenide.Selenide.*;
import static helpers.CookieManager.*;
import static helpers.TestConfig.getSqlExPassword;
import static helpers.TestConfig.getSqlExUsername;

@Epic("Авторизация пользователей")
@Feature("Авторизация на сайт SQL-EX")
public class SqlExTest extends BaseTest {
    private SqlExPage sqlExPage;
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
    @Story("Авторизация с использованием куки")
    @Description("Проверка авторизации на сайте с использованием сохраненных куки")
    @Severity(SeverityLevel.CRITICAL)
    public void cookiesLoginTest() {
        sqlExPage.checkLoginFormVisibility();
        sqlExPage.loginWithCookiesOrCredentials(getSqlExUsername(), getSqlExPassword());
        refresh();
        sqlExPage.checkSuccessfulLogin();
    }
}

