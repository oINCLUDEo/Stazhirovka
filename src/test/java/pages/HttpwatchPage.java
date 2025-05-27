package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;

public class HttpwatchPage {
    @FindBy(id = "downloadImg")
    private SelenideElement authenticatedImage;
    @FindBy(id = "displayImage")
    private SelenideElement displayImageButton;

    @Step("Кликаем на кнопку для отображения изображения")
    public HttpwatchPage clickDisplayImageButton() {
        displayImageButton.click();
        return this;
    }

    @Step("Проверка видимости аутентификационного изображения")
    public HttpwatchPage checkVisibilityAuthenticatedImage() {
        authenticatedImage.shouldBe(visible);
        return this;
    }
}
