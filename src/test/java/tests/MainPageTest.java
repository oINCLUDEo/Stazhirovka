package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.LifetimeMembershipPage;
import helpers.MainPageMessages;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.AssertHelper.assertEqualsWithMessage;
import static org.testng.Assert.assertEquals;

public class MainPageTest extends BaseTest {
    private MainPage mainPage;

    @BeforeMethod
    public void openMainPage() {
        open(Configuration.baseUrl);
        mainPage = page(MainPage.class);
        mainPage.disablePopup();
    }

    @Test
    @Description("Проверка открытия главной страницы и отображения основных элементов")
    public void mainElementsVisibilityTest() {
        mainPage.checkVisibilityHeader()
                .checkVisibilityNav()
                .checkVisibilityRegisterButton()
                .checkVisibilityCoursesSection()
                .checkVisibilityFooter();
    }

    @Test
    @Description("Проверка хедера с контактной информацией")
    public void headerContactInfoTest() {
        mainPage.checkVisibilityHeader();
        MainPage.HeaderContactData headerData = mainPage.getHeaderContactData();
        assertEqualsWithMessage(headerData.getWhatsapp1(), MainPageMessages.HEADER_WHATSAPP_1, "WhatsApp 1");
        assertEqualsWithMessage(headerData.getWhatsapp2(), MainPageMessages.HEADER_WHATSAPP_2, "WhatsApp 2");
        assertEqualsWithMessage(headerData.getPhone(), MainPageMessages.HEADER_PHONE, "Тел.");
        assertEqualsWithMessage(headerData.getSkype(), MainPageMessages.HEADER_SKYPE, "Skype");
        assertEqualsWithMessage(headerData.getEmail(), MainPageMessages.HEADER_EMAIL, "Email");
        assertEqualsWithMessage(headerData.getWhatsapp1Href(), MainPageMessages.HEADER_WHATSAPP_1_HREF, "Ссылка WhatsApp 1");
        assertEqualsWithMessage(headerData.getWhatsapp2Href(), MainPageMessages.HEADER_WHATSAPP_2_HREF, "Ссылка WhatsApp 2");
        assertEqualsWithMessage(headerData.getPhoneHref(), MainPageMessages.HEADER_PHONE_HREF, "Ссылка телефона");
        assertEqualsWithMessage(headerData.getSkypeHref(), MainPageMessages.HEADER_SKYPE_HREF, "Ссылка Skype");
        assertEqualsWithMessage(headerData.getEmailHref(), MainPageMessages.HEADER_EMAIL_HREF, "Ссылка Email");
        assertEqualsWithMessage(headerData.getFacebookHref(), MainPageMessages.FACEBOOK_HREF, "Ссылка Facebook");
        assertEqualsWithMessage(headerData.getLinkedinHref(), MainPageMessages.LINKEDIN_HREF, "Ссылка LinkedIn");
        assertEqualsWithMessage(headerData.getGooglePlusHref(), MainPageMessages.GOOGLE_PLUS_HREF, "Ссылка Google Plus");
        assertEqualsWithMessage(headerData.getYoutubeHref(), MainPageMessages.YOUTUBE_HREF, "Ссылка YouTube");
    }

    @Test
    @Description("Проверка навигации по слайдам в блоке 'Most Popular Software Testing Courses'")
    public void coursesNavigationTest() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(7));
        mainPage.checkVisibilityCoursesNavigation()
                .activateSwiper();
        String nextSlideIndexBefore = mainPage.getNextSlideIndex();
        mainPage.clickNextSlide();
        wait.until(driver -> mainPage.getActiveSlideIndex().equals(nextSlideIndexBefore));
        assertEquals(mainPage.getActiveSlideIndex(), nextSlideIndexBefore, "Слайд не переключился вперед как ожидалось");
        String prevSlideIndexBefore = mainPage.getPrevSlideIndex();
        mainPage.clickPrevSlide();
        wait.until(driver -> mainPage.getActiveSlideIndex().equals(prevSlideIndexBefore));
        assertEquals(mainPage.getActiveSlideIndex(), prevSlideIndexBefore, "Слайд не переключился назад как ожидалось");
    }

    @Test
    @Description("Проверка футера с контактной информацией")
    public void footContactInfoTest() {
        mainPage.checkVisibilityFooter();
        MainPage.FooterContactData footerData = mainPage.getFooterContactData();
        assertEqualsWithMessage(MainPageMessages.WAY2AUTOMATION_ADDRESS, footerData.getAddress(), "Адрес");
        assertEqualsWithMessage(MainPageMessages.FOOTER_PHONE_1, footerData.getPhone1(), "Тел. 1");
        assertEqualsWithMessage(MainPageMessages.FOOTER_PHONE_2, footerData.getPhone2(), "Тел. 2");
        assertEqualsWithMessage(MainPageMessages.FOOTER_EMAIL_1, footerData.getEmail1(), "Почта 1");
        assertEqualsWithMessage(MainPageMessages.FOOTER_EMAIL_2, footerData.getEmail2(), "Почта 2");
    }

    @Test
    @Description("Проверка навигационного меню при прокрутке")
    public void navMenuVisibilityWhenScrollTest() {
        mainPage.checkNavigationWhenScrollDown();
    }

    @Test
    @Description("Проверка перехода на страницу Lifetime Membership через меню All courses")
    public void navigationMenuRedirectTest() {
        LifetimeMembershipPage lifetimeMembershipPage = page(LifetimeMembershipPage.class);
        mainPage.navigateToLifetimeMembership();
        lifetimeMembershipPage.getPageTitle().shouldHave(text(MainPageMessages.LIFETIME_MEMBERSHIP_TITLE));

    }
} 