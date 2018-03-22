package co.edureka.useroperation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPageImpl {
	public RegistrationPageImpl(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "email")
	private WebElement userName;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(name = "confirmPassword")
	private WebElement confirmPassword;

	@FindBy(name = "register")
	private WebElement registerButton;

	public void registerNewUser(String id, String pass) {
		userName.sendKeys(id);
		password.sendKeys(pass);
		confirmPassword.sendKeys(pass);
		registerButton.click();
	}
}