package testautomationproject.Seleniumprojectjava;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testautomationproject.TestComponents.BaseTest;

public class StandaloneTest extends BaseTest {

	String productname = "ZARA COAT 3";

	@Test(groups = { "Smoke", "Regression" })
	public void submitOrder() throws IOException, InterruptedException {

		ProductCataloguePOM productcatalogue = landingpage.loginApplication("athirasankar2017@gmail.com", "Ammu@1234");
		List<WebElement> cartproduct = productcatalogue.getProductList();
		productcatalogue.addProducttoCart(productname);
		MyCartPOM mycart = productcatalogue.clickOnCart();
		Boolean match = mycart.ValidateCartItems(productname);
		Assert.assertTrue(match);
		OrderPagePOM order = mycart.checkout();
		order.addShippingInfo("India");
		ConfirmationPagePOM confirmationpage = order.placeOrder();
		String message = confirmationpage.getconfirmationMessage();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", message);
		confirmationpage.getOrderId();

	}

	@Test(groups = { "Purchase" }, dataProvider = "getData")
	public void validateMultipleData(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCataloguePOM productcatalogue = landingpage.loginApplication(input.get("email"), input.get("pwd"));
		List<WebElement> cartproduct = productcatalogue.getProductList();
		productcatalogue.addProducttoCart(input.get("product"));
		MyCartPOM mycart = productcatalogue.clickOnCart();
		Boolean match = mycart.ValidateCartItems(input.get("product"));
		Assert.assertTrue(match);
		OrderPagePOM order = mycart.checkout();
		order.addShippingInfo("India");
		ConfirmationPagePOM confirmationpage = order.placeOrder();
		String message = confirmationpage.getconfirmationMessage();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", message);
		confirmationpage.getOrderId();

	}

	@Test(dependsOnMethods = "submitOrder", groups = { "Regression" })
	public void viewOrder() {
		ProductCataloguePOM productcatalogue = landingpage.loginApplication("athirasankar2017@gmail.com", "Ammu@1234");
		MyOrdersPagesPOM orders = productcatalogue.clickOnOrders();
		Assert.assertTrue(orders.verifyOrders(productname));

	}

	@Test(groups = { "Purchase" })

	public void deleteIteminCart() {

		String productname = "ZARA COAT 3";
		ProductCataloguePOM productcatalogue = landingpage.loginApplication("ems019@gmail.com", "Suju@1234");
		List<WebElement> cartproduct = productcatalogue.getProductList();
		productcatalogue.addProducttoCart(productname);
		MyCartPOM mycart = productcatalogue.clickOnCart();
		Boolean match = mycart.ValidateCartItems(productname);
		Assert.assertTrue(match);
		mycart.deleteItemInCart();
		productcatalogue.signOut();

	}

	@DataProvider

//	public Object[][] getData() {
//		return new Object[][] { { "athirasankar2017@gmail.com", "Ammu@1234", "ZARA COAT 3" },
//				{ "ems019@gmail.com", "Suju@1234", "ADIDAS ORIGINAL" } };
//	}

	public Object[][] getData() throws IOException {
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "athirasankar2017@gmail.com");
//		map1.put("pwd", "Ammu@1234");
//		map1.put("product", "ZARA COAT 3");
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("email", "ems019@gmail.com");
//		map2.put("pwd", "Suju@1234");
//		map2.put("product", "ADIDAS ORIGINAL");
//
//		return new Object[][] { { map2 }, { map1 } };
		List<HashMap<String, String>> data = getJsonDatatoMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\testautomationproject\\data\\purchaseData.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
