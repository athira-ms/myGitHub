package testautomationproject.Seleniumprojectjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import testautomationproject.TestComponents.BaseTest;
import testautomationproject.TestComponents.Listeners;

public class sampleShadowDOM extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(Listeners.class);

	@Test
	public void shadowDomTest() throws InterruptedException {

		shadowDOMsamplePOM sdom = new shadowDOMsamplePOM(driver);
		sdom.goTourl();
		sdom.setUsername();
		Thread.sleep(3000);

		logger.info("Text enetered in username field");
	}

}
