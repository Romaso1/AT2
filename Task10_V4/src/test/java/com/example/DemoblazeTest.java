package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoblazeTest {

    // Тестовий метод з використанням ручного налаштування драйвера
    @Test
    public void testWithManualDriver() throws InterruptedException {
        // 1. Налаштування ChromeDriver через System.setProperty
        // Замініть "path/to/chromedriver" на фактичний шлях до вашого файлу chromedriver.exe (або без розширення в Unix-системах)
        System.setProperty("webdriver.chrome.driver", "C:/Users/romad/IdeaProjects/Task10/src/test/java/com/example/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            // 3. Перехід на сторінку Demoblaze
            driver.get("https://www.demoblaze.com/");
            Thread.sleep(2000);

            // 4. Вибір трьох елементів: кнопки Log in, Sign up і Cart (ідентифікатори з сайту)
            WebElement loginButton = driver.findElement(By.id("login2"));
            WebElement signUpButton = driver.findElement(By.id("signin2"));
            WebElement cartButton = driver.findElement(By.id("cartur"));

            // 6. Перевірка видимості елементів
            Assert.assertTrue(loginButton.isDisplayed(), "Кнопка 'Log in' не відображається!");
            Assert.assertTrue(signUpButton.isDisplayed(), "Кнопка 'Sign up' не відображається!");
            Assert.assertTrue(cartButton.isDisplayed(), "Кнопка 'Cart' не відображається!");

            // 5. Взаємодія з елементами:
            // Клік по кнопці Log in та перевірка появи модального вікна
            loginButton.click();
            Thread.sleep(2000);
            WebElement loginModal = driver.findElement(By.id("logInModal"));
            Assert.assertTrue(loginModal.isDisplayed(), "Модальне вікно 'Log in' не відкрилося!");

            // Закриття модального вікна Log in (припустимо, що є кнопка Close)
            WebElement loginClose = loginModal.findElement(By.xpath(".//button[text()='Close']"));
            loginClose.click();
            Thread.sleep(2000);

            // Клік по кнопці Sign up та перевірка появи модального вікна
            signUpButton.click();
            Thread.sleep(2000);
            WebElement signUpModal = driver.findElement(By.id("signInModal"));
            Assert.assertTrue(signUpModal.isDisplayed(), "Модальне вікно 'Sign up' не відкрилося!");

            // Закриття модального вікна Sign up
            WebElement signUpClose = signUpModal.findElement(By.xpath(".//button[text()='Close']"));
            signUpClose.click();
            Thread.sleep(2000);

            // Клік по кнопці Cart та перевірка, що сторінка Cart завантажилася (наприклад, перевірка URL)
            cartButton.click();
            Thread.sleep(3000);
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("cart"), "Сторінка 'Cart' не завантажилася!");
        } finally {
            driver.quit();
        }
    }

    // Тестовий метод з використанням WebDriverManager для автоматичного налаштування ChromeDriver
    @Test
    public void testWithWebDriverManager() throws InterruptedException {
        // 2. Використання WebDriverManager для налаштування драйвера
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // 3. Перехід на сторінку Demoblaze
            driver.get("https://www.demoblaze.com/");
            Thread.sleep(2000);

            // 4. Вибір трьох елементів
            WebElement loginButton = driver.findElement(By.id("login2"));
            WebElement signUpButton = driver.findElement(By.id("signin2"));
            WebElement cartButton = driver.findElement(By.id("cartur"));

            // 6. Перевірка видимості елементів
            Assert.assertTrue(loginButton.isDisplayed(), "Кнопка 'Log in' не відображається!");
            Assert.assertTrue(signUpButton.isDisplayed(), "Кнопка 'Sign up' не відображається!");
            Assert.assertTrue(cartButton.isDisplayed(), "Кнопка 'Cart' не відображається!");

            // 5. Взаємодія з елементами:
            // Клік по кнопці Log in, перевірка модального вікна та його закриття
            loginButton.click();
            Thread.sleep(2000);
            WebElement loginModal = driver.findElement(By.id("logInModal"));
            Assert.assertTrue(loginModal.isDisplayed(), "Модальне вікно 'Log in' не відкрилося!");
            WebElement loginClose = loginModal.findElement(By.xpath(".//button[text()='Close']"));
            loginClose.click();
            Thread.sleep(2000);

            // Клік по кнопці Sign up, перевірка модального вікна та його закриття
            signUpButton.click();
            Thread.sleep(2000);
            WebElement signUpModal = driver.findElement(By.id("signInModal"));
            Assert.assertTrue(signUpModal.isDisplayed(), "Модальне вікно 'Sign up' не відкрилося!");
            WebElement signUpClose = signUpModal.findElement(By.xpath(".//button[text()='Close']"));
            signUpClose.click();
            Thread.sleep(2000);

            // Клік по кнопці Cart та перевірка завантаження сторінки
            cartButton.click();
            Thread.sleep(3000);
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("cart"), "Сторінка 'Cart' не завантажилася!");
        } finally {
            driver.quit();
        }
    }
}
