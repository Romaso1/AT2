package com.example.ui.scenarios;

import com.example.po.LoginPage;
import com.example.drivers.DriverPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class LoginTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = DriverPool.createDriver();
    }

    @Test
    public void loginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("administrator", "root");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
