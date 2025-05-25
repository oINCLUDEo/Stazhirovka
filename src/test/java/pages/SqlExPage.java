package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.SqlExTest;

import java.util.Set;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.CookieManager.loadCookiesFromFile;
import static helpers.CookieManager.saveCookiesToFile;

public class SqlExPage {
    private static final Logger LOG = LoggerFactory.getLogger(SqlExPage.class);

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

    @Step("Снятие фокуса с поля ввода")
    public SqlExPage removeFocusFromInput(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.activeElement.blur();");
        return this;
    }

    @Step("Проверка снятия фокуса с элемента")
    public SqlExPage isFocusRemovedFromElement(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean isBlurred = (boolean) js.executeScript("return document.activeElement !== arguments[0];", login);
        assert isBlurred : "Фокус не был снят с элемента";
        LOG.info("Фокус успешно снят с элемента");
        return this;
    }

    @Step("Проверка наличия скролла на странице")
    public SqlExPage isScroll(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean isScroll = (boolean) js.executeScript("return document.documentElement.scrollHeight > document.documentElement.clientHeight;");
        assert isScroll : "Скролл отсутствует на странице";
        LOG.info("Скролл присутствует на странице");
        return this;
    }
}
