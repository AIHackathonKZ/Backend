# Используем базовый образ OpenJDK
FROM openjdk:17-jdk

# Устанавливаем временный каталог для приложения
VOLUME /tmp

# Копируем собранный JAR файл в контейнер
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Устанавливаем точку входа для запуска Spring Boot приложения
ENTRYPOINT ["java", "-jar", "/app.jar"]
