package testautomationproject.Seleniumprojectjava;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testautomationproject.AbstractComponents.AbstractComponent;

public class shadowDOMsamplePOM extends AbstractComponent {

	WebDriver driver;

	public shadowDOMsamplePOM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='userName']")
	private WebElement shadowHost;

	public void goTourl() {
		driver.get("https://selectorshub.com/xpath-practice-page/");
	}

	public void setUsername() {

		SearchContext shadowRoot = shadowHost.getShadowRoot();
		WebElement nameInput = shadowRoot.findElement(By.cssSelector("input#kils"));
		nameInput.sendKeys("Test");
	}

}
