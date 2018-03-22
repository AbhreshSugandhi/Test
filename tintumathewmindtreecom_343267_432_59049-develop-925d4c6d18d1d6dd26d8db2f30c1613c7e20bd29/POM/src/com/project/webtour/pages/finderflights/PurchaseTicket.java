package com.project.webtour.pages.finderflights;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.project.webtours.base.Base;

public class PurchaseTicket extends Base {
	

	@FindBy(how=How.XPATH,using="//input[@name='passFirst0']")
	@CacheLookup
	WebElement first_name;
	
	@FindBy(how=How.XPATH,using="//input[@name='passLast0']")
	@CacheLookup
	WebElement last_name;
	
	@FindBy(how=How.XPATH,using="//input[@name='creditnumber']")
	@CacheLookup
	WebElement credit_no;
	
	@FindBy(how=How.XPATH,using="//input[@name='buyFlights']")
	@CacheLookup
	WebElement purchase_button;
	
	public PurchaseTicket()
	{
		super();
	}
	
	public void purchase()
	{
	
		first_name.sendKeys(config.getProperty("firstname"));
		last_name.sendKeys(config.getProperty("lastname"));
		credit_no.sendKeys(config.getProperty("credit_no"));
		purchase_button.click();
		
	}

}
