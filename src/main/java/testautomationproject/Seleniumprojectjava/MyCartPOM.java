package testautomationproject.Seleniumprojectjava;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testautomationproject.AbstractComponents.AbstractComponent;

public class MyCartPOM extends AbstractComponent {

	WebDriver driver;

	public MyCartPOM(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartproducts;

	@FindBy(xpath = "//button[normalize-space()='Checkout']")
	WebElement checkout;

	@FindBy(css = ".fa.fa-trash-o")
	WebElement deleteCart;

	public List<WebElement> getCartList() {
		return cartproducts;
	}

	public Boolean ValidateCartItems(String productname) {

		Boolean match = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
		return match;

	}

	public OrderPagePOM checkout() {

		moveToElement(checkout);
		checkout.click();
		OrderPagePOM order = new OrderPagePOM(driver);
		return order;
	}

	public void deleteItemInCart() {
		waitForWebElementtoAppear(deleteCart);
		deleteCart.click();
	}

}
