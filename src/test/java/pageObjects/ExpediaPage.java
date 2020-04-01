package pageObjects;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ExpediaPage extends PageObject {

	// WebElements for links to the various expedia pages
	@FindBy(id="primary-header-flight")
	private WebElement flightsPageLink;
	@FindBy(id="primary-header-hotel")
	private WebElement hotelsPageLink;
	@FindBy(id="primary-header-package")
	private WebElement bundleAndSavePageLink;
	@FindBy(id="primary-header-car")
	private WebElement carsPageLink;
	@FindBy(id="primary-header-cruise")
	private WebElement cruisesPageLink;
	@FindBy(id="primary-header-activity")
	private WebElement thingstoDoPageLink;
	@FindBy(id="primary-header-vacationRental")
	private WebElement vacationRentalsPageLink;

	// constructor
	public ExpediaPage(WebDriver driver) {
		super(driver);
	}
	
	// methods to interact with a web page
	/* 
	 * these methods are package-private as subclasses will utilize only the 
	 * methods of these which are needed for their (subclasses) specific 
	 * functionality.
	 * */
	void insertDestinationLocation(String location, WebElement destinationLocation) {
		wait.until(ExpectedConditions.elementToBeClickable(destinationLocation));
		destinationLocation.clear();
		ExpectedCondition<Boolean> destinationLocationNotEmpty = ExpectedConditions.attributeToBeNotEmpty(destinationLocation, "value");
		wait.until(ExpectedConditions.not(destinationLocationNotEmpty)); // wait until text box is empty before invoking sendKeys()
		destinationLocation.sendKeys(location);
	}
	
	void insertOriginLocation(String location, WebElement originLocation) {
		wait.until(ExpectedConditions.elementToBeClickable(originLocation));
		originLocation.clear();
		ExpectedCondition<Boolean> originLocationNotEmpty = ExpectedConditions.attributeToBeNotEmpty(originLocation, "value");
		wait.until(ExpectedConditions.not(originLocationNotEmpty)); // wait until text box is empty before invoking sendKeys()
		originLocation.sendKeys(location);
	}
	
	void selectDepartureDate(LocalDate desiredDate, WebElement departureDropDown) {
		// check if desired date is within valid range for booking
		LocalDate today = LocalDate.now();
		long numDaysDifference = ChronoUnit.DAYS.between(today, desiredDate);
		if (numDaysDifference > 329) {
			System.out.println("Can't book a trip that far in advance");
			return; // exit from the method if desired date is too distant in future
		}
		// open the drop down menu
		wait.until(ExpectedConditions.elementToBeClickable(departureDropDown)); // wait for drop down to be clickable
		departureDropDown.click();
		// create WebElement for "next month" button on calendar dropdown
		String xpathToNextMonthButton = "//button[span=\"next month\"]";
		WebElement nextMonthButton = driver.findElement(By.xpath(xpathToNextMonthButton));
		// navigate to the desired month and year
		long numMonthsDifference = ChronoUnit.MONTHS.between(today, desiredDate);
		for (long i = 0; i < numMonthsDifference; i++) {
			wait.until(ExpectedConditions.elementToBeClickable(nextMonthButton));
			nextMonthButton.click();
			// reassign the nextMonthButton as it will lose reference once the button is clicked
			nextMonthButton = driver.findElement(By.xpath(xpathToNextMonthButton));
		}
		// select desired date
		int month = desiredDate.getMonthValue();
		month--; // expedia uses months 0-11 rather than 1-12
		int day = desiredDate.getDayOfMonth();
		int year = desiredDate.getYear();
		String driverXpathToDate = "//button[@data-month=" + month + "][@data-day=" + day + "][@data-year=" + year + "]";
		WebElement departureDate = driver.findElement(By.xpath(driverXpathToDate));
		wait.until(ExpectedConditions.elementToBeClickable(departureDate));
		departureDate.click();

	}

	
	void selectReturnDate(LocalDate desiredDate, WebElement returnDropDown) {
		// click dropdown calendar
		wait.until(ExpectedConditions.elementToBeClickable(returnDropDown)); // wait for drop down to be clickable
		returnDropDown.click();
		// create WebElement for "next month" button on calendar dropdown
		String xpathToNextMonthButton = "//button[span=\"next month\"]";
		WebElement nextMonthButton = driver.findElement(By.xpath(xpathToNextMonthButton));
		// select desired date
		int month = desiredDate.getMonthValue();
		month--; // expedia uses months 0-11 rather than 1-12
		int day = desiredDate.getDayOfMonth();
		int year = desiredDate.getYear();
		String xpathToDate = "//button[@data-month=" + month + "][@data-day=" + day + "][@data-year=" + year + "]";
		// see if return date is currently visible on calendar
		try {
			WebElement returnDate = driver.findElement(By.xpath(xpathToDate));
			wait.until(ExpectedConditions.elementToBeClickable(returnDate));
			returnDate.click();
			return; // exit method once desired date clicked
		}
		catch (Exception e) {
			System.out.println("Can't click on return date; not in calendar view");
		}
		// see if return date will become visible after incrementing a month
		try {
			nextMonthButton.click();
			WebElement returnDate = driver.findElement(By.xpath(xpathToDate));
			wait.until(ExpectedConditions.elementToBeClickable(returnDate));
			returnDate.click();
			return; // exit method once desired date clicked
		}
		catch (Exception e) {
			System.out.println("Can't click nextMonthButton. Printing stack trace....");
			e.printStackTrace();
			System.out.println("Can't choose a return date that late");
		}

	}
	
	void clickSearch(WebElement searchButton, String titleContent) {
		try {
			searchButton.click();
			wait.until(ExpectedConditions.titleContains(titleContent));
		}
		catch (NoSuchElementException e) {
			System.out.println("Cannot locate search button");
			e.printStackTrace();
		}
		catch (StaleElementReferenceException e) {
			System.out.println("Cannot click search button");
			e.printStackTrace();
		}
		catch (TimeoutException e) {
			System.out.println("Timed out waiting for search results to load");
			e.printStackTrace();
		}
		catch (Exception e){
			System.out.println("Something else prevented either search button from being clicked or search results from being loaded");
			e.printStackTrace();
		}
	}
	
	// method to use in testing to validate that search results were successfully obtains
	// return the title of a given web page
	public boolean insideSearchResultsPage() {
		try {
		String xpathToTitleAttribute = "//title[contains(text(), \"Search Results | Expedia\")]";
		String title = driver.findElement(By.xpath(xpathToTitleAttribute)).getText();
		System.out.println("The title of the search results page is: " + title);
		return true;
		}
		catch (Exception e) {
			System.out.println("Not in the search results page");
			return false;
		}
		
	}
	
	// methods to navigate to different web pages
	
	public void openHomePage() {
		driver.get("https://expedia.com");
	}
	
	public FlightsPage clickFlightsPageLink() {
		flightsPageLink.click();
		return new FlightsPage(driver);
	}
	
	public HotelsPage clickHotelsPageLink() {
		hotelsPageLink.click();
		return new HotelsPage(driver);
	}
	
	public BundleAndSavePage clickBundleAndSavePageLink() {
		bundleAndSavePageLink.click();
		return new BundleAndSavePage(driver);
	}
	
	public CarsPage clickCarsPageLink() {
		carsPageLink.click();
		return new CarsPage(driver);
	}
	
	

}
