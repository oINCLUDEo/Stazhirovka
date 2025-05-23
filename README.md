# Tests Automation

Проект автоматизации тестирования веб-приложения Way2Automation.

## Запуск тестов

```bash
# Запуск всех тестов
mvn clean test

# Запуск конкретного теста
mvn clean test -Dtest=MainPageTest
```

## Запуск сьютов

- ## test_suite.xml
```bash
mvn clean test -DsuiteXmlFile="src/test/resources/test_suite.xml" -Dusername=angular -Dpassword=password
```

- ## universal_login_suite.xml
```bash
mvn clean test -DsuiteXmlFile="src/test/resources/universal_login_suite.xml" -Dusername=angular -Dpassword=password
```

## Параметры запуска

### Базовый URL
```bash
mvn clean test -DbaseUrl=https://way2automation.com/
```

### Браузер
```bash
# Chrome (по умолчанию)
mvn clean test -Dbrowser=chrome

# Firefox
mvn clean test -Dbrowser=firefox

# Edge
mvn clean test -Dbrowser=edge
```
## Авторизация: передача логина и пароля
Для тестов, требующих авторизации, логин и пароль можно передать двумя способами:

- ## Через параметры JVM
```bash
mvn clean test -Dusername=<Ваш логин> -Dpassword=<Ваш пароль>
```

- ## Через переменные окружения
```bash
$env:USERNAME = "angular"; $env:PASSWORD = "password"; mvn clean test
```
> ⚠️ Если значения не заданы, тесты завершатся с ошибкой `IllegalStateException`.

### Комбинация параметров
```bash
mvn clean test -Dtest=<Выбранный тест класс> -Dbrowser=<Выбранный браузер> -DbaseUrl=<Ваш Url> -Dusername=<Ваш логин> -Dpassword=<Ваш пароль>
```
## 📊 Allure отчёты

### Генерация отчёта:

```bash
# Генерация и открытие отчёта
allure serve target/allure-results
```