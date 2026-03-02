package ravichouhanworks.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ravichouhanworks.pageobject.CheckOutPage;
import ravichouhanworks.pageobject.ConfirmationScreen;
import ravichouhanworks.pageobject.HomePage;
import ravichouhanworks.pageobject.MyCart;
import ravichouhanworks.pageobject.OrderPage;
import ravichouhanworks.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		HomePage homePage = landingPage.login(input.get("userName"), input.get("password"));

		homePage.addProductToCart(input.get("productName"));
		MyCart myCart = homePage.moveToCart();

		boolean match = myCart.verifyProduct(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = myCart.pressCheckout();

		checkOutPage.selectCountry(input.get("countryName"));
		ConfirmationScreen confirmationScreen = checkOutPage.placeOrder();

		String actualMessage = confirmationScreen.getConfirmMessage();
		Assert.assertTrue(actualMessage.equalsIgnoreCase(input.get("confirmMessage")));
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
		
		HomePage homePage = landingPage.login("ravichouhan@gmail.com", "Rahul@123");
		OrderPage  orderPage = homePage.moveToOrders();
		boolean match = orderPage.verifyOrderDisplay("ZARA COAT 3");
		Assert.assertTrue(match);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonDataMap(System.getProperty("user.dir")+"/src/main/java/ravichouhanworks/data/PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
