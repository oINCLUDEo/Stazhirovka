package data;

public class BankingPageMessages {
    // Регистрация
    public static final String SUCCESS_REGISTRATION_MESSAGE = "User registered successfully!";

    // Банковские операции
    public static final String DEPOSIT_SUCCESS_MESSAGE = "Deposit Successful";
    public static final String WITHDRAW_SUCCESS_MESSAGE = "Transaction successful";
    public static final String WITHDRAW_FAILED_MESSAGE = "Transaction Failed. You can not withdraw amount more than the balance.";

    // Приветствия
    public static final String WELCOME_MESSAGE_PREFIX = "Welcome ";
    public static final String WELCOME_MESSAGE_SUFFIX = " !!";

    // Интерфейсы
    public static final String SAMPLE_FORM_INTERFACE = "Sample Form";
    public static final String CUSTOMER_LOGIN_INTERFACE = "Customer Login";
    public static final String BANK_MANAGER_INTERFACE = "Bank Manager Login";

    // Кнопки и действия
    public static final String ADD_CUSTOMER_BUTTON = "Add Customer";
    public static final String OPEN_ACCOUNT_BUTTON = "Open Account";
    public static final String CUSTOMERS_BUTTON = "Customers";
    public static final String DEPOSIT_BUTTON = "Deposit";
    public static final String WITHDRAW_BUTTON_MAIN = "Withdrawl";
    public static final String WITHDRAW_BUTTON_ACCEPT = "Withdraw";
    public static final String TRANSACTIONS_BUTTON = "Transactions";
    public static final String PROCESS_BUTTON = "Process";
    public static final String LOGIN_BUTTON = "Login";
    public static final String REGISTER_BUTTON = "Register";
    public static final String RESET_BUTTON = "Reset";
    public static final String BACK_BUTTON = "Back";
    public static final String DELETE_BUTTON = "Delete";

    // Типы транзакций
    public static final String CREDIT_TRANSACTION = "Credit";
    public static final String DEBIT_TRANSACTION = "Debit";

    // Прочие тексты
    public static final String BALANCE_TEXT = "Balance";
    public static final String CURRENCY_DOLLAR = "Dollar";
    public static final String CURRENCY_POUND = "Pound";
    public static final String CURRENCY_RUPEE = "Rupee";
    public static final String GENDER_MALE = "Male";
    public static final String GENDER_FEMALE = "Female";
    public static final String HOBBY_SPORTS = "Sports";

    // Константы
    public static final String ZERO_AMOUNT = "0";
    public static final String LARGE_AMOUNT = "1000000";

    // Плейсхолдеры
    public static final String SEARCH_PLACEHOLDER = "Search Customer";
    public static final String FIRST_NAME_PLACEHOLDER = "First Name";
    public static final String LAST_NAME_PLACEHOLDER = "Last Name";
    public static final String POST_CODE_PLACEHOLDER = "Post Code";
    public static final String AMOUNT_PLACEHOLDER = "amount";

    // Сообщения для утверждений
    public static final String TRANSACTION_SHOULD_BE_PRESENT = "Транзакция на сумму %s должна присутствовать в истории";
    public static final String TRANSACTION_SHOULD_NOT_BE_PRESENT = "Транзакция на сумму %s не должна присутствовать в истории";
    public static final String WELCOME_MESSAGE_SHOULD_CONTAIN = "Приветственное сообщение должно содержать имя клиента";
    public static final String BALANCE_SHOULD_BE_ZERO = "Баланс должен быть равен 0 после снятия всех средств";
    public static final String TRANSACTIONS_SHOULD_BE_CLEARED = "Транзакции должны быть очищены";
    public static final String CUSTOMER_SHOULD_BE_FOUND = "Клиент должен быть найден в таблице";
    public static final String CUSTOMER_SHOULD_BE_DELETED = "Клиент должен быть удален из таблицы";
    public static final String SHOULD_HAVE_TRANSACTIONS = "Должны быть транзакции перед очисткой";
}