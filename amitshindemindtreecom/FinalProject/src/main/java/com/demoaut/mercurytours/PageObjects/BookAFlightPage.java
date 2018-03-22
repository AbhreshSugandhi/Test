package com.demoaut.mercurytours.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BookAFlightPage {
	
	@FindBy(name="passFirst0")
	private WebElement passengerFirstName;
	
	@FindBy(name="passLast0")
	private WebElement passengerLastName;
	
	@FindBy (name= "pass.0.meal")
	private WebElement passengerMealOptions;
	
	@FindBy(name= "creditCard")
	private WebElement creditCard_Type;
	
	@FindBy(name= "creditnumber")
	private WebElement creditCard_Number;
	
	@FindBy(name= "cc_exp_dt_mn")
	private WebElement creditCard_ExpiryMonth;
	
	@FindBy(name= "cc_exp_dt_yr")
	private WebElement creditCard_ExpiryYear;
	
	@FindBy(name= "cc_frst_name")
	private WebElement creditCard_FirstName;
	
	@FindBy(name= "cc_mid_name")
	private WebElement creditCard_MidName;
	
	@FindBy(name= "cc_last_name")
	private WebElement creditCard_LastName;
	
	@FindBy(name= "billAddress1")
	private WebElement billing_AdressLine1;
	
	@FindBy(name= "billAddress2")
	private WebElement billing_AdressLine2;
	
	@FindBy(name= "billCity")
	private WebElement billing_City;
	
	@FindBy(name= "billState")
	private WebElement billing_State;
	
	@FindBy(name= "billZip")
	private WebElement billing_PostCode;
	
	@FindBy(name= "billCountry")
	private WebElement billing_Country;
	
	@FindBy(name= "delAddress1")
	private WebElement delivery_AddressLine1;
	
	@FindBy(name= "delAddress2")
	private WebElement delivery_AddressLine2;
	
	@FindBy(name= "delCity")
	private WebElement delivery_City;
	
	@FindBy(name= "delState")
	private WebElement delivery_State;
	
	@FindBy(name= "delZip")
	private WebElement delivery_PostCode;
	
	@FindBy(name= "delCountry")
	private WebElement delivery_Country;
	
	@FindBy(name= "buyFlights")
	private WebElement securePurchaseBtn;
	
	public BookAFlightPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void enterBookingDetails(String[][] data){
		
		passengerFirstName.sendKeys(data[12][1]);
		passengerLastName.sendKeys(data[13][1]);
		Select mealOption = new Select(passengerMealOptions);
		mealOption.selectByVisibleText(data[16][1]);
		
		Select cc_CardType = new Select(creditCard_Type);
		cc_CardType.selectByVisibleText(data[17][1]);
		creditCard_Number.sendKeys(data[18][1]);
		Select cc_ExpiryMonth = new Select(creditCard_ExpiryMonth);
		cc_ExpiryMonth.selectByVisibleText(data[19][1]);
		Select cc_ExpiryYear = new Select(creditCard_ExpiryYear);
		cc_ExpiryYear.selectByVisibleText(data[20][1]);
		creditCard_FirstName.sendKeys(data[21][1]);
		creditCard_MidName.sendKeys(data[22][1]);
		creditCard_LastName.sendKeys(data[23][1]);
		
		billing_AdressLine1.clear();
		billing_AdressLine1.sendKeys(data[24][1]);
		billing_AdressLine2.sendKeys(data[25][1]);
		
		billing_City.clear();
		billing_City.sendKeys(data[26][1]);
		
		billing_State.clear();
		billing_State.sendKeys(data[27][1]);
		
		billing_PostCode.clear();
		billing_PostCode.sendKeys(data[28][1]);
		
		Select billingCountry = new Select(billing_Country);
		billingCountry.selectByVisibleText(data[29][1]);
		
		delivery_AddressLine1.clear();
		delivery_AddressLine1.sendKeys(data[30][1]);
		delivery_AddressLine2.sendKeys(data[31][1]);
		
		delivery_City.clear();
		delivery_City.sendKeys(data[32][1]);
		
		delivery_State.clear();
		delivery_State.sendKeys(data[33][1]);
		
		delivery_PostCode.clear();
		delivery_PostCode.sendKeys(data[34][1]);

		Select delCountry = new Select(delivery_Country);
		delCountry.selectByVisibleText(data[35][1]);
		
	}
	
	public void clickPurchaseButton(){
		securePurchaseBtn.click();
		//validate selected depart and arrive location in test cases
	}
	
}
