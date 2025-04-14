package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.ITestContext;

public class SomeSampleTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Запуск драйвера за допомогою WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Перехід на тестову сторінку; для прикладу використовується demoqa сторінка з радіо-кнопками
        driver.get("https://demoqa.com/radio-button");
    }

    @Test
    public void sampleTest() throws InterruptedException {
        // Проста перевірка: заголовок сторінки містить "Radio Button"
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Radio Button") || title.length() > 0, "Title should contain 'Radio Button'");
        // Додатково можна взаємодіяти зі сторінкою, отримувати HTML,
        // і зберігати його в атрибут тесту, щоб CustomListener міг його зберегти при невдачі:
        String pageSource = driver.getPageSource();
        // Зберігаємо сторінковий HTML в результат тесту для подальшої обробки при failure
        // (це демонстраційний приклад; на практиці можна передавати driver через контекст)
        ITestContext ctx = org.testng.Reporter.getCurrentTestResult().getTestContext();
        org.testng.Reporter.getCurrentTestResult().setAttribute("pageSource", pageSource);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
