package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.*;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.openqa.selenium.WebDriver;

public class HotelsStepDefs {
	
	static WebDriver driver = WebDriverSingleton.getInstanceOfWebDriverSingleton().getWebDriver();
	ExpediaPage ep = new ExpediaPage(driver);
	HotelsPage hp;

	@After
	public void cleanUp() {
		driver.manage().deleteAllCookies();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Given("the user is on the hotels page")
	public void the_user_is_on_the_hotels_page() {
	    hp = ep.clickHotelsPageLink();
	}

	@When("the user enters a destination {string}")
	public void the_user_enters_a_destination(String string) {
		hp.insertDestinationLocation(string);
	}

	@When("the user enters a checkin date {string}")
	public void the_user_enters_a_checkin_date(String string) {
		LocalDate checkinDate = LocalDate.parse(string);
	    hp.selectCheckInDate(checkinDate);
	}

	@When("the user enters a checkout date {string}")
	public void the_user_enters_a_checkout_date(String string) {
		LocalDate checkoutDate = LocalDate.parse(string);
	    hp.selectCheckOutDate(checkoutDate);
	}

	@When("the user clicks the search button")
	public void the_user_clicks_the_search_button() {
	    hp.clickSearch();
	}

	@Then("check the results have been loaded")
	public void check_the_results_have_been_loaded() {
		assertTrue(hp.insideSearchResultsPage());
	}

}
