package com.Adaptix.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {

    public String path;
    public FileInputStream fis = null;
    public FileOutputStream fileOut = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;

    public ExcelReader(String path) {
        this.path = path;
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Row Count
    public int getRowCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return 0;

        sheet = workbook.getSheetAt(index);
        return sheet.getLastRowNum() + 1;
    }

    // ✅ Column Count
    public int getColumnCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return -1;

        sheet = workbook.getSheetAt(index);
        row = sheet.getRow(0);
        if (row == null)
            return -1;

        return row.getLastCellNum();
    }

    // ✅ Get Cell Data (by column name)
    public String getCellData(String sheetName, String colName, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);

            int colNum = -1;
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
                    colNum = i;
                    break;
                }
            }

            if (colNum == -1)
                return "";

            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";

            cell = row.getCell(colNum);
            return getCellValue(cell);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // ✅ Get Cell Data (by index)
    public String getCellData(String sheetName, int colNum, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";

            cell = row.getCell(colNum);
            return getCellValue(cell);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // ✅ 🔥 Common Cell Value Handler (IMPORTANT FIX)
    private String getCellValue(Cell cell) {

        if (cell == null)
            return "";

        if (cell.getCellType() == CellType.STRING)
            return cell.getStringCellValue();

        else if (cell.getCellType() == CellType.NUMERIC) {

            // ✅ FIXED: HSSFDateUtil → DateUtil
            if (DateUtil.isCellDateFormatted(cell)) {

                Calendar cal = Calendar.getInstance();
                cal.setTime(cell.getDateCellValue());

                return cal.get(Calendar.DAY_OF_MONTH) + "/" +
                        (cal.get(Calendar.MONTH) + 1) + "/" +
                        cal.get(Calendar.YEAR);
            }

            return String.valueOf(cell.getNumericCellValue());
        }

        else if (cell.getCellType() == CellType.BOOLEAN)
            return String.valueOf(cell.getBooleanCellValue());

        else if (cell.getCellType() == CellType.BLANK)
            return "";

        return "";
    }

    // ✅ Set Cell Data
    public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return false;

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);

            int colNum = -1;
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName)) {
                    colNum = i;
                    break;
                }
            }

            if (colNum == -1)
                return false;

            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            cell.setCellValue(data);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}