package testautomationproject.Seleniumprojectjava;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testautomationproject.AbstractComponents.AbstractComponent;

public class FlightBookingHomePOM extends AbstractComponent {

	WebDriver driver;

	public FlightBookingHomePOM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "a[title='Flights'] span[class='text-label']")
	private WebElement flights;

	@FindBy(xpath = "(//input[@value='Departure City'])[1]")
	private WebElement departureCity;

	@FindBy(xpath = "//input[@id='ctl00_mainContent_ddl_destinationStation1_CTXT']")
	private WebElement arrivalCity;

	@FindBy(css = "#ctl00_mainContent_rbtnl_Trip_1")
	private WebElement roundtrip;

	@FindBy(xpath = "//a[@class='ui-state-default ui-state-active']")
	private WebElement currDate;

	public WebElement getFlights() {
		return flights;
	}

	public void goToPractiseurl() {
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/#");
	}

	public WebElement chooseRoundTrip() {
		return roundtrip;
	}

	public WebElement DepartureCity() {
		return departureCity;
	}

	public WebElement ArrivalCity() {
		// TODO Auto-generated method stub
		return arrivalCity;
	}

	public void chooseCity(String fromCity) {
		WebElement fromcity = driver.findElement(By.xpath("(//a[normalize-space()='" + fromCity + "'])[1]"));
		waitForWebElementtoAppear(fromcity);
		moveToElement(fromcity);
		fromcity.click();

	}

	public void chooseToCity(String ToCity) {
		WebElement Tocity = driver
				.findElement(By.xpath("//li[contains(@class,'city_selected')]//a[normalize-space()='" + ToCity + "']"));
		waitForElementtoBecomeInteractable(Tocity);
		moveToElement(Tocity);
		Tocity.click();

	}

	public void chooseDate() {
		currDate.click();
	}

}
