// BugDetailsPage.java
package com.example.po;

import com.example.elements.BaseElement;
import com.example.drivers.DriverPool;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;

public class BugDetailsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "table#buglist tbody tr td.column-id a")
    private WebElement firstBugLinkElement;
    private BaseElement firstBugLink;

    @FindBy(id = "bugnote_text")
    private WebElement commentTextAreaElement;
    private BaseElement commentTextArea;

    @FindBy(css = "form#bugnoteadd input[type='submit'][value='Добавить']")
    private WebElement addCommentButtonElement;
    private BaseElement addCommentButton;

    public BugDetailsPage() {
        this.driver = DriverPool.createDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        this.firstBugLink = new BaseElement(firstBugLinkElement);
        this.commentTextArea = new BaseElement(commentTextAreaElement);
        this.addCommentButton = new BaseElement(addCommentButtonElement);
    }

    public void openFirstBugFromList() {
        firstBugLink.click();
    }

    private void addComment(String comment) {
        commentTextArea.setText(comment);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                addCommentButtonElement
        );
        addCommentButton.click();
    }

    public boolean addCommentAndVerify(String comment) {
        addComment(comment);
        try {
            By commentLocator = By.xpath(
                    "//div[@id='bugnotes']//td[contains(@class,'bugnote-note') and normalize-space(text())='" + comment + "']"
            );
            wait.until(ExpectedConditions.visibilityOfElementLocated(commentLocator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
