package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class DemoqaRadioTest {
    WebDriver driver;
    RadioButtonPage radioPage;

    @BeforeClass
    public void setUp() {
        // Налаштування ChromeDriver за допомогою WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Перехід на сторінку радіо-кнопок DemoQA
        driver.get("https://demoqa.com/radio-button");
        radioPage = new RadioButtonPage(driver);
    }

    @Test
    public void testRadioButtonSelections() throws InterruptedException {
        // Обгортаємо елементи "Yes" і "Impressive" у наш Wrapper,
        // передаємо label для кліку і відповідний input для перевірки стану.
        ElementWrapper yesWrapper = new ElementWrapper(
                radioPage.yesRadioLabel, radioPage.yesRadioInput, "Yes");
        ElementWrapper impressiveWrapper = new ElementWrapper(
                radioPage.impressiveRadioLabel, radioPage.impressiveRadioInput, "Impressive");

        // Переконаємося, що "No" кнопка недоступна (disabled)
        Assert.assertFalse(radioPage.noRadioInput.isEnabled(), "'No' radio button should be disabled.");

        // Вибираємо "Yes" та перевіряємо результат
        yesWrapper.select();
        Thread.sleep(1000);  // невелика затримка для відображення ефекту
        Assert.assertTrue(yesWrapper.isSelected(), "Radio button 'Yes' should be selected.");
        String resultAfterYes = radioPage.resultText.getText();
        System.out.println("Result text after selecting Yes: " + resultAfterYes);
        Assert.assertTrue(resultAfterYes.contains("Yes"), "Result text should contain 'Yes'.");

        // Вибираємо "Impressive" та перевіряємо, що "Yes" більше не обраний
        impressiveWrapper.select();
        Thread.sleep(1000);
        Assert.assertTrue(impressiveWrapper.isSelected(), "Radio button 'Impressive' should be selected.");
        Assert.assertFalse(yesWrapper.isSelected(), "Radio button 'Yes' should be deselected after selecting 'Impressive'.");
        String resultAfterImpressive = radioPage.resultText.getText();
        System.out.println("Result text after selecting Impressive: " + resultAfterImpressive);
        Assert.assertTrue(resultAfterImpressive.contains("Impressive"), "Result text should contain 'Impressive'.");

        // Виклик методу deselect (логування, що дія не підтримується)
        impressiveWrapper.deselect();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
