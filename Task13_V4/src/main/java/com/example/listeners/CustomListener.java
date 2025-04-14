package com.example.listeners;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.*;

import org.testng.IInvokedMethodListener;
import org.testng.IInvokedMethod;
import org.testng.ITestResult;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.IExecutionListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CustomListener implements ITestListener, ISuiteListener, IExecutionListener, IInvokedMethodListener {

    private static final Logger logger = Logger.getLogger(CustomListener.class);

    static {
        // Завантаження конфігурації log4j
        PropertyConfigurator.configure("log4j.properties");
    }

    // ITestListener методи

    // Завдання 1: onStart() – завантаження тестових даних з локальної БД та запуск браузера.
    @Override
    public void onStart(ITestContext context) {
        logger.info("onStart (ITestContext): Завантаження тестових даних з локальної БД та запуск браузера.");
        // Наприклад: TestData.loadFromDB();
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("onTestStart: Тест " + result.getName() + " починається.");
    }

    // Завдання 5: onTestFailure() – збереження HTML сторінки у файл та запуск відеозапису.
    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("onTestFailure: Тест " + result.getName() + " провалено. Збереження HTML-коду сторінки.");
        String testName = result.getName();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        String fileName = testName + "_" + uniqueId + "_" + timestamp + ".html";

        // Припускаємо, що ми можемо отримати сторінковий HTML код із тестового контексту.
        // У реальному сценарії, коли є WebDriver, можна використовувати driver.getPageSource().
        Object htmlObj = result.getAttribute("pageSource");
        String pageSource = (htmlObj != null) ? htmlObj.toString() : "<html><body>Немає HTML</body></html>";

        File dir = new File("failures");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (FileWriter fw = new FileWriter(new File(dir, fileName))) {
            fw.write(pageSource);
            logger.info("onTestFailure: HTML збережено у файл " + fileName);
        } catch (IOException e) {
            logger.error("onTestFailure: Помилка при збереженні HTML", e);
        }

        // Виклик відеозапису (симуляція)
        simulateVideoRecording();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("onTestSuccess: Тест " + result.getName() + " успішно пройшов.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("onTestSkipped: Тест " + result.getName() + " пропущено.");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("onFinish (ITestContext): Завершення тестового контексту " + context.getName());
    }

    // ISuiteListener методи
    @Override
    public void onStart(ISuite suite) {
        logger.info("onStart (ISuite): Запуск сьюту " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        logger.info("onFinish (ISuite): Завершення сьюту " + suite.getName());
    }

    // IExecutionListener методи
    @Override
    public void onExecutionStart() {
        logger.info("onExecutionStart (IExecutionListener): Початок виконання тестів.");
    }

    // Завдання 4: onExecutionFinish() – логування завершення виконання як в консоль, так і в локальну БД.
    @Override
    public void onExecutionFinish() {
        logger.info("onExecutionFinish (IExecutionListener): Завершення виконання тестів.");
        // Наприклад: DBLogger.logExecutionEnd(new Date());
    }

    // IInvokedMethodListener методи
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        logger.info("beforeInvocation: Перед викликом методу " +
                method.getTestMethod().getMethodName());
    }

    // Завдання 6: afterInvocation() – логування завершення тесту та закриття браузера, якщо потрібно.
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        logger.info("afterInvocation: Завершено виклик методу " +
                method.getTestMethod().getMethodName());
        logger.info("afterInvocation: Закриття браузера, якщо це потрібно (логічний блок).");
        // Наприклад, якщо використовуєте thread-local WebDriver, можна перевірити та закрити його тут.
    }

    // Симуляція відеозапису – замініть цю частину на реальну інтеграцію з бібліотекою відеозапису.
    private void simulateVideoRecording() {
        logger.info("simulateVideoRecording: Старт запису відео з провалу тесту...");
        try {
            Thread.sleep(1000); // симулюємо затримку
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        logger.info("simulateVideoRecording: Відеозапис завершено.");
    }
}
