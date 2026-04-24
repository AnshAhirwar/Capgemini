package com.demoqa.hooks;

import com.demoqa.base.DriverFactory;
import com.demoqa.utils.ConfigReader;
import com.demoqa.utils.JMeterAllureConverter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

import io.qameta.allure.Allure;

public class Hooks {

    // 🔥 Runs before each scenario
    @Before
    public void setup() {

        // Initialize browser
        DriverFactory.initDriver();

        // Get URL from config
        String url = ConfigReader.get("url");

        // Debug print
        System.out.println("🌐 Opening URL: " + url);

        // Open application
        DriverFactory.getDriver().get(url);
    }

    // 🔥 Runs after each scenario
    @After
    public void tearDown(Scenario scenario) {

        // Capture screenshot if failed
        if (scenario.isFailed()) {

            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(
                    "Failure Screenshot",
                    new ByteArrayInputStream(screenshot)
            );
        }

        // Quit browser
        DriverFactory.quitDriver();
    }

    // 🔥 Runs once after all scenarios
    @AfterAll
    public static void attachJMeterResults() {

        System.out.println("📊 Attaching JMeter results to Allure...");

        JMeterAllureConverter.attachJMeterResults();
    }
}