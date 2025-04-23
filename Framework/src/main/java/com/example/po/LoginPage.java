// LoginPage.java
package com.example.po;

import com.example.elements.BaseElement;
import com.example.drivers.DriverPool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String url = "http://localhost/mantis/login_page.php";

    @FindBy(name = "username")
    private WebElement usernameFieldElement;
    private BaseElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordFieldElement;
    private BaseElement passwordField;

    @FindBy(css = "input[type='submit']")
    private WebElement loginButtonElement;
    private BaseElement loginButton;

    @FindBy(css = ".navbar-buttons .user-info")
    private WebElement userMenuElement;
    private BaseElement userMenu;

    public LoginPage() {
        this.driver = DriverPool.createDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        this.usernameField = new BaseElement(usernameFieldElement);
        this.passwordField = new BaseElement(passwordFieldElement);
        this.loginButton = new BaseElement(loginButtonElement);
        this.userMenu = new BaseElement(userMenuElement);
    }

    public void open() {
        driver.get(url);
    }

    public boolean loginAndVerify(String username, String password) {
        open();
        // Step 1: enter username and proceed
        usernameField.setText(username);
        loginButton.click();
        // wait for password field to appear
        wait.until(ExpectedConditions.visibilityOf(passwordFieldElement));
        // Step 2: enter password and submit
        passwordField.setText(password);
        loginButton.click();
        // verify user menu
        try {
            wait.until(ExpectedConditions.visibilityOf(userMenuElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}