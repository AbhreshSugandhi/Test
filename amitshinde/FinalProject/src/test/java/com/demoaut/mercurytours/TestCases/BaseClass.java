package com.demoaut.mercurytours.TestCases;

import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {

	public BaseClass(){}
	public static WebDriver driver;
	static ExtentReports extent;
	static ExtentTest logger;
	
	@BeforeTest
	public void startReport(){
				//ExtentReports will be used to create customized test reports.
				SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now = new Date();
        
				String strDate = currentDate.format(now).replace(":", ".");
				
				extent = new ExtentReports ("C:\\workspace\\Mercury Tours\\Reports\\TestReports_"+strDate+".html", true);
				
				String hostname= null;
				try{
				InetAddress IPAddress;
				IPAddress = InetAddress.getLocalHost();
			    hostname = IPAddress.getHostName();
				}
				catch(Exception e){
					 System.out.println("Hostname can not be resolved");
				}
				extent
		                .addSystemInfo("Host Name", hostname)
		                .addSystemInfo("Environment", "Java-Selenium Automation Testing")
		                .addSystemInfo("User Name", System.getProperty("user.name"));
			}

		
	@BeforeMethod
	public void openBrowserTest(){
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);	
		driver.get("http://newtours.demoaut.com/mercurywelcome.php");
				
			}
	
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException{
		driver.close();
	}
	
	@AfterTest
	public void afterTestCheck(){
		extent.flush();
		extent.close();
	}
}
