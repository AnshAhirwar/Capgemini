package com.demoqa.utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;

public class ExcelUtil {

    private static Workbook workbook;
    private static Sheet sheet;

    // 🔹 Load Excel file
    public static void loadExcel(String path, String sheetName) {
        try {
            System.out.println("📂 Loading Excel from: " + path);

            FileInputStream fis = new FileInputStream(path);
            workbook = WorkbookFactory.create(fis);

            int totalSheets = workbook.getNumberOfSheets();
            System.out.println("📊 Total sheets: " + totalSheets);

            for (int i = 0; i < totalSheets; i++) {
                System.out.println("👉 Sheet found: " + workbook.getSheetName(i));
            }

            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("❌ Sheet NOT FOUND: " + sheetName);
            }

        } catch (Exception e) {
            throw new RuntimeException("Excel loading failed: " + e.getMessage());
        }
    }

    // 🔹 Get row count
    public static int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    // 🔹 Get cell data (safe handling)
    public static String getCellData(int row, int col) {

        Row rowObj = sheet.getRow(row);
        if (rowObj == null) return "";

        Cell cell = rowObj.getCell(col);
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue());

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                return cell.getCellFormula();

            default:
                return "";
        }
    }

    // 🔥 🔹 MAIN METHOD FOR CUCUMBER + TESTNG
    public static Object[][] getExcelData(String path, String sheetName) {

        loadExcel(path, sheetName);

        int rows = getRowCount();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rows - 1][cols];

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }

        return data;
    }
}