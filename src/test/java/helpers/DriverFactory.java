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

public class DriverFactory {
    private static final Logger LOG = LoggerFactory.getLogger(DriverFactory.class);
    private static final String GRID_URL = System.getProperty("gridUrl", "");

    public enum Browser {
        CHROME,
        FIREFOX,
        EDGE
    }

    public static WebDriver createDriver(Browser browser) {
        if (!GRID_URL.isEmpty()) {
            return createRemoteDriver(browser);
        }
        return createLocalDriver(browser);
    }

    private static WebDriver createLocalDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                setCommonOptions(chromeOptions);
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                setCommonOptions(firefoxOptions);
                return new FirefoxDriver(firefoxOptions);
            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                setCommonOptions(edgeOptions);
                return new EdgeDriver(edgeOptions);
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private static WebDriver createRemoteDriver(Browser browser) {
        try {
            URL gridUrl = new URL(GRID_URL);

            switch (browser) {
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    setCommonOptions(chromeOptions);
                    return new RemoteWebDriver(gridUrl, chromeOptions);
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    setCommonOptions(firefoxOptions);
                    return new RemoteWebDriver(gridUrl, firefoxOptions);
                case EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    setCommonOptions(edgeOptions);
                    return new RemoteWebDriver(gridUrl, edgeOptions);
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid Grid URL: " + GRID_URL, e);
        }
    }

    private static void setCommonOptions(Object options) {
        if (options instanceof ChromeOptions opts) {
            opts.addArguments("--start-maximized", "--disable-notifications", "--disable-popup-blocking",
                    "--disable-extensions", "--disable-dev-shm-usage");
        } else if (options instanceof FirefoxOptions opts) {
            opts.addArguments("--start-maximized", "--disable-notifications", "--disable-popup-blocking",
                    "--disable-extensions", "--disable-dev-shm-usage");
        } else if (options instanceof EdgeOptions opts) {
            opts.addArguments("--start-maximized", "--disable-notifications", "--disable-popup-blocking",
                    "--disable-extensions", "--disable-dev-shm-usage");
        }
    }
} 