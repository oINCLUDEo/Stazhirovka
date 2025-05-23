package tests;

import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import helpers.LoginPageMessages;
import helpers.TestConfig;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static helpers.AssertHelper.assertEqualsWithMessage;
import static helpers.GenerateData.*;
import static org.testng.Assert.assertEquals;

public class LoginPageTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void openLoginPage() {
        open("angularjs-protractor/registeration/#/login");
        loginPage = page(LoginPage.class);
    }

    @Test
    @Description("Проверка видимости полей ввода и состояния кнопки Login при пустых полях")
    public void loginFieldsAndButtonStateWithEmptyFieldsTest() {
        loginPage.checkLoginFieldsVisibility()
                .checkLoginButtonStateWithEmptyFields();
    }

    @Test
    @Description("Проверка успешной авторизации")
    public void successfulLoginTest() {
        loginPage.enterCredentials( TestConfig.getUsername(), TestConfig.getPassword(), generateUsernameDescription())
                .clickLogin();
        String actualSuccessMessage = loginPage.getSuccessMessageText();
        String expectedSuccessMessage = LoginPageMessages.SUCCESS_LOGIN_MESSAGE;
        assertEqualsWithMessage(expectedSuccessMessage, actualSuccessMessage, "Текст сообщения об успешной авторизации");
    }

    @Test
    @Description("Проверка авторизации с невалидными данными")
    public void invalidLoginTest() {
        loginPage.enterCredentials(generateWrongUsername(), generateWrongPassword(), generateUsernameDescription())
                .clickLogin();
        String actualErrorMessage = loginPage.getErrorMessageText();
        String expectedErrorMessage = LoginPageMessages.ERROR_LOGIN_MESSAGE;
        assertEqualsWithMessage(expectedErrorMessage, actualErrorMessage, "Текст сообщения об ошибке авторизации");
    }

    @Test(dependsOnMethods = "successfulLoginTest")
    @Description("Проверка успешного разлогирования")
    public void logoutTest() {
        loginPage.enterCredentials(TestConfig.getUsername(), TestConfig.getPassword(), generateUsernameDescription())
                .clickLogin();
        String actualSuccessMessage = loginPage.getSuccessMessageText();
        String expectedSuccessMessage = LoginPageMessages.SUCCESS_LOGIN_MESSAGE;
        assertEqualsWithMessage(expectedSuccessMessage, actualSuccessMessage, "Текст сообщения об успешной авторизации");
        loginPage.logout();
    }
} 