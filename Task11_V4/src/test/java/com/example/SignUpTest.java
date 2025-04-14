package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SignUpTest {
    private WebDriver driver;
    private HomePage homePage;
    // Генеруємо унікальне ім'я користувача, щоб уникнути повторень
    private String username = "testuser" + System.currentTimeMillis();
    private String password = "StrongPass123";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Перехід на домашню сторінку Demoblaze
        driver.get("https://www.demoblaze.com/");
        homePage = new HomePage(driver);
    }

    @Test
    public void testSignUpAndLogin() throws InterruptedException {
        // --- Sign Up ---
        // Натискаємо кнопку "Sign up" на домашній сторінці
        homePage.clickSignUp();
        // Затримка, щоб дочекатися появи модального вікна
        Thread.sleep(2000);
        // Створюємо об'єкт SignUpPage і заповнюємо форму
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterUsername(username);
        signUpPage.enterPassword(password);
        signUpPage.clickSignUpButton();

        // Очікуємо появу alert і приймаємо його
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert text: " + alert.getText());
        alert.accept();
        Thread.sleep(2000);

        // --- Log In ---
        // Натискаємо кнопку "Log in" на домашній сторінці
        homePage.clickLogin();
        Thread.sleep(2000);
        // Створюємо об'єкт LoginPage і заповнюємо форму входу
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        // Чекаємо деякий час, щоб вхід завершився
        Thread.sleep(3000);

        // --- Перевірка ---
        // Повертаємо привітальне повідомлення і перевіряємо, що воно містить ім'я користувача
        String welcomeMsg = homePage.getWelcomeMessage();
        Assert.assertTrue(welcomeMsg.contains(username),
                "Welcome message does not contain the username: " + username);
    }

    @AfterClass
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
