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

    /**
     * Генерирует случайное имя для клиента банка
     * @return случайное имя
     */
    public static String generateCustomerFirstName() {
        String firstName = faker.name().firstName();
        LOG.info("Сгенерированное имя клиента: " + firstName);
        return firstName;
    }

    /**
     * Генерирует случайную фамилию для клиента банка
     * @return случайная фамилия
     */
    public static String generateCustomerLastName() {
        String lastName = faker.name().lastName();
        LOG.info("Сгенерированная фамилия клиента: " + lastName);
        return lastName;
    }

    /**
     * Генерирует случайный почтовый индекс
     * @return случайный почтовый индекс
     */
    public static String generatePostCode() {
        String postCode = faker.address().zipCode();
        LOG.info("Сгенерированный почтовый индекс: " + postCode);
        return postCode;
    }

    /**
     * Генерирует случайный email
     * @return случайный email
     */
    public static String generateEmail() {
        String email = faker.internet().emailAddress();
        LOG.info("Сгенерированный email: " + email);
        return email;
    }

    /**
     * Генерирует случайный пароль
     * @return случайный пароль
     */
    public static String generatePassword() {
        String password = faker.internet().password();
        LOG.info("Сгенерированный пароль: " + password);
        return password;
    }

    /**
     * Генерирует случайную сумму для депозита
     * @return случайная сумма от 100 до 10000
     */
    public static String generateDepositAmount() {
        int amount = random.nextInt(9900) + 100;
        LOG.info("Сгенерированная сумма депозита: " + amount);
        return String.valueOf(amount);
    }

    /**
     * Генерирует случайную сумму для снятия средств
     * @param maxBalance максимальный баланс
     * @return случайная сумма от 1 до maxBalance
     */
    public static String generateWithdrawAmount(int maxBalance) {
        int amount = random.nextInt(maxBalance) + 1;
        LOG.info("Сгенерированная сумма снятия: " + amount);
        return String.valueOf(amount);
    }

    /**
     * Генерирует случайную валюту из списка доступных
     * @return случайная валюта
     */
    public static String generateCurrency() {
        String[] currencies = {"Dollar", "Pound", "Rupee"};
        String currency = currencies[random.nextInt(currencies.length)];
        LOG.info("Сгенерированная валюта: " + currency);
        return currency;
    }
}
