// LoginBO.java
package com.example.bo;

import com.example.po.LoginPage;

public class LoginBO {
    private final LoginPage loginPage;

    public LoginBO() {
        this.loginPage = new LoginPage();
    }

    public boolean login(String username, String password) {
        return loginPage.loginAndVerify(username, password);
    }
}