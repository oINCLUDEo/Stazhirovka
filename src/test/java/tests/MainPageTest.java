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
} 