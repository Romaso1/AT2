package com.example.po;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class BugDetailsPage {
    private final WebDriver driver;

    public BugDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openFirstBugFromList() {
        driver.findElement(By.cssSelector("table#buglist tbody tr td.column-id a")).click();
    }

    public void addComment(String comment) {
        // Прокрутити до поля введення
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(By.id("bugnote_text")));

        driver.findElement(By.id("bugnote_text")).sendKeys(comment);

        // Прокрутити до кнопки "Добавить" перед кліком
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(By.cssSelector("input[type='submit'][value='Добавить']")));

        driver.findElement(By.cssSelector("input[type='submit'][value='Добавить']")).click();
    }
}
