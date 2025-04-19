package com.example.po;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BugDetailsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public BugDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Clicks the first bug in the bug list table.
     */
    public void openFirstBugFromList() {
        WebElement firstLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("table#buglist tbody tr td.column-id a")));
        firstLink.click();
    }

    /**
     * Adds a comment via the "Добавить комментарий" form and waits for it to appear.
     * @param comment текст комментариия
     * @return true если комментарий появился на странице, иначе false
     */
    public boolean addCommentAndVerify(String comment) {
        addComment(comment);
        return isCommentPresent(comment);
    }

    /**
     * Добавляет комментарий, но не проверяет результат.
     */
    private void addComment(String comment) {
        // 1) Wait for & scroll the textarea into the center of the viewport
        WebElement textarea = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("bugnote_text")));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", textarea);
        textarea.clear();
        textarea.sendKeys(comment);

        // 2) Find the correct submit button in the comment form
        By submitLocator = By.cssSelector(
                "form#bugnoteadd input[type='submit'][value='Добавить']");
        WebElement submit = wait.until(
                ExpectedConditions.elementToBeClickable(submitLocator));

        // 3) Scroll that button into center & click it via JS (bypasses sticky nav overlap)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", submit);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", submit);
    }

    /**
     * Проверяет, что комментарий появился в списке комментариев.
     */
    public boolean isCommentPresent(String comment) {
        // Ожидаем до 10 секунд появления текста комментария
        try {
            By commentLocator = By.xpath(
                    "//div[@id='bugnotes']//td[contains(@class,'bugnote-note') and normalize-space(text())='" + comment + "']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(commentLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
