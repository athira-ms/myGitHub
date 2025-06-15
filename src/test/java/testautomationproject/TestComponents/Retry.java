package testautomationproject.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	private int count = 0; // Counter to track retries
	private int maxRetryCount = 2; // Maximum number of retries

	@Override
	public boolean retry(ITestResult result) {
		if (count < maxRetryCount) {
			count++;
			return true; // Retry the test
		}
		return false; // No more retries
	}
}
