package test;




import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.Properties;

import javax.activation.DataHandler;

import javax.activation.DataSource;

import javax.activation.FileDataSource;

import javax.mail.BodyPart;

import javax.mail.Message;

import javax.mail.MessagingException;

import javax.mail.Multipart;

import javax.mail.Session;

import javax.mail.Transport;

import javax.mail.internet.AddressException;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeBodyPart;

import javax.mail.internet.MimeMessage;

import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;

import org.testng.annotations.AfterSuite;

import org.testng.annotations.Listeners;

import org.testng.annotations.Test;

//import reporter.JyperionListener;

import org.testng.annotations.AfterTest;
import pages.FlightConfirmation;
import pages.FlightFinder;
import pages.LoginPage;
import pages.SelectFlight;
import pages.SignOn;
import pages.BookAFlight;


@Listeners(JyperionListener.class)
public class TestClassPdfEmail {
	WebDriver driver;
	LoginPage loginObj;
	FlightFinder flightFinderObj;
	SelectFlight selectFlightObj;
	BookAFlight bookAFlightObj;
	FlightConfirmation flightConfirmationObj;
	SignOn signOnObj;

	@AfterTest
	public void closeBrowser()
	{
		driver.close();
	}
	/*@BeforeTest
	public void OpenBrowser()
	{
		System.setProperty("webdriver.chrome.driver",
				"D:\\selenium\\selenium web driver\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://newtours.demoaut.com");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}*/
	@Test(priority=1)
	public void successfullyBookingATicketByLoggingIntoApplication() throws InterruptedException{
		driver=BaseClass.getDriver();
		driver.get("http://newtours.demoaut.com");
		loginObj=new LoginPage(driver);
		loginObj.loginPageVerification("mercury","mercury");
		flightFinderObj=new FlightFinder(driver);
		flightFinderObj.flightFinderVerification();
		selectFlightObj=new SelectFlight(driver);
		selectFlightObj.selectFlightVerification();
		bookAFlightObj=new BookAFlight(driver);
		bookAFlightObj.bookAFlightFinderVerfication();
		flightConfirmationObj=new FlightConfirmation(driver);
		flightConfirmationObj.flightConfirmationVerification();
	}
	@Test(priority=2)
	public void verificationOfLoginCredentials() throws InterruptedException{
		driver=BaseClass.getDriver();
		driver.get("http://newtours.demoaut.com");
		signOnObj=new SignOn(driver);
		signOnObj.verificationOfLoginCredentialsForUser("xyz","abc");
	}

	@AfterSuite

	public void tearDown(){

		sendPDFReportByGMail("FROM@gmail.com", "password", "TO@gmail.com", "PDF Report", "");

	}
	private static void sendPDFReportByGMail(String from, String pass, String to, String subject, String body) {

		Properties props = System.getProperties();

		String host = "smtp.gmail.com";

		props.put("mail.smtp.starttls.enable", "true");

		props.put("mail.smtp.host", host);

		props.put("mail.smtp.user", from);

		props.put("mail.smtp.password", pass);

		props.put("mail.smtp.port", "587");

		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);

		MimeMessage message = new MimeMessage(session);

		try {//Set from address

			message.setFrom(new InternetAddress(from));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			//Set subject

			message.setSubject(subject);

			message.setText(body);

			BodyPart objMessageBodyPart = new MimeBodyPart();

			objMessageBodyPart.setText("Please Find The Attached Report File!");

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(objMessageBodyPart);

			objMessageBodyPart = new MimeBodyPart();
			//Set path to the pdf report file

			String filename = System.getProperty("user.dir")+"\\Default test.pdf";

			//Create data source to attach the file in mail

			DataSource source = new FileDataSource(filename);

			objMessageBodyPart.setDataHandler(new DataHandler(source));

			objMessageBodyPart.setFileName(filename);

			multipart.addBodyPart(objMessageBodyPart);

			message.setContent(multipart);

			Transport transport = session.getTransport("smtp");

			transport.connect(host, from, pass);

			transport.sendMessage(message, message.getAllRecipients());

			transport.close();

		}catch (AddressException ae) {

			ae.printStackTrace();

		}

		catch (MessagingException me) {

			me.printStackTrace();

		}

	}

}
