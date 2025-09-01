package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static data.BankingPageMessages.*;

public class BankingPage {
    // Основные элементы навигации
    @FindBy(xpath = "//a[contains(text(), '" + SAMPLE_FORM_INTERFACE + "')]")
    private SelenideElement sampleFormButton;
    @FindBy(xpath = "//button[contains(text(), '" + CUSTOMER_LOGIN_INTERFACE + "')]")
    private SelenideElement customerLoginButton;
    @FindBy(xpath = "//button[contains(text(), '" + BANK_MANAGER_INTERFACE + "')]")
    private SelenideElement bankManagerLoginButton;
    // Элементы формы регистрации
    @FindBy(id = "firstName")
    private SelenideElement firstNameInput;
    @FindBy(id = "lastName")
    private SelenideElement lastNameInput;
    @FindBy(id = "email")
    private SelenideElement emailInput;
    @FindBy(id = "password")
    private SelenideElement passwordInput;
    @FindBy(id = "gender")
    private SelenideElement genderSelect;
    @FindBy(id = "about")
    private SelenideElement aboutInput;
    @FindBy(name = "hobbies")
    private ElementsCollection hobbiesInput;
    @FindBy(xpath = "//button[contains(text(), '" + REGISTER_BUTTON + "')]")
    private SelenideElement registerButton;
    @FindBy(id = "successMessage")
    private SelenideElement successMessage;
    @FindBy(id = "errorMessage")
    private SelenideElement errorMessage;
    // Элементы Bank Manager
    @FindBy(xpath = "//button[contains(text(), '" + ADD_CUSTOMER_BUTTON + "')]")
    private SelenideElement addCustomerButton;
    @FindBy(xpath = "//button[contains(text(), '" + OPEN_ACCOUNT_BUTTON + "')]")
    private SelenideElement openAccountButton;
    @FindBy(xpath = "//button[contains(text(), '" + CUSTOMERS_BUTTON + "')]")
    private SelenideElement customersButton;
    // Элементы добавления клиента
    @FindBy(xpath = "//input[@placeholder='First Name']")
    private SelenideElement addCustomerFirstNameInput;
    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private SelenideElement addCustomerLastNameInput;
    @FindBy(xpath = "//input[@placeholder='Post Code']")
    private SelenideElement addCustomerPostCodeInput;
    @FindBy(xpath = "//button[contains(text(), '" + ADD_CUSTOMER_BUTTON + "') and @type='submit']")
    private SelenideElement submitAddCustomerButton;
    // Элементы открытия аккаунта
    @FindBy(id = "userSelect")
    private SelenideElement customerSelect;
    @FindBy(id = "currency")
    private SelenideElement currencySelect;
    @FindBy(xpath = "//button[contains(text(), '" + PROCESS_BUTTON + "')]")
    private SelenideElement processButton;
    // Элементы Customer Login
    @FindBy(id = "userSelect")
    private SelenideElement loginCustomerSelect;
    @FindBy(xpath = "//button[contains(text(), '" + LOGIN_BUTTON + "')]")
    private SelenideElement customerLoginSubmitButton;
    // Элементы банковских операций
    @FindBy(xpath = "//button[contains(text(), '" + DEPOSIT_BUTTON + "')]")
    private SelenideElement depositButton;
    @FindBy(xpath = "//button[contains(text(), '" + WITHDRAW_BUTTON_MAIN + "')]")
    private SelenideElement withdrawButton;
    @FindBy(xpath = "//button[contains(text(), '" + TRANSACTIONS_BUTTON + "')]")
    private SelenideElement transactionsButton;
    @FindBy(xpath = "//input[@placeholder='amount']")
    private SelenideElement amountInput;
    @FindBy(xpath = "//button[contains(text(), '" + DEPOSIT_BUTTON + "') and @type='submit']")
    private SelenideElement submitDepositButton;
    @FindBy(xpath = "//button[contains(text(), '" + WITHDRAW_BUTTON_ACCEPT + "') and @type='submit']")
    private SelenideElement submitWithdrawButton;
    @FindBy(css = "span[ng-show='message']")
    private SelenideElement transactionMessage;
    @FindBy(css = "tbody tr")
    private ElementsCollection transactionRows;
    @FindBy(css = "div[ng-hide='noAccount'] strong:nth-child(2)")
    private SelenideElement balanceElement;
    @FindBy(xpath = "//button[contains(text(), '" + RESET_BUTTON + "')]")
    private SelenideElement resetButton;
    @FindBy(xpath = "//button[contains(text(), '" + BACK_BUTTON + "')]")
    private SelenideElement backButton;
    @FindBy(xpath = "//strong[contains(text(), '" + WELCOME_MESSAGE_PREFIX + "')]")
    private SelenideElement welcomeMessage;
    // Элементы управления клиентами
    @FindBy(xpath = "//input[@placeholder='Search Customer']")
    private SelenideElement searchCustomerInput;
    @FindBy(xpath = "//button[contains(text(), '" + DELETE_BUTTON + "')]")
    private SelenideElement deleteCustomerButton;
    @FindBy(xpath = "//tbody//tr")
    private ElementsCollection customerRows;

