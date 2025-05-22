package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "src/test/resources/test.properties";

    static {
        try {
            properties.load(new FileInputStream(CONFIG_FILE));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки конфигурационного файла", e);
        }
    }

    public static String getUsername() {
        return System.getenv("TEST_USERNAME") != null ? 
               System.getenv("TEST_USERNAME") : 
               properties.getProperty("test.username");
    }

    public static String getPassword() {
        return System.getenv("TEST_PASSWORD") != null ? 
               System.getenv("TEST_PASSWORD") : 
               properties.getProperty("test.password");
    }
} 