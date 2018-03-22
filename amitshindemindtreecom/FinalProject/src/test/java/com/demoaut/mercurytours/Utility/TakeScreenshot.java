package com.demoaut.mercurytours.Utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenshot {
	
	public static void getScreenshot(WebDriver driver, String screenshotName) throws IOException{
		try	
		{
			File ss = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
			SimpleDateFormat sdfDate1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
			Date now = new Date();
        
			String strDateFolder = sdfDate1.format(now).replace(":", ".");
			String strDate = sdfDate.format(now).replace(":", "-");
        
			//FileUtils.copyFile(ss, new File("C:\\workspace\\Mercury Tours\\Screenshots\\" + screenshotName+ "_" + strDate +".png"));
			FileUtils.copyFile(ss, new File("C:\\workspace\\Mercury Tours\\Screenshots\\"+ strDateFolder+"\\"+ screenshotName+"_"+strDate+".png"));
		
		}
		
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
