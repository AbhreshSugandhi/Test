package com.project.webtours.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.project.webtours.test.utility.MailUtility;
import com.project.webtours.test.utility.PdfUtility;


public class Base {
	
	public static WebDriver driver=null;
	public static Properties config = null;
	public static Properties loc =null;
	public static JavascriptExecutor js = null;
	public static List<String> resultList=new ArrayList<String>();
	 PdfUtility pdfUtility=new PdfUtility();
	 MailUtility  mail = new MailUtility();
	
	public Base()
	{
		config= new Properties();
		loc= new Properties();
		
		
		try
		{
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\project\\config\\properties\\object.properties");
			config.load(fs);
            FileInputStream fs1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\project\\config\\properties\\location.properties");
			loc.load(fs1);
			
		}
		catch (Exception e)
		{
			return;
		}
		
	}
	@BeforeClass
	public void startclass()
	{
		System.out.println("Start of class");
		
	}
	@BeforeSuite
	public void browserSetup()
	{
		System.out.println("before  suite");
		if (driver==null){
			
			String browser=config.getProperty("browser");
			
			
			if (browser.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver\\chromedriver.exe");
				this.driver=new ChromeDriver();	
			}
			else if (browser.equals("firefox"))
			{
			
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\drivers\\firefoxdriver\\geckodriver.exe");
			this.driver = new FirefoxDriver();
			}
			//applicable for the entire DOM of the page,if element not found for 30 sec will give timeout exception
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			js=(JavascriptExecutor) driver;}
	}
	
	   
	public boolean isElementPresent(String xpathkey)
	{
		try{
			
			driver.findElement(By.xpath(loc.getProperty(xpathkey)));
			System.out.println("FOUND OK");
	        
	        return true;
		   }
		catch(Exception e)
		 {
			
			System.out.println("DID NOT FIND ELEMENT");
			return false;
		 }
		
	}
	public  void addToResult(int no,String Test,String msg,String result)
	{
	        resultList.add(no+" : "+Test+", "+msg+", "+result);
	        
	}
	
	
   public void scroll()
   {  try{
	   js.executeScript("window.scrollBy(0,1000)");  
   }
   catch (Exception e)
   {
	   return;
   }
   
   }
   
   @AfterClass
   public void endclass()
   {
	   System.out.println("End of Class");
   }
   @AfterSuite
   public void report() throws COSVisitorException, IOException 
   {
	     driver.close();
	 //define a time stamp string to add to the test result 
		 String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(Calendar.getInstance().getTime());
		 //add time stamp to the resultList
		 resultList.add("Tests Ends: " + timeStamp);
		 //write the test result pdf file with file name TestResult
		 pdfUtility.writeTestResultToPdfFile( System.getProperty("user.dir")+"\\src\\TestResult.pdf", resultList);
		 mail.sentemail();
	     driver.quit();
	     
   }
  
 
  
}
