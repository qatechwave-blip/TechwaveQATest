package com.Adaptix.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.Adaptix.base.BaseTest;

import org.apache.commons.io.FileUtils;



public class TestUtil extends BaseTest {

    public static String screenshotName;

    public static void captureScreenshot() throws IOException {

        // Create timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Screenshot folder path
        String folderPath = System.getProperty("user.dir") + "/reports/screenshots/";

        // Create folder if not exists
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Screenshot file path
        screenshotName = folderPath + "Screenshot_" + timestamp + ".png";

        // Take screenshot
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Save file
        FileUtils.copyFile(src, new File(screenshotName));

        // Log (optional)
        System.out.println(" Screenshot saved at: " + screenshotName);
    }
}