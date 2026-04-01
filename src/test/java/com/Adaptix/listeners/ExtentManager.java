package com.Adaptix.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.Adaptix.base.BaseTest;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;
    public static String fileName;

    public static ExtentReports createInstance(String fileName) {

        ExtentSparkReporter reporter = new ExtentSparkReporter(fileName);
        reporter.config().setReportName("Automation Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);

        return extent;
    }

    public static void captureScreenshot() throws IOException {

        Date d = new Date();
        fileName = d.toString().replace(":", "_").replace(" ", "_") + ".png";

        WebDriver driver = BaseTest.getDriver();   // ✅ FIX

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("./reports/" + fileName));
    }
}