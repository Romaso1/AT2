package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    // Введення імені користувача
    public void enterUsername(String username) {
        WebElement usernameField = driver.findElement(By.id("sign-username"));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    // Введення пароля
    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("sign-password"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    // Натискання кнопки "Sign up" (шукаємо кнопку за її текстом)
    public void clickSignUpButton() {
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
    }
}
