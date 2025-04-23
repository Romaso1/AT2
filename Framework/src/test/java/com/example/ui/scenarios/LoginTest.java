// LoginTest.java
package com.example.ui.scenarios;

import com.example.po.LoginPage;
import com.example.drivers.DriverPool;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class LoginTest {
    @Test
    public void loginTest() {
        LoginPage loginPage = new LoginPage();
        boolean success = loginPage.loginAndVerify("administrator", "root");
        Assert.assertTrue(success, "Login should be successful");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverPool.quitDriver();
    }
}
