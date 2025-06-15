package testautomationproject.Seleniumprojectjava;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import testautomationproject.AbstractComponents.AbstractComponent;

public class ProductCataloguePOM extends AbstractComponent {

	WebDriver driver;

	public ProductCataloguePOM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	private List<WebElement> product;

	@FindBy(css = ".ng-animating")
	private WebElement spinner;

	@FindBy(css = ".continue")
	private WebElement continueShopping;

	@FindBy(css = ".m-2.blink_me")
	private WebElement blinkText;

	@FindBy(xpath = "//div[@class='py-2 border-bottom ml-3']//label[@for='cat']")
	private List<WebElement> checkboxCategory;

	@FindBy(css = ".card-body b")
	private List<WebElement> cartitems;

	@FindBy(xpath = "(//input[@placeholder='search'])[2]")
	private WebElement searchField;

	@FindBy(xpath = "//button[normalize-space()='Sign Out']")
	private WebElement signout;

	By productBy = By.cssSelector(".mb-3");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementtoAppear(productBy);
		return product;
	}

	public WebElement getProductByName(String productname) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProducttoCart(String productname) {
		WebElement prod = getProductByName(productname);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		waitForElementtoAppear(toastMessage);
		waitForElementtoDisappear(spinner);

	}

	public void viewProductinCart() {

		driver.findElement(By.xpath("//div[@class='container']//div[1]//div[1]//div[1]//button[1]")).click();
		continueShopping.click();
		driver.findElement(By.xpath("//div[@class='row']//div[2]//div[1]//div[1]//button[1]")).click();
		continueShopping.click();
		driver.findElement(By.xpath("//div[3]//div[1]//div[1]//button[1]")).click();
		continueShopping.click();

	}

	public String verifyBlinkText() {

		waitForWebElementtoAppear(blinkText);
		String blinkmessage = blinkText.getText();
		return blinkmessage;
	}

	public Boolean selectCheckboxCategories(String filter, String subfilter) {

		Boolean flag = false;
		String dynamicXpath = "";

		if (filter.equalsIgnoreCase("categories")) {

			dynamicXpath = String.format("(//label[@for='cat'][normalize-space()='%s']/preceding-sibling::input[1])[2]",
					subfilter);
		} else if (filter.equalsIgnoreCase("sub categories")) {

			dynamicXpath = String.format("(//label[@for='sub'][normalize-space()='%s']/preceding-sibling::input[1])[2]",
					subfilter);
		} else if (filter.equalsIgnoreCase("search For")) {

			dynamicXpath = String.format("(//label[@for='ge'][normalize-space()='%s']/preceding-sibling::input[1])[2]",
					subfilter);
		}

		WebElement checkbox = driver.findElement(By.xpath(dynamicXpath));

		waitForWebElementtoAppear(checkbox);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", checkbox);

		js.executeScript("arguments[0].click();", checkbox);

		flag = checkbox.isSelected();
		return flag;

	}

	public Boolean validateCartItemsonFilter(String subfilter) {
		List<WebElement> cartlists = cartitems;
		List<String> actualList = new ArrayList<>();
		List<String> expectedList = new ArrayList<>();
		Boolean flag = false;

		for (WebElement cartlist : cartlists) {
			actualList.add(cartlist.getText());
		}
		String filePath = System.getProperty("user.dir")
				+ "\\src\\test\\java\\testautomationproject\\data\\FilterData.json";
		Map<String, Object> testData = readJson(filePath);

		if (subfilter == "fashion") {
			expectedList = (List<String>) testData.get("fashion");
			// expectedList = Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL");
		} else if (subfilter == "electronics") {
			expectedList = (List<String>) testData.get("electronics");
			// expectedList = Arrays.asList("IPHONE 13 PRO");
		} else if (subfilter == "household") {
			expectedList = (List<String>) testData.get("household");
			// expectedList = new ArrayList<>();
		} else if (subfilter == "t-shirts") {
			expectedList = (List<String>) testData.get("t-shirts");
		} else if (subfilter == "shirts") {
			expectedList = (List<String>) testData.get("shirts");
		} else if (subfilter == "mobiles") {
			expectedList = (List<String>) testData.get("mobiles");
		} else if (subfilter == "men") {
			expectedList = (List<String>) testData.get("men");
		} else if (subfilter == "women") {
			expectedList = (List<String>) testData.get("women");
		} else if (subfilter == "ADIDAS") {
			expectedList = (List<String>) testData.get("ADIDAS");
		} else if (subfilter == "ZARA") {
			expectedList = (List<String>) testData.get("ZARA");
		} else if (subfilter == "IPHONE") {
			expectedList = (List<String>) testData.get("IPHONE");
		}

		System.out.println("Actual List is " + actualList);
		System.out.println("Expected List is " + expectedList);
		flag = actualList.equals(expectedList) ? true : false;
		return flag;

	}

	public void searchProduct(String product) throws AWTException {
		waitForWebElementtoAppear(searchField);
		searchField.sendKeys(product);
		pressEnterKey();

	}

	public WebElement getSearchInput() {
		return searchField;
	}

	public static Map<String, Object> readJson(String filePath) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(new File(filePath), Map.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void signOut() {

		signout.click();

	}

}
