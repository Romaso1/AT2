package com.example.ui.scenarios;

import com.example.bo.LoginBO;
import com.example.drivers.DriverPool;
import com.example.po.BugDetailsPage;
import com.example.po.DashboardPage;
import com.example.utils.AllureUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

public class BugCommentTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverPool.createDriver();
    }

    @Test(groups = "ui")
    public void shouldAddCommentToBug() {
        new LoginBO(driver).loginAs("administrator", "root");
        new DashboardPage(driver).navigateToBugs();

        BugDetailsPage bugPage = new BugDetailsPage(driver);
        bugPage.openFirstBugFromList();
        bugPage.addCommentAndVerify("Test comment from automation");
    }

    @AfterMethod
    public void tearDown() {
        AllureUtils.takeScreenshot(driver);
        driver.quit();
    }
}
