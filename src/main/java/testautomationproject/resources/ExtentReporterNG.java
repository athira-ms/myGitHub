package testautomationproject.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {

		// ExtentSpartReporter, ExtentReports

		String path = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "index.html";
		;
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web AUtomation Report");
		reporter.config().setDocumentTitle("Test results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Athira M S");
		return extent;

	}

}
