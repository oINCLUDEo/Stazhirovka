package tests;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import helpers.LoginPageMessages;
import helpers.TestConfig;
import listeners.TestListener;

import static com.codeborne.selenide.Selenide.*;
import static helpers.AssertHelper.assertEqualsWithMessage;
import static helpers.GenerateData.*;

@Listeners(TestListener.class)
@Epic("Авторизация пользователей")
@Feature("Функционал авторизации")
public class LoginPageTest extends BaseTest {
    private LoginPage loginPage;
    public static final String SUCCESS_URL = "https://www.way2automation.com/angularjs-protractor/registeration/#/";
    public static final String ERROR_URL = "https://www.way2automation.com/angularjs-protractor/registeration/#/login";

    @BeforeMethod
    public void openLoginPage() {
        open("angularjs-protractor/registeration/#/login");
        loginPage = page(LoginPage.class);
    }

    @Test
    @Story("Пользователь видит форму авторизации и не может войти с пустыми полями")
    @Description("Проверка видимости полей ввода и состояния кнопки Login при пустых полях")
    @Severity(SeverityLevel.CRITICAL)
    public void loginFieldsAndButtonStateWithEmptyFieldsTest() {
        loginPage.checkLoginFieldsVisibility()
                .checkLoginButtonStateWithEmptyFields();
    }

    @Test
    @Story("Пользователь входит в систему с корректными данными")
    @Description("Проверка успешной авторизации")
    @Severity(SeverityLevel.BLOCKER)
    public void successfulLoginTest() {
        loginPage.enterCredentials(TestConfig.getUsername(), TestConfig.getPassword(), generateUsernameDescription())
                .clickLogin();
        String actualSuccessMessage = loginPage.getSuccessMessageText();
        String expectedSuccessMessage = LoginPageMessages.SUCCESS_LOGIN_MESSAGE;
        assertEqualsWithMessage(expectedSuccessMessage, actualSuccessMessage);
    }

    @Test
    @Story("Пользователь получает сообщение об ошибке при вводе неправильных данных")
    @Description("Проверка авторизации с невалидными данными")
    @Severity(SeverityLevel.CRITICAL)
    public void invalidLoginTest() {
        loginPage.enterCredentials(generateWrongUsername(), generateWrongPassword(), generateUsernameDescription())
                .clickLogin();
        String actualErrorMessage = loginPage.getErrorMessageText();
        String expectedErrorMessage = LoginPageMessages.ERROR_LOGIN_MESSAGE;
        assertEqualsWithMessage(expectedErrorMessage, actualErrorMessage);
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {TestConfig.getUsername(), TestConfig.getPassword(), generateUsernameDescription(), SUCCESS_URL},
                {generateWrongUsername(), generateWrongPassword(), generateUsernameDescription(), ERROR_URL}
        };
    }

    @Test(dataProvider = "loginData")
    @Story("Проверка авторизации с разными наборами данных")
    @Description("Тест проверяет авторизацию с различными данными, в том числе и невалидными")
    @Severity(SeverityLevel.CRITICAL)
    public void universalLoginTest(String username, String password, String description, String expectedUrl) {
        LoginPage loginPage = page(LoginPage.class);
        loginPage.enterCredentials(username, password, description)
                .clickLogin()
                .checkUrl(expectedUrl);
    }

    @Test(dependsOnMethods = "successfulLoginTest")
    @Story("Пользователь выходит из аккаунта после успешной авторизации")
    @Description("Проверка успешного разлогирования")
    @Severity(SeverityLevel.CRITICAL)
    public void logoutTest() {
        loginPage.enterCredentials(TestConfig.getUsername(), TestConfig.getPassword(), generateUsernameDescription())
                .clickLogin();
        String actualSuccessMessage = loginPage.getSuccessMessageText();
        String expectedSuccessMessage = LoginPageMessages.SUCCESS_LOGIN_MESSAGE;
        assertEqualsWithMessage(expectedSuccessMessage, actualSuccessMessage);
        loginPage.logout();
    }

    @Test(groups = "expectedFailures")
    @Story("Искусственное падение")
    @Description("Тест создан специально для проверки снятия скриншота при падении теста")
    @Severity(SeverityLevel.TRIVIAL)
    public void failingLoginTest() {
        loginPage.enterCredentials(generateWrongUsername(), generateWrongPassword(), generateUsernameDescription())
                .clickLogin()
                .checkUrl(SUCCESS_URL);
    }
} 