package com.example.ui.scenarios;

import com.example.bo.LoginBO;
import com.example.po.DashboardPage;
import com.example.drivers.DriverPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenDashboardTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = DriverPool.createDriver();
    }

    @Test
    public void shouldDisplayDashboardAfterLogin() {
        new LoginBO(driver).loginAs("administrator", "root");
        DashboardPage dashboardPage = new DashboardPage(driver);
        assertTrue(dashboardPage.isUserLoggedIn());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
