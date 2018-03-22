package mercury.bookflight.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import helper.BrowserHelper;
import helper.JyperionListener;
import helper.SendEmailReport;
import mercury.bookflight.pages.BookFlightPage;
import mercury.bookflight.pages.FindFlightPage;
import mercury.bookflight.pages.FlightConfirmation;
import mercury.bookflight.pages.MercuryLogInPage;
import mercury.bookflight.pages.SelectFlightPage;

@Listeners(JyperionListener.class)

public class MercuryTestCases {

	WebDriver driver;
	
	public MercuryTestCases() {
		System.out.println("Empty constructor.");
	}

	@BeforeMethod
	public void openBrowser() {
		driver = BrowserHelper.startBrowser("chrome", "http://newtours.demoaut.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

	@Test(priority = 0, description = "Test: Validating the user credentials")
	public void validateLogIn() {
		MercuryLogInPage logInPage = PageFactory.initElements(driver, MercuryLogInPage.class);
		logInPage.userSignIn("mselenium", "mselenium");
		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, "Find a Flight: Mercury Tours:");
	}

	@Test(priority = 1, description = "Test: Book flight tickets for return journey")
	public void bookReturnFlightTickets() {
		MercuryLogInPage logInPage = PageFactory.initElements(driver, MercuryLogInPage.class);
		logInPage.userSignIn("mselenium", "mselenium");

		FindFlightPage findFlightPage = PageFactory.initElements(driver, FindFlightPage.class);
		findFlightPage.roundTripJourney();

		findFlightPage.passengerCount("1");

		findFlightPage.departingFrom("Frankfurt");

		findFlightPage.departingMonth("3");

		findFlightPage.departingDay("12");

		findFlightPage.arrivingIn("London");

		findFlightPage.returningMonth("3");

		findFlightPage.returningDay("15");

		findFlightPage.businessServiceClass();

		findFlightPage.airLinePreference("Blue Skies Airlines");

		findFlightPage.clickOnFindFlightsFinishButton();

		SelectFlightPage selectFlightsPage = PageFactory.initElements(driver, SelectFlightPage.class);

		selectFlightsPage.departFlightBlueSkiesAirlines361();

		selectFlightsPage.returnFlightBlueSkiesAirlines631();

		selectFlightsPage.clickOnSelectFlightsFinishButton();

		BookFlightPage bookFlightsPage = PageFactory.initElements(driver, BookFlightPage.class);

		bookFlightsPage.enterPassengerFirstName("mselenium");

		bookFlightsPage.enterPassengerLastName("mindtree");

		bookFlightsPage.mealsPreference("VGML");

		bookFlightsPage.creditCradType("BA");

		bookFlightsPage.enterCreditcardNumber("4554787239674290");

		bookFlightsPage.creditcardExpiryMonth("03");

		bookFlightsPage.creditcardExpiryYear("2010");

		bookFlightsPage.enterCreditcardFirstName("mindtree");

		bookFlightsPage.enterCreditcardMidName("selenium");

		bookFlightsPage.enterCreditcardLastName("edureka");

		bookFlightsPage.enterBillingAddress("Mindtree Ltd");

		bookFlightsPage.enterBillingCity("Bengaluru");

		bookFlightsPage.enterBillingState("Karnataka");

		bookFlightsPage.enterBillingZipcode("560059");

		bookFlightsPage.enterBillingCountry("92");

		bookFlightsPage.deliveryAddress();

		bookFlightsPage.clickOnBuyFlightsFinishButton();

		FlightConfirmation booking_confirmation = PageFactory.initElements(driver, FlightConfirmation.class);

		booking_confirmation.flightConfirmation();
	}

	@AfterSuite
	public void sendReport() {
		SendEmailReport.sendPdfReport("sendFrom@gmail.com", "passwordhere", "sendTo@gmail.com", "Test report",
				"Report");

	}

}
