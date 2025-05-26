package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;

public class FramesAndWindowsPage {
    @FindBy(css = "#example-1-tab-1 .demo-frame")
    private SelenideElement iframe;
    @FindBy(xpath = "//a[contains(text(), 'New Browser Tab')]")
    private SelenideElement newTabLink;

    @Step("Проверка отображения элементов")
    public FramesAndWindowsPage elementsVisibilityCheck() {
        switchTo().frame(iframe);
        newTabLink.shouldBe(visible);
        switchTo().defaultContent();
        return this;
    }

    @Step("Нажатие на кнопку для создания новой вкладки")
    public FramesAndWindowsPage clickNewTabLink() {
        newTabLink.click();
        return this;
    }

    @Step("Переключение на новую вкладку (индекс {0})")
    public FramesAndWindowsPage switchToTab(int tabIndex) {
        switchTo().window(tabIndex);
        return this;
    }

    @Step("Переход в iframe")
    public FramesAndWindowsPage switchToFrame() {
        switchTo().frame(iframe);
        return this;
    }
}
