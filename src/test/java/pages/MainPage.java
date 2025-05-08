package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;


public class MainPage {
    private static final Logger LOG = LoggerFactory.getLogger(MainPage.class);


    @FindBy(id = "masthead")
    private SelenideElement header;
    @FindBy(id = "site-navigation")
    private SelenideElement nav;
    @FindBy(xpath = "//a[text()='Register Now']")
    private SelenideElement registerButton;
    @FindBy(xpath = "//section[.//h1[contains(text(), 'Best Selenium Certification Course Online')]]")
    private SelenideElement coursesSection;
    @FindBy(xpath = "//div[@data-elementor-type='footer']")
    private SelenideElement footer;

    @Step("Проверка отображения хедера")
    public MainPage checkVisibilityHeader() {
        header.shouldBe(visible);
        LOG.info("Проверка видимости хедера");
        return this;
    }

    @Step("Проверка отображения навигационного меню")
    public MainPage checkVisibilityNav() {
        nav.shouldBe(visible);
        LOG.info("Проверка видимости навигационного меню");
        return this;
    }

    @Step("Проверка отображения кнопки регистрации")
    public MainPage checkVisibilityRegisterButton() {
        registerButton.shouldBe(visible);
        LOG.info("Проверка видимости кнопки регистрации");
        return this;
    }

    @Step("Проверка отображения секции списка курсов")
    public MainPage checkVisibilityCoursesSection() {
        coursesSection.shouldBe(visible);
        LOG.info("Проверка видимости секции списка курсов");
        return this;
    }

    @Step("Проверка отображения футера")
    public MainPage checkVisibilityFooter() {
        footer.shouldBe(visible);
        LOG.info("Проверка видимости футера");
        return this;
    }
}
