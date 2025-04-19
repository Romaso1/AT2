// src/main/java/com/example/drivers/DriverPool.java
package com.example.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverPool {

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver()
                        .clearDriverCache()
                        .setup();
                return new FirefoxDriver();

            case "chrome":
            default:
                WebDriverManager.chromedriver()
                        .clearDriverCache()
                        .setup();
                ChromeOptions chromeOpts = new ChromeOptions();
                // chromeOpts.addArguments("--headless=new");  // за потреби
                return new ChromeDriver(chromeOpts);
        }
    }
}
