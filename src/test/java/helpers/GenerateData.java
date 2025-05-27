package helpers;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class GenerateData {
    private static final Random random = new Random();
    private static final Faker faker = new Faker();
    private static final Logger LOG = LoggerFactory.getLogger(GenerateData.class);

    /**
     * Генерирует случайный текст для алерта
     * @return строка с текстом длиной от 5 до 15 символов
     */
    public static String generateAlertText() {
        int length = random.nextInt(6) + 5; // от 5 до 15 символов
        String alertText = faker.lorem().characters(length);
        LOG.info("Сгенерированный текст для алерта: " + alertText);
        return alertText;
    }

    /**
     * Генерирует случайное описание имени пользователя.
     * Использует Lorem Ipsum из Faker и логирует результат.
     *
     * @return строка с описанием длиной от 3 до 10 символов
     */
    public static String generateUsernameDescription() {
        int length = random.nextInt(8) + 3;
        String usernameDescription = faker.lorem().characters(length);
        LOG.info("Сгенерированное описание: " + usernameDescription);
        return usernameDescription;
    }

    /**
     * Генерирует невалидный пароль, отличный от "password".
     * Длина пароля от 3 до 50 символов.
     *
     * @return невалидный пароль
     */
    public static String generateWrongPassword() {
        int length = random.nextInt(48) + 3;
        String wrongPassword;
        do {
            wrongPassword = faker.lorem().characters(length);
        } while (wrongPassword.equals("password"));
        LOG.info("Сгенерированный невалидный пароль: " + wrongPassword);
        return wrongPassword;
    }

    /**
     * Генерирует невалидное имя пользователя, отличное от "angular".
     * Длина имени от 3 до 100 символов.
     *
     * @return невалидное имя пользователя
     */
    public static String generateWrongUsername() {
        int length = random.nextInt(98) + 3;
        String wrongUsername;
        do {
            wrongUsername = faker.lorem().characters(length);
        } while (wrongUsername.equals("angular"));
        LOG.info("Сгенерированное невалидное имя пользователя: " + wrongUsername);
        return wrongUsername;
    }
}
