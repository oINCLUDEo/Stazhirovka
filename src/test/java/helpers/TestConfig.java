package helpers;

public class TestConfig {
    public static String getWay2AutomationUsername() {
        return getCredential("username", "USERNAME", "Имя пользователя для Way2Automation");
    }

    public static String getWay2AutomationPassword() {
        return getCredential("password", "PASSWORD", "Пароль для Way2Automation");
    }

    public static String getSqlExUsername() {
        return getCredential("sqlExUsername", "SQLEX_USERNAME", "Имя пользователя для SQL-Ex");
    }

    public static String getSqlExPassword() {
        return getCredential("sqlExPassword", "SQLEX_PASSWORD", "Пароль для SQL-Ex");
    }

    public static String getHttpWatchUsername() {
        return getCredential("httpwatchUsername", "HTTPWATCH_USERNAME", "Имя пользователя для HttpWatch");
    }

    public static String getHttpWatchPassword() {
        return getCredential("httpwatchPassword", "HTTPWATCH_PASSWORD", "Пароль для HttpWatch");
    }

    private static String getCredential(String systemPropertyKey, String envVarKey, String description) {
        String value = System.getProperty(systemPropertyKey);
        if (value == null) {
            value = System.getenv(envVarKey);
        }
        if (value == null) {
            throw new IllegalStateException(
                    String.format("%s не указано. Укажите через -D%s=... или задайте переменную окружения %s",
                            description, systemPropertyKey, envVarKey));
        }
        return value;
    }
}