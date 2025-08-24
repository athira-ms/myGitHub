package testautomationproject.Seleniumprojectjava;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testautomationproject.AbstractComponents.AbstractComponent;

public class sampleWindowHPOM extends AbstractComponent {

	WebDriver driver;

	public sampleWindowHPOM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//a[@class='blinkingText']")
	private WebElement link;

	@FindBy(xpath = "//strong/a")
	private WebElement email;

	@FindBy(id = "username")
	private WebElement username;

	public void goToPractiseurl() {
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
	}

	public String getEmail() {
		waitForWebElementtoAppear(email);
		String em = email.getText();
		System.out.println(em);
		return em;
	}

	public void switchWindow() {
		link.click();
		Set<String> s1 = driver.getWindowHandles();
		System.out.println(s1);
		Iterator<String> l1 = s1.iterator();
		while (l1.hasNext()) {
			String parent = l1.next();// index 0
			String child = l1.next(); // index 1
			driver.switchTo().window(child);
		}

	}

	public void switchToParent() {
		driver.switchTo().defaultContent();
	}

	public void setUsername(String em) {
		waitForWebElementtoAppear(username);
		username.sendKeys(em);
	}
}
