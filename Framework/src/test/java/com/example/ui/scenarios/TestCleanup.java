package com.example.ui.scenarios;

import com.example.drivers.DriverPool;
import org.testng.annotations.AfterSuite;

/**
 * Гарантує, що браузер буде закрито після завершення всіх тестів.
 */
public class TestCleanup {

    @AfterSuite(alwaysRun = true)
    public void cleanUpSuite() {
        DriverPool.quitDriver();
    }
}
