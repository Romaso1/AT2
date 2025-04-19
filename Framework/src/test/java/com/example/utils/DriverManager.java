package com.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> TL_DRIVER = new ThreadLocal<>();

    // уже существующий метод
    public static WebDriver getDriver() {
        if (TL_DRIVER.get() == null) {
            WebDriver driver = new ChromeDriver();
            TL_DRIVER.set(driver);
        }
        return TL_DRIVER.get();
    }

    // новый: только проверяет, есть ли уже инстанс, но не создаёт
    public static boolean hasDriver() {
        return TL_DRIVER.get() != null;
    }

    public static void quitDriver() {
        if (TL_DRIVER.get() != null) {
            TL_DRIVER.get().quit();
            TL_DRIVER.remove();
        }
    }
}
