package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.SqlExPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.CookieManager.*;
import static helpers.TestConfig.getSqlExPassword;
import static helpers.TestConfig.getSqlExUsername;
import static org.testng.Assert.assertTrue;

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
    @Story("Пользователь авторизуется с использованием куки или через поля ввода, если куки отсутствуют")
    @Description("Проверка авторизации на сайте с использованием сохраненных куки")
    @Severity(SeverityLevel.CRITICAL)
    public void cookiesLoginTest() {
        sqlExPage.checkLoginFormVisibility();
        sqlExPage.loginWithCookiesOrCredentials(getSqlExUsername(), getSqlExPassword());
        refresh();
        sqlExPage.checkSuccessfulLogin();
    }

    @Test
    @Story("Пользователь открывает SQL-EX и убирает фокус с поля")
    @Description("Тест проверяет снятие фокуса с элемента и наличие скролла на странице")
    @Severity(SeverityLevel.MINOR)
    public void checkFocusAndScrollTest() {
        WebDriver driver = getWebDriver();
        sqlExPage.removeFocusFromInput(driver)
                .isFocusRemovedFromElement(driver);
        assertTrue(sqlExPage.isFocusRemovedFromElement(driver), "Фокус не был снят с элемента");
        assertTrue(sqlExPage.isScroll(driver), "Скролл должен быть на странице");
    }
}

