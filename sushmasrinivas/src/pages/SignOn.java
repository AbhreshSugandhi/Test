package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignOn {
	WebDriver driver;
	By userName=By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[1]/td[2]/input");
	By password=By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td[2]/input");
	By submit=By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td/input");
	By continueOption=By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[14]/td/input");

	public SignOn(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	public void verificationOfLoginCredentialsForUser(String name,String pass) throws InterruptedException {
		// TODO Auto-generated method stub
		driver.findElement(userName).sendKeys(name);
		Thread.sleep(1000);
		driver.findElement(password).sendKeys(pass);
		Thread.sleep(1000);
		driver.findElement(submit).click();
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(continueOption));
	}

}

