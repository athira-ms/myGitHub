package testautomationproject.Seleniumprojectjava;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testautomationproject.AbstractComponents.AbstractComponent;

public class LandingPagePOM extends AbstractComponent {

	WebDriver driver;

	public LandingPagePOM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	WebElement username;
	WebElement password;
	WebElement login;

	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
	WebElement invalidlogin;

	public void initializeElements() {

		username = driver.findElement(By.id("userEmail"));
		password = driver.findElement(By.id("userPassword"));
		login = driver.findElement(By.id("login"));
	}

	public String ValidateErrorInvalidLogin() {

		waitForWebElementtoAppear(invalidlogin);
		return invalidlogin.getText();
	}

	public ProductCataloguePOM loginApplication(String unm, String pwd) {
		initializeElements();
		username.sendKeys(unm);
		password.sendKeys(pwd);
		login.click();
		return new ProductCataloguePOM(driver);

	}

	public void goTourl() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
