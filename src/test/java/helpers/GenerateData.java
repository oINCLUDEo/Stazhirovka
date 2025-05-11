package helpers;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class GenerateData {
    private static final Random random = new Random();
    private static final Faker faker = new Faker();
    private static final Logger LOG = LoggerFactory.getLogger(GenerateData.class);


    public static String generateUsernameDescription() {
        int length = random.nextInt(8) + 3;
        String usernameDescription = faker.lorem().characters(length);
        LOG.info("Сгенерированное описание: " + usernameDescription);
        return usernameDescription;
    }

    public static String generateWrongPassword() {
        int length = random.nextInt(48) + 3;
        String wrongPassword;
        do {
            wrongPassword = faker.lorem().characters(length);
        } while (wrongPassword.equals("password"));
        LOG.info("Сгенерированный невалидный пароль: " + wrongPassword);
        return wrongPassword;
    }

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
