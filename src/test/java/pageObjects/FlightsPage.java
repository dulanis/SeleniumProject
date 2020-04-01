package pageObjects;

import java.time.LocalDate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlightsPage extends ExpediaPage {

	@FindBy(id="flight-origin-flp")
	private WebElement originLocation;
	
	@FindBy(id="flight-destination-flp")
	private WebElement destinationLocation;
	
	@FindBy(id="flight-departing-flp")
	private WebElement departureDateDropDown;
	
	@FindBy(id="flight-returning-flp")
	private WebElement returnDateDropDown;
	
	@FindBy(xpath="//button[span=\"Search\"]")
	private WebElement searchButton;

	public FlightsPage(WebDriver driver) {
		super(driver);
	}
	
	public void insertOriginLocation(String location) {
		super.insertOriginLocation(location, this.originLocation);
	}

	public void insertDestinationLocation(String location) {
		super.insertDestinationLocation(location, this.destinationLocation);

	}
		
	public void selectDepartureDate(LocalDate date) {
		super.selectDepartureDate(date, this.departureDateDropDown);
	}
	
	
	public void selectReturnDate(LocalDate date) {
		super.selectReturnDate(date, this.returnDateDropDown);
	}
	
	public void clickSearch() {
		String titleContent = "Flights | Expedia";
		super.clickSearch(this.searchButton, titleContent);
	}

}
