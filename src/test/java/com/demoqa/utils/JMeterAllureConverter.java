package com.demoqa.utils;

import io.qameta.allure.Allure;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JMeterAllureConverter {

    public static void attachJMeterResults() {

        try {
            String path = "target/jmeter/results.jtl";

            if (Files.exists(Paths.get(path))) {

                byte[] content = Files.readAllBytes(Paths.get(path));

                Allure.addAttachment("JMeter Results",
                        "text/xml",
                        new ByteArrayInputStream(content),
                        ".xml");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}