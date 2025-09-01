package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.testng.annotations.*;
import pages.BankingPage;
import data.BankingPageMessages;
import helpers.GenerateData;
import listeners.TestListener;

import static com.codeborne.selenide.Selenide.*;
import static helpers.AssertHelper.assertEqualsWithMessage;
import static helpers.AssertHelper.assertTrueWithMessage;

@Listeners(TestListener.class)
@Epic("Банковская система")
@Feature("Функционал банковских операций")
public class BankingPageTest extends BaseTest {
    private BankingPage bankingPage;
    private String customerFirstName;
    private String customerLastName;
    private String customerPostCode;
    private String customerFullName;
    private String currency;
    private String depositAmount = "100321";
    private String withdrawAmount;

    @BeforeClass
    public void setupTestData() {
        customerFirstName = GenerateData.generateCustomerFirstName();
        customerLastName = GenerateData.generateCustomerLastName();
        customerPostCode = GenerateData.generatePostCode();
        customerFullName = customerFirstName + " " + customerLastName;
        currency = GenerateData.generateCurrency();
    }

    @BeforeMethod
    public void openBankingPage() {
        open(Configuration.baseUrl + "angularjs-protractor/banking/#/login");
        bankingPage = page(BankingPage.class);
    }

    @Test
    @TmsLink("5.1")
    @Story("Регистрация пользователя в Sample Form")
    @Description("Проверка успешной регистрации пользователя с корректными данными")
    @Severity(SeverityLevel.CRITICAL)
    public void sampleFormRegistrationTest() {
        bankingPage
                .chooseInterface(BankingPageMessages.SAMPLE_FORM_INTERFACE)
                .enterRegisterData(
                        customerFirstName,
                        customerLastName,
                        GenerateData.generateEmail(),
                        GenerateData.generatePassword(),
                        BankingPageMessages.GENDER_MALE)
                .selectHobby(BankingPageMessages.HOBBY_SPORTS);
        String aboutText = "Самое длинное слово из предложенных хобби - " + bankingPage.getLongestHobbyWord();
        bankingPage
                .enterAboutYourself(aboutText)
                .clickRegister();
        assertEqualsWithMessage(BankingPageMessages.SUCCESS_REGISTRATION_MESSAGE, bankingPage.getSuccessMessageText());
    }

    @Test(priority = 1)
    @TmsLink("5.2.1")
    @Story("Добавление покупателя в Bank Manager")
    @Description("Проверка успешного добавления нового клиента банка")
    @Severity(SeverityLevel.CRITICAL)
    public void addCustomerTest() {
        bankingPage
                .chooseInterface(BankingPageMessages.BANK_MANAGER_INTERFACE)
                .clickAddCustomer()
                .enterCustomerData(customerFirstName, customerLastName, customerPostCode)
                .submitAddCustomer()
                .acceptAlert();
    }

    @Test(priority = 2, dependsOnMethods = "addCustomerTest")
    @TmsLink("5.2.2")
    @Story("Открытие аккаунта для клиента")
    @Description("Проверка успешного открытия банковского аккаунта для клиента")
    @Severity(SeverityLevel.CRITICAL)
    public void openAccountTest() {
        bankingPage
                .chooseInterface(BankingPageMessages.BANK_MANAGER_INTERFACE)
                .clickOpenAccount()
                .selectCustomer(customerFullName)
                .selectCurrency(currency)
                .clickProcess()
                .acceptAlert();
    }

    @Test(priority = 3, dependsOnMethods = "addCustomerTest")
    @TmsLink("5.3")
    @Story("Вход клиента в систему")
    @Description("Проверка успешного входа клиента в банковскую систему")
    @Severity(SeverityLevel.CRITICAL)
    public void customerLoginTest() {
        bankingPage
                .chooseInterface(BankingPageMessages.CUSTOMER_LOGIN_INTERFACE)
                .selectCustomerForLogin(customerFullName)
                .clickCustomerLogin();
        String expectedWelcome = BankingPageMessages.WELCOME_MESSAGE_PREFIX +
                customerFullName +
                BankingPageMessages.WELCOME_MESSAGE_SUFFIX;
        String welcomeMessage = bankingPage.getWelcomeMessage();
        assertEqualsWithMessage(expectedWelcome, welcomeMessage, BankingPageMessages.WELCOME_MESSAGE_SHOULD_CONTAIN);
    }

    @Test(priority = 4, dependsOnMethods = {"addCustomerTest", "openAccountTest", "customerLoginTest"})
    @TmsLink("5.3.1")
    @Story("Успешное пополнение счета")
    @Description("Проверка успешного пополнения счета на сумму 100321")
    @Severity(SeverityLevel.CRITICAL)
    public void successfulDepositTest() {
        bankingPage
                .chooseInterface(BankingPageMessages.CUSTOMER_LOGIN_INTERFACE)
                .selectCustomerForLogin(customerFullName)
                .clickCustomerLogin()
                .clickDeposit()
                .enterAmount(depositAmount)
                .submitDeposit();
        assertEqualsWithMessage(BankingPageMessages.DEPOSIT_SUCCESS_MESSAGE, bankingPage.getTransactionMessage());
        // Добавлено явное ожидание из-за бага в сайте, он не успевает отгрузить корректную таблицу транзакций
        sleep(700);
        bankingPage.clickTransactions();
        assertTrueWithMessage(bankingPage.isTransactionPresent(depositAmount, BankingPageMessages.CREDIT_TRANSACTION),
                String.format(BankingPageMessages.TRANSACTION_SHOULD_BE_PRESENT, depositAmount));
    }

    @Test(priority = 5, dependsOnMethods = {"addCustomerTest", "openAccountTest", "customerLoginTest"})
    @TmsLink("5.3.2")
    @Story("Неуспешное пополнение счета")
    @Description("Проверка попытки пополнения счета на сумму 0")
    @Severity(SeverityLevel.NORMAL)
    public void failedDepositTest() {
        bankingPage
                .chooseInterface(BankingPageMessages.CUSTOMER_LOGIN_INTERFACE)
                .selectCustomerForLogin(customerFullName)
                .clickCustomerLogin()
                .clickDeposit()
                .enterAmount(BankingPageMessages.ZERO_AMOUNT)
                .submitDeposit()
                .checkVisibilityMessage()
                .clickTransactions();
        assertTrueWithMessage(!bankingPage.hasAnyTransactions() ||
                        !bankingPage.isTransactionPresent(BankingPageMessages.ZERO_AMOUNT, BankingPageMessages.CREDIT_TRANSACTION),
                String.format(BankingPageMessages.TRANSACTION_SHOULD_NOT_BE_PRESENT, BankingPageMessages.ZERO_AMOUNT));
    }

    @Test(priority = 6, dependsOnMethods = {"addCustomerTest", "openAccountTest", "customerLoginTest", "successfulDepositTest"})
    @TmsLink("5.3.3")
    @Story("Успешное снятие средств")
    @Description("Проверка успешного снятия средств со счета")
    @Severity(SeverityLevel.CRITICAL)
    public void successfulWithdrawTest() {
        bankingPage
                .chooseInterface(BankingPageMessages.CUSTOMER_LOGIN_INTERFACE)
                .selectCustomerForLogin(customerFullName)
                .clickCustomerLogin();
        int balanceAmount = bankingPage.getBalanceAmount();
        withdrawAmount = GenerateData.generateWithdrawAmount(balanceAmount);
        bankingPage
                .clickWithdraw()
                .enterAmount(withdrawAmount)
                .submitWithdraw();
        assertEqualsWithMessage(BankingPageMessages.WITHDRAW_SUCCESS_MESSAGE, bankingPage.getTransactionMessage());
        // Добавлено явное ожидание из-за бага в сайте, он не успевает отгрузить корректную таблицу транзакций
        sleep(700);
        bankingPage.clickTransactions();
        assertTrueWithMessage(bankingPage.isTransactionPresent(withdrawAmount, BankingPageMessages.DEBIT_TRANSACTION),
                String.format(BankingPageMessages.TRANSACTION_SHOULD_BE_PRESENT, withdrawAmount));
    }

    @Test(priority = 7, dependsOnMethods = {"addCustomerTest", "openAccountTest", "customerLoginTest"})
    @TmsLink("5.3.4")
    @Story("Неуспешное снятие средств")
    @Description("Проверка попытки снятия средств больше текущего баланса")
    @Severity(SeverityLevel.NORMAL)
    public void failedWithdrawTest() {
        bankingPage
                .chooseInterface(BankingPageMessages.CUSTOMER_LOGIN_INTERFACE)
                .selectCustomerForLogin(customerFullName)
                .clickCustomerLogin()
                .clickWithdraw()
                .enterAmount(BankingPageMessages.LARGE_AMOUNT)
                .submitWithdraw();
        assertEqualsWithMessage(BankingPageMessages.WITHDRAW_FAILED_MESSAGE, bankingPage.getTransactionMessage());
        // Добавлено явное ожидание из-за бага в сайте, он не успевает отгрузить корректную таблицу транзакций
        sleep(700);
        bankingPage.clickTransactions();
        assertTrueWithMessage(!bankingPage.isTransactionPresent(BankingPageMessages.LARGE_AMOUNT, BankingPageMessages.DEBIT_TRANSACTION),
                String.format(BankingPageMessages.TRANSACTION_SHOULD_NOT_BE_PRESENT, BankingPageMessages.LARGE_AMOUNT));
    }

