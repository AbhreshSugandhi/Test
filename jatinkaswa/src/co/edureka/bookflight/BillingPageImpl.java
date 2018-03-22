package co.edureka.bookflight;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BillingPageImpl {
	public BillingPageImpl(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(name = "passFirst0")
	private WebElement passFirstName;

	@FindBy(name = "passLast0")
	private WebElement passLastName;

	@FindBy(name = "pass.0.meal")
	private WebElement meal;

	@FindBy(name = "creditCard")
	private WebElement cardType;

	@FindBy(name = "creditnumber")
	public WebElement cardNumber;

	@FindBy(name = "cc_exp_dt_mn")
	private WebElement expiryMonth;

	@FindBy(name = "cc_exp_dt_yr")
	private WebElement expiryYear;

	@FindBy(name = "cc_frst_name")
	@CacheLookup
	public WebElement cardFirstName;

	@FindBy(name = "cc_mid_name")
	public WebElement cardMiddleName;

	@FindBy(name = "cc_last_name")
	@CacheLookup
	public WebElement cardLastName;

	@FindBy(name = "billAddress1")
	public WebElement billAddressOne;

	@FindBy(name = "billAddress2")
	public WebElement billAddressTwo;

	@FindBy(name = "billCity")
	public WebElement billCity;

	@FindBy(name = "billState")
	public WebElement billState;

	@FindBy(name = "billZip")
	public WebElement billZip;

	@FindBy(name = "billCountry")
	private WebElement billCountry;

	@FindBy(name = "delAddress1")
	public WebElement delAddressOne;

	@FindBy(name = "delAddress2")
	public WebElement delAddressTwo;

	@FindBy(name = "delCity")
	public WebElement delCity;

	@FindBy(name = "delState")
	public WebElement delState;

	@FindBy(name = "delZip")
	public WebElement delZip;

	@FindBy(name = "delCountry")
	private WebElement delCountry;

	@FindBy(name = "buyFlights")
	public WebElement buyFlights;

	private void selectMeal(String value) {
		Select dropdown = new Select(meal);
		dropdown.selectByVisibleText(value);
	}

	public void selectCardType(String value) {
		Select dropdown = new Select(cardType);
		dropdown.selectByVisibleText(value);
	}

	public void selectExpiryMonth(String value) {
		Select dropdown = new Select(expiryMonth);
		dropdown.selectByVisibleText(value);
	}

	public void selectExpiryYear(String value) {
		Select dropdown = new Select(expiryYear);
		dropdown.selectByVisibleText(value);
	}

	public void selectBillingCountry(String value) {
		Select dropdown = new Select(billCountry);
		dropdown.selectByVisibleText(value);
	}

	public void selectDeliveryCountry(String value) {
		Select dropdown = new Select(delCountry);
		dropdown.selectByVisibleText(value);
	}

	public void providePassengerDetails(String firstName, String lastName, String mealPreferance) {
		passFirstName.sendKeys(firstName);
		passLastName.sendKeys(lastName);
		selectMeal(mealPreferance);
	}

	public void provideCardDetails(String cardType, String cNumber, String cExpiryMonth, String cExpiryYear,
			String cFirstName, String cMiddleName, String cLastName) {
		selectCardType(cardType);
		cardNumber.sendKeys(cNumber);
		selectExpiryMonth(cExpiryMonth);
		selectExpiryYear(cExpiryYear);
		cardFirstName.sendKeys(cFirstName);
		cardMiddleName.sendKeys(cMiddleName);
		cardLastName.sendKeys(cLastName);
	}

	public void provideBillingAddress(String billingAddressOne, String billingAddressTwo, String city, String state, String postcode, String country) {
		billAddressOne.sendKeys(billingAddressOne);
		billAddressTwo.sendKeys(billingAddressTwo);
		billCity.sendKeys(city);
		billState.sendKeys(state);
		billZip.sendKeys(postcode);
		selectBillingCountry(country);
	}

	public void provideDeliveryAddress(String deliveryAddressOne, String deliveryAddressTwo, String city, String state, String postcode, String country) {
		delAddressOne.sendKeys(deliveryAddressOne);
		delAddressTwo.sendKeys(deliveryAddressTwo);
		delCity.sendKeys(city);
		delState.sendKeys(state);
		delZip.sendKeys(postcode);
		selectDeliveryCountry(country);
	}
}