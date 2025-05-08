package pages;

import com.codeborne.selenide.SelenideElement;
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

    @Step("Проверка заголовка страницы Lifetime Membership")
    public LifetimeMembershipPage checkPageTitle() {
        pageTitle.shouldBe(visible)
                .shouldHave(text("LIFETIME MEMBERSHIP CLUB"));
        LOG.info("Проверка заголовка страницы Lifetime Membership");
        return this;
    }
} 