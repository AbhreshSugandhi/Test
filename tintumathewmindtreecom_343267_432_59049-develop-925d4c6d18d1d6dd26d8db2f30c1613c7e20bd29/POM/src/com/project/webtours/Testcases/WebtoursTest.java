package com.project.webtours.Testcases;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.project.webtour.pages.finderflights.FinderFlights;
import com.project.webtour.pages.finderflights.PurchaseTicket;
import com.project.webtour.pages.finderflights.SelectFlight;
import com.project.webtour.pages.login.LoginPage;
import com.project.webtours.base.Base;
public class WebtoursTest extends Base
{
	WebtoursTest()
	{
		super();
	}
	
	
//Test case for login 
	@Test
	public void loginTest()
	{
		LoginPage lp =PageFactory.initElements(driver, LoginPage.class);
		
		lp.login(config.getProperty("username"),config.getProperty("password"));
				
		resultList.add("Test Suite Starts: " );
		if( isElementPresent("SIGNOFF"))
		{
			System.out.println("Login success");
			Assert.assertFalse(false);
			addToResult(1,"Login Test","All scenarios cleared "," ->Pass");
		
		}
		else
		{
			System.out.println("Login not success");
			Assert.assertFalse(true);
			addToResult(1,"Login Test","All scenarios not cleared"," ->Fail");
			
		}
		
		
	}
	
	
	//Test case for booking return ticket
	@Test
	public void returnTicketTest()
	{
		
		
		FinderFlights ff=PageFactory.initElements(driver, FinderFlights.class);
		ff.findflights();
		SelectFlight sf= PageFactory.initElements(driver, SelectFlight.class);
		sf.reservation();
		PurchaseTicket pf = PageFactory.initElements(driver, PurchaseTicket.class);
		pf.purchase();

		if(isElementPresent("CONFIRMATION_IMG"))
		{
			System.out.println("Ticket Booking succcess");
			Assert.assertFalse(false);
			addToResult(2,"Return Ticket","All scenarios cleared "," ->Pass");
		
		}
		else
		{
			System.out.println("Ticket Booking Not success");
			Assert.assertFalse(true);
			addToResult(2,"Return Ticket","All scenarios not cleared "," ->Fail");
			
		}
		 
	}
	
	
}
