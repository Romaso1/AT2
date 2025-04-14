package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Введення імені користувача для входу
    public void enterUsername(String username) {
        WebElement usernameField = driver.findElement(By.id("loginusername"));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    // Введення пароля для входу
    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    // Натискання кнопки "Log in"
    public void clickLoginButton() {
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
    }
}
