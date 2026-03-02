package ravichouhanworks.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ravichouhanworks.utility.AbstractComponent;

public class MyCart extends AbstractComponent {

	WebDriver driver;

	public MyCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartproducts;

	By checkOutButton = By.xpath("//li[@class='totalRow']/button");
	By cart = By.cssSelector(".cartSection h3");

	public boolean verifyProduct(String productName) {
		boolean match = cartproducts.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckOutPage pressCheckout() {
		WebElement button = waitForElementPresent(checkOutButton);
		scrollIntoView(button);
		return new CheckOutPage(driver);
	}

}
