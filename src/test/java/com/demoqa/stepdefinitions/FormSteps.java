package com.demoqa.stepdefinitions;

import com.demoqa.pages.FormPage;
import com.demoqa.utils.ConfigReader;
import com.demoqa.utils.ExcelUtil;
import com.demoqa.base.DriverFactory;

import io.cucumber.java.en.When;

public class FormSteps {

    FormPage formPage = new FormPage();

    @When("user fills the form from excel")
    public void user_fills_the_form_from_excel() {

        String path = System.getProperty("user.dir") + "/"
                + ConfigReader.get("excelPath");

        String sheet = ConfigReader.get("sheetName");

        ExcelUtil.loadExcel(path, sheet);

        int rows = ExcelUtil.getRowCount();

        for (int i = 1; i < rows; i++) {

            DriverFactory.getDriver().get(ConfigReader.get("url"));

            String first = ExcelUtil.getCellData(i, 0);
            String last = ExcelUtil.getCellData(i, 1);
            String email = ExcelUtil.getCellData(i, 2);
            String mobile = ExcelUtil.getCellData(i, 3);

            formPage.enterFirstName(first);
            formPage.enterLastName(last);
            formPage.enterEmail(email);
            formPage.enterMobile(mobile);

            formPage.clickSubmit();
        }
    }
}