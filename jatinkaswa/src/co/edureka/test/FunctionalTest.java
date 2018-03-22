package co.edureka.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edureka.bookflight.BillingPageImpl;
import co.edureka.bookflight.ConfirmationPageImpl;
import co.edureka.bookflight.FlightFinderPageImpl;
import co.edureka.bookflight.SelectFlightPageImpl;
import co.edureka.driver.CommonDriver;
import co.edureka.sendemail.EmailSendingImpl;
import co.edureka.testreport.GeneratePdfReportImpl;
import co.edureka.useroperation.HomePage;
import co.edureka.useroperation.RegistrationPageImpl;

public class FunctionalTest extends CommonDriver{
	
	public FunctionalTest() {

	}

	CommonDriver commonDriver = new CommonDriver();
	HomePage homePage;
	GeneratePdfReportImpl generatePdfReportImpl;
	ConfirmationPageImpl confirmationPageImpl;

	@BeforeClass
    public void welcomeMessage() {
        System.out.println("Welcome to flight booking site.");
    }

    @AfterClass
    public void bookingMessage() {
        System.out.println("Thank you for booking. You will receive the tickets via mail.");
    }
	
	@BeforeMethod
	public void invokeBrowser() {
		commonDriver.openBrowser("chrome", "http://newtours.demoaut.com/");
		homePage = new HomePage(CommonDriver.oDriver);
	}

	@AfterMethod
	public void takeScreenshot(ITestResult testResult) throws Exception {
		String testStatus;
		if (testResult.getStatus() == ITestResult.FAILURE)
			testStatus = "Failed";
		else
			testStatus = "Passed";
		if (testStatus.equalsIgnoreCase("failed"))
			commonDriver.takeScreenshot();
		commonDriver.closeAllBrowser();

		generatePdfReportImpl.documentPdf(testResult.getMethod().getMethodName() + " : " + testStatus);
	}

	@BeforeTest
	public void createPdfReport() {
		generatePdfReportImpl = new GeneratePdfReportImpl();
		generatePdfReportImpl.createPdfTestReport();
	}

	@AfterTest
	public void closeBrowser() {
		generatePdfReportImpl.closePdf();
		EmailSendingImpl emailSendingImpl = new EmailSendingImpl();
		emailSendingImpl.sendReport();
	}

	@Test
	public void bookFlight() {
		register();
		navigateToFlightsBookingPage();
		enterFlightBookingDetails();
		selectFlight();
		enterBillingDetails();
		confirmationPageImpl = new ConfirmationPageImpl(CommonDriver.oDriver);
		assertEquals(confirmationPageImpl.flightBookingConfirmation.getText(), "Your itinerary has been booked!");
	}

	@Test
	public void validateCredentials() {
		
		homePage.userLogIn("jka", "jka");
		assertTrue(commonDriver.getDriver().getPageSource().contains("SIGN-OFF"));
		
		homePage.userLogIn("cde", "opq");
		assertFalse(commonDriver.getDriver().getPageSource().contains("SIGN-OFF"));
		assertTrue(commonDriver.getDriver().getPageSource()
				.contains("Enter your user information to access the member-only \n" + "        areas of this site."));
	}

	private void register() {
		RegistrationPageImpl registerPage = new RegistrationPageImpl(commonDriver.getDriver());
		homePage.registerMenu.click();
		registerPage.registerNewUser("jka", "jka");
	}

	private void navigateToFlightsBookingPage() {
		homePage.flightsDetails.click();
	}

	private void enterFlightBookingDetails() {
		FlightFinderPageImpl flightFinder = new FlightFinderPageImpl(commonDriver.getDriver());
		flightFinder.setFlightDetails("round trip", "1");
		flightFinder.setOutboundFlightDetails("London", "12", "10");
		flightFinder.setInboundFlightDetails("Paris", "12", "20");
		flightFinder.setAirlineClassDetails("economy", "No Preference");
		flightFinder.findFlights.click();
	}

	private void selectFlight() {
		SelectFlightPageImpl selectFlight = new SelectFlightPageImpl(commonDriver.getDriver());
		selectFlight.setOutboundFlight(1);
		selectFlight.setInboundFlight(2);
		selectFlight.reserveFlights.click();
	}

	private void enterBillingDetails() {
		BillingPageImpl billing = new BillingPageImpl(commonDriver.getDriver());
		billing.providePassengerDetails("Raul", "Fuentes", "Vegetarian");
		billing.provideCardDetails("Visa", "8765432112345678", "10", "2010", "Raul", "S", "Fuentes");
		billing.provideBillingAddress("", "", "", "", "", "UNITED STATES ");
		billing.provideDeliveryAddress("", "", "", "", "", "UNITED STATES ");
		billing.buyFlights.click();
	}
	
    @AfterSuite
    public void userFeedback() {
        System.out.println("Hope you enjoyed booking with newstours.demoaut.com. Kindly share your valuable feedback.");
    }
}