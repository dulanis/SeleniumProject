package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSingleton {
	
	private static WebDriverSingleton instanceOfWebDriver = null;
	private WebDriver driver;
	
	private WebDriverSingleton() {
		System.setProperty("webdriver.chrome.driver", "/Users/SaagarDulani/Desktop/Java Tools/chromedriver");
		this.driver = new ChromeDriver();
	}
	
	public static WebDriverSingleton getInstanceOfWebDriverSingleton() {
		if (instanceOfWebDriver == null) {
			instanceOfWebDriver = new WebDriverSingleton();
		}
		return instanceOfWebDriver;
	}
	
	public WebDriver getWebDriver() {
		return this.driver;
	}

}
