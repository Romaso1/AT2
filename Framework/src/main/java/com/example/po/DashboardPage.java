// DashboardPage.java
package com.example.po;

import com.example.elements.BaseElement;
import com.example.drivers.DriverPool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;

public class DashboardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//a[contains(@href,'account_page.php') and text()='administrator']")
    private WebElement userLinkElement;
    private BaseElement userLink;

    @FindBy(css = ".main-container .page-content .widget-title")
    private WebElement dashboardHeaderElement;

    @FindBy(css = "a[href*='view_all_bug_page.php']")
    private WebElement viewAllBugsLinkElement;
    private BaseElement viewAllBugsLink;

    public DashboardPage() {
        this.driver = DriverPool.createDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        this.userLink = new BaseElement(userLinkElement);
        this.viewAllBugsLink = new BaseElement(viewAllBugsLinkElement);
    }

    public boolean isUserLoggedIn() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(userLinkElement)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isPageLoaded() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(dashboardHeaderElement)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void navigateToBugs() {
        viewAllBugsLink.click();
    }
}