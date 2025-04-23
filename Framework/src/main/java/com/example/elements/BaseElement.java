// BaseElement.java
package com.example.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.drivers.DriverPool;
import java.time.Duration;

/**
 * Wrapper for WebElement using WebDriver from DriverPool.
 */
public class BaseElement {
    private final WebElement element;
    private final WebDriverWait wait;

    /**
     * Constructor: obtains WebDriver from DriverPool.
     */
    public BaseElement(WebElement element) {
        this.element = element;
        this.wait = new WebDriverWait(DriverPool.createDriver(), Duration.ofSeconds(10));
    }

    /**
     * Clicks the element after waiting until it is clickable.
     */
    public void click() {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    /**
     * Clears and sets text on the element after waiting until it is visible.
     */
    public void setText(String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Returns the visible text of the element after waiting until it is visible.
     */
    public String getText() {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
}