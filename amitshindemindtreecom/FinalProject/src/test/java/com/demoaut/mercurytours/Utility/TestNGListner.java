package com.demoaut.mercurytours.Utility;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.demoaut.mercurytours.TestCases.BaseClass;

public class TestNGListner implements ITestListener{

	        
	public void onTestStart(ITestResult result) {
		System.out.println("Testcase Started ....." +result.getName());
		
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Testcase PASS....." +result.getName());
		
	}

	public void onTestFailure(ITestResult result) {
		WebDriver driver=BaseClass.driver;
		System.out.println("Testcase failed.....");
		try {
			TakeScreenshot.getScreenshot(driver, result.getName());
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	public void onStart(ITestContext context) {
		
		
	}

	public void onFinish(ITestContext context) {
		
		
	}
	

}
