FROM maven:3.8.5-openjdk-17-slim

WORKDIR /app

# Копируем весь проект внутрь контейнера
COPY . .

# Создаем директорию для загрузок
RUN mkdir -p /app/downloads

# Запускаем Maven с нужными параметрами
ENTRYPOINT ["mvn", "test", "-Dtest=LoginPageTest", "-Dusername=angular", "-Dpassword=password"]

