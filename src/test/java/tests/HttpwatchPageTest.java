package tests;

import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HttpwatchPage;

import static com.codeborne.selenide.Selenide.*;
import static helpers.TestConfig.getHttpWatchPassword;
import static helpers.TestConfig.getHttpWatchUsername;

@Epic("Авторизация пользователей")
@Feature("Basic Auth на httpwatch.com")
public class HttpwatchPageTest extends BaseTest {
    private HttpwatchPage httpwatchPage;
    private static String BASE_URL;

    @BeforeClass
    public void loadCredentials() {
        String username = getHttpWatchUsername();
        String password = getHttpWatchPassword();
        BASE_URL = String.format("https://%s:%s@www.httpwatch.com/httpgallery/authentication/#showExample10", username, password);
    }

    @BeforeMethod
    public void setUp() {
        open(BASE_URL);
        httpwatchPage = page(HttpwatchPage.class);
    }

    @Test
    @Story("Пользователь проходит Basic-авторизацию")
    @Description("Открытие защищённой страницы и проверка успешной авторизации через встроенные креды в URL")
    @Severity(SeverityLevel.CRITICAL)
    public void basicAuthImageShouldBeVisible() {
        httpwatchPage.clickDisplayImageButton()
                .checkVisibilityAuthenticatedImage();
    }
}
