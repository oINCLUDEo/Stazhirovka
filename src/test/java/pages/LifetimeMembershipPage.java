package pages;

import com.codeborne.selenide.SelenideElement;
import helpers.MainPageMessages;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class LifetimeMembershipPage {
    private static final Logger LOG = LoggerFactory.getLogger(LifetimeMembershipPage.class);

    @FindBy(tagName = "h1")
    private SelenideElement pageTitle;

    public SelenideElement getPageTitle() {
        return pageTitle;
    }

    @Step("Проверка отображения заголовка Lifetime Membership Club")
    public LifetimeMembershipPage checkPageTitle(String expectedTitle) {
        pageTitle.shouldBe(visible);
        LOG.info("Проверка заголовка страницы: {}", expectedTitle);
        return this;
    }
} 