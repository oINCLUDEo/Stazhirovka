package helpers;

import org.openqa.selenium.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class CookieManager {
    private static final String COOKIES_FILE = "cookies.dat";
    private static final Logger LOG = LoggerFactory.getLogger(CookieManager.class);

    public static void saveCookiesToFile(Set<Cookie> cookies) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COOKIES_FILE))) {
            oos.writeObject(new HashSet<>(cookies));
        } catch (IOException e) {
            LOG.error("Ошибка сохранения куки", e);
        }
    }

    public static Set<Cookie> loadCookiesFromFile() {
        Set<Cookie> cookies = new HashSet<>();
        File file = new File(COOKIES_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                cookies = (HashSet<Cookie>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                LOG.error("Ошибка загрузки куки из файла", e);
            }
        } else {
            LOG.warn("Файла {} не существует", COOKIES_FILE);
        }
        return cookies;
    }

    public static void clearCookiesFile() {
        File file = new File(COOKIES_FILE);
        if (file.exists()) {
            file.delete();
            LOG.info("Куки успешно удалены");
        }
    }
}
