// src/main/java/com/example/drivers/DriverPool.java
package com.example.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverPool {

    // Єдиний екземпляр WebDriver для всього прогону
    private static WebDriver driver;

    /**
     * Повертає поцінований екземпляр ChromeDriver, створює його при першому виклику.
     */
    public static WebDriver createDriver() {
        if (driver == null) {
            // Підтягуємо chromedriver
            WebDriverManager.chromedriver().setup();

            // Конфігуруємо окремий тимчасовий профіль, щоб не лізти у ваш основний Chrome
            ChromeOptions opts = new ChromeOptions();
            String tmpProfile = System.getProperty("java.io.tmpdir") + "/selenium-profile";
            opts.addArguments("--user-data-dir=" + tmpProfile);
            opts.addArguments("--disable-extensions");
            opts.addArguments("--start-maximized");

            // Створюємо драйвер
            driver = new ChromeDriver(opts);
        }
        return driver;
    }

    /**
     * Закриває браузер і обнуляє внутрішнє поле.
     * Викликайте в @AfterClass або @AfterSuite.
     */
    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignore) {
            }
            driver = null;
        }
    }
}
