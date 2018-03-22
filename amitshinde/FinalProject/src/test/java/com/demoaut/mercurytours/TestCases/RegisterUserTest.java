package com.demoaut.mercurytours.TestCases;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.demoaut.mercurytours.PageObjects.*;
import com.demoaut.mercurytours.Utility.ReadExcel;
import com.relevantcodes.extentreports.LogStatus;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class RegisterUserTest extends BaseClass{
	
	public RegisterUserTest(){}
	
	@Test(description= "This test will perform to register new user")
	public void registerNewUser() throws IOException, Exception{
		
		logger= extent.startTest("Register user test");
		String[][] data = ReadExcel.getLoginOrRegisterUserData();
		
		//Click on "REGISTER" button hyperlink
		driver.findElement(By.xpath("//tr[2]/td/table/tbody/tr/td[2]/a")).click();
		logger.log(LogStatus.INFO, "Clicked on REGISTER Link.....");
		
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("mercuryregister.php"), "Invalid navigation.....");
		logger.log(LogStatus.INFO, "Successfully navigated to REGISTER Link.....");
		
		RegisterUserPage ru = new RegisterUserPage(driver);
		ru.registerUserDetails(data);
		logger.log(LogStatus.INFO, "Entered details in registration form.....");
		
		assertTrue(driver.getCurrentUrl().endsWith("create_account_success.php"), "Registration failed:: ");;
		logger.log(LogStatus.PASS, "User successfully registered.....");
		extent.endTest(logger);
		
	}	
}
