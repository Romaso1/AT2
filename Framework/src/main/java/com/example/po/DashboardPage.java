package com.example.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private final WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isUserLoggedIn() {
        return driver.findElement(By.xpath("//a[contains(@href,'account_page.php') and text()='administrator']")).isDisplayed();
    }

    public void navigateToBugs() {
        driver.findElement(By.cssSelector("a[href*='view_all_bug_page.php']")).click();
    }
}
