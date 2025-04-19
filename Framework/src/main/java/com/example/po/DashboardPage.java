package com.example.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

public class DashboardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locator for the logged-in user's link (used to verify login)
    private static final By USER_LINK = By.xpath("//a[contains(@href,'account_page.php') and text()='administrator']");
    // Locator for a dashboard-specific header or element
    private static final By DASHBOARD_HEADER = By.cssSelector(".main-container .page-content .widget-title");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Проверяет, что пользователь успешно залогинен на дашборде.
     * @return true, если элемент с именем пользователя отображается, иначе false
     */
    public boolean isUserLoggedIn() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(USER_LINK)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Проверяет, что страница Dashboard загружена по наличию уникального заголовка.
     * @return true, если заголовок дашборда отображается, иначе false
     */
    public boolean isPageLoaded() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(DASHBOARD_HEADER)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Переходит на страницу списка багов.
     */
    public void navigateToBugs() {
        driver.findElement(By.cssSelector("a[href*='view_all_bug_page.php']")).click();
    }
}
