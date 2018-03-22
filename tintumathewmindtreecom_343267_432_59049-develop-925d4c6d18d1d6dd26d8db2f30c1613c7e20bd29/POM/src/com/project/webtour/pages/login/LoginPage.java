package com.project.webtour.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.project.webtour.pages.finderflights.FinderFlights;
import com.project.webtours.base.Base;

public class LoginPage extends Base


{	
	@FindBy(how=How.XPATH,using="//input[@name='userName']")
	@CacheLookup
	WebElement username;
	
	@FindBy(how=How.XPATH,using="//input[@name='password']")
	@CacheLookup
	WebElement password;
	
	@FindBy(how=How.XPATH,using="//input[@name='login']")
	@CacheLookup
	WebElement login_button;
  public LoginPage()
  {
	  super();
	  	  
  }
	public FinderFlights login(String username,String password)
	{    driver.get(config.getProperty("url"));
	     this.username.sendKeys(username);
	     this.password.sendKeys(password);
		 login_button.click();
		 return new FinderFlights ();
		
	}
	
	
}
