package pageObjects;

import java.time.LocalDate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BundleAndSavePage extends ExpediaPage {
	
	@FindBy(id="package-origin-plp-fh")
	private WebElement originLocation;
	
	@FindBy(id="package-destination-plp-fh")
	private WebElement destinationLocation;
	
	@FindBy(id="package-departing-plp-fh")
	private WebElement departureDateDropDown;
	
	@FindBy(id="package-returning-plp-fh")
	private WebElement returnDateDropDown;
	
	@FindBy(id="search-button-plp-fh")
	private WebElement searchButton;

	public BundleAndSavePage(WebDriver driver) {
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
		//temp line
		String titleContents = "Hotel Search Results | Expedia";
		super.clickSearch(this.searchButton, titleContents);
	}

}
