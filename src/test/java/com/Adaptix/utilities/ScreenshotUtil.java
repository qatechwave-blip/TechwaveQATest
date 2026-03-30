package com.Adaptix.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver) {

        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String path = System.getProperty("user.dir") + "/reports/screenshots/";

        new File(path).mkdirs();

        String filePath = path + "Screenshot_" + time + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filePath;
    }
}