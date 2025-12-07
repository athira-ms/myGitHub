package testautomationproject.Seleniumprojectjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import testautomationproject.TestComponents.BaseTest;
import testautomationproject.TestComponents.Listeners;

public class UploadFileAutoIT extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(Listeners.class);

	@Test
	public void uploadFile() throws InterruptedException {

		FileUploadAutoIT fup = new FileUploadAutoIT(driver);
		Thread.sleep(5);
		fup.uploadFile();

	}
}
