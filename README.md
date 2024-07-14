# Дипломный проект по профессии «Тестировщик»

## Тема — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

<img width="705" alt="service" src="https://github.com/user-attachments/assets/0766f651-63be-4650-89f8-3aad2f4da75e">


## Инструкция по запуску

### Установленнно на локальный компьютер:
    1) IntelliJ IDEA Ultimate
    2) Docker
    3) Git
    4) DBeaver
#### 1. Запустить IntelliJ IDEA
#### 2. Склонированить репозиторий https://github.com/Shampsonn/Diplom.git
#### 3. Запустить приложение Docker на локальном компьютере
#### 4. Запуситить контейнер Docker командой docker-compose up
#### 5. Запуск приложения
##### MySQl:
    java '-Dspring.datasource.url=jdbc:mysql://localhost:3306/app' -jar ./artifacts/aqa-shop.jar
##### Postgres:
    java '-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app' -jar ./artifacts/aqa-shop.jar
#### 6. Запуск тестов
##### MySQl:
    ./gradlew clean test '-Ddb.url=jdbc:mysql://localhost:3306/app'
##### Postgres:
     ./gradlew clean test '-Ddb.url=jdbc:postgresql://localhost:5432/app'
#### 7. Сформировать отчет:
    ./gradlew allureReport
#### 8. Открыть отчет в браузере командой:
    ./gradlew allureServe    
