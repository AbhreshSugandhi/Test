package co.edureka.bookflight;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectFlightPageImpl {

	public SelectFlightPageImpl(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "outFlight")
	private List<WebElement> outboundFlight;

	@FindBy(name = "inFlight")
	private List<WebElement> inboundFlight;

	@FindBy(name = "reserveFlights")
	public WebElement reserveFlights;

	public void setOutboundFlight(int number) {
		outboundFlight.get(number).click();
	}

	public void setInboundFlight(int number) {
		inboundFlight.get(number).click();
	}
}