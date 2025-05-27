package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.function.Supplier;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DroppablePage {
    @FindBy(css = "#example-1-tab-1 .demo-frame")
    private SelenideElement iframe;
    @FindBy(id = "draggable")
    private SelenideElement draggableBox;
    @FindBy(id = "droppable")
    private SelenideElement droppableBox;

    private void withinFrame(Runnable action) {
        switchTo().frame(iframe);
        try {
            action.run();
        } finally {
            switchTo().defaultContent();
        }
    }

    private <T> T withinFrame(Supplier<T> action) {
        switchTo().frame(iframe);
        try {
            return action.get();
        } finally {
            switchTo().defaultContent();
        }
    }

    @Step("Проверка отображения элементов")
    public DroppablePage elementsVisibilityCheck() {
        withinFrame(() -> {
            draggableBox.shouldBe(visible);
            droppableBox.shouldBe(visible);
        });
        return this;
    }

    @Step("Передвижение элемента")
    public DroppablePage dragToDroppableBox() {
        withinFrame(() -> {
            draggableBox.shouldBe(visible);
            droppableBox.shouldBe(visible);
            actions()
                    .clickAndHold(draggableBox)
                    .moveToElement(droppableBox)
                    .release()
                    .perform();
        });
        return this;
    }

    @Step("Получение текста droppableBox")
    public String getDroppableText() {
        return withinFrame(() -> droppableBox.text());
    }
}
