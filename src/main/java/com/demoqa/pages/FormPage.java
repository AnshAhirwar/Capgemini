package com.demoqa.pages;

import com.demoqa.base.DriverFactory;
import io.qameta.allure.Step;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class FormPage {

    WebDriver driver = DriverFactory.getDriver();

    By firstName = By.id("firstName");
    By lastName = By.id("lastName");
    By email = By.id("userEmail");
    By mobile = By.id("userNumber");
    By submitBtn = By.id("submit");

    // 🔥 Allure Step
    @Step("Enter First Name: {0}")
    public void enterFirstName(String name) {
        driver.findElement(firstName).sendKeys(name);
    }

    @Step("Enter Last Name: {0}")
    public void enterLastName(String name) {
        driver.findElement(lastName).sendKeys(name);
    }

    @Step("Enter Email: {0}")
    public void enterEmail(String mail) {
        driver.findElement(email).sendKeys(mail);
    }

    @Step("Enter Mobile: {0}")
    public void enterMobile(String mob) {
        driver.findElement(mobile).sendKeys(mob);
    }

    @Step("Click Submit Button")
    public void clickSubmit() {

        WebElement submit = driver.findElement(submitBtn);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", submit);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", submit);
    }
}