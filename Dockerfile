# Используем официальный образ OpenJDK 17
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем JAR-файл (если у вас уже есть)
# COPY target/avangard-website-0.0.1-SNAPSHOT.jar app.jar

# Копируем исходный код
COPY . .

# Собираем проект
RUN ./mvnw clean package -DskipTests

# Запускаем приложение
CMD ["java", "-jar", "target/*.jar"]