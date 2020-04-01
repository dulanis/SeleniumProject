package runner;

import io.cucumber.junit.CucumberOptions;
import pageObjects.WebDriverSingleton;
import io.cucumber.junit.Cucumber;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty"}, 
		features = {"src/test/resources/features"},
		glue = {"stepDefs"},
		tags = {"@testBundles"}
		)
public class RunCucumberTest {
	
	public static WebDriver driver; 
	
	@BeforeClass
	public static void setup() {
		driver = WebDriverSingleton.getInstanceOfWebDriverSingleton().getWebDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://expedia.com");

	}
	
	@AfterClass
	public static void tearDown() {
		driver.close();
	}
	
}
