package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageObject {
	
	WebDriver driver;
	static WebDriverWait wait;
	
	public PageObject(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		// wait for webpage to be loaded before populating WebElement fields via PageFactory
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
		PageFactory.initElements(driver, this); 
	}

}
