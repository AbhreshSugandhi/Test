package com.demoaut.mercurytours.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FlightFinderPage {
	@FindBy(css= "input[value='roundtrip']")
	private WebElement roundTripRadioBtn;
	
	@FindBy(css= "input[value='oneway']")
	private WebElement oneWayRadioBtn;
	
	@FindBy(name= "passCount")
	private WebElement noOfPassengers;
	
	@FindBy(name= "fromPort")
	private WebElement departingLocation;
	
	@FindBy(name= "fromMonth")
	private WebElement departingMonth;
	
	@FindBy(name= "fromDay")
	private WebElement departingDay;
	
	@FindBy(name= "toPort")
	private WebElement arrivingLocation;
	
	@FindBy(name= "toMonth")
	private WebElement arrivingMonth;
	
	@FindBy(name= "toDay")
	private WebElement arrivingDay;
	
	@FindBy(css= "input[value='Business']")
	private WebElement businessClassRadioBtn;
	
	@FindBy(name= "airline")
	private WebElement airlineName;
	
	@FindBy(name= "findFlights")
	private WebElement continueButton;
	
	public String departLocSelected;
	public String arriveLocSelected;
	
	public FlightFinderPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void enterFlightDetails(String[][] data){
		roundTripRadioBtn.click();
		Select passengers = new Select(noOfPassengers);
		passengers.selectByVisibleText(data[2][1]);
		Select departLoc = new Select(departingLocation);
		departLoc.selectByValue(data[3][1]);
		
		//Storing value to verify on select flight, book flight and confirmation page
		departLocSelected= departLoc.getFirstSelectedOption().getText();
		
		Select departMonth = new Select(departingMonth);
		departMonth.selectByVisibleText(data[4][1]);
		Select departDay = new Select(departingDay);
		departDay.selectByValue(data[5][1]);
		
		Select arriveLoc = new Select(arrivingLocation);
		arriveLoc.selectByValue(data[6][1]);
		
		//Storing value to verify on select flight, book flight and confirmation page
		arriveLocSelected= arriveLoc.getFirstSelectedOption().getText();
		
		Select arriveMonth = new Select(arrivingMonth);
		arriveMonth.selectByVisibleText(data[7][1]);
		Select arriveDay = new Select(arrivingDay);
		arriveDay.selectByValue(data[8][1]);
		businessClassRadioBtn.click();
		Select selectAirline = new Select(airlineName);
		selectAirline.selectByVisibleText(data[9][1]);
		
		continueButton.click();
	}
}
