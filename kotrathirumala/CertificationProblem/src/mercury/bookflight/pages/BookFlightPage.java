package mercury.bookflight.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class BookFlightPage {

  WebDriver driver;

  public BookFlightPage(WebDriver driver) {
    this.driver = driver;
  }

	@FindBy(how = How.XPATH, using = "//input[@name='passFirst0']")
	WebElement pFirstname;

	@FindBy(how = How.XPATH, using = "//input[@name='passLast0']")
	WebElement pLastname;

	@FindBy(how = How.XPATH, using = "//select[@name='pass.0.meal']")
	WebElement mealPref;

	@FindBy(how = How.XPATH, using = "//select[@name='creditCard']")
	WebElement cardType;

	@FindBy(how = How.XPATH, using = "//input[@name='creditnumber']")
	WebElement creditCardNumber;

	@FindBy(how = How.XPATH, using = "//select[@name='cc_exp_dt_mn']")
	WebElement cardExpiryMonth;

	@FindBy(how = How.XPATH, using = "//select[@name='cc_exp_dt_yr']")
	WebElement cardExpiryYear;

	@FindBy(how = How.XPATH, using = "//input[@name='cc_frst_name']")
	WebElement cardFirstName;

	@FindBy(how = How.XPATH, using = "//input[@name='cc_mid_name']")
	WebElement cardMidName;

	@FindBy(how = How.XPATH, using = "//input[@name='cc_last_name']")
	WebElement cardLastName;

	@FindBy(how = How.XPATH, using = "//input[@name='billAddress1']")
	WebElement billAddr;

	@FindBy(how = How.XPATH, using = "//input[@name='billCity']")
	WebElement billCity;

	@FindBy(how = How.XPATH, using = "//input[@name='billState']")
	WebElement billState;

	@FindBy(how = How.XPATH, using = "//input[@name='billZip']")
	WebElement billZipCode;

	@FindBy(how = How.XPATH, using = "//select[@name='billCountry']")
	WebElement billCountry;

	@FindBy(how = How.XPATH, using = "(//input[@type='checkbox'])[2]")
	WebElement deliAddr;

	@FindBy(how = How.XPATH, using = "//input[@name='buyFlights']")
	WebElement buyFlightsButton;

  public void enterPassengerFirstName(String fname) {
    pFirstname.clear();
    pFirstname.sendKeys(fname);
  }

	public void enterPassengerLastName(String lname) {
		pLastname.clear();
		pLastname.sendKeys(lname);
	}

	public void mealsPreference(String mealtype) {
		Select meal = new Select(mealPref);
		meal.selectByValue(mealtype);
	}

	public void creditCradType(String creditcardType) {
		Select card = new Select(cardType);
		card.selectByValue(creditcardType);
	}

	public void enterCreditcardNumber(String ccdNumber) {
		creditCardNumber.clear();
		creditCardNumber.sendKeys(ccdNumber);
	}

	public void creditcardExpiryMonth(String eMonth) {
		Select expMonth = new Select(cardExpiryMonth);
		expMonth.selectByVisibleText(eMonth);
	}

	public void creditcardExpiryYear(String eYear) {
		Select expYear = new Select(cardExpiryYear);
		expYear.selectByValue(eYear);
	}

	public void enterCreditcardFirstName(String fname) {
		cardFirstName.clear();
		cardFirstName.sendKeys(fname);
	}

	public void enterCreditcardMidName(String mName) {
		cardMidName.clear();
		cardMidName.sendKeys(mName);
	}

	public void enterCreditcardLastName(String lName) {
		cardLastName.clear();
		cardLastName.sendKeys(lName);
	}

	public void enterBillingAddress(String baddr) {
		billAddr.clear();
		billAddr.sendKeys(baddr);
	}

	public void enterBillingCity(String bcity) {
		billCity.clear();
		billCity.sendKeys(bcity);
	}

	public void enterBillingState(String bstate) {
		billState.clear();
		billState.sendKeys(bstate);
	}

	public void enterBillingCountry(String bcountry) {
		Select country = new Select(billCountry);
		country.selectByValue(bcountry);
	}

	public void enterBillingZipcode(String bzip) {
		billZipCode.clear();
		billZipCode.sendKeys(bzip);
	}

	public void deliveryAddress() {
		deliAddr.click();
	}

	public void clickOnBuyFlightsFinishButton() {
		buyFlightsButton.click();
	}
}
