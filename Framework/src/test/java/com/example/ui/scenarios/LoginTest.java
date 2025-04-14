package com.example.ui.scenarios;

import com.example.drivers.DriverPool;
import com.example.po.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverPool.getDriver("chrome");
        driver.get("http://localhost/mantis/login_page.php");
    }

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("administrator", "root");
        assertTrue(driver.findElement(By.cssSelector("span.user-info")).isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        DriverPool.quitDriver();
    }
}
