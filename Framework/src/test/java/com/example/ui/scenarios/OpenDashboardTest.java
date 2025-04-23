// OpenDashboardTest.java
package com.example.ui.scenarios;

import com.example.bo.LoginBO;
import com.example.po.DashboardPage;
import com.example.drivers.DriverPool;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class OpenDashboardTest {
    @Test
    public void shouldDisplayDashboardAfterLogin() {
        LoginBO loginBO = new LoginBO();
        DashboardPage dashboard = new DashboardPage();
        Assert.assertTrue(loginBO.login("administrator", "root"), "Login should succeed");
        dashboard.navigateToBugs();
        Assert.assertTrue(dashboard.isPageLoaded(), "Dashboard should be loaded");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverPool.quitDriver();
    }
}