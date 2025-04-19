package com.example.ui.scenarios;

import com.example.po.LoginPage;
import com.example.drivers.DriverPool;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

public class LoginTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverPool.createDriver();
    }

    @Test(groups = "ui")
    public void loginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.loginAndVerify("administrator", "root");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
