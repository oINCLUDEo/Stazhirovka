package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AlertPage;

import static com.codeborne.selenide.Selenide.*;
import static helpers.GenerateData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Epic("Работа с pop-up сайта")
@Feature("Взаимодействие с Alert")
public class AlertPageTest extends BaseTest {
    private AlertPage alertPage;

    @BeforeMethod
    public void openAlertPage() {
        open(Configuration.baseUrl + "way2auto_jquery/alert.php");
        alertPage = page(AlertPage.class);
    }

    @Test
    @Story("Пользователь вызывает alert, вводит текст в prompt-диалог и получает его на странице")
    @Description("Проверка, что введённый текст в alert prompt корректно отображается после подтверждения")
    @Severity(SeverityLevel.CRITICAL)
    public void alertPageTest() {
        String expectedText = generateAlertText();
        alertPage.switchToInputAlert()
                .switchToIframe()
                .clickToAlertButton()
                .inputToAlert(expectedText);
        String actualText = alertPage.getTextFieldText();
        assertThat(actualText)
                .as("Проверка текста после ввода в alert")
                .contains(expectedText);
    }
}
