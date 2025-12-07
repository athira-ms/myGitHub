package testautomationproject.Seleniumprojectjava;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testautomationproject.AbstractComponents.AbstractComponent;

public class FileUploadAutoIT extends AbstractComponent {

	WebDriver driver;

	public FileUploadAutoIT(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "file-upload")
	private WebElement chooseFile;

	@FindBy(id = "file-submit")
	WebElement upload;

	public void uploadFile() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/upload");
		Thread.sleep(5000);
		waitForWebElementtoAppear(chooseFile);
		upload.sendKeys("C:\\Users\\ems01\\OneDrive\\Documents\\EclipseProgramming\\eclipse\\TestUpload.txt");

	}

}
