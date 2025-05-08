package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;


public class MainPage {
    private static final Logger LOG = LoggerFactory.getLogger(MainPage.class);

    @FindBy(id = "masthead")
    private SelenideElement header;
    @FindBy(id = "site-navigation")
    private SelenideElement navMenu;
    @FindBy(xpath = "//a[text()='Register Now']")
    private SelenideElement registerButton;
    @FindBy(xpath = "//section[.//h1[contains(text(), 'Best Selenium Certification Course Online')]]")
    private SelenideElement coursesSection;
    @FindBy(xpath = "//div[@data-elementor-type='footer']")
    private SelenideElement footer;

    @FindBy(css = "#masthead a[href^='tel:']")
    private SelenideElement headerPhoneNumber;
    @FindBy(css = "#masthead a[href^='skype:']")
    private SelenideElement headerSkypeLink;
    @FindBy(css = "#masthead a[href^='mailto:']")
    private SelenideElement headerEmailLink;
    @FindBy(css = "div[data-section='section-hb-social-icons-1'] a")
    private ElementsCollection socialLinks;

    @FindBy(css = "div.pp-slider-arrow.swiper-button-prev")
    private SelenideElement prevSlideButton;
    @FindBy(css = "div.pp-slider-arrow.swiper-button-next")
    private SelenideElement nextSlideButton;
    @FindBy(xpath = "//h2[contains(text(), 'Most Popular Software Testing Courses')")
    private SelenideElement slidesTitle;

    @FindBy(css = "div[data-elementor-type='footer'] a[href^='tel:']")
    private SelenideElement footerPhoneNumber;
    @FindBy(css = "div[data-elementor-type='footer'] a[href^='mailto:']")
    private SelenideElement footerEmailLink;
    @FindBy(xpath = "//div[@data-elementor-type='footer']//span[contains(., 'Way2Automation')]")
    private SelenideElement footerAddress;

    @FindBy(xpath = "//nav[@id='site-navigation']//span[contains(., 'All Courses')]")
    private SelenideElement allCoursesLink;
    @FindBy(xpath = "//nav[@id='site-navigation']//span[contains(., 'Lifetime Membership')]")
    private SelenideElement lifetimeMembershipLink;

    @Step("Проверка отображения хедера")
    public MainPage checkVisibilityHeader() {
        header.shouldBe(visible);
        LOG.info("Проверка видимости хедера");
        return this;
    }

    @Step("Проверка отображения навигационного меню")
    public MainPage checkVisibilityNav() {
        navMenu.shouldBe(visible);
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

    @Step("Проверка контактной информации в хедере")
    public MainPage checkHeaderContactInfo() {
        headerPhoneNumber.shouldBe(visible);
        headerSkypeLink.shouldBe(visible);
        headerEmailLink.shouldBe(visible);
        socialLinks.shouldHave(sizeGreaterThan(0));
        LOG.info("Проверка контактной информации в хедере");
        return this;
    }

    //TODO: Придумать реализацию проверки прокрутки слайдов
    @Step("Проверка навигации по слайдам курсов")
    public MainPage checkCoursesNavigation() {
        prevSlideButton.shouldBe(visible);
        nextSlideButton.shouldBe(visible);
        prevSlideButton.scrollIntoView("{behavior: 'instant', block: 'center'}").click();;
        nextSlideButton.click();
        LOG.info("Проверка навигации по слайдам курсов успешно завершена");
        return this;
    }

    @Step("Проверка отображения информации в футере")
    public MainPage checkFooterContactInfo() {
        footerAddress.shouldBe(visible);
        footerEmailLink.shouldBe(visible);
        footerPhoneNumber.shouldBe(visible);
        LOG.info("Проверка видимости информации в футере");
        return this;
    }

    @Step("Проверка отображения навигационного меню при прокрутке страницы")
    public MainPage checkNavigationWhenScrollDown() {
        footer.scrollIntoView("{behavior: 'block'}");
        navMenu.shouldBe(visible);
        LOG.info("Проверка видимости навигационного меню при прокрутке страницы");
        return this;
    }

    @Step("Переход на страницу Lifetime Membership через меню All courses")
    public MainPage navigateToLifetimeMembership() {
        allCoursesLink.shouldBe(visible).click();
        lifetimeMembershipLink.shouldBe(visible).click();
        LOG.info("Переход на страницу Lifetime Membership");
        return this;
    }
}