    @Test(priority = 8, dependsOnMethods = {"addCustomerTest", "openAccountTest", "customerLoginTest", "successfulDepositTest", "successfulWithdrawTest"})
    @TmsLink("5.3.5")
    @Story("Проверка баланса")
    @Description("Проверка соответствия баланса с суммой транзакций")
    @Severity(SeverityLevel.NORMAL)
    public void balanceVerificationTest() {
        bankingPage
                .chooseInterface(BankingPageMessages.CUSTOMER_LOGIN_INTERFACE)
                .selectCustomerForLogin(customerFullName)
                .clickCustomerLogin();
        String balanceText = bankingPage.getBalance();
        // Добавлено явное ожидание из-за бага в сайте, он не успевает отгрузить корректную таблицу транзакций
        sleep(700);
        bankingPage.clickTransactions();
        String calculatedBalance = bankingPage.calculateBalanceFromTransactions();
        assertEqualsWithMessage(balanceText, calculatedBalance,
                "Баланс на главной странице (" + balanceText + ") должен совпадать с расчетным балансом из транзакций (" + calculatedBalance + ")");
    }

    @Test(priority = 9, dependsOnMethods = {"addCustomerTest", "openAccountTest", "customerLoginTest", "successfulDepositTest"})
    @TmsLink("5.3.6")
    @Story("Снятие оставшихся средств")
    @Description("Проверка снятия всех оставшихся средств со счета")
    @Severity(SeverityLevel.NORMAL)
    public void withdrawAllFundsTest() {
        bankingPage
                .chooseInterface(BankingPageMessages.CUSTOMER_LOGIN_INTERFACE)
                .selectCustomerForLogin(customerFullName)
                .clickCustomerLogin();
        int balanceAmount = bankingPage.getBalanceAmount();
        bankingPage
                .clickWithdraw()
                .enterAmount(String.valueOf(balanceAmount))
                .submitWithdraw();
        assertEqualsWithMessage(BankingPageMessages.WITHDRAW_SUCCESS_MESSAGE, bankingPage.getTransactionMessage());
        String finalBalanceText = bankingPage.getBalance();
        assertTrueWithMessage(finalBalanceText.contains("0"), BankingPageMessages.BALANCE_SHOULD_BE_ZERO);
    }

    @Test(priority = 10, dependsOnMethods = {"addCustomerTest", "openAccountTest", "customerLoginTest", "successfulDepositTest"})
    @TmsLink("5.3.7")
    @Story("Очистка истории транзакций")
    @Description("Проверка успешной очистки истории транзакций")
    @Severity(SeverityLevel.NORMAL)
    public void clearTransactionHistoryTest() {
        bankingPage
                .chooseInterface(BankingPageMessages.CUSTOMER_LOGIN_INTERFACE)
                .selectCustomerForLogin(customerFullName)
                .clickCustomerLogin();
        // Добавлено явное ожидание из-за бага в сайте, он не успевает отгрузить корректную таблицу транзакций
        sleep(700);
        bankingPage.clickTransactions();
        int transactionCount = bankingPage.getTransactionCount();
        assertTrueWithMessage(transactionCount > 0, BankingPageMessages.SHOULD_HAVE_TRANSACTIONS);
        bankingPage.clickReset();
        int clearedTransactionCount = bankingPage.getTransactionCount();
        assertTrueWithMessage(clearedTransactionCount == 0, BankingPageMessages.TRANSACTIONS_SHOULD_BE_CLEARED);
        bankingPage.clickBack();
        String balanceText = bankingPage.getBalance();
        assertTrueWithMessage(balanceText.contains("0"), BankingPageMessages.BALANCE_SHOULD_BE_ZERO);
    }

    @Test(priority = 100, dependsOnMethods = {"addCustomerTest"})
    @TmsLink("5.4")
    @Story("Удаление покупателя")
    @Description("Проверка успешного удаления созданного клиента")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteCustomerTest() {
        bankingPage
                .chooseInterface(BankingPageMessages.BANK_MANAGER_INTERFACE)
                .clickCustomers()
                .searchCustomer(customerFirstName);
        assertTrueWithMessage(bankingPage.isCustomerInTable(customerFirstName),
                BankingPageMessages.CUSTOMER_SHOULD_BE_FOUND);
        bankingPage.deleteCustomer().clearCustomerSearch();
        assertTrueWithMessage(!bankingPage.isCustomerInTable(customerFirstName),
                BankingPageMessages.CUSTOMER_SHOULD_BE_DELETED);
    }
}
