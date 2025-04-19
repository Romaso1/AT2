# MantisBT Test Framework

## Опис
Проект включає:
- UI тести на Selenium + TestNG з PageFactory і BaseElement враперами
- API тести на REST Assured: end-to-end CRUD для сутності Project
- Performance тести на JMeter з модульною авторизацією і HTML Dashboard
- Allure звіти зі скріншотами при падінні
- TestNG Listener-и для автоматичного зняття скріншотів

## Передумови
- Java 11+
- Maven 3.x
- JMeter 5.x
- MantisBT, запущений на http://localhost/mantis
- API Token користувача

## Конфігурація
Створіть `src/test/resources/config.properties`:
```
api.baseUrl=http://localhost/mantis/api/rest/
api.token=<YOUR_API_TOKEN>
ui.baseUrl=http://localhost/mantis
ui.username=administrator
ui.password=secret
```

## Запуск
- UI тести: `mvn clean test -Pui`
- API тести: `mvn clean test -Papi`
- Performance тести (CLI):
  ```bash
  cd jmeter
  jmeter -n -t TestPlan.jmx -l results.jtl -e -o Reports/HTML -Jtoken=<YOUR_API_TOKEN>
  ```
- Allure звіт: `allure serve target/allure-results`
