package ravichouhanworks.utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ravichouhanworks.pageobject.MyCart;
import ravichouhanworks.pageobject.OrderPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	By cartButton = By.cssSelector("[routerlink*=cart]");
	By orderButton = By.cssSelector("[routerlink*=myorders]");

	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToDisappear(WebElement element) {

		try {
			Thread.sleep(Duration.ofSeconds(1));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public MyCart moveToCart() {
		driver.findElement(cartButton).click();
		return new MyCart(driver);
	}

	public OrderPage moveToOrders() {
		driver.findElement(orderButton).click();
		return new OrderPage(driver);
	}

	public WebElement waitForElementPresent(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
	}

	public void scrollIntoView(WebElement button) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", button);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

	}

}
