package com.example;

import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import ru.sbtqa.monte.media.Format;
import ru.sbtqa.monte.media.math.Rational;

import java.awt.*;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static ru.sbtqa.monte.media.FormatKeys.*;
import static ru.sbtqa.monte.media.VideoFormatKeys.*;

public class CustomListener implements ITestListener, ISuiteListener, IExecutionListener, IInvokedMethodListener {

    private static final Logger logger = Logger.getLogger(CustomListener.class);

    // Карта для хранения экземпляров видео-записывателя для каждого теста
    private Map<String, MonteScreenRecorder> recorders = new HashMap<>();

    static {
        // Загружаем конфигурацию log4j (файл log4j.properties должен быть в classpath)
        PropertyConfigurator.configure("log4j.properties");
    }

    // ITestListener методы
    @Override
    public void onStart(ITestContext context) {
        logger.info("onStart(ITestContext): Завантаження тестових даних та запуск браузера.");
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("onTestStart: Тест " + result.getName() + " починається.");
        try {
            GraphicsConfiguration gc = GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration();

            Rectangle captureArea = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            Format fileFormat = new Format(
                    MediaTypeKey, MediaType.FILE,
                    MimeTypeKey, MIME_QUICKTIME
            );

            Format screenFormat = new Format(
                    MediaTypeKey, MediaType.VIDEO,
                    EncodingKey, ENCODING_QUICKTIME_ANIMATION,
                    CompressorNameKey, COMPRESSOR_NAME_QUICKTIME_ANIMATION,
                    DepthKey, 24,
                    FrameRateKey, Rational.valueOf(15),
                    QualityKey, 1.0f,
                    KeyFrameIntervalKey, 15 * 60
            );

            Format mouseFormat = null;
            Format audioFormat = null;

            MonteScreenRecorder recorder = new MonteScreenRecorder(
                    gc, captureArea,
                    fileFormat, screenFormat,
                    mouseFormat, audioFormat,
                    new File("videos"), result.getName()
            );
            recorder.start();
            recorders.put(result.getName(), recorder);
        } catch (Exception e) {
            logger.error("Не вдалося запустити відеозапис для тесту " + result.getName(), e);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("onTestSuccess: Тест " + result.getName() + " пройшов успішно.");
        stopAndAttachVideo(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("onTestFailure: Тест " + result.getName() + " провалено. Збираємо артефакти.");

        // Получаем WebDriver из контекста теста
        Object driverObj = result.getTestContext().getAttribute("WebDriver");
        if (driverObj instanceof WebDriver) {
            WebDriver driver = (WebDriver) driverObj;
            // Прикрепляем скриншот и HTML страницы к Allure
            saveScreenshotPNG(driver);
            savePageSource(driver);
        }

        // Сохраняем HTML страницы в файл (для локального хранения артефактов)
        String testName = result.getName();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        String fileName = testName + "_" + uniqueId + "_" + timestamp + ".html";
        File dir = new File("failures");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try (FileWriter fw = new FileWriter(new File(dir, fileName))) {
            String pageSource = (driverObj instanceof WebDriver)
                    ? ((WebDriver) driverObj).getPageSource()
                    : "<html><body>No Source</body></html>";
            fw.write(pageSource);
            logger.info("onTestFailure: HTML збережено у файл " + fileName);
        } catch (IOException e) {
            logger.error("onTestFailure: Помилка при збереженні HTML", e);
        }
        stopAndAttachVideo(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("onTestSkipped: Тест " + result.getName() + " пропущено.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Не використовується у цьому прикладі
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("onFinish(ITestContext): Завершення тестового контексту: " + context.getName());
    }

    // ISuiteListener методы
    @Override
    public void onStart(ISuite suite) {
        logger.info("onStart(ISuite): Запуск сьюту " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        logger.info("onFinish(ISuite): Завершення сьюту " + suite.getName());
    }

    // IExecutionListener методы
    @Override
    public void onExecutionStart() {
        logger.info("onExecutionStart: Початок виконання тестів.");
    }

    @Override
    public void onExecutionFinish() {
        logger.info("onExecutionFinish: Завершення виконання тестів.");
    }

    // IInvokedMethodListener методы
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        logger.info("beforeInvocation: Перед викликом методу " + method.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        logger.info("afterInvocation: Після виклику методу " + method.getTestMethod().getMethodName());
    }

    // Методы для прикреплення артефактів в Allure

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        logger.info("Збереження скріншоту для Allure.");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page Source", type = "text/html")
    public String savePageSource(WebDriver driver) {
        logger.info("Збереження DOM (HTML) сторінки для Allure.");
        return driver.getPageSource();
    }

    @Attachment(value = "Test Video", type = "video/mp4")
    public byte[] attachVideo(String filePath) throws IOException {
        logger.info("Прикріплення відео до Allure-звіту: " + filePath);
        return Files.readAllBytes(Paths.get(filePath));
    }

    // Остановить видео и прикрепить файл к Allure
    private void stopAndAttachVideo(ITestResult result) {
        try {
            MonteScreenRecorder recorder = recorders.get(result.getName());
            if (recorder != null) {
                recorder.stop();
                recorders.remove(result.getName());
                // Получаем созданный файл видео
                File videoFile = recorder.getCreatedMovieFiles().get(0);
                if (videoFile != null && videoFile.exists()) {
                    attachVideo(videoFile.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            logger.error("Не вдалося зупинити відеозапис або прикріпити відео для тесту " + result.getName(), e);
        }
    }
}
