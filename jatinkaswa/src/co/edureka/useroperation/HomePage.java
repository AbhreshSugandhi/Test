package co.edureka.useroperation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "userName")
	public WebElement userName;

	@FindBy(name = "password")
	public WebElement password;

	@FindBy(name = "login")
	public WebElement loginButton;

	@FindBy(linkText = "Flights")
	public WebElement flightsDetails;

	@FindBy(linkText = "Home")
	public WebElement homeMenu;

	@FindBy(xpath = "//a[text()='REGISTER']")
	public WebElement registerMenu;

	public void userLogIn(String id, String pass) {
		homeMenu.click();
		userName.sendKeys(id);
		password.sendKeys(pass);
		loginButton.click();
	}
}