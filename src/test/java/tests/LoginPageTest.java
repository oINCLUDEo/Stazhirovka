package tests;

import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import helpers.LoginPageMessages;
import helpers.TestConfig;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
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
        assertEquals(loginPage.getSuccessMessageText(), LoginPageMessages.SUCCESS_LOGIN_MESSAGE,
                "Текст сообщения об успешной авторизации не соответствует ожидаемому");
    }

    @Test
    @Description("Проверка авторизации с невалидными данными")
    public void invalidLoginTest() {
        loginPage.enterCredentials(generateWrongUsername(), generateWrongPassword(), generateUsernameDescription())
                .clickLogin();
        assertEquals(loginPage.getErrorMessageText(), LoginPageMessages.ERROR_LOGIN_MESSAGE,
                "Текст сообщения об ошибке авторизации не соответствует ожидаемому");
    }

    @Test(dependsOnMethods = "successfulLoginTest")
    @Description("Проверка успешного разлогирования")
    public void logoutTest() {
        loginPage.enterCredentials(TestConfig.getUsername(), TestConfig.getPassword(), generateUsernameDescription())
                .clickLogin();
        assertEquals(loginPage.getSuccessMessageText(), LoginPageMessages.SUCCESS_LOGIN_MESSAGE,
                "Текст сообщения об успешной авторизации не соответствует ожидаемому");
        loginPage.logout();
    }
} 