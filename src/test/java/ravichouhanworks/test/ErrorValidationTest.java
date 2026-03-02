package ravichouhanworks.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import ravichouhanworks.pageobject.HomePage;
import ravichouhanworks.pageobject.MyCart;
import ravichouhanworks.testcomponents.BaseTest;
import ravichouhanworks.testcomponents.RetryTest;
public class ErrorValidationTest extends BaseTest {

	@Test(retryAnalyzer = RetryTest.class)
	public void loginErrorMessageTest() {

		String userName = "ravichouhan@gmail.com";
		String password = "Rahul@1234";
		String actualError = "Incorrects email or password.";

		landingPage.login(userName, password);
		String error = landingPage.getLoginErrorMessage();
		System.out.println(error);
		Assert.assertEquals(actualError, error);

	}
	
	@Test
	public void productNameValidation() throws InterruptedException {
		String productName = "ZARA COAT 33";
		String userName = "ravichouhan@gmail.com";
		String password = "Rahul@123";

		HomePage homePage = landingPage.login(userName, password);

		homePage.addProductToCart("ZARA COAT 3");
		MyCart myCart = homePage.moveToCart();

		boolean match = myCart.verifyProduct(productName);
		Assert.assertFalse(match);
	}

}
