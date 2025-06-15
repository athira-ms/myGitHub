package testautomationproject.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testautomationproject.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal();// thread safe
	private static final Logger logger = LoggerFactory.getLogger(Listeners.class);

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // unique thread id (store thread id of each test as a map)
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		// test.log(Status.PASS, "Test Passed");
		extentTest.get().log(Status.PASS, "Test Passed");
		logger.info("Test is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		// test.log(Status.FAIL, "Test Failed");
		// test.fail(result.getThrowable());

		extentTest.get().log(Status.FAIL, "Test Failed");
		logger.info("Test is failed");
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String FilePath = null;
		try {
			FilePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// test.addScreenCaptureFromPath(FilePath, result.getMethod().getMethodName());

		extentTest.get().addScreenCaptureFromPath(FilePath, result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush(); // Don't forget to flush the report at the end of the test execution
	}

}
