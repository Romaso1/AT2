package com.example.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(css = "input[type='submit']")
    private WebElement submitButton;

    @FindBy(name = "password")
    private WebElement passwordInput;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        submitButton.click(); // submit username
        passwordInput.sendKeys(password);
        submitButton.click(); // submit password
    }
}
