// BugCommentTest.java
package com.example.ui.scenarios;

import com.example.bo.LoginBO;
import com.example.po.DashboardPage;
import com.example.po.BugDetailsPage;
import com.example.drivers.DriverPool;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class BugCommentTest {
    @Test
    public void addAndVerifyComment() {
        LoginBO loginBO = new LoginBO();
        DashboardPage dashboard = new DashboardPage();
        BugDetailsPage bugPage = new BugDetailsPage();
        String comment = "Test via PageFactory";
        Assert.assertTrue(loginBO.login("administrator", "root"), "Login should succeed");
        dashboard.navigateToBugs();
        bugPage.openFirstBugFromList();
        Assert.assertTrue(bugPage.addCommentAndVerify(comment), "Comment should appear on page");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverPool.quitDriver();
    }
}