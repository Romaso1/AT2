package com.example.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String url = "http://localhost/mantis/login_page.php";

    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.cssSelector("input[type='submit']");
    // Locator for element present on dashboard after successful login
    private final By userMenu = By.cssSelector(".navbar-buttons .user-info");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get(url);
    }

    public void setUsername(String username) {
        WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        user.clear();
        user.sendKeys(username);
        driver.findElement(loginButton).click(); // Mantis login step 1
    }

    public void setPassword(String password) {
        WebElement pass = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        pass.clear();
        pass.sendKeys(password);
    }

    public void submit() {
        driver.findElement(loginButton).click();
    }

    /**
     * Performs login and returns true if dashboard loaded.
     */
    public boolean loginAndVerify(String username, String password) {
        open();
        setUsername(username);
        setPassword(password);
        submit();
        return isLoginSuccessful();
    }

    /**
     * Checks presence of user menu indicating successful login.
     */
    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(userMenu));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
