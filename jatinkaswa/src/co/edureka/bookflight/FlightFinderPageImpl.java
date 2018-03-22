package co.edureka.bookflight;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FlightFinderPageImpl {

	public FlightFinderPageImpl(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@value='roundtrip']")
	private WebElement roundTripType;

	@FindBy(xpath = "//input[@value='oneway']")
	private WebElement oneWayTripType;

	@FindBy(name = "passCount")
	private WebElement passCount;

	@FindBy(name = "fromPort")
	private WebElement fromPort;

	@FindBy(name = "fromMonth")
	private WebElement fromMonth;

	@FindBy(name = "fromDay")
	private WebElement fromDay;

	@FindBy(name = "toPort")
	private WebElement toPort;

	@FindBy(name = "toMonth")
	private WebElement toMonth;

	@FindBy(name = "toDay")
	private WebElement toDay;

	@FindBy(xpath = "//input[@value='Coach']")
	private WebElement economyClass;

	@FindBy(xpath = "//input[@value='Business']")
	private WebElement businessClass;

	@FindBy(xpath = "//input[@value='First']")
	private WebElement firstClass;

	@FindBy(name = "airline")
	private WebElement airline;

	@FindBy(name = "findFlights")
	public WebElement findFlights;

	private void selectPassCount(String number) {
		Select dropdown = new Select(passCount);
		dropdown.selectByValue(number);
	}

	private void selectFromPort(String from) {
		Select dropdown = new Select(fromPort);
		dropdown.selectByValue(from);
	}

	private void selectFromMonth(String month) {
		Select dropdown = new Select(fromMonth);
		dropdown.selectByValue(month);
	}

	private void selectFromDay(String day) {
		Select dropdown = new Select(fromDay);
		dropdown.selectByValue(day);
	}

	private void selectToPort(String to) {
		Select dropdown = new Select(toPort);
		dropdown.selectByValue(to);
	}

	private void selectToMonth(String month) {
		Select dropdown = new Select(toMonth);
		dropdown.selectByValue(month);
	}

	private void selectToDay(String day) {
		Select dropdown = new Select(toDay);
		dropdown.selectByValue(day);
	}

	private void selectAirline(String line) {
		Select dropdown = new Select(airline);
		dropdown.selectByVisibleText(line);
	}

	public void setFlightDetails(String tripType, String passCount) {
		if (tripType.equalsIgnoreCase("round trip"))
			roundTripType.click();
		else
			oneWayTripType.click();
		selectPassCount(passCount);
	}

	public void setOutboundFlightDetails(String location, String month, String day) {
		selectFromPort(location);
		selectFromMonth(month);
		selectFromDay(day);
	}

	public void setInboundFlightDetails(String location, String month, String day) {
		selectToPort(location);
		selectToMonth(month);
		selectToDay(day);
	}

	public void setAirlineClassDetails(String serviceClass, String airlinePreference) {
		if (serviceClass.equalsIgnoreCase("economy"))
			economyClass.click();
		else if (serviceClass.equalsIgnoreCase("business"))
			businessClass.click();
		else
			firstClass.click();
		selectAirline(airlinePreference);
	}
}