package ravichouhanworks.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import ravichouhanworks.pageobject.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/ravichouhanworks/resources/Global.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Edge code
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// Firefox code
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataMap(String filePath) throws IOException {

		File file = new File(filePath);
		String jsonContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = (List<HashMap<String, String>>) mapper.readValue(jsonContent,
				new TypeReference() {
				});

		return data;
	}

	public String getScreenShot(String testName, WebDriver driver) throws IOException {

		TakesScreenshot take = ((TakesScreenshot) driver);
		File source = take.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//testresults//" + testName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//testreports//" + testName + ".png";
	}

	@BeforeMethod
	public LandingPage launchingApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
