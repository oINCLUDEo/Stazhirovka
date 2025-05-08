package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.page;

public class MainPageTest extends BaseTest {

    @Test
    @Description("Проверка открытия главной страницы и отображения основных элементов")
    public void mainElementsVisibilityTest() {
        MainPage mainPage = page(MainPage.class);
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
        mainPage.checkCoursesNavigation();
    }

    @Test
    @Description("Проверка футера с контактной информацией")
    public void footContactInfoTest() {
        MainPage mainPage = page(MainPage.class);
        mainPage.checkFooterContactInfo();
    }
} 