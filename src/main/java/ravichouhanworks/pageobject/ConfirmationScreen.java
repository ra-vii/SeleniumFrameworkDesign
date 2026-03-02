package ravichouhanworks.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationScreen {

	WebDriver driver;

	public ConfirmationScreen(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmmessage;

	public String getConfirmMessage() {
		return confirmmessage.getText();
	}

}
