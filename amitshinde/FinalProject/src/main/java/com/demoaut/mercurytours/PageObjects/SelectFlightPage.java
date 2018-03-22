package com.demoaut.mercurytours.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SelectFlightPage {

		/*@FindBy(xpath="//input[contains(@value, 'Blue Skies Airlines$361')]")
		private WebElement departBlueSkies361Radio;*/
		
		@FindBy(css="table:nth-child(9) > tbody > tr:nth-child(5) > td.frame_action_xrows > input[type='radio']")
		private WebElement departBlueSkies361Radio;
		
				
		/*@FindBy(xpath="//input[contains(@value, 'Blue Skies Airlines$631')]")
		private WebElement returnBlueSkies631Radio;*/
		@FindBy(css="table:nth-child(10) > tbody > tr:nth-child(5) > td.frame_action_xrows > input[type='radio']")
		private WebElement returnBlueSkies631Radio;
								
		@FindBy(name="reserveFlights")
		private WebElement reserveContinueBtn;
				
		public SelectFlightPage(WebDriver driver){
			PageFactory.initElements(driver, this);
		}
		
		public void reserveFlight(){
			
			departBlueSkies361Radio.click();
			returnBlueSkies631Radio.click();
			reserveContinueBtn.click();
		}
}
