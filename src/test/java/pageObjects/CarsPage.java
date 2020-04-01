package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CarsPage extends ExpediaPage {

	@FindBy(id="")
	private WebElement pickupLocation;
	
	@FindBy(id="")
	private WebElement dropOffLocation;
	
	@FindBy(id="")
	private WebElement pickupDateDropDown;
	
	@FindBy(id="")
	private WebElement pickupTimeDropDown;
	
	@FindBy(id="")
	private WebElement dropOffDateDropDown;
	
	@FindBy(id="")
	private WebElement dropOffTimeDropDown;
	
	@FindBy(xpath="//button[span=\"Search\"]")
	private WebElement searchButton;
	
	public CarsPage(WebDriver driver) {
		super(driver);
	}

}
