package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.File;

public class SomeSampleTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Определяем путь относительно корня проекта
        String projectPath = System.getProperty("user.dir");
        String driverRelativePath = "chromedriver-win64/chromedriver.exe"; // Папка и файл драйвера
        String driverFullPath = projectPath + File.separator + driverRelativePath;

        // Устанавливаем системное свойство
        System.setProperty("webdriver.chrome.driver", driverFullPath);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/radio-button");
    }

    @Test
    public void sampleTest() throws InterruptedException {
        String title = driver.getTitle();
        System.out.println("Page title: " + title);
        Thread.sleep(1000);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
