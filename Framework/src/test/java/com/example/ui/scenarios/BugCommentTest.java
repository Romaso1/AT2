package com.example.ui.scenarios;

import com.example.bo.LoginBO;
import com.example.drivers.DriverPool;
import com.example.po.BugDetailsPage;
import com.example.po.DashboardPage;
import com.example.utils.AllureUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class BugCommentTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = DriverPool.createDriver();
    }

    @Test
    public void shouldAddCommentToBug() {
        new LoginBO(driver).loginAs("administrator", "root");
        new DashboardPage(driver).navigateToBugs();

        BugDetailsPage bugPage = new BugDetailsPage(driver);
        bugPage.openFirstBugFromList();
        bugPage.addComment("Test comment from automation");
    }

    @AfterEach
    public void tearDown() {
        AllureUtils.takeScreenshot(driver);
        driver.quit();
    }
}
