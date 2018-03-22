package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookAFlight {
	WebDriver driver;
	By securePurchase=By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[23]/td/input");
	By logout=By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[7]/td/table/tbody/tr/td[3]/a/img");


	public BookAFlight(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}



	public void bookAFlightFinderVerfication() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.findElement(securePurchase).click();
		Thread.sleep(1000);
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(logout));
	}

}
