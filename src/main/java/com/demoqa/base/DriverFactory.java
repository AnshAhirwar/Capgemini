package com.demoqa.base;

import com.demoqa.utils.ConfigReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;

import java.net.URL;

public class DriverFactory {

    // Thread-safe driver (VERY IMPORTANT for parallel)
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver() {

        try {

            String gridUrl = "http://localhost:4444";
            String browser = ConfigReader.get("browser");

            System.out.println("🌐 Starting browser: " + browser);

            if (browser.equalsIgnoreCase("chrome")) {

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");

                driver.set(new RemoteWebDriver(new URL(gridUrl), options));

            } else if (browser.equalsIgnoreCase("firefox")) {

                FirefoxOptions options = new FirefoxOptions();

                driver.set(new RemoteWebDriver(new URL(gridUrl), options));

            } else if (browser.equalsIgnoreCase("edge")) {

                EdgeOptions options = new EdgeOptions();

                driver.set(new RemoteWebDriver(new URL(gridUrl), options));

            } else {
                throw new RuntimeException("❌ Invalid browser in config: " + browser);
            }

            // Maximize window
            driver.get().manage().window().maximize();

        } catch (Exception e) {
            throw new RuntimeException("Driver initialization failed: " + e.getMessage());
        }

        return driver.get();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}