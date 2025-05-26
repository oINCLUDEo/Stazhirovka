package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FramesAndWindowsPage;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;

public class FramesAndWindowsPageTest extends BaseTest {
    private FramesAndWindowsPage framesAndWindowsPage;

    @BeforeMethod
    public void openFramesAndWindowsPage() {
        open(Configuration.baseUrl + "way2auto_jquery/frames-and-windows.php");
        framesAndWindowsPage = page(FramesAndWindowsPage.class);

    }

    @Test
    public void framesAndWindowsPageTest() {
        framesAndWindowsPage.elementsVisibilityCheck()
                .switchToFrame()
                .clickNewTabLink()
                .switchToTab(1)
                .clickNewTabLink()
                .switchToTab(2);
        assertEquals(getWebDriver().getWindowHandles().size(), 3,
                "Должно быть открыто 3 вкладки");
    }
}
