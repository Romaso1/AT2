package com.example.bo;

import com.example.po.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginBO {
    private final WebDriver driver;
    private final LoginPage loginPage;

    public LoginBO(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    public void loginAs(String username, String password) {
        loginPage.open();
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.submit();
    }
}
