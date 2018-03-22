package com.demoaut.mercurytours.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegisterUserPage {
	
	@FindBy(xpath= "//input[@name='firstName']")
	private WebElement firstName;

	@FindBy(css= "tr:nth-child(3) > td:nth-child(2) >input")
	private WebElement lastName;

	@FindBy(name = "phone")
	private WebElement phoneNumber;

	@FindBy(id = "userName")
	private WebElement user_Email;

	@FindBy(name = "address1")
	private WebElement address_FistLine;

	@FindBy(name = "address2")
	private WebElement address_SecondLine;

	@FindBy(name = "city")
	private WebElement address_City;

	@FindBy(name = "state")
	private WebElement address_State;

	@FindBy(name = "postalCode")
	private WebElement address_PostCode;

	@FindBy(name = "country")
	private WebElement address_Country;

	@FindBy(name = "email")
	private WebElement user_LoginName;

	@FindBy(name = "password")
	private WebElement user_LoginPassword;

	@FindBy(name = "confirmPassword")
	private WebElement user_ConfirmPassword;

	@FindBy(name = "register")
	private WebElement register_SubmitBtn;

	
	public RegisterUserPage(WebDriver driver)
	{
			PageFactory.initElements(driver, this);
	}
	
	
	public void registerUserDetails(String[][] data){
		
		firstName.sendKeys(data[7][1]);
		lastName.sendKeys(data[8][1]);
		phoneNumber.sendKeys(data[9][1]);
		user_Email.sendKeys(data[10][1]);
		address_FistLine.sendKeys(data[11][1]);
		address_SecondLine.sendKeys(data[12][1]);
		address_City.sendKeys(data[13][1]);
		address_State.sendKeys(data[14][1]);
		address_PostCode.sendKeys(data[15][1]);
		Select userCountry = new Select(address_Country);
		userCountry.selectByVisibleText(data[16][1]);
		user_LoginName.sendKeys(data[17][1]);
		user_LoginPassword.sendKeys(data[18][1]);
		user_ConfirmPassword.sendKeys(data[19][1]);
		
		register_SubmitBtn.click();
		
	}
}