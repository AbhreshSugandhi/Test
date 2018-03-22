package com.project.webtour.pages.finderflights;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.project.webtours.base.Base;

public class FinderFlights extends Base
{
	
	@FindBy(how=How.XPATH,using="//select[@name='fromPort']")
	@CacheLookup
	WebElement from_input;
	
	@FindBy(how=How.XPATH,using="//select[@name='toPort']")
	@CacheLookup
	WebElement to_input;
	
	@FindBy(how=How.XPATH,using="//input[@name='findFlights']")
	@CacheLookup
	WebElement continue_button;
	
	public FinderFlights()
	{
		System.out.println("From Base Page");
	}
	
	public SelectFlight findflights()
	{
		scroll();
		from_input.sendKeys(config.getProperty("source"));
		to_input.sendKeys(config.getProperty("destination"));
	    continue_button.click();
	    return new SelectFlight();
	}

}
