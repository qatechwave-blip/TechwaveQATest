package com.Adaptix.listeners;

import java.io.IOException;

import org.testng.*;

import com.Adaptix.utilities.EmailUtil;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.*;

public class ExtentListeners implements ITestListener {

	private static ExtentReports extent = ExtentManager.getExtent();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {

		ExtentTest extentTest = extent
				.createTest(result.getTestClass().getName() + " :: " + result.getMethod().getMethodName());

		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test.get().pass("Test Passed");

		// ✅ Screenshot on PASS
		try {
			String path = ExtentManager.captureScreenshot();
			test.get().addScreenCaptureFromPath(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Markup m = MarkupHelper.createLabel("TEST CASE PASSED", ExtentColor.GREEN);

		test.get().pass(m);
	}

	@Override
	public void onTestFailure(ITestResult result) {

		test.get().fail(result.getThrowable());

		try {
			String path = ExtentManager.captureScreenshot();

			test.get().fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(path).build());

		} catch (IOException e) {
			e.printStackTrace();
		}

		Markup m = MarkupHelper.createLabel("TEST CASE FAILED", ExtentColor.RED);

		test.get().log(Status.FAIL, m);
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		Markup m = MarkupHelper.createLabel("TEST CASE SKIPPED", ExtentColor.YELLOW);

		test.get().skip(m);
	}

	@Override
	public void onFinish(ITestContext context) {

		if (extent != null) {
			extent.flush();
		}

		//  Email send
		EmailUtil.sendEmail();

	}
}