package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.CookieManager.loadCookiesFromFile;
import static helpers.CookieManager.saveCookiesToFile;

public class SqlExPage {
    private static final Logger LOG = LoggerFactory.getLogger(SqlExPage.class);

    @FindBy(name = "login")
    private SelenideElement loginInput;
    @FindBy(name = "psw")
    private SelenideElement passwordInput;
    @FindBy(name = "subm1")
    private SelenideElement loginButton;
    @FindBy(css = "a[href*='personal.php']")
    private SelenideElement personalPage;
    @FindBy(css = "font[color='red']")
    private SelenideElement errorMessage;

    @Step("Проверка отображения элементов")
    public SqlExPage checkLoginFormVisibility() {
        loginInput.shouldBe(visible);
        passwordInput.shouldBe(visible);
        loginButton.shouldBe(visible);
        return this;
    }

    @Step("Ввод данных для входа")
    public SqlExPage enterCredentials(String username, String password) {
        loginInput.setValue(username);
        passwordInput.setValue(password);
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
        loginInput.shouldNotBe(visible);
        loginButton.shouldNotBe(visible);
        passwordInput.shouldNotBe(visible);
        return this;
    }

    @Step("Авторизация с куками или вводом учетных данных")
    public SqlExPage loginWithCookiesOrCredentials(String username, String password) {
        Set<Cookie> cookies = loadCookiesFromFile();
        if (cookies.isEmpty()) {
            enterCredentials(username, password);
            saveCookiesToFile(getWebDriver().manage().getCookies());
        } else {
            for (Cookie cookie : cookies) {
                getWebDriver().manage().addCookie(new Cookie(cookie.getName(), cookie.getValue()));
            }
        }
        return this;
    }

    @Step("Снятие фокуса с поля ввода")
    public SqlExPage removeFocusFromInput(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.activeElement.blur();");
        return this;
    }

    @Step("Проверка снятия фокуса с элемента")
    public boolean isFocusRemovedFromElement(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (boolean) js.executeScript("return document.activeElement !== arguments[0];", loginInput);
    }

    @Step("Проверка наличия скролла на странице")
    public boolean isScroll(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (boolean) js.executeScript("return document.documentElement.scrollHeight > document.documentElement.clientHeight;");
    }
}
