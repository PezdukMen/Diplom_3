# Stellar Burgers — UI Autotests (Диплом 3)

Автоматизированное тестирование веб-интерфейса Stellar Burgers с использованием **Selenium WebDriver**, **Page Object Model** и **Allure**.

---

## Задание

### Что было протестировано:

### Регистрация
- Успешная регистрация нового пользователя
- Ошибка при вводе некорректного пароля (менее 6 символов)

### Вход в аккаунт
- Вход по кнопке «Войти в аккаунт» на главной странице
- Вход через кнопку «Личный кабинет»
- Вход через кнопку в форме регистрации
- Вход через кнопку в форме восстановления пароля

### Раздел «Конструктор»
- Переход между вкладками:
  - **Булки**
  - **Соусы**
  - **Начинки**

### Браузеры
- Google Chrome
- Яндекс Браузер

---

## Стек

- **Java 11**
- **Maven**
- **Selenium WebDriver 4.23.0**
- **JUnit 4.13.2**
- **RestAssured**
- **Allure 2.21.0**
- **Page Object Model**

---

## Структура проекта
```bash
Diplom_3/
├── src/
│   └── test/java/
│       ├── api/
│       │   ├── User.java
│       │   └── UserClient.java
│       ├── pom/
│       │   ├── ForgotPasswordPage.java
│       │   ├── LoginPage.java
│       │   ├── MainPage.java
│       │   ├── ProfilePage.java
│       │   └── RegisterPage.java
│       ├── tests/
│       │   ├── BaseTest.java
│       │   ├── EntranceTest.java
│       │   ├── RegisterTest.java
│       │   └── SectionConstructorTest.java
│       └── util/
│           ├── ConfigReader.java
│           ├── UserGenerator.java
│           └── WebDriverFactory.java
├── src/test/resources/
│   ├── allure.properties
│   └── config.properties
├── pom.xml
└── README.md
```

---

## Как запустить проект

### 1. Клонирование репозитория
```bash
git clone https://github.com/berezikovM/Diplom_3.git
cd Diplom_3
```

2. Запуск тестов Google Chrome и Яндекс Браузер
```bash
mvn test -Dbrowser=chrome
```
```bash
mvn test -Dbrowser=yandex
```

### 3. Генерация Allure отчета
```bash
mvn allure:serve
```
Все .html будут доступны по пути:
target/allure-results/