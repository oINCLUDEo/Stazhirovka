package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FramesAndWindowsPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;

@Epic("Работа с окнами и фреймами")
@Feature("Переход между вкладками и фреймами")
public class FramesAndWindowsPageTest extends BaseTest {
    private FramesAndWindowsPage framesAndWindowsPage;

    @BeforeMethod
    public void openFramesAndWindowsPage() {
        open(Configuration.baseUrl + "way2auto_jquery/frames-and-windows.php");
        framesAndWindowsPage = page(FramesAndWindowsPage.class);
    }

    @Test
    @Story("Пользователь открывает новые вкладки и переключается между ними")
    @Description("Проверка, что при кликах по ссылкам внутри iframe открываются новые вкладки и общее количество вкладок равно 3")
    @Severity(SeverityLevel.NORMAL)
    public void framesAndWindowsPageTest() {
        framesAndWindowsPage.elementsVisibilityCheck()
                .switchToFrame()
                .clickNewTabLink()
                .switchToTab(1)
                .clickNewTabLink()
                .switchToTab(2);
        assertEquals(getWebDriver().getWindowHandles().size(), 3, "Должно быть открыто 3 вкладки");
    }
}
