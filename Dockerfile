# Используем официальный образ OpenJDK 17
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем всё содержимое проекта
COPY . .

# Делаем mvnw исполняемым
RUN chmod +x ./mvnw

# Собираем проект
RUN ./mvnw clean package -DskipTests

# Запускаем приложение
CMD ["java", "-jar", "target/avangard-website-0.0.1-SNAPSHOT.jar"]