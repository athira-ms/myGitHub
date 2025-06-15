package testautomationproject.Seleniumprojectjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import testautomationproject.TestComponents.BaseTest;
import testautomationproject.TestComponents.Listeners;

public class FlightBookingBasic extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(Listeners.class);

	@Test
	public void BasicTestFlights() throws InterruptedException {

		FlightBookingHomePOM f = new FlightBookingHomePOM(driver);
		f.goToPractiseurl();
		f.getFlights().click();
		logger.info("Tab Flights is clicked successfully");
		f.chooseRoundTrip().click();
		Assert.assertTrue(f.chooseRoundTrip().isSelected());
		logger.info("Checkbox RoundTrip is clicked successfully");
		f.DepartureCity().click();
		logger.info("Text Input Departure City is clicked successfully");
		f.chooseCity("Adampur (AIP)");
		logger.info("From city is selected successfully");
		f.ArrivalCity().click();
		logger.info("Text Input Arrival City is clicked successfully");
		f.chooseToCity("Bhopal (BHO)");
		logger.info("Arrival city is selected successfully");
		Thread.sleep(2000);
		f.chooseDate();
		logger.info("Current Date is selected successfully");

	}

}
