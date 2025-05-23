package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import helpers.MainPageMessages;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class MainPage {
    private static final Logger LOG = LoggerFactory.getLogger(MainPage.class);

    @FindBy(id = "masthead")
    private SelenideElement header;
    @FindBy(id = "site-navigation")
    private SelenideElement navMenu;
    @FindBy(xpath = "//a[text()='" + MainPageMessages.REGISTER_NOW_BUTTON + "']")
    private ElementsCollection registerButton;
    @FindBy(xpath = "//section[.//h1[contains(text(), '" + MainPageMessages.COURSES_SECTION_TITLE + "')]]")
    private SelenideElement coursesSection;
    @FindBy(xpath = "//div[@data-elementor-type='footer']")
    private SelenideElement footer;
    @FindBy(css = "#masthead a[href^='tel:']")
    private SelenideElement headerPhoneNumber;
    @FindBy(css = "#masthead a[href^='skype:']")
    private SelenideElement headerSkypeLink;
    @FindBy(css = "#masthead a[href^='mailto:']")
    private SelenideElement headerEmailLink;
    @FindBy(css = "#masthead a[href^='https://wa.me/']")
    private ElementsCollection headerWhatsappLinks;
    @FindBy(css = "div[data-section='section-hb-social-icons-1'] a")
    private ElementsCollection socialLinks;
    @FindBy(css = "div.pp-slider-arrow.swiper-button-prev")
    private SelenideElement prevSlideButton;
    @FindBy(css = "div.pp-slider-arrow.swiper-button-next")
    private SelenideElement nextSlideButton;
    @FindBy(xpath = "//h2[contains(text(), '" + MainPageMessages.POPULAR_COURSES_TITLE + "')]")
    private SelenideElement slidesTitle;
    @FindBy(css = "div[data-elementor-type='footer'] a[href^='tel:']")
    private ElementsCollection footerPhoneNumbers;
    @FindBy(css = "div[data-elementor-type='footer'] a[href^='mailto:']")
    private ElementsCollection footerEmailLinks;
    @FindBy(xpath = "//div[@data-elementor-type='footer']//span[contains(., 'Way2Automation')]")
    private SelenideElement footerAddress;
    @FindBy(xpath = "//nav[@id='site-navigation']//span[contains(., '" + MainPageMessages.ALL_COURSES_MENU_ITEM + "')]")
    private SelenideElement allCoursesLink;
    @FindBy(xpath = "//nav[@id='site-navigation']//span[contains(., '" + MainPageMessages.LIFETIME_MEMBERSHIP_MENU_ITEM + "')]")
    private SelenideElement lifetimeMembershipLink;
    @FindBy(css = ".dialog-close-button.dialog-lightbox-close-button")
    private SelenideElement closePopupButton;
    @FindBy(css = ".swiper-slide")
    private ElementsCollection swiperSlide;

    public SelenideElement getActiveSlide() {
        return $$(".swiper-slide.swiper-slide-active").filter(visible).first();
    }

    public SelenideElement getNextSlide() {
        return $$(".swiper-slide.swiper-slide-next").filter(visible).first();
    }

    public SelenideElement getPrevSlide() {
        return $$(".swiper-slide.swiper-slide-prev").filter(visible).first();
    }
    public String getActiveSlideIndex() {
        return getActiveSlide().getAttribute("data-swiper-slide-index");
    }

    public String getNextSlideIndex() {
        return getNextSlide().getAttribute("data-swiper-slide-index");
    }

    public String getPrevSlideIndex() {
        return getPrevSlide().getAttribute("data-swiper-slide-index");
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

    @Step("Проверка отображения навигации по слайдам курсов")
    public MainPage checkVisibilityCoursesNavigation() {
        prevSlideButton.shouldBe(visible);
        nextSlideButton.shouldBe(visible);
        prevSlideButton.scrollIntoView("{behavior: 'instant', block: 'center'}");
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

    @Step("Клик на кнопку следующего слайда")
    public MainPage clickNextSlide() {
        nextSlideButton.click();
        return this;
    }

    @Step("Клик на кнопку предыдущего слайда")
    public MainPage clickPrevSlide() {
        prevSlideButton.click();
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

    @Step("Получение контактной информации из футера")
    public FooterContactData getFooterContactData() {
        footer.scrollIntoView("{behavior: 'instant', block: 'center'}");
        String address = footerAddress.getText();
        String phone1 = footerPhoneNumbers.findBy(href(MainPageMessages.FOOTER_PHONE_1_HREF)).getText();
        String phone2 = footerPhoneNumbers.findBy(href(MainPageMessages.FOOTER_PHONE_2_HREF)).getText();
        String email1 = footerEmailLinks.findBy(href(MainPageMessages.FOOTER_EMAIL_1_HREF)).getText();
        String email2 = footerEmailLinks.findBy(href(MainPageMessages.FOOTER_EMAIL_2_HREF)).getText();
        LOG.info("Получена контактная информация из футера");
        return new FooterContactData(address, phone1, phone2, email1, email2);
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

    @Step("Получение контактной информации из хедера")
    public HeaderContactData getHeaderContactData() {
        String whatsapp1 = headerWhatsappLinks.findBy(href(MainPageMessages.HEADER_WHATSAPP_1_HREF)).getText();
        String whatsapp2 = headerWhatsappLinks.findBy(href(MainPageMessages.HEADER_WHATSAPP_2_HREF)).getText();
        String phone = headerPhoneNumber.getText();
        String skype = headerSkypeLink.getText();
        String email = headerEmailLink.getText();
        String whatsapp1Href = headerWhatsappLinks.findBy(href(MainPageMessages.HEADER_WHATSAPP_1_HREF)).getAttribute("href");
        String whatsapp2Href = headerWhatsappLinks.findBy(href(MainPageMessages.HEADER_WHATSAPP_2_HREF)).getAttribute("href");
        String phoneHref = headerPhoneNumber.getAttribute("href");
        String skypeHref = headerSkypeLink.getAttribute("href");
        String emailHref = headerEmailLink.getAttribute("href");
        ElementsCollection socialLinks = this.socialLinks.shouldHave(size(4));
        String facebookHref = socialLinks.findBy(href(MainPageMessages.FACEBOOK_HREF)).getAttribute("href");
        String linkedinHref = socialLinks.findBy(href(MainPageMessages.LINKEDIN_HREF)).getAttribute("href");
        String googlePlusHref = socialLinks.findBy(href(MainPageMessages.GOOGLE_PLUS_HREF)).getAttribute("href");
        String youtubeHref = socialLinks.findBy(href(MainPageMessages.YOUTUBE_HREF)).getAttribute("href");

        LOG.info("Получена контактная информация из хедера");
        return new HeaderContactData(
            whatsapp1, whatsapp2, phone, skype, email,
            whatsapp1Href, whatsapp2Href, phoneHref, skypeHref, emailHref,
            facebookHref, linkedinHref, googlePlusHref, youtubeHref
        );
    }

    public static class FooterContactData {
        private final String address;
        private final String phone1;
        private final String phone2;
        private final String email1;
        private final String email2;

        public FooterContactData(String address, String phone1, String phone2, String email1, String email2) {
            this.address = address;
            this.phone1 = phone1;
            this.phone2 = phone2;
            this.email1 = email1;
            this.email2 = email2;
        }

        public String getAddress() { return address; }
        public String getPhone1() { return phone1; }
        public String getPhone2() { return phone2; }
        public String getEmail1() { return email1; }
        public String getEmail2() { return email2; }
    }

    public static class HeaderContactData {
        private final String whatsapp1;
        private final String whatsapp2;
        private final String phone;
        private final String skype;
        private final String email;
        private final String whatsapp1Href;
        private final String whatsapp2Href;
        private final String phoneHref;
        private final String skypeHref;
        private final String emailHref;
        private final String facebookHref;
        private final String linkedinHref;
        private final String googlePlusHref;
        private final String youtubeHref;

        public HeaderContactData(
            String whatsapp1, String whatsapp2, String phone, String skype, String email,
            String whatsapp1Href, String whatsapp2Href, String phoneHref, String skypeHref, String emailHref,
            String facebookHref, String linkedinHref, String googlePlusHref, String youtubeHref
        ) {
            this.whatsapp1 = whatsapp1;
            this.whatsapp2 = whatsapp2;
            this.phone = phone;
            this.skype = skype;
            this.email = email;
            this.whatsapp1Href = whatsapp1Href;
            this.whatsapp2Href = whatsapp2Href;
            this.phoneHref = phoneHref;
            this.skypeHref = skypeHref;
            this.emailHref = emailHref;
            this.facebookHref = facebookHref;
            this.linkedinHref = linkedinHref;
            this.googlePlusHref = googlePlusHref;
            this.youtubeHref = youtubeHref;
        }

        public String getWhatsapp1() { return whatsapp1; }
        public String getWhatsapp2() { return whatsapp2; }
        public String getPhone() { return phone; }
        public String getSkype() { return skype; }
        public String getEmail() { return email; }
        public String getWhatsapp1Href() { return whatsapp1Href; }
        public String getWhatsapp2Href() { return whatsapp2Href; }
        public String getPhoneHref() { return phoneHref; }
        public String getSkypeHref() { return skypeHref; }
        public String getEmailHref() { return emailHref; }
        public String getFacebookHref() { return facebookHref; }
        public String getLinkedinHref() { return linkedinHref; }
        public String getGooglePlusHref() { return googlePlusHref; }
        public String getYoutubeHref() { return youtubeHref; }
    }
}
