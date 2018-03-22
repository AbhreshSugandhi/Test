package com.project.webtour.pages.finderflights;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.project.webtours.base.Base;

public class FlightConfirmation extends Base {
	
	@FindBy(how=How.XPATH,using="//img[@src='/images/forms/backtoflights.gif']")
	@CacheLookup
	WebElement return_option;
	
	FlightConfirmation()
	{
		super();
	}
	public void returnticket()
	{
		return_option.click();
	
		
	}

}
