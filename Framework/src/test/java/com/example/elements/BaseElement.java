package com.example.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import com.example.utils.DriverManager;

public class BaseElement {
    protected WebElement element;
    private WebDriverWait wait;

    public BaseElement(WebElement element) {
        this.element = element;
        this.wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
    }

    public void click() {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void setText(String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    public String getText() {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
}