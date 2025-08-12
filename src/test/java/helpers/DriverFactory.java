package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Map;

public class DriverFactory {
    private static final Logger LOG = LoggerFactory.getLogger(DriverFactory.class);
    private static final String GRID_URL = System.getProperty("gridUrl", "");
    private static final String SELENOID_URL = System.getenv("SELENOID_URL");

    public enum Browser {
        CHROME,
        FIREFOX,
        EDGE
    }

    public static WebDriver createDriver(Browser browser) {
        // Проверяем наличие Selenoid URL или Grid URL
        String remoteUrl = SELENOID_URL != null && !SELENOID_URL.isEmpty() ? SELENOID_URL : GRID_URL;
        
        if (!remoteUrl.isEmpty()) {
            LOG.info("Используем удаленный WebDriver с URL: {}", remoteUrl);
            return createRemoteDriver(browser, remoteUrl);
        }
        
        LOG.info("Используем локальный WebDriver");
        return createLocalDriver(browser);
    }

    private static WebDriver createLocalDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                setCommonOptions(chromeOptions);
                // Добавляем опции для работы в Docker без GUI
                chromeOptions.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                setCommonOptions(firefoxOptions);
                // Добавляем опции для работы в Docker без GUI
                firefoxOptions.addArguments("--headless");
                return new FirefoxDriver(firefoxOptions);
            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                setCommonOptions(edgeOptions);
                // Добавляем опции для работы в Docker без GUI
                edgeOptions.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
                return new EdgeDriver(edgeOptions);
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private static WebDriver createRemoteDriver(Browser browser, String remoteUrl) {
        try {
            URL gridUrl = new URL(remoteUrl);

            switch (browser) {
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    // Упрощенные опции для стабильности
                    chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
                    chromeOptions.setCapability("browserVersion", "latest");
                    chromeOptions.setCapability("selenoid:options", Map.of(
                        "enableVNC", false,
                        "enableVideo", false
                    ));
                    LOG.info("Создание RemoteWebDriver для Chrome с упрощенными capabilities");
                    return new RemoteWebDriver(gridUrl, chromeOptions);

                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setCapability("browserVersion", "latest");
                    firefoxOptions.setCapability("selenoid:options", Map.of(
                        "enableVNC", false,
                        "enableVideo", false
                    ));
                    LOG.info("Создание RemoteWebDriver для Firefox с упрощенными capabilities");
                    return new RemoteWebDriver(gridUrl, firefoxOptions);

                case EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setCapability("browserVersion", "latest");
                    edgeOptions.setCapability("selenoid:options", Map.of(
                        "enableVNC", false,
                        "enableVideo", false
                    ));
                    LOG.info("Создание RemoteWebDriver для Edge с упрощенными capabilities");
                    return new RemoteWebDriver(gridUrl, edgeOptions);

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        } catch (Exception e) {
            LOG.error("Ошибка при создании RemoteWebDriver: {}", e.getMessage(), e);
            throw new RuntimeException("Invalid Grid URL: " + remoteUrl, e);
        }
    }

    private static void setCommonOptions(Object options) {
        if (options instanceof ChromeOptions opts) {
            opts.addArguments("--start-maximized", "--disable-notifications", "--disable-popup-blocking",
                    "--disable-extensions", "--disable-dev-shm-usage", "--no-sandbox",
                    "--disable-gpu");
        } else if (options instanceof FirefoxOptions opts) {
            opts.addArguments("--start-maximized", "--disable-notifications", "--disable-popup-blocking",
                    "--disable-extensions", "--disable-dev-shm-usage");
        } else if (options instanceof EdgeOptions opts) {
            opts.addArguments("--start-maximized", "--disable-notifications", "--disable-popup-blocking",
                    "--disable-extensions", "--disable-dev-shm-usage");
        }
    }
}
