package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static helpers.GenerateData.*;

public class LoginPageTest extends BaseTest {

    @BeforeMethod
    public void openLoginPage() {
        open("https://www.way2automation.com/angularjs-protractor/registeration/#/login");
    }

    @Test
    @Description("Проверка полей ввода и состояния кнопки Login")
    public void fieldsAndButtonStateTest() {
        LoginPage loginPage = page(LoginPage.class);
        loginPage.checkFieldsAndButtonState();
    }

    @Test
    @Description("Проверка успешной авторизации")
    public void successfulLoginTest() {
        LoginPage loginPage = page(LoginPage.class);
        loginPage.enterCredentials("angular", "password", generateUsernameDescription())
                .clickLogin()
                .checkSuccessLogin();
    }

    @Test
    @Description("Проверка авторизации с невалидными данными")
    public void invalidLoginTest() {
        LoginPage loginPage = page(LoginPage.class);
        loginPage.enterCredentials(generateWrongUsername(), generateWrongPassword(), generateUsernameDescription())
                .clickLogin()
                .checkErrorLogin();
    }

    @Test(dependsOnMethods = "successfulLoginTest")
    @Description("Проверка успешного разлогирования")
    public void logoutTest() {
        LoginPage loginPage = page(LoginPage.class);
        loginPage.enterCredentials("angular", "password", generateUsernameDescription())
                .clickLogin()
                .checkSuccessLogin()
                .logout();
    }
} 