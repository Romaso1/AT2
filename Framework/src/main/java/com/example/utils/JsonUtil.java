package com.example.utils;

import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public class JsonUtil {
    public static void attachJson(String name, String json) {
        Allure.addAttachment(name, "application/json", json);
    }
}
