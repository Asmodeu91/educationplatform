# Образовательная платформа

Приложение на Spring Boot для образовательной платформы с базой данных PostgreSQL, развертыванием в Kubernetes и полным CI/CD пайплайном.

## Содержание

- [Общее описание](#общее-описание)
- [Функциональные возможности](#функциональные-возможности)
- [Технологический стек](#технологический-стек)
- [Предварительные требования](#предварительные-требования)
- [Установка и настройка](#установка-и-настройка)
  - [Локальная разработка](#локальная-разработка)
  - [Развертывание с Docker](#развертывание-с-docker)
  - [Развертывание в Kubernetes](#развертывание-в-kubernetes)
- [Документация API](#документация-api)
  - [Базовый URL](#базовый-url)
  - [Обработка ошибок](#обработка-ошибок)
  - [Эндпоинты](#эндпоинты)
- [CI/CD пайплайн](#cicd-пайплайн)
- [Конфигурация](#конфигурация)
- [Тестирование](#тестирование)
- [Структура проекта](#структура-проекта)
- [Заполнение данными](#заполнение-данными)
- [Helm Chart](#helm-chart)
- [Участие в разработке](#участие-в-разработке)
- [Лицензия](#лицензия)

## Общее описание

Образовательная платформа — это комплексная система управления обучением, которая позволяет преподавателям создавать и управлять курсами, студентам записываться на курсы и предоставляет инструменты для заданий, тестов и отзывов о курсах. Платформа имеет полноценное REST API, правильную аутентификацию и разработана для масштабирования с развертыванием в Kubernetes.

## Функциональные возможности

- **Управление пользователями**: Преподаватели и студенты с различными ролями
- **Управление курсами**: Создание, обновление и управление курсами с категориями
- **Структура контента**: Курсы содержат модули, уроки и задания
- **Система записи**: Студенты могут записываться на курсы с отслеживанием статуса
- **Система заданий**: Студенты отправляют задания, преподаватели выставляют оценки
- **Система тестов**: Вопросы с выбором одного или нескольких ответов, true/false
- **Отзывы о курсах**: Студенты могут оставлять отзывы о пройденных курсах
- **Полные CRUD операции**: Полноценное REST API для всех сущностей
- **Валидация и обработка ошибок**: Комплексная валидация ввода и понятные сообщения об ошибках
- **Поддержка Docker и Kubernetes**: Контейнерное развертывание
- **CI/CD пайплайн**: Автоматизированное тестирование и развертывание
- **Helm Chart**: Готовое к продакшену развертывание в Kubernetes

## Технологический стек

### Backend
- **Фреймворк**: Spring Boot 3.4.0
- **Язык**: Java 17
- **База данных**: PostgreSQL
- **ORM**: Spring Data JPA
- **Документация API**: Springdoc OpenAPI (Swagger)

### Frontend
- **Клиент API**: REST API доступен через любой HTTP клиент
- **Документация**: Swagger UI по адресу `/swagger-ui.html`

### Инфраструктура
- **Контейнеризация**: Docker
- **Оркестрация**: Kubernetes
- **Система управления зависимостями**: Maven
- **CI/CD**: GitLab CI, GitHub Actions
- **Управление конфигурацией**: Helm

## Предварительные требования

Перед началом убедитесь, что у вас установлены следующие компоненты:

- Java 17 или выше
- Maven 3.6.3 или выше
- PostgreSQL 14
- Docker (для контейнерного развертывания)
- Kubernetes (для продакшен развертывания)
- Helm (для развертывания в Kubernetes)
- Аккаунт GitLab или GitHub (для CI/CD)

## Установка и настройка

### Локальная разработка

1. **Установите PostgreSQL**:
```bash
brew install postgresql@14
brew services start postgresql@14
```

2. **Создайте базу данных**:
```bash
createdb education_db
```

3. **Обновите учетные данные базы данных** (опционально):
Отредактируйте `src/main/resources/application.properties` при необходимости:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/education_db
spring.datasource.username=postgres
spring.datasource.password=your_password
```

4. **Запустите приложение**:
```bash
mvn spring-boot:run
```

5. **Доступ к приложению**:
- API: `http://localhost:8080/api`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Документация OpenAPI: `http://localhost:8080/api-docs`

### Развертывание с Docker

1. **Соберите Docker образ**:
```bash
docker build -t educationplatform:latest .
```

2. **Запустите с docker-compose**:
```bash
docker-compose up -d
```

3. **Доступ к приложению**:
- API: `http://localhost:8080/api`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

### Развертывание в Kubernetes

#### Использование Helm Chart

1. **Установите Helm**:
```bash
# macOS
brew install helm
```

2. **Добавьте репозиторий Helm**:
```bash
helm repo add educationplatform ./helm/educationplatform
```

3. **Установите чарт**:
```bash
helm install educationplatform ./helm/educationplatform \
  --namespace educationplatform-dev \
  --create-namespace
```

4. **Обновите релиз**:
```bash
helm upgrade educationplatform ./helm/educationplatform \
  --namespace educationplatform-dev \
  --set image.tag=latest
```

5. **Просмотр статуса релиза**:
```bash
helm status educationplatform -n educationplatform-dev
```

#### Ручное развертывание в Kubernetes

1. **Создайте namespaces**:
```bash
kubectl apply -f k8s/namespaces/
```

2. **Разверните в dev окружении**:
```bash
helm upgrade --install educationplatform-dev helm/educationplatform \
  --namespace educationplatform-dev \
  --create-namespace \
  --set image.repository=localhost:5000/educationplatform \
  --set image.tag=latest \
  --set env.SPRING_PROFILES_ACTIVE=dev
```

## Документация API

### Базовый URL

```
http://localhost:8080/api
```

### Обработка ошибок

API возвращает стандартизированные ответы об ошибках:

```json
{
  "timestamp": "2024-01-15T10:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Entity not found with id: 999",
  "path": "/api/courses/999"
}
```

Для ошибок валидации:

```json
{
  "timestamp": "2024-01-15T10:00:00",
  "status": 400,
  "error": "Validation Failed",
  "message": "Validation failed for one or more fields",
  "path": "/api/courses",
  "errors": {
    "title": "must not be null",
    "description": "must not be empty"
  }
}
```

### Эндпоинты

#### Пользователи

| Метод | Эндпоинт | Описание | Доступ |
|--------|--------|-------------|---------|
| GET | `/users` | Получить всех пользователей | Публично |
| GET | `/users/{id}` | Получить пользователя по ID | Публично |
| GET | `/users/email/{email}` | Получить пользователя по email | Публично |
| POST | `/users` | Создать нового пользователя | Админ |
| PUT | `/users/{id}` | Обновить пользователя | Пользователь/Админ |
| DELETE | `/users/{id}` | Удалить пользователя | Админ |

#### Курсы

| Метод | Эндпоинт | Описание | Доступ |
|--------|--------|-------------|---------|
| GET | `/courses` | Получить все курсы | Публично |
| GET | `/courses/{id}` | Получить курс по ID | Публично |
| GET | `/courses/teacher/{teacherId}` | Получить курсы по преподавателю | Публично |
| POST | `/courses` | Создать новый курс | Преподаватель |
| PUT | `/courses/{id}` | Обновить курс | Преподаватель/Админ |
| DELETE | `/courses/{id}` | Удалить курс | Преподаватель/Админ |

#### Категории

| Метод | Эндпоинт | Описание | Доступ |
|--------|--------|-------------|---------|
| GET | `/categories` | Получить все категории | Публично |
| GET | `/categories/{id}` | Получить категорию по ID | Публично |
| POST | `/categories` | Создать новую категорию | Админ |
| PUT | `/categories/{id}` | Обновить категорию | Админ |
| DELETE | `/categories/{id}` | Удалить категорию | Админ |

#### Модули

| Метод | Эндпоинт | Описание | Доступ |
|--------|--------|-------------|---------|
| GET | `/modules` | Получить все модули | Публично |
| GET | `/modules/{id}` | Получить модуль по ID | Публично |
| GET | `/modules/course/{courseId}` | Получить модули по курсу | Публично |
| POST | `/modules` | Создать новый модуль | Преподаватель |
| PUT | `/modules/{id}` | Обновить модуль | Преподаватель/Админ |
| DELETE | `/modules/{id}` | Удалить модуль | Преподаватель/Админ |

#### Уроки

| Метод | Эндпоинт | Описание | Доступ |
|--------|--------|-------------|---------|
| GET | `/lessons` | Получить все уроки | Публично |
| GET | `/lessons/{id}` | Получить урок по ID | Публично |
| GET | `/lessons/module/{moduleId}` | Получить уроки по модулю | Публично |
| POST | `/lessons` | Создать новый урок | Преподаватель |
| PUT | `/lessons/{id}` | Обновить урок | Преподаватель/Админ |
| DELETE | `/lessons/{id}` | Удалить урок | Преподаватель/Админ |

#### Задания

| Метод | Эндпоинт | Описание | Доступ |
|--------|--------|-------------|---------|
| GET | `/assignments` | Получить все задания | Публично |
| GET | `/assignments/{id}` | Получить задание по ID | Публично |
| GET | `/assignments/lesson/{lessonId}` | Получить задания по уроку | Публично |
| POST | `/assignments` | Создать новое задание | Преподаватель |
| PUT | `/assignments/{id}` | Обновить задание | Преподаватель/Админ |
| DELETE | `/assignments/{id}` | Удалить задание | Преподаватель/Админ |

#### Решения

| Метод | Эндпоинт | Описание | Доступ |
|--------|--------|-------------|---------|
| GET | `/submissions` | Получить все решения | Преподаватель |
| GET | `/submissions/{id}` | Получить решение по ID | Студент/Преподаватель |
| GET | `/submissions/assignment/{assignmentId}` | Получить решения по заданию | Преподаватель |
| GET | `/submissions/student/{studentId}` | Получить решения по студенту | Студент/Преподаватель |
| POST | `/submissions/submit` | Отправить задание | Студент |
| PUT | `/submissions/{id}/grade` | Оценить решение | Преподаватель |
| DELETE | `/submissions/{id}` | Удалить решение | Преподаватель |

#### Записи на курс

| Метод | Эндпоинт | Описание | Доступ |
|--------|--------|-------------|---------|
| GET | `/enrollments` | Получить все записи | Преподаватель |
| GET | `/enrollments/{id}` | Получить запись по ID | Студент/Преподаватель |
| GET | `/enrollments/student/{studentId}` | Получить записи по студенту | Студент/Преподаватель |
| GET | `/enrollments/course/{courseId}` | Получить записи по курсу | Преподаватель |
| POST | `/enrollments/enroll` | Записаться на курс | Студент |
| PUT | `/enrollments/{id}/status` | Обновить статус записи | Преподаватель |
| DELETE | `/enrollments/{id}` | Отписаться от курса | Студент |

#### Тесты

| Метод | Эндпоинт | Описание | Доступ |
|--------|--------|-------------|---------|
| GET | `/quizzes` | Получить все тесты | Публично |
| GET | `/quizzes/{id}` | Получить тест по ID | Публично |
| GET | `/quizzes/course/{courseId}` | Получить тесты по курсу | Публично |
| GET | `/quizzes/module/{moduleId}` | Получить тесты по модулю | Публично |
| POST | `/quizzes` | Создать новый тест | Преподаватель |
| PUT | `/quizzes/{id}` | Обновить тест | Преподаватель/Админ |
| DELETE | `/quizzes/{id}` | Удалить тест | Преподаватель/Админ |

#### Вопросы

| Метод | Эндпоинт | Описание | Доступ |
|--------|--------|-------------|---------|
| GET | `/questions` | Получить все вопросы | Публично |
| GET | `/questions/{id}` | Получить вопрос по ID | Публично |
| GET | `/questions/quiz/{quizId}` | Получить вопросы по тесту | Публично |
| POST | `/questions` | Создать новый вопрос | Преподаватель |
| PUT | `/questions/{id}` | Обновить вопрос | Преподаватель/Админ |
| DELETE | `/questions/{id}` | Удалить вопрос | Преподаватель/Админ |

#### Варианты ответов

| Метод | Эндпоинт | Описание | Доступ |
|--------|--------|-------------|---------|
| GET | `/answer-options` | Получить все варианты ответов | Публично |
| GET | `/answer-options/{id}` | Получить вариант ответа по ID | Публично |
| GET | `/answer-options/question/{questionId}` | Получить варианты по вопросу | Публично |
| POST | `/answer-options` | Создать новый вариант ответа | Преподаватель |
| PUT | `/answer-options/{id}` | Обновить вариант ответа | Преподаватель/Админ |
| DELETE | `/answer-options/{id}` | Удалить вариант ответа | Преподаватель/Админ |

#### Результаты тестов

| Метод | Эндпоинт | Описание | Доступ |
|--------|--------|-------------|---------|
| GET | `/quiz-submissions` | Получить все результаты тестов | Преподаватель |
| GET | `/quiz-submissions/{id}` | Получить результат теста по ID | Студент/Преподаватель |
| GET | `/quiz-submissions/quiz/{quizId}` | Получить результаты по тесту | Преподаватель |
| GET | `/quiz-submissions/student/{studentId}` | Получить результаты по студенту | Студент/Преподаватель |
| POST | `/quiz-submissions` | Отправить ответы на тест | Студент |
| DELETE | `/quiz-submissions/{id}` | Удалить результат теста | Преподаватель |

#### Теги

| Метод | Эндпоинт | Описание | Доступ |
|--------|--------|-------------|---------|
| GET | `/tags` | Получить все теги | Публично |
| GET | `/tags/{id}` | Получить тег по ID | Публично |
| POST | `/tags` | Создать новый тег | Админ |
| PUT | `/tags/{id}` | Обновить тег | Админ |
| DELETE | `/tags/{id}` | Удалить тег | Админ |

#### Отзывы о курсах

| Метод | Эндпоинт | Описание | Доступ |
|--------|--------|-------------|---------|
| GET | `/course-reviews` | Получить все отзывы | Публично |
| GET | `/course-reviews/{id}` | Получить отзыв по ID | Публично |
| GET | `/course-reviews/course/{courseId}` | Получить отзывы по курсу | Публично |
| GET | `/course-reviews/student/{studentId}` | Получить отзывы по студенту | Студент/Преподаватель |
| POST | `/course-reviews` | Создать отзыв о курсе | Студент |
| PUT | `/course-reviews/{id}` | Обновить отзыв | Студент/Админ |
| DELETE | `/course-reviews/{id}` | Удалить отзыв | Студент/Админ |

#### Профили

| Метод | Эндпоинт | Описание | Доступ |
|--------|--------|-------------|---------|
| GET | `/profiles` | Получить все профили | Публично |
| GET | `/profiles/{id}` | Получить профиль по ID | Публично |
| GET | `/profiles/user/{userId}` | Получить профиль по пользователю | Публично |
| POST | `/profiles` | Создать новый профиль | Пользователь |
| PUT | `/profiles/{id}` | Обновить профиль | Пользователь/Админ |
| DELETE | `/profiles/{id}` | Удалить профиль | Админ |

## CI/CD пайплайн

Проект включает комплексный CI/CD пайплайн для автоматизированного тестирования, сборки и развертывания.

### GitHub Actions

Расположен в `.github/workflows/ci-cd.yml`, пайплайн включает:

- **Сборка**: Компиляция приложения с помощью Maven
- **Тестирование**: Запуск юнит и интеграционных тестов
- **Покрытие кода**: Загрузка результатов в Codecov
- **Сканирование безопасности**: OWASP Dependency Check для обнаружения уязвимостей

### GitLab CI/CD

Расположен в `.gitlab-ci.yml`, пайплайн включает несколько стадий:

#### Стадии

1. **Сборка**: Компиляция приложения
2. **Тестирование**: Запуск юнит и интеграционных тестов с отчетом о покрытии
3. **Пакетирование**: Сборка Docker образа и загрузка в реестр
4. **Развертывание**: Развертывание в окружения Kubernetes

#### Окружения развертывания

- **Dev**: Автоматическое развертывание при каждом коммите в ветку main
  - URL: `http://dev.educationplatform.example.com`
  - Namespace: `educationplatform-dev`

- **Staging**: Ручное развертывание для тестирования
  - URL: `http://staging.educationplatform.example.com`
  - Namespace: `educationplatform-staging`
  - 3 реплики

- **Production**: Ручное развертывание с дополнительными требованиями
  - URL: `http://educationplatform.example.com`
  - Namespace: `educationplatform-prod`
  - 5 реплик
  - Увеличенные лимиты ресурсов (1Gi/2Gi памяти)
  - Развертывание через Git тег или переменную `$DEPLOY_TO_PROD`

#### Переменные окружения

- `CI_REGISTRY_USER`, `CI_REGISTRY_PASSWORD`: Учетные данные реестра контейнеров
- `CI_REGISTRY_IMAGE`: Путь к образу реестра
- `KUBE_CONFIG`: Base64-закодированный конфиг Kubernetes для доступа к кластеру
- `DEPLOY_TO_PROD`: Флаг для включения развертывания в продакшен

## Конфигурация

### Свойства приложения

Приложение настроено через `src/main/resources/application.properties`:

```properties
# Swagger/OpenAPI
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html

# База данных
spring.datasource.url=jdbc:postgresql://localhost:5432/education_db
spring.datasource.username=postgres
spring.datasource.password=your_password

# Hibernate
spring.jpa.hibernate.ddl-auto=update

# Сервер
server.port=8080
server.error.include-message=always
```

### Конфигурация Kubernetes

Helm чарт в `helm/educationplatform/` предоставляет конфигурацию для разных окружений:

- **Разработка**: Меньшее использование ресурсов, одна реплика
- **Staging**: Средние ресурсы, 3 реплики
- **Продакшен**: Высокие ресурсы, 5 реплик, расширенный мониторинг

## Тестирование

Проект включает комплексное тестирование:

### Юнит тесты

- Расположены в `src/test/java/com/educationplatform/model/`
- Тестируют валидацию сущностей, геттеры/сеттеры и базовую функциональность
- Более 15 юнит-тестов, покрывающих все сущности

### Интеграционные тесты

- Расположены в `src/test/java/com/educationplatform/repository/`
- Тестируют взаимодействие с базой данных с аннотацией `@DataJpaTest`
- Тестируют функциональность слоя сервисов
- Используют базу данных H2 для быстрого выполнения

### Покрытие кода

- Запуск тестов: `mvn test`
- Просмотр отчета о покрытии в `target/site/jacoco/`
- Целевое покрытие не менее 70%

## Структура проекта

```
educationplatform/
├── .github/                    # Рабочие процессы GitHub Actions
├── .gitlab-ci.yml              # Конфигурация GitLab CI/CD
├── helm/                       # Helm чарты для Kubernetes
│   └── educationplatform/
│       ├── Chart.yaml
│       ├── values.yaml
│       └── templates/
├── k8s/                        # Манифесты Kubernetes
│   ├── deployments/
│   └── namespaces/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/educationplatform/
│   │   │       ├── controller/     # REST контроллеры
│   │   │       ├── model/        # JPA сущности
│   │   │       ├── repository/   # Spring Data репозитории
│   │   │       ├── service/      # Сервисы бизнес-логики
│   │   │       └── exception/    # Кастомные исключения
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql          # Заполнение начальными данными
│   └── test/
│       └── java/
│           └── com/educationplatform/
│               ├── model/        # Юнит тесты
│               ├── repository/   # Интеграционные тесты
│               └── service/      # Тесты сервисов
├── docker-compose.yml          # Конфигурация Docker
├── Dockerfile                  # Инструкции сборки Docker
├── pom.xml                     # Зависимости Maven
└── README.md                   # Документация проекта
```

## Заполнение данными

Приложение заполняет начальными данными при запуске через `src/main/resources/data.sql`:

### Пользователи

- **Преподаватели (3)**: Alice Johnson, Bob Smith, Carol Williams
- **Студенты (7)**: David Brown, Emma Davis, Frank Miller и др.

### Курсы (7)

- Основы программирования на Java
- Продвинутый Spring Boot
- Full-Stack веб-разработка
- Python для Data Science
- Разработка мобильных приложений на React Native
- Основы Docker и Kubernetes
- Архитектура программного обеспечения и паттерны проектирования

### Другие данные

- Категории: Программирование, Веб-разработка, Data Science и т.д.
- Теги: Java, Spring Boot, Python, JavaScript и т.д.
- Модули, уроки, задания, тесты и вопросы
- Записи на курсы и решения

Это обеспечивает полностью функциональную демо-среду "из коробки".

## Helm Chart

Helm чарт в `helm/educationplatform/` предоставляет готовое к продакшену развертывание в


ps 
локально все тесты проходят 

[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.039 s -- in com.educationplatform.repository.UserRepositoryTest
[INFO] Running com.educationplatform.model.SubmissionTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.002 s -- in com.educationplatform.model.SubmissionTest
[INFO] Running com.educationplatform.model.ProfileTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in com.educationplatform.model.ProfileTest
[INFO] Running com.educationplatform.model.CategoryTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in com.educationplatform.model.CategoryTest
[INFO] Running com.educationplatform.model.EnrollmentTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in com.educationplatform.model.EnrollmentTest
[INFO] Running com.educationplatform.model.QuestionTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.002 s -- in com.educationplatform.model.QuestionTest
[INFO] Running com.educationplatform.model.QuizSubmissionTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in com.educationplatform.model.QuizSubmissionTest
[INFO] Running com.educationplatform.model.CourseReviewTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in com.educationplatform.model.CourseReviewTest
[INFO] Running com.educationplatform.model.CourseTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in com.educationplatform.model.CourseTest
[INFO] Running com.educationplatform.model.AssignmentTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in com.educationplatform.model.AssignmentTest
[INFO] Running com.educationplatform.model.UserTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in com.educationplatform.model.UserTest
[INFO] Running com.educationplatform.model.TagTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in com.educationplatform.model.TagTest
[INFO] Running com.educationplatform.model.ModuleTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in com.educationplatform.model.ModuleTest
[INFO] Running com.educationplatform.model.QuizTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in com.educationplatform.model.QuizTest
[INFO] Running com.educationplatform.model.LessonTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in com.educationplatform.model.LessonTest
[INFO] Running com.educationplatform.model.AnswerOptionTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.002 s -- in com.educationplatform.model.AnswerOptionTest
[INFO] Running com.educationplatform.service.UserServiceTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.092 s -- in com.educationplatform.service.UserServiceTest
[INFO] Running com.educationplatform.service.CourseServiceTest
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.035 s -- in com.educationplatform.service.CourseServiceTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 61, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.076 s
[INFO] Finished at: 2025-12-28T01:10:06+03:00
[INFO] ------------------------------------------------------------------------

на gitlab тоже