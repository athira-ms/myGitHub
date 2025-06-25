package testautomationproject.Seleniumprojectjava;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testautomationproject.AbstractComponents.AbstractComponent;

public class FileUploadPOM extends AbstractComponent {

	WebDriver driver;

	public FileUploadPOM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "downloadButton")
	private WebElement download;

	@FindBy(xpath = "//input[@type='file']")
	private WebElement upload;

	@FindBy(css = ".Toastify div:nth-child(2)")
	private WebElement confirmMsg;

	public void goToUploadUrl() {
		driver.get("https://rahulshettyacademy.com/upload-download-test/");
	}

	public void clickDownload() {
		download.click();
	}

	public WebElement clickUpload() {
		return upload;
	}

	public WebElement DisplayConfirmation() {
		waitForWebElementtoAppear(confirmMsg);
		return confirmMsg;

	}

	public void DisappearConfirmation() {

		waitForElementtoDisappear(confirmMsg);
	}

	public String getPrice(String fruitName) {

		WebElement price = driver
				.findElement(By.xpath("//div[text()='" + fruitName + "']/parent::div/following-sibling::div[2]/div"));
		String priceValue = price.getText();
		return priceValue;
	}

}
