# Currency Service
Этот проект представляет собой сервис для работы с курсами валют. Он включает в себя REST API для загрузки и получения данных о курсах валют.
Сервис использует Spring Boot и взаимодействует с внешним API для получения данных о курсах валют и сохраняет их в базе данных. 
Также предусмотрены методы для получения информации о курсах валют по заданной дате и идентификатору валюты.
# Структура проекта
1. Контроллер (CurrencyController): Обрабатывает HTTP-запросы для загрузки курсов валют и получения информации о курсе по дате и идентификатору валюты.
2. Сервис (CurrencyService): Логика обработки данных, включая загрузку данных из внешнего API и взаимодействие с репозиторием.
3. Репозиторий (CurrencyRepository): Интерфейс для работы с базой данных, реализующий методы для сохранения и поиска данных о курсах валют.
4. Модель (CurrencyAnswer): Представляет собой сущность, которая хранит данные о курсах валют.
# Зависимости
- Spring Boot
- Spring Data JPA
- RestTemplate
- Lombok
- SLF4J для логирования
# Требования
- Java 17 или выше
- Maven
- Подключение к базе данных (я использовал PostgreSQL)
