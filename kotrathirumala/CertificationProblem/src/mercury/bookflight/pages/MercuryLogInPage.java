package mercury.bookflight.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MercuryLogInPage {

	WebDriver driver;

	public MercuryLogInPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.NAME, using = "userName")
	WebElement uname;

	@FindBy(how = How.NAME, using = "password")
	WebElement password;

	@FindBy(how = How.NAME, using = "login")
	WebElement signInButton;

	public void userSignIn(String uid, String pwd) {

		uname.sendKeys(uid);
		password.sendKeys(pwd);
		signInButton.click();

	}
}
