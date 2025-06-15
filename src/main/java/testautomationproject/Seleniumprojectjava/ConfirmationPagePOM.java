package testautomationproject.Seleniumprojectjava;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testautomationproject.AbstractComponents.AbstractComponent;

public class ConfirmationPagePOM extends AbstractComponent {

	WebDriver driver;

	public ConfirmationPagePOM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmmsg;

	@FindBy(css = "tr[class='ng-star-inserted'] td label")
	List<WebElement> orderIds;

	public String getconfirmationMessage() {
		String message = confirmmsg.getText();
		return message;

	}

	public void getOrderId() {
		for (WebElement orderid : orderIds) {
			System.out.println(orderid.getText());
		}

	}

}
