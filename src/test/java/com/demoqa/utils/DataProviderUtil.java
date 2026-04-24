package com.demoqa.utils;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {

    @DataProvider(name = "excelData", parallel = true)
    public static Object[][] getData() {

        String path = System.getProperty("user.dir") + "/"
                + ConfigReader.get("excelPath");

        String sheet = ConfigReader.get("sheetName");

        ExcelUtil.loadExcel(path, sheet);

        int rows = ExcelUtil.getRowCount();

        Object[][] data = new Object[rows - 1][4];

        for (int i = 1; i < rows; i++) {
            data[i - 1][0] = ExcelUtil.getCellData(i, 0);
            data[i - 1][1] = ExcelUtil.getCellData(i, 1);
            data[i - 1][2] = ExcelUtil.getCellData(i, 2);
            data[i - 1][3] = ExcelUtil.getCellData(i, 3);
        }

        return data;
    }
}