package com.Adaptix.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.Adaptix.base.TestBase;

public class TestUtil extends TestBase {

    public static String screenshotName;

    public static void captureScreenshot() throws IOException {

        // Create timestamp
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Screenshot file name
        screenshotName = System.getProperty("user.dir") + "/test-output/screenshots/" + timeStamp + ".png";

        // Take screenshot
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Create directory if not exists
        File destFile = new File(screenshotName);
        destFile.getParentFile().mkdirs();

        // Save file
        FileUtils.copyFile(src, destFile);

    }
}
