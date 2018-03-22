package com.demoaut.mercurytours.TestCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.demoaut.mercurytours.PageObjects.SignOnPage;
import com.demoaut.mercurytours.Utility.ReadExcel;
import com.relevantcodes.extentreports.LogStatus;

public class LoginUserTest extends BaseClass {
	
	public LoginUserTest(){}
	
	//@Test(description= "This test will perform login scenario with VALID username and password")
	@Test(priority=0 ,description= "This test will perform login scenario with VALID username and password")
	public void validCredentialLogin() throws IOException, Exception{
		
		logger= extent.startTest("Valid Credential Login");
		String[][] data= ReadExcel.getLoginOrRegisterUserData(); //Getting VALID user credentials.
					
		logger.log(LogStatus.INFO, "Click on Sign-On Link.....");
		driver.findElement(By.linkText("SIGN-ON")).click();
				
		assertTrue(driver.getCurrentUrl().contains("mercurysignon.php"), "Invalid navigation: ");
		logger.log(LogStatus.INFO, "Successfully navigated to Sign-On Link.....");
		
		SignOnPage signOn = new SignOnPage(driver);
		signOn.applicationLogin(data);
		logger.log(LogStatus.INFO, "Entered username and passowrd.....");
		
		assertTrue(driver.getTitle().startsWith("Find a Flight"), "Incorrect username or password: ");
		logger.log(LogStatus.PASS, "User successfully logged in.....");
				
		extent.endTest(logger);
	}	
	
	@Test(priority=1, description= "This test will perform negative login scenario with INVALID username and password")
	public void invalidCredentialLogin() throws IOException, Exception{
		
		logger= extent.startTest("Invalid Credential Login");
		
		String[][] data= ReadExcel.getInvalidLoginData(); //Getting INVALID user credentials.
		
		logger.log(LogStatus.INFO, "Click on Sign-On Link.....");
		driver.findElement(By.linkText("SIGN-ON")).click();
				
		assertTrue(driver.getCurrentUrl().contains("mercurysignon.php"), "Invalid navigation: ");
		logger.log(LogStatus.INFO, "Successfully navigated to Sign-On Link.....");
		
		SignOnPage signOn = new SignOnPage(driver);
		signOn.applicationLogin(data);
		logger.log(LogStatus.INFO, "Entered invalid username and passowrd.....");
		
		assertTrue(driver.getCurrentUrl().contains("mercurysignon.php"), "Log in successful with invalid credentials: ");
		logger.log(LogStatus.PASS, "User cannot logged in.....");
		extent.endTest(logger);
		
	}	
	
}
