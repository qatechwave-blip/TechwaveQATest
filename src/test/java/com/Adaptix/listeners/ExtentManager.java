package com.Adaptix.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import com.Adaptix.base.BaseTest;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;
    public static String fileName;

    
    public static ExtentReports getExtent() {

        if (extent == null) {

            String reportDir = System.getProperty("user.dir") + "/reports/";
            new File(reportDir).mkdirs(); // folder create

            String reportPath = reportDir + "ExtentReport.html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("Automation Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            
            

            // ✅ System Info yahi add hoga
            extent.setSystemInfo("Project", "Adaptix");
            extent.setSystemInfo("Tester", "Shivam");
            extent.setSystemInfo("Environment", "QA");
        }

        return extent;
    }

    public static String captureScreenshot() throws IOException {

        Date d = new Date();
        fileName = d.toString().replace(":", "_").replace(" ", "_") + ".png";

        WebDriver driver = BaseTest.getDriver();

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // ✅ SAME FOLDER as report
        String path = System.getProperty("user.dir") + "/reports/" + fileName;

        FileUtils.copyFile(src, new File(path));

        return path;
    }
}