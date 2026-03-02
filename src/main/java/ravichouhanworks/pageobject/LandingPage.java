package ravichouhanworks.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ravichouhanworks.utility.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	String url="https://rahulshettyacademy.com/client/#/auth/login";
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[role='alert']")
	WebElement errorMessage;
	
	
	public HomePage login(String email, String pass) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(pass);
		submit.click();
		return new HomePage(driver);
	}
	public void goTo() {
		driver.get(url);
	}
	
	public String getLoginErrorMessage() {
		
		waitForWebElementToAppear(errorMessage);
		
		return errorMessage.getText();
	}
}
