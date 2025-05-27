package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static data.OtherPagesMessages.DEMONSTRATE_INPUT_BOX;
import static data.OtherPagesMessages.INPUT_ALERT_BUTTON;

public class AlertPage {
    @FindBy(css = "#example-1-tab-2 .demo-frame")
    private SelenideElement iframe;
    @FindBy(xpath = "//a[contains(text(), '" + INPUT_ALERT_BUTTON + "')]")
    private SelenideElement inputAlertTab;
    @FindBy(xpath = "//button[contains(text(), '" + DEMONSTRATE_INPUT_BOX +"')]")
    private SelenideElement alertButton;
    @FindBy(id = "demo")
    private SelenideElement textField;

    @Step("Переходим на Input Alert")
    public AlertPage switchToInputAlert() {
        inputAlertTab.shouldBe(visible).click();
        return this;
    }

    @Step("Переход на iframe")
    public AlertPage switchToIframe() {
        switchTo().frame(iframe);
        return this;
    }

    @Step("Отобразить Alert нажав на кнопку")
    public AlertPage clickToAlertButton() {
        alertButton.shouldBe(visible).click();
        return this;
    }

    @Step("Вводим сообщение {0} в alert")
    public AlertPage inputToAlert(String alertText) {
        switchTo().alert().sendKeys(alertText);
        switchTo().alert().accept();
        return this;
    }

    @Step("Получаем текст из текстового поля")
    public String getTextFieldText() {
        return textField.shouldBe(visible).text();
    }
}
