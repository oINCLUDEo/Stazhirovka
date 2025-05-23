package helpers;

public class TestConfig {
    public static String getUsername() {
        String username = System.getProperty("username");
        if (username == null) {
            username = System.getenv("USERNAME");
        }
        if (username == null) {
            throw new IllegalStateException("Имя пользователя не указано. Укажите его через -Dusername=... или задайте переменную окружения USERNAME");
        }
        return username;
    }

    public static String getPassword() {
        String password = System.getProperty("password");
        if (password == null) {
            password = System.getenv("PASSWORD");
        }
        if (password == null) {
            throw new IllegalStateException("Пароль не указан. Укажите его через -Dpassword=... или задайте переменную окружения PASSWORD");
        }
        return password;
    }
}