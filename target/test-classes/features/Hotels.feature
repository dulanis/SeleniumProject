@testHotels
Feature: Hotel booking

Scenario Outline: Get a list of results for available hotels
	Given the user is on the hotels page
	When the user enters a destination "<destination>"
	And the user enters a checkin date "<checkin_date>"
	And the user enters a checkout date "<checkout_date>"
	When the user clicks the search button
	Then check the results have been loaded
		
		Examples:
		| destination | checkin_date | checkout_date |
		| nyc | 2020-05-01 | 2020-05-08 | 
		| yellowstone | 2020-05-02 | 2020-05-09 |
		| seattle | 2020-05-03 | 2020-05-10 |
		| miami | 2020-06-03 | 2020-06-15 |