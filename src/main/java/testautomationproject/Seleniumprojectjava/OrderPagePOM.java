package testautomationproject.Seleniumprojectjava;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testautomationproject.AbstractComponents.AbstractComponent;

public class OrderPagePOM extends AbstractComponent {

	WebDriver driver;

	public OrderPagePOM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement dropdown;

	@FindBy(css = ".ta-results.list-group.ng-star-inserted button:last-of-type")
	WebElement country;

	@FindBy(xpath = "//a[normalize-space()='Place Order']")
	WebElement submit;

	By countrydropdown = By.cssSelector(".ta-results.list-group.ng-star-inserted button:last-of-type");

	public void addShippingInfo(String region) {
		dropdown.sendKeys(region);
		waitForElementtoAppear(countrydropdown);
		moveToElement(country);
		country.click();

	}

	public ConfirmationPagePOM placeOrder() {
		moveToElement(submit);
		submit.click();
		ConfirmationPagePOM confirmationpage = new ConfirmationPagePOM(driver);
		return confirmationpage;
	}

}
