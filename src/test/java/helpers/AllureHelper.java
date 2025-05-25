package helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

public class AllureHelper {
    private static final Logger LOG = LoggerFactory.getLogger(AllureHelper.class);

    public static void attachScreenshotToAllure() {
        try {
            Allure.addAttachment("Скриншот при падении теста",
                    "image/png",
                    new ByteArrayInputStream(Selenide.screenshot(OutputType.BYTES)),
                    "png");
            LOG.info("Скриншот успешно создан");
        } catch (Exception e) {
            LOG.error("Не удалось создать скриншот: {}", e.getMessage());
        }
    }

    public static void attachPageSourceToAllure() {
        try {
            Allure.addAttachment("Исходный код страницы",
                    "text/html",
                    WebDriverRunner.getWebDriver().getPageSource(),
                    "html");
            LOG.info("Исходный код страницы сохранен");
        } catch (Exception e) {
            LOG.error("Не удалось сохранить исходный код страницы: {}", e.getMessage());
        }
    }
}
