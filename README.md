# Tests Automation

Проект автоматизации тестирования веб-приложений: **Way2Automation** и **SQL-Ex**.

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
mvn clean test -DsuiteXmlFile="src/test/resources/test_suite.xml" -Dusername=angular -Dpassword=password -DsqlExUsername=sqluser -DsqlExPassword=sqlpass
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

Тесты требуют логины и пароли для:

- **Way2Automation**

- **SQL-Ex (sql-ex.ru)**


Параметры можно передать двумя способами:
### Через параметры JVM

```bash
mvn clean test 
  -Dusername=<Логин Way2Automation>
  -Dpassword=<Пароль Way2Automation>
  -DsqlExUsername=<Логин SQL-Ex>
  -DsqlExPassword=<Пароль SQL-Ex>
```

### Через переменные окружения

```powershell
$env:USERNAME = "angular"
$env:PASSWORD = "password"
$env:SQLEX_USERNAME = "sqluser"
$env:SQLEX_PASSWORD = "sqlpass"
mvn clean test
```

> ⚠️ Если хотя бы одно значение не задано, соответствующие тесты завершатся с ошибкой `IllegalStateException`.

### Комбинация параметров

```bash
mvn clean test
  -Dtest=UniversalLoginTest
  -Dbrowser=firefox
  -DbaseUrl=https://www.way2automation.com/
  -Dusername=angular
  -Dpassword=password
  -DsqlExUsername=sqluser
  -DsqlExPassword=sqlpass
```

## 📊 Allure отчёты

### Генерация отчёта:

```bash
# Генерация и открытие отчёта
allure serve target/allure-results
```