# Tests Automation

Проект автоматизации тестирования веб-приложения Way2Automation.

## Запуск тестов

```bash
# Запуск всех тестов
mvn clean test

# Запуск конкретного теста
mvn clean test -Dtest=MainPageTest
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