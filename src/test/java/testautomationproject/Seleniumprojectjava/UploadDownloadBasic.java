package testautomationproject.Seleniumprojectjava;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import testautomationproject.TestComponents.BaseTest;
import testautomationproject.TestComponents.Listeners;
import testautomationproject.data.ExcelReaderEndtoEnd;

public class UploadDownloadBasic extends BaseTest {
	private static final Logger logger = LoggerFactory.getLogger(Listeners.class);

	@Test
	public void BasicUploadDownload() throws InterruptedException, InvalidFormatException, IOException {

		String actualprice = "345";
		String updatedprice = "545";
		FileUploadPOM uploadobj = new FileUploadPOM(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		uploadobj.goToUploadUrl();
		uploadobj.clickDownload();
		Thread.sleep(3);
		logger.info("Download button is clicked");
		File file = new File("C:\\Users\\ems01\\Downloads\\download.xlsx");
		int waitTime = 0;

		while (!file.exists() && waitTime < 10000) { // wait max 10 seconds
			try {
				Thread.sleep(500);
				waitTime += 500;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (file.exists()) {
			logger.info("File exists in download");
		} else {
			logger.info("File does not exist in download");
		}

		uploadobj.clickUpload().sendKeys("C:\\Users\\ems01\\Downloads\\download.xlsx");
		logger.info("Upload button is clicked");
		String expMsg = uploadobj.DisplayConfirmation().getText();
		logger.info("Confirmation message is dispalyed");
		String message = "Updated Excel Data Successfully.";
		Assert.assertEquals(expMsg, message);

		logger.info("Confirmation message is expected");
		Thread.sleep(3);
		uploadobj.DisappearConfirmation();
		logger.info("Confirmation message disappear");
		Thread.sleep(3);
		driver.navigate().refresh();
		String price = uploadobj.getPrice("Apple");
		Assert.assertEquals(price, actualprice);
		logger.info("Price is expected");
//		ExcelReader ex = new ExcelReader();
//		System.out.println(ex.readFile(file, "Apple"));

		ExcelReaderEndtoEnd exc = new ExcelReaderEndtoEnd();
		System.out.println(exc.getRowNumber(file, "Apple"));
		int row = exc.getRowNumber(file, "Apple");
		System.out.println(exc.getColumnNumber(file, "price"));
		int col = exc.getColumnNumber(file, "price");
		Assert.assertTrue(exc.updateCell(file, row, col, updatedprice));
		uploadobj.clickUpload().sendKeys("C:\\Users\\ems01\\Downloads\\download.xlsx");
		logger.info("Updated excel is uploaded");
		String newPrice = uploadobj.getPrice("Apple");
		Assert.assertEquals(newPrice, updatedprice);
		logger.info("Updated price is matching");

	}
}
