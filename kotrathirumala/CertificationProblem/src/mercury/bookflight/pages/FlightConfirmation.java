package mercury.bookflight.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FlightConfirmation {

	WebDriver driver;

	public FlightConfirmation(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//table/tbody/tr[3]/td/p/font/b/font[2]")
	WebElement ticketConfirmation;

	public void flightConfirmation() {
		ticketConfirmation.isDisplayed();
	}

}
