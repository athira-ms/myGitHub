package testautomationproject.Seleniumprojectjava;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testautomationproject.AbstractComponents.AbstractComponent;

public class MyOrdersPagesPOM extends AbstractComponent {

	WebDriver driver;

	public MyOrdersPagesPOM(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "tbody tr td:nth-child(3)")
	List<WebElement> productnames;

	public Boolean verifyOrders(String productname) {

		Boolean match = productnames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productname));
		System.out.println(match);
		return match;

	}

}
