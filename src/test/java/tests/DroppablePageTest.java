package tests;

import com.codeborne.selenide.Configuration;
import helpers.OtherPagesMessages;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DroppablePage;

import static com.codeborne.selenide.Selenide.*;
import static helpers.AssertHelper.assertEqualsWithMessage;

@Epic("Взаимодействие с элементами")
@Feature("Drag & Drop функциональность")
public class DroppablePageTest extends BaseTest {
    private DroppablePage droppablePage;

    @BeforeMethod
    public void openDroppablePage() {
        open(Configuration.baseUrl + "way2auto_jquery/droppable.php");
        droppablePage = page(DroppablePage.class);

    }

    @Test
    @Story("Пользователь перетаскивает элемент в принимающий элемент")
    @Description("Проверка корректности перетаскивания одного элемента к другому")
    @Severity(SeverityLevel.NORMAL)
    public void dragAndDropTest() {
        droppablePage.elementsVisibilityCheck()
                .dragToDroppableBox();
        String actualText = droppablePage.getDroppableText();
        assertEqualsWithMessage(actualText, OtherPagesMessages.TEXT_WHEN_DRAG_N_DROP, "Текст в droppable-элементе");
    }
}
