package testautomationproject.Seleniumprojectjava;

import java.awt.AWTException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testautomationproject.TestComponents.BaseTest;
import testautomationproject.TestComponents.Listeners;

public class ExtendedTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(Listeners.class);

	@Test(groups = { "Filter" }, dataProvider = "getFilterData")
	public void filterProducts(Map<String, String> filterMap) throws InterruptedException {

		ProductCataloguePOM productcatalogue = landingpage.loginApplication("achuss@gmail.com", "Achu@123");
		String blinkTextmessage = productcatalogue.verifyBlinkText();
		String messageblink = "User can only see maximum 9 products on a page";
		Assert.assertEquals(messageblink, blinkTextmessage);
		// productcatalogue.checkboxforCategory.click();
		Boolean checkflag = productcatalogue.selectCheckboxCategories(filterMap.get("filter"),
				filterMap.get("subfilter"));
		Thread.sleep(9000);
		Assert.assertTrue(checkflag);
		logger.info("Checkbox " + filterMap.get("subfilter") + " is selected successfully");

		Assert.assertTrue(productcatalogue.validateCartItemsonFilter(filterMap.get("subfilter")));
		logger.info("Filtered items are matching for filter " + filterMap.get("subfilter"));

	}

	@DataProvider
	public Object[][] getFilterData() {

		Map<String, String> data1 = new HashMap<>();
		data1.put("filter", "Categories");
		data1.put("subfilter", "fashion");

		Map<String, String> data2 = new HashMap<>();
		data2.put("filter", "Categories");
		data2.put("subfilter", "electronics");

		Map<String, String> data3 = new HashMap<>();
		data3.put("filter", "Categories");
		data3.put("subfilter", "household");

		Map<String, String> data4 = new HashMap<>();
		data4.put("filter", "Sub categories");
		data4.put("subfilter", "shirts");

		Map<String, String> data5 = new HashMap<>();
		data5.put("filter", "Sub categories");
		data5.put("subfilter", "mobiles");

		Map<String, String> data6 = new HashMap<>();
		data6.put("filter", "Search For");
		data6.put("subfilter", "women");

		return new Object[][] { { data1 }, { data2 }, { data3 }, { data4 }, { data5 }, { data6 } };

	}

	@Test(dataProvider = "getFilterwithTextdata", groups = { "Filter" })
	public void filterWithText(String product) throws AWTException {

		try {

			ProductCataloguePOM productcatalogue = landingpage.loginApplication("email@exc.com", "Email@123.com");

			productcatalogue.searchProduct(product);
			logger.info("Product search with text " + product);
			Thread.sleep(1000);
			Assert.assertTrue(productcatalogue.validateCartItemsonFilter(product));
			Thread.sleep(1000);
			logger.info("Filtered items are matching for filter " + product);
			productcatalogue.clearTextField(productcatalogue.getSearchInput());
			Thread.sleep(1000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@DataProvider
	public Object[][] getFilterwithTextdata() {
		return new Object[][] { { "ADIDAS" }, { "IPHONE" }, { "ZARA" } };
	}
}
