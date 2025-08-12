FROM maven:3.8.5-openjdk-17-slim

WORKDIR /app

# Копируем весь проект внутрь контейнера
COPY . .

# Запускаем Maven с нужными параметрами
ENTRYPOINT ["mvn", "test", "-Dtest=LoginPageTest", "-Dusername=angular", "-Dpassword=password"]
