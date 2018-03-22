package com.demoaut.mercurytours.TestCases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class VerifyItinerary extends BaseClass{
	
	public VerifyItinerary(){}
	
	@Test(description= "This test will check booking history and verify itinerary")
	public void verifyBookingHistory() throws Exception{
		//1. Login to the newtours.com site
		LoginUserTest login= new LoginUserTest();
		login.validCredentialLogin();
		logger.appendChild(logger);
		logger= extent.startTest("Verify Itinerary.....");
		logger.log(LogStatus.INFO, "User has been logged in successfully.....");
		
		driver.findElement(By.linkText("ITINERARY")).click();
		logger.log(LogStatus.INFO, "Click on ITINERARY hyperlink.....");
		
		assertTrue(driver.getCurrentUrl().contains("mercuryitinerary.php"), "Invalid navigation...");
		logger.log(LogStatus.PASS, "navigatest to ITINERARY page.....");
		
		assertTrue(driver.findElement(By.cssSelector("td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(1)")).isDisplayed(), "Itinerary data not available..");
		logger.log(LogStatus.PASS, "Verified ITINERARY data available on page.....");
		
		extent.endTest(logger);
		
}
}