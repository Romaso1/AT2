package com.example.listeners;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.example.utils.DriverManager;

public class ScreenshotListener implements ITestListener {

    private void attachScreenshot(String title) {
        try {
            WebDriver driver = DriverManager.getDriver();
            if (driver == null) {
                // драйвер ещё не поднят — пропускаем
                return;
            }
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            if (screenshot.length == 0) {
                // ничего не снялось
                return;
            }
            Allure.getLifecycle().addAttachment(
                    title,
                    "image/png",
                    "png",
                    screenshot
            );
        } catch (Exception e) {
            System.err.println("Could not capture screenshot: " + e.getMessage());
        }
    }

    // убираем onStart/ onTestStart — они дают пустые скриншоты
    @Override public void onStart(ITestContext context) { /* не нужен */ }
    @Override public void onTestStart(ITestResult result) { /* не нужен */ }

    @Override
    public void onTestSuccess(ITestResult result) {
        attachScreenshot("✅ TEST SUCCESS: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenshot("❌ TEST FAILURE: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        attachScreenshot("⚠️ TEST SKIPPED: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        attachScreenshot("ℹ️ TEST PARTIAL FAILURE: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        // если у вас браузер ещё не закрыт — можно сделать итоговый скриншот
        attachScreenshot("🏁 SUITE FINISH: " + context.getName());
    }
}
