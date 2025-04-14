package com.example.ui.dataproviders;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
            {"administrator", "root"},
            {"invalid", "invalid"}
        };
    }
}
