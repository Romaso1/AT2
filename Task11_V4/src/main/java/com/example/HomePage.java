package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Натискання кнопки "Sign up" для відкриття модального вікна
    public void clickSignUp() {
        driver.findElement(By.id("signin2")).click();
    }

    // Натискання кнопки "Log in" для відкриття модального вікна входу
    public void clickLogin() {
        driver.findElement(By.id("login2")).click();
    }

    // Повертає текст привітання користувача (елемент з id "nameofuser")
    public String getWelcomeMessage() {
        return driver.findElement(By.id("nameofuser")).getText();
    }
}
