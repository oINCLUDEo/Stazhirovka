package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

public class LoginPage {
    @FindBy(id = "username")
    private SelenideElement usernameInput;
    @FindBy(id = "password")
    private SelenideElement passwordInput;
    @FindBy(xpath = "//input[following-sibling::p[contains(text(), 'username description')]]")
    private SelenideElement usernameDescriptionInput;
    @FindBy(xpath = "//button[contains(text(), 'Login')]")
    private SelenideElement loginButton;
    @FindBy(xpath = "//p[contains(text(), \"You're logged in!!\")]")
    private SelenideElement successMessage;
    @FindBy(xpath = "//div[contains(@class,'alert-danger')]")
    private SelenideElement errorMessage;
    @FindBy(xpath = "//a[text()='Logout']")
    private SelenideElement logoutButton;

    @Step("Проверка отображения полей Username и Password и состояния кнопки Login")
    public LoginPage checkFieldsAndButtonState() {
        usernameInput.shouldBe(visible);
        passwordInput.shouldBe(visible);
        loginButton.shouldBe(disabled);
        return this;
    }

    @Step("Ввод логина, пароля и описания: {0}, {1}, {2}")
    public LoginPage enterCredentials(String username, String password, String usernameDescription) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        usernameDescriptionInput.setValue(usernameDescription);
        return this;
    }

    @Step("Нажатие на кнопку Login")
    public LoginPage clickLogin() {
        loginButton.shouldBe(enabled).click();
        return this;
    }

    @Step("Проверка успешной авторизации")
    public LoginPage checkSuccessLogin() {
        successMessage.shouldBe(visible).shouldHave(text("You're logged in!!"));
        return this;
    }

    @Step("Проверка ошибки авторизации")
    public LoginPage checkErrorLogin() {
        errorMessage.shouldBe(visible).shouldHave(text("Username or password is incorrect"));
        return this;
    }

    @Step("Разлогиниться")
    public LoginPage logout() {
        logoutButton.shouldBe(visible).click();
        usernameInput.shouldBe(visible);
        passwordInput.shouldBe(visible);
        usernameDescriptionInput.shouldBe(visible);
        return this;
    }
} 