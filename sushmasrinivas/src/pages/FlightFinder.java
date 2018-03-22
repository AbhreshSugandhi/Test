package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightFinder {
	WebDriver driver;
	By continueButton=By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[14]/td/input");
	By continueSelect=By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/p/input");

	public FlightFinder(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}

	public void flightFinderVerification() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.findElement(continueButton).click();
		Thread.sleep(1000);
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(continueSelect));
	}

}
