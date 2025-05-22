package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class MainPage {
    private static final Logger LOG = LoggerFactory.getLogger(MainPage.class);

    @FindBy(id = "masthead")
    private SelenideElement header;
    @FindBy(id = "site-navigation")
    private SelenideElement navMenu;
    @FindBy(xpath = "//a[text()='Register Now']")
    private ElementsCollection registerButton;
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
    @FindBy(xpath = "//h2[contains(text(), 'Most Popular Software Testing Courses')]")
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
    @FindBy(css = ".dialog-close-button.dialog-lightbox-close-button")
    private SelenideElement closePopupButton;
    @FindBy(css = ".swiper-slide")
    private ElementsCollection swiperSlide;

    private SelenideElement getActiveSlide() {
        return $$(".swiper-slide.swiper-slide-active").filter(visible).first();
    }

    private SelenideElement getNextSlide() {
        return $$(".swiper-slide.swiper-slide-next").filter(visible).first();
    }

    private SelenideElement getPrevSlide() {
        return $$(".swiper-slide.swiper-slide-prev").filter(visible).first();
    }

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
        registerButton.filter(visible).shouldHave(sizeGreaterThan(0));
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

    @Step("Проверка отображения навигации по слайдам курсов")
    public MainPage checkVisibilityCoursesNavigation() {
        prevSlideButton.scrollIntoView("{behavior: 'instant', block: 'center'}");
        prevSlideButton.shouldBe(visible);
        nextSlideButton.shouldBe(visible);
        LOG.info("Проверка отображения навигации по слайдам курсов");
        return this;
    }

    @Step("Проверка переключения слайдов вперед и назад")
    public MainPage checkSwitchingCourses() {
        LOG.info("Количество слайдов: {}",swiperSlide.size());
        sleep(3000);
        SelenideElement nextSlide = getNextSlide();
        String nextSlideIndexBefore = nextSlide.getAttribute("data-swiper-slide-index");
        nextSlideButton.click();
        getActiveSlide()
                .shouldBe(visible)
                .shouldHave(attribute("data-swiper-slide-index", nextSlideIndexBefore), Duration.ofSeconds(7));
        LOG.info("Слайд переключился вперед на индекс {}", nextSlideIndexBefore);
        SelenideElement prevSlide = getPrevSlide();
        String prevSlideIndexBefore = prevSlide.getAttribute("data-swiper-slide-index");
        prevSlideButton.click();
        getActiveSlide()
                .shouldBe(visible)
                .shouldHave(attribute("data-swiper-slide-index", prevSlideIndexBefore), Duration.ofSeconds(7));
        LOG.info("Слайд переключился назад на индекс {}", prevSlideIndexBefore);
        LOG.info("Слайды успешно переключаются вперед и назад");
        return this;
    }

    @Step("Отключение попап окна")
    public MainPage disablePopup() {
        executeJavaScript("""
        document.querySelectorAll('.dialog-widget-content').forEach(popup => {
            popup.remove();
        });
        
        const style = document.createElement('style');
        style.textContent = `
            .dialog-widget-content { display: none !important; }
            .elementor-popup-modal { display: none !important; }
            .dialog-lightbox-widget-content { display: none !important; }
        `;
        document.head.appendChild(style);
        
        document.querySelectorAll('[data-elementor-type="popup"]').forEach(popup => {
            popup.removeAttribute('data-elementor-settings');
            popup.style.display = 'none';
        });
    """);
        LOG.info("Попап окно отключено");
        return this;
    }

    @Step("Активация карусели курсов")
    public MainPage activateSwiper() {
        executeJavaScript("""
                    const swiper = document.querySelector('.swiper-container');
                    const event = new MouseEvent('mousemove', {
                        bubbles: true,
                        clientX: swiper.getBoundingClientRect().x + 100,
                        clientY: swiper.getBoundingClientRect().y + 100
                    });
                    swiper.dispatchEvent(event);
                """);
        return this;
    }

    @Step("Проверка отображения информации в футере")
    public MainPage checkFooterContactInfo() {
        footer.scrollIntoView("{behavior: 'instant', block: 'center'}");
        footerAddress.shouldBe(visible);
        footerEmailLink.shouldBe(visible);
        footerPhoneNumber.shouldBe(visible);
        LOG.info("Проверка видимости информации в футере");
        return this;
    }

    @Step("Проверка отображения навигационного меню при прокрутке страницы")
    public MainPage checkNavigationWhenScrollDown() {
        footer.scrollIntoView("{behavior: 'instant'}");
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
