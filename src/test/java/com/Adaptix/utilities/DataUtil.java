package com.Adaptix.utilities;

import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;

public class DataUtil {

    private static ExcelReader excel = new ExcelReader(
            System.getProperty("user.dir") + "/src/test/resources/excel/testdata.xlsx"
    );

    @DataProvider(name = "dp1")
    public static Object[][] getData(Method m) {

        String sheetName = m.getName();

        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        if (rows <= 1) return new Object[0][0];

        Object[][] data = new Object[rows - 1][cols];

        for (int i = 2; i <= rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 2][j] = excel.getCellData(sheetName, j, i);
            }
        }

        return data;
    }
}