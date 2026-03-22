package com.Adaptix.listeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.Adaptix.base.TestBase;
import com.Adaptix.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends TestBase implements ITestListener {

    public void onStart(org.testng.ITestContext context) {
    }

    public void onFinish(org.testng.ITestContext context) {
    }

    public void onTestStart(ITestResult result) {

        test = rep.startTest(result.getName());

    }

    public void onTestSuccess(ITestResult result) {

        test.log(LogStatus.PASS, result.getName().toUpperCase() + " PASSED");

        rep.endTest(test);
        rep.flush();

    }

    public void onTestFailure(ITestResult result) {

        System.setProperty("org.uncommons.reportng.escape-output", "false");

        try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        test.log(LogStatus.FAIL,
                result.getName().toUpperCase() + " FAILED with Exception: " + result.getThrowable());

        test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

        Reporter.log("Click to see Screenshot");

        Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Screenshot</a>");
        Reporter.log("<br>");
        Reporter.log("<br>");

        Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">"
                + "<img src=" + TestUtil.screenshotName + " height='100' width='100'/> </a>");

        rep.endTest(test);
        rep.flush();

    }

    public void onTestSkipped(ITestResult result) {

        test.log(LogStatus.SKIP, result.getName().toUpperCase() + " SKIPPED");

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

}