package com.project.webtour.pages.finderflights;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.project.webtours.base.Base;

public class SelectFlight extends Base{
	

	@FindBy(how=How.XPATH,using="//input[@name='reserveFlights']")
	@CacheLookup
	WebElement continue_button_reserve;
	
	public SelectFlight()
	{
		super();	
	}
	
	public PurchaseTicket reservation()
	{
		
		scroll();
		//click("CONTINUE_BUTTON_RESERVE");
		continue_button_reserve.click();
		return new PurchaseTicket();
	
	}

}
