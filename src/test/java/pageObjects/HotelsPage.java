package pageObjects;

import java.time.LocalDate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelsPage extends ExpediaPage {
	
	@FindBy(id="hotel-destination-hlp")
	private WebElement destinationLocation;
	
	@FindBy(id="hotel-checkin-hlp")
	private WebElement checkinDateDropDown;
	
	@FindBy(id="hotel-checkout-hlp")
	private WebElement checkoutDateDropDown;
	
	@FindBy(xpath="//button[span=\"Search\"]")
	private WebElement searchButton;

	public HotelsPage(WebDriver driver) {
		super(driver);
	}
	
	public void insertDestinationLocation(String location) {
		super.insertDestinationLocation(location, this.destinationLocation);

	}
		
	public void selectCheckInDate(LocalDate date) {
		super.selectDepartureDate(date, this.checkinDateDropDown);
	}
	
	
	public void selectCheckOutDate(LocalDate date) {
		super.selectReturnDate(date, this.checkoutDateDropDown);
	}
	
	public void clickSearch() {
		String titleContent = "Hotel Search Results | Expedia";
		super.clickSearch(this.searchButton, titleContent);
	}

}
