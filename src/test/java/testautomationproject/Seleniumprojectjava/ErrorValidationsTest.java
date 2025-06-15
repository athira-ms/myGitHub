package testautomationproject.Seleniumprojectjava;

import org.testng.Assert;
import org.testng.annotations.Test;

import testautomationproject.TestComponents.BaseTest;
import testautomationproject.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(retryAnalyzer = Retry.class, groups = { "Regression" })
	public void ErrorMsgValidations() {

		landingpage.loginApplication("athirasankar2017@gmail.com", "Ammu");
		Assert.assertEquals("Incorrect email or password.", landingpage.ValidateErrorInvalidLogin());
	}

	@Test(groups = { "Smoke", "Regression" })
	public void viewProduct() {
		ProductCataloguePOM productcatalogue = landingpage.loginApplication("athirasankar2017@gmail.com", "Ammu@1234");
		productcatalogue.viewProductinCart();
		productcatalogue.signOut();

	}

}
