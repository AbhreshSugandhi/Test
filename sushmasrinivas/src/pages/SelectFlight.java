package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectFlight {
	WebDriver driver;
	By continueSelect=By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/p/input");
	By securePurchase=By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[23]/td/input");

	public SelectFlight(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}

	public void selectFlightVerification() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.findElement(continueSelect).click();
		Thread.sleep(1000);
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(securePurchase));
	}


}
