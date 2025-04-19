package com.example.ui.scenarios;

import com.example.bo.LoginBO;
import com.example.po.DashboardPage;
import com.example.drivers.DriverPool;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;;

public class OpenDashboardTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverPool.createDriver();
    }

    @Test(groups = "ui")
    public void shouldDisplayDashboardAfterLogin() {
        new LoginBO(driver).loginAs("administrator", "root");
        DashboardPage dashboardPage = new DashboardPage(driver);
        assertTrue(dashboardPage.isUserLoggedIn());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