    @Step("Нажатие на кнопку интерфейса: {0}")
    public BankingPage chooseInterface(String interfaceName) {
        switch (interfaceName) {
            case SAMPLE_FORM_INTERFACE:
                sampleFormButton.click();
                break;
            case CUSTOMER_LOGIN_INTERFACE:
                customerLoginButton.click();
                break;
            case BANK_MANAGER_INTERFACE:
                bankManagerLoginButton.click();
                break;
            default:
                throw new IllegalArgumentException("Неизвестный интерфейс: " + interfaceName);
        }
        return this;
    }

    @Step("Ввод данных для регистрации: {0}, {1}, {2}, {3}, {4}")
    public BankingPage enterRegisterData(String firstName, String lastName, String email, String password, String gender) {
        firstNameInput.setValue(firstName);
        lastNameInput.setValue(lastName);
        emailInput.setValue(email);
        passwordInput.setValue(password);
        genderSelect.selectOption(gender);
        return this;
    }

    @Step("Выбор хобби: {0}")
    public BankingPage selectHobby(String hobbyName) {
        hobbiesInput.findBy(value(hobbyName)).click();
        return this;
    }

    @Step("Ввод текста о себе: {0}")
    public BankingPage enterAboutYourself(String text) {
        aboutInput.setValue(text);
        return this;
    }

    @Step("Нажатие кнопки Register")
    public BankingPage clickRegister() {
        registerButton.shouldBe(visible).click();
        return this;
    }

    @Step("Получение сообщения об успешной регистрации")
    public String getSuccessMessageText() {
        return successMessage.shouldBe(visible).getText();
    }

    @Step("Нажатие на кнопку Add Customer")
    public BankingPage clickAddCustomer() {
        addCustomerButton.shouldBe(visible).click();
        return this;
    }

    @Step("Заполнение данных клиента: {0}, {1}, {2}")
    public BankingPage enterCustomerData(String firstName, String lastName, String postCode) {
        addCustomerFirstNameInput.setValue(firstName);
        addCustomerLastNameInput.setValue(lastName);
        addCustomerPostCodeInput.setValue(postCode);
        return this;
    }

    @Step("Подтверждение добавления клиента")
    public BankingPage submitAddCustomer() {
        submitAddCustomerButton.shouldBe(visible, Duration.ofSeconds(3)).click();
        return this;
    }

    @Step("Нажатие на кнопку Open Account")
    public BankingPage clickOpenAccount() {
        openAccountButton.shouldBe(visible).click();
        return this;
    }

    @Step("Выбор клиента: {0}")
    public BankingPage selectCustomer(String customerName) {
        customerSelect.selectOption(customerName);
        return this;
    }

    @Step("Выбор валюты: {0}")
    public BankingPage selectCurrency(String currency) {
        currencySelect.selectOption(currency);
        return this;
    }

    @Step("Нажатие кнопки Process")
    public BankingPage clickProcess() {
        processButton.shouldBe(visible).click();
        return this;
    }

    @Step("Выбор клиента для входа: {0}")
    public BankingPage selectCustomerForLogin(String customerName) {
        loginCustomerSelect.selectOption(customerName);
        return this;
    }

    @Step("Нажатие кнопки Login для клиента")
    public BankingPage clickCustomerLogin() {
        customerLoginSubmitButton.shouldBe(visible).click();
        return this;
    }

    @Step("Получение приветственного сообщения")
    public String getWelcomeMessage() {
        return welcomeMessage.shouldBe(visible).getText();
    }

    @Step("Нажатие кнопки Deposit")
    public BankingPage clickDeposit() {
        depositButton.shouldBe(visible, Duration.ofSeconds(3)).click();
        return this;
    }

    @Step("Ввод суммы: {0}")
    public BankingPage enterAmount(String amount) {
        amountInput.setValue(amount);
        return this;
    }

