package testautomationproject.Seleniumprojectjava;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testautomationproject.TestComponents.BaseTest;
import testautomationproject.TestComponents.Listeners;

public class DatafromExcelTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(Listeners.class);

	@Test(dataProvider = "getData")
	public void getDataFromExcel(String username, String password) throws EncryptedDocumentException, IOException {

		driver.get("https://rahulshettyacademy.com/client");
		dataFromExcel data = new dataFromExcel(driver);
		data.Login(username, password);
	}

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException {
		dataFromExcel data = new dataFromExcel(driver);
		return data.getDataFromExcel();
	}

}
