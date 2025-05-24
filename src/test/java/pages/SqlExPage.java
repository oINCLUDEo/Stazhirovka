package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.CookieManager.loadCookiesFromFile;
import static helpers.CookieManager.saveCookiesToFile;

public class SqlExPage {
    @FindBy(name = "login")
    private SelenideElement login;
    @FindBy(name = "psw")
    private SelenideElement password;
    @FindBy(name = "subm1")
    private SelenideElement loginButton;
    @FindBy(css = "a[href*='personal.php']")
    private SelenideElement personalPage;
    @FindBy(css = "font[color='red']")
    private SelenideElement errorMessage;

    @Step("Проверка отображения элементов")
    public SqlExPage checkLoginFormVisibility() {
        login.shouldBe(visible);
        password.shouldBe(visible);
        loginButton.shouldBe(visible);
        return this;
    }

    @Step("Ввод данных для входа")
    public SqlExPage enterCredentials() {
        login.setValue("karlfras");
        password.setValue("rec!oJA@jXC12&");
        loginButton.click();
        return this;
    }

    @Step("Нажатие на кнопку Login")
    public SqlExPage clickLogin() {
        loginButton.click();
        return this;
    }

    @Step("Проверка успешности входа")
    public SqlExPage checkSuccessfulLogin() {
        login.shouldNotBe(visible);
        loginButton.shouldNotBe(visible);
        password.shouldNotBe(visible);
        return this;
    }

    @Step("Авторизация с куками или вводом учетных данных")
    public SqlExPage loginWithCookiesOrCredentials() {
        Set<Cookie> cookies = loadCookiesFromFile();
        if (cookies.isEmpty()) {
            enterCredentials();
            saveCookiesToFile(getWebDriver().manage().getCookies());
        } else {
            for (Cookie cookie : cookies) {
                getWebDriver().manage().addCookie(new Cookie(cookie.getName(), cookie.getValue()));
            }
        }
        return this;
    }
}
