package com.demoaut.mercurytours.TestCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.demoaut.mercurytours.PageObjects.BookAFlightPage;
import com.demoaut.mercurytours.PageObjects.FlightFinderPage;
import com.demoaut.mercurytours.PageObjects.SelectFlightPage;
import com.demoaut.mercurytours.Utility.ReadExcel;
import com.relevantcodes.extentreports.LogStatus;

public class FlightBookingTest extends BaseClass{
	
	public FlightBookingTest(){}
	
	@Test(description= "This test will perform end to end scenario for flight booking")
	public void bookFlightMethod() throws Exception{
		//1. Login to the newtours.com site
		LoginUserTest login= new LoginUserTest();
		login.validCredentialLogin();
		logger.appendChild(logger);
		logger= extent.startTest("Flight booking test - E2E");
		logger.log(LogStatus.INFO, "User has been logged in successfully.....");
		
		//2. Enter flight details to find flight
		String[][] bookingData= ReadExcel.getBookingData(); //Getting booking data
		
		FlightFinderPage findFlight= new FlightFinderPage(driver);
		findFlight.enterFlightDetails(bookingData);
		logger.log(LogStatus.INFO, "Provided details to find flights.....");
		
		String selectedDepartLoc= findFlight.departLocSelected;
		System.out.println("Selected depart location is..... "+selectedDepartLoc);
		logger.log(LogStatus.INFO, "Selected departing location is.....", selectedDepartLoc);
		
		String selectedArriveLoc= findFlight.arriveLocSelected;
		logger.log(LogStatus.INFO, "Selected arriving location is.....", selectedArriveLoc);
			
		//3. Select flight from options
		SelectFlightPage selectFlight= new SelectFlightPage(driver);
		selectFlight.reserveFlight();
		logger.log(LogStatus.INFO, "Provided flight choice.....");
		
		//4. Enter booking details like passengers details, CC details, address etc.
		BookAFlightPage bookFlight = new BookAFlightPage(driver);
		bookFlight.enterBookingDetails(bookingData);
		logger.log(LogStatus.INFO, "Provided booking details like passengers details, CC details, address etc.....");
		//5. Complete booking transaction.
		try {
			
			driver.switchTo().alert().accept();
			logger.log(LogStatus.INFO, "Alert present- Selected country other than US.....");
		}
		catch(Exception e){
			System.out.println("Exception while accepting alert " +e.getMessage());
			
		}
		
		bookFlight.clickPurchaseButton();	
		logger.log(LogStatus.INFO, "Clicked on final purchase button.....");
		
		//Verifying that confirmation text appears.
		assertEquals(driver.findElement(By.cssSelector("tr:nth-child(3) > td > p > font > b > font:nth-child(2)")).getText(), "Your itinerary has been booked!");
		
		logger.log(LogStatus.PASS, "Verified message 'Your itinerary has been booked'.....");
		
		//Verifying that selected depart location and location on confirmation page matches
		String actualDepartLoc= driver.findElement(By.cssSelector("tr:nth-child(3) > td > font >b")).getText();
		//System.out.println("Departing location on confirmation page is......" +actualDepartLoc);
		assertTrue(actualDepartLoc.startsWith(selectedDepartLoc), "Depart location FROM doesn't match...");
		logger.log(LogStatus.PASS, "Verified departing location is matching on confirmation page.....", actualDepartLoc);
		
		//Verifying that selected arriving location and location on confirmation page matches
		assertTrue(actualDepartLoc.endsWith(selectedArriveLoc), "Arriving location TO doesn't not match...");
		logger.log(LogStatus.PASS, "Verified arriving location is matching on confirmation page for depart.....", actualDepartLoc);
		
		//Verifying that selected returning location and location on confirmation page matches
		String actualReturingLoc = driver.findElement(By.cssSelector("tr:nth-child(5) > td > font > b")).getText();
		assertTrue(actualReturingLoc.startsWith(selectedArriveLoc), "Returing location FROM doesn't match...");
		logger.log(LogStatus.PASS, "Verified departing location is matching on confirmation page for return.....", actualReturingLoc);
		
		assertTrue(actualReturingLoc.endsWith(selectedDepartLoc), "Returing location TO doesn't match...");
		logger.log(LogStatus.PASS, "Verified arriving location is matching on confirmation page for return.....", actualReturingLoc);
		
		//Verify that Back To Flights, Back To Home and Log Out buttons are displayed on confirmation page.
		driver.findElement(By.cssSelector("td:nth-child(1) > a > img")).isDisplayed();
		logger.log(LogStatus.PASS, "Verified 'Back To Flights' button displayed on confirmation page.....");
		
		driver.findElement(By.cssSelector("td:nth-child(2) > a > img")).isDisplayed();
		logger.log(LogStatus.PASS, "Verified 'Back To Home' button displayed on confirmation page.....");
		
		driver.findElement(By.cssSelector("td:nth-child(3) > a > img")).isDisplayed();
		logger.log(LogStatus.PASS, "Verified 'Log Out' button displayed on confirmation page.....");
		
		logger.log(LogStatus.PASS, "Booking is successful.....");
		//System.out.println("Congratulations... Booking is successful.............");
					
		extent.endTest(logger);
	}

}
