package com.demoqa.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    static {
        try {
            String path = System.getProperty("user.dir")
                    + "/src/test/resources/config.properties";

            FileInputStream fis = new FileInputStream(path);
            prop = new Properties();
            prop.load(fis);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}