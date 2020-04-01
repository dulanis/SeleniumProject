@testBundles
Feature: Test flight and hotel bundling

Scenario Outline: Get a list of results for flight and hotel deals
	Given the user is on the flight + hotel bundle tab
	When the user enters in a departure location "<departure_location>"
	And the user enters a destination location "<destination_location>"
	And the user enters a departure date "<departure_date>"
	And the user enters a return date "<return_date>"
	When the user clicks the submit button
	Then check that the results have been loaded
		
		Examples:
		| departure_location | destination_location | departure_date | return_date |
		| ewr | cvg | 2020-05-01 | 2020-05-08 | 
		| pbi | jfk | 2020-05-02 | 2020-05-09 |
		| psm | cvg | 2020-05-03 | 2020-05-10 |
		| nyc | miami | 2020-06-03 | 2020-06-15 |