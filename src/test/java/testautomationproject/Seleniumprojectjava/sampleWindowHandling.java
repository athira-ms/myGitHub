package testautomationproject.Seleniumprojectjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import testautomationproject.TestComponents.BaseTest;
import testautomationproject.TestComponents.Listeners;

public class sampleWindowHandling extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(Listeners.class);

	@Test
	public void BasicWindowSwitch() {

		sampleWindowHPOM sm = new sampleWindowHPOM(driver);
		sm.goToPractiseurl();
		logger.info("Navigated to Parent window");
		sm.switchWindow();
		logger.info("Navigated to Child window");
		String email = sm.getEmail();
		sm.switchToParent();
		logger.info("Navigated back Parent window");
		sm.goToPractiseurl(); // Initialize driver again
		sm.setUsername(email);

	}

}
