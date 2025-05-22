package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.LifetimeMembershipPage;

import static com.codeborne.selenide.Selenide.*;

public class MainPageTest extends BaseTest {
    @BeforeMethod
    public void openMainPage() {
        open(Configuration.baseUrl);
        MainPage mainPage = new MainPage();
        mainPage.disablePopup();
    }

    @Test
    @Description("Проверка открытия главной страницы и отображения основных элементов")
    public void mainElementsVisibilityTest() {
        MainPage mainPage = page(MainPage.class);
        sleep(5000);
        mainPage.checkVisibilityHeader()
                .checkVisibilityNav()
                .checkVisibilityRegisterButton()
                .checkVisibilityCoursesSection()
                .checkVisibilityFooter();
    }

    @Test
    @Description("Проверка хедера с контактной информацией")
    public void headerContactInfoTest() {
        MainPage mainPage = page(MainPage.class);
        mainPage.checkHeaderContactInfo();
    }

    @Test
    @Description("Проверка навигации по слайдам в блоке 'Most Popular Software Testing Courses'")
    public void coursesNavigationTest() {
        MainPage mainPage = page(MainPage.class);
        mainPage.checkVisibilityCoursesNavigation()
                .activateSwiper()
                .checkSwitchingCourses();
    }

    @Test
    @Description("Проверка футера с контактной информацией")
    public void footContactInfoTest() {
        MainPage mainPage = page(MainPage.class);
        mainPage.checkFooterContactInfo();
    }

    @Test
    @Description("Проверка навигационного меню при прокрутке")
    public void navMenuVisibilityWhenScrollTest() {
        MainPage mainPage = page(MainPage.class);
        mainPage.checkNavigationWhenScrollDown();
    }

    @Test
    @Description("Проверка перехода на страницу Lifetime Membership через меню All courses")
    public void navigationMenuRedirectTest() {
        MainPage mainPage = page(MainPage.class);
        LifetimeMembershipPage lifetimeMembershipPage = page(LifetimeMembershipPage.class);
        mainPage.navigateToLifetimeMembership();
        lifetimeMembershipPage.checkPageTitle();
    }
} 