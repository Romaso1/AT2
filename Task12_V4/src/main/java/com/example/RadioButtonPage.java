package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RadioButtonPage extends BasePage {

    public RadioButtonPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Оскільки стандартний input для радіо-кнопок прихований, ми використовуємо label для кліку,
    // а також отримуємо input для перевірки стану.

    @FindBy(id = "yesRadio")
    public WebElement yesRadioInput;

    @FindBy(css = "label[for='yesRadio']")
    public WebElement yesRadioLabel;

    @FindBy(id = "impressiveRadio")
    public WebElement impressiveRadioInput;

    @FindBy(css = "label[for='impressiveRadio']")
    public WebElement impressiveRadioLabel;

    @FindBy(id = "noRadio")
    public WebElement noRadioInput; // кнопка "No" – зазвичай вимкнена

    // Елемент, де відображається вибір (наприклад, "You have selected Yes")
    @FindBy(css = "p.mt-3")
    public WebElement resultText;
}
