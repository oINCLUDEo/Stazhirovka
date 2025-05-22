package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

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

    @Step("Проверка видимости полей ввода на странице логина")
    public LoginPage checkLoginFieldsVisibility() {
        usernameInput.shouldBe(visible);
        passwordInput.shouldBe(visible);
        usernameDescriptionInput.shouldBe(visible);
        return this;
    }

    @Step("Проверка состояния кнопки Login при пустых полях ввода")
    public LoginPage checkLoginButtonStateWithEmptyFields() {
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

    @Step("Получение текста сообщения об успешной авторизации")
    public String getSuccessMessageText() {
        return successMessage.shouldBe(visible).getText();
    }

    @Step("Получение текста сообщения об ошибке авторизации")
    public String getErrorMessageText() {
        return errorMessage.shouldBe(visible).getText();
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