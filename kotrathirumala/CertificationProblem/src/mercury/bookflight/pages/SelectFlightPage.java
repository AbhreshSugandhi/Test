package mercury.bookflight.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SelectFlightPage {

	WebDriver driver;

	public SelectFlightPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//input[@value='Blue Skies Airlines$361$271$7:10']")
	WebElement depFlight;

	@FindBy(how = How.XPATH, using = "//input[@value='Blue Skies Airlines$631$273$14:30']")
	WebElement destFlight;

	@FindBy(how = How.XPATH, using = "//input[@name='reserveFlights']")
	WebElement selectFlightDoneButton;

	public void departFlightBlueSkiesAirlines361() {
		depFlight.click();
	}

	public void returnFlightBlueSkiesAirlines631() {
		destFlight.click();
	}

	public void clickOnSelectFlightsFinishButton() {
		selectFlightDoneButton.click();
	}
}
