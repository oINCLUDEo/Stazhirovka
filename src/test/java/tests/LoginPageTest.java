package tests;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import helpers.LoginPageMessages;
import helpers.TestConfig;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static helpers.AssertHelper.assertEqualsWithMessage;
import static helpers.GenerateData.*;

@Epic("Авторизация пользователей")
@Feature("Функционал авторизации")
public class LoginPageTest extends BaseTest {
    private LoginPage loginPage;

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
} 