package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.*;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.openqa.selenium.WebDriver;

public class FlightAndHotelBundleStepDefs {
	
	static WebDriver driver = WebDriverSingleton.getInstanceOfWebDriverSingleton().getWebDriver();
	ExpediaPage ep = new ExpediaPage(driver);
	BundleAndSavePage bsp;

	@After
	public void cleanUp() {
		driver.manage().deleteAllCookies();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Given("the user is on the flight + hotel bundle tab")
	public void the_user_is_on_the_flight_hotel_bundle_tab() {
	    bsp = ep.clickBundleAndSavePageLink();
	}

	@When("the user enters in a departure location {string}")
	public void the_user_enters_in_a_departure_location(String string) {
	    bsp.insertOriginLocation(string);
	}

	@When("the user enters a destination location {string}")
	public void the_user_enters_a_destination_location(String string) {
	    bsp.insertDestinationLocation(string);
	}

	@When("the user enters a departure date {string}")
	public void the_user_enters_a_departure_date(String string) {
	    LocalDate departureDate = LocalDate.parse(string);
	    bsp.selectDepartureDate(departureDate);
	}

	@When("the user enters a return date {string}")
	public void the_user_enters_a_return_date(String string) {
	    LocalDate returnDate = LocalDate.parse(string);
	    bsp.selectReturnDate(returnDate);
	}

	@When("the user clicks the submit button")
	public void the_user_clicks_the_submit_button() {
	    bsp.clickSearch();
	}

	@Then("check that the results have been loaded")
	public void check_that_the_results_have_been_loaded() {
		assertTrue(bsp.insideSearchResultsPage());
	}

}
