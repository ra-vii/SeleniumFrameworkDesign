package ravichouhanworks.pageobject;

import java.util.List;
import ravichouhanworks.pageobject.ConfirmationScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import ravichouhanworks.utility.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "span[class='ng-star-inserted']")
	List<WebElement> contryList;
	

	By contryInput = By.cssSelector(".form-group input");
	By conutyName = By.cssSelector("span[class='ng-star-inserted']");
	By placeOrderElement = By.cssSelector(".actions a");

	public void selectCountry(String contryName) {
		driver.findElement(contryInput).sendKeys(contryName);
		waitForElementPresent(conutyName);
		contryList.stream().filter(contry -> contry.getText().trim().equals(contryName)).findFirst()
		.ifPresent(WebElement::click);
	}
	
	public ConfirmationScreen placeOrder() {
		WebElement placeOrderButton =waitForElementPresent(placeOrderElement);
		scrollIntoView(placeOrderButton);
		return 	 new ConfirmationScreen(driver);
	}
}