    @Step("Подтверждение депозита")
    public BankingPage submitDeposit() {
        submitDepositButton.shouldBe(visible, Duration.ofSeconds(3)).click();
        return this;
    }

    @Step("Подтверждение снятия средств")
    public BankingPage submitWithdraw() {
        submitWithdrawButton.shouldBe(visible, Duration.ofSeconds(3)).click();
        return this;
    }

    @Step("Получение сообщения о транзакции")
    public String getTransactionMessage() {
        return transactionMessage.shouldBe(visible).getText();
    }

    @Step("Проверка на отсутствие сообщения об успешной транзакции")
    public BankingPage checkVisibilityMessage() {
        transactionMessage.shouldHave(cssClass("ng-hide"));
        return this;
    }

    @Step("Получение баланса")
    public String getBalance() {
        return balanceElement.shouldBe(visible).getText();
    }

    @Step("Получение текущего баланса как числа")
    public int getBalanceAmount() {
        String balanceText = getBalance();
        return Integer.parseInt(balanceText.replaceAll("[^0-9]", ""));
    }

    @Step("Нажатие кнопки Withdraw")
    public BankingPage clickWithdraw() {
        withdrawButton.shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие кнопки Transactions")
    public BankingPage clickTransactions() {
        transactionsButton.shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие кнопки Reset")
    public BankingPage clickReset() {
        resetButton.shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие кнопки Back")
    public BankingPage clickBack() {
        backButton.shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие кнопки Customers")
    public BankingPage clickCustomers() {
        customersButton.shouldBe(visible).click();
        return this;
    }

    @Step("Поиск клиента: {0}")
    public BankingPage searchCustomer(String firstName) {
        searchCustomerInput.setValue(firstName);
        return this;
    }

    @Step("Удаление клиента")
    public BankingPage deleteCustomer() {
        deleteCustomerButton.shouldBe(visible).click();
        return this;
    }

    @Step("Очистка поиска клиентов")
    public BankingPage clearCustomerSearch() {
        searchCustomerInput.clear();
        return this;
    }

    @Step("Проверка наличия клиента в таблице")
    public boolean isCustomerInTable(String firstName) {
        for (SelenideElement row : customerRows) {
            if (row.getText().contains(firstName)) {
                return true;
            }
        }
        return false;
    }

    @Step("Проверка наличия транзакции с суммой: {0} и типом: {1}")
    public boolean isTransactionPresent(String exactAmount, String transactionType) {
        return hasAnyTransactions() &&
                transactionRows
                        .asFixedIterable()
                        .stream()
                        .anyMatch(row -> {
                            String rowText = row.getText();
                            return hasExactAmount(rowText, exactAmount) &&
                                    rowText.contains(transactionType);
                        });
    }

    @Step("Проверка точного совпадения суммы: {1} в тексте")
    private boolean hasExactAmount(String text, String exactAmount) {
        return text.matches(".*\\b" + exactAmount + "\\b.*");
    }

    @Step("Проверка наличия любых транзакций")
    public boolean hasAnyTransactions() {
        try {
            transactionRows.shouldBe(CollectionCondition.sizeGreaterThan(0));
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    @Step("Получение количества транзакций")
    public int getTransactionCount() {
        return transactionRows.size();
    }

    @Step("Подсчет баланса из транзакций")
    public String calculateBalanceFromTransactions() {
        if (transactionRows.isEmpty()) {
            return "0";
        }
        int totalBalance = 0;

        for (SelenideElement row : transactionRows) {
            ElementsCollection columns = row.$$("td");
            if (columns.size() >= 3) {
                String amountText = columns.get(1).getText().trim();
                String typeText = columns.get(2).getText().trim();
                int amount = Integer.parseInt(amountText.replaceAll("[^0-9]", ""));
                if (typeText.equalsIgnoreCase("Credit")) {
                    totalBalance += amount;
                } else if (typeText.equalsIgnoreCase("Debit")) {
                    totalBalance -= amount;
                }
            }
        }
        return String.valueOf(totalBalance);
    }

    @Step("Получение самого длинного слова из хобби")
    public String getLongestHobbyWord() {
        String longestWord = "";
        for (SelenideElement hobby : hobbiesInput) {
            String hobbyText = hobby.getAttribute("value");
            if (hobbyText != null && hobbyText.length() > longestWord.length()) {
                longestWord = hobbyText;
            }
        }
        return longestWord;
    }

    @Step("Подтверждение алерта")
    public BankingPage acceptAlert() {
        Alert alert = WebDriverRunner.getWebDriver().switchTo().alert();
        alert.accept();
        return this;
    }
}
