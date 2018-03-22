package com.project.webtours.Testcases;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.project.webtour.pages.finderflights.FinderFlights;
import com.project.webtour.pages.finderflights.PurchaseTicket;
import com.project.webtour.pages.finderflights.SelectFlight;
import com.project.webtour.pages.login.LoginPage;
import com.project.webtours.base.Base;
public class ReturnTicketTest extends Base
{
	ReturnTicketTest()
	{
		super();
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
			System.out.println("Did not reach here");
		}
		else
		{
			System.out.println("Ticket Booking Not success");
			Assert.assertFalse(true);
			addToResult(2,"Return Ticket","All scenarios not cleared "," ->Fail");
			
		}
		 
	}
	
	
}
