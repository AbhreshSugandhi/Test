package com.demoaut.mercurytours.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignOnPage {
	
	@FindBy(name= "userName")
	@CacheLookup
	private WebElement username;
	
	@FindBy(name= "password")
	@CacheLookup
	private WebElement password;
	
	@FindBy(xpath= "//input[@name='login']|//input[@value='Login']")
	@CacheLookup
	private WebElement login_SubmitBtn;
	
	public SignOnPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void applicationLogin(String[][] data)
	{	
		username.sendKeys(data[2][0]);
		password.sendKeys(data[2][1]);
		login_SubmitBtn.click();
	}
}
