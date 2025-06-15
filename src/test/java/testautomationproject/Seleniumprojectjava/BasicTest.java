package testautomationproject.Seleniumprojectjava;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String productname = "ZARA COAT 3";
		// webdriver setup
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		// implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		// maximise browser
		driver.manage().window().maximize();
		// Login test
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("athirasankar2017@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ammu@1234");
		driver.findElement(By.id("login")).click();

		// Wait for home page
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		// Add element to cart
		List<WebElement> product = driver.findElements(By.className("mb-3"));
		Iterator<WebElement> p = product.iterator();
		while (p.hasNext()) {
			WebElement l = p.next();
			if (l.findElement(By.cssSelector("b")).getText().equals(productname)) {
				l.findElement(By.cssSelector(".card-body button:last-of-type")).click();
			}

		}

		// Wait for confirmation on adding to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

		// Add element to cart
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		// Validate element is displayed in the cart
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(match);

		// Validate checkout
		WebElement ele = driver.findElement(By.xpath("//button[normalize-space()='Checkout']"));

		Actions actions = new Actions(driver);
		actions.moveToElement(ele).build().perform();
		ele.click();

		// Fill the form and place order
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector(".ta-results.list-group.ng-star-inserted button:last-of-type"))).click();
		WebElement submit = driver.findElement(By.xpath("//a[normalize-space()='Place Order']"));
		actions.moveToElement(submit).build().perform();
		submit.click();

		// Validate the confirmation message
		String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
		String exp = "THANKYOU FOR THE ORDER.";
		Assert.assertEquals(exp, message);

		// Retrieve the order ID
		List<WebElement> orderids = driver.findElements(By.cssSelector("tr[class='ng-star-inserted'] td label"));
		for (WebElement orderid : orderids) {
			System.out.println(orderid.getText());
		}
		driver.close();
	}

}
