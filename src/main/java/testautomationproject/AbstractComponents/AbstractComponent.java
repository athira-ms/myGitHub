package testautomationproject.AbstractComponents;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testautomationproject.Seleniumprojectjava.MyCartPOM;
import testautomationproject.Seleniumprojectjava.MyOrdersPagesPOM;

public class AbstractComponent {

	private WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cart;

	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement Orders;

	public void waitForElementtoAppear(By findBy) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementtoAppear(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitForElementtoDisappear(WebElement ele) {
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public void waitForElementtoBecomeInteractable(WebElement ele) {
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void moveToElement(WebElement ele) {
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).build().perform();
	}

	public void pressEnterKey() throws AWTException {
		Robot robot = new Robot();
		// Press Enter key
		robot.keyPress(KeyEvent.VK_ENTER);
		// Release Enter key
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	public void clearTextField(WebElement ele) throws AWTException {

		ele.sendKeys(Keys.CONTROL, "a"); // Select all text (Cmd + A for Mac)
		ele.sendKeys(Keys.DELETE);
		pressEnterKey();
	}

	public MyCartPOM clickOnCart() {
		cart.click();
		MyCartPOM mycart = new MyCartPOM(driver);
		return mycart;

	}

	public MyOrdersPagesPOM clickOnOrders() {

		Orders.click();
		MyOrdersPagesPOM orders = new MyOrdersPagesPOM(driver);
		return orders;
	}
}
