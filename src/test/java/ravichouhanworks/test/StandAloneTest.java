package ravichouhanworks.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		String productName = "ZARA COAT 3";
		String contryName = "India";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");

		driver.findElement(By.id("userEmail")).sendKeys("ravichouhan@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rahul@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*=cart]")).click();

		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));

		boolean match = cartproducts.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='totalRow']/button")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

		driver.findElement(By.cssSelector(".form-group input")).sendKeys("Ind");
		List<WebElement> contries = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span[class='ng-star-inserted']")));

		contries.stream().filter(contry -> contry.getText().trim().equals(contryName)).findFirst()
				.ifPresent(WebElement::click);

		WebElement btn1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".actions a")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn1);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn1);

		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
		driver.quit();
	}

}
