package helper;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GenerateScreenshot {

  static WebDriver driver;
  
  public GenerateScreenshot() {
	  System.out.println("Empty constructor.");
}

  /**
 * This method is for taking the screenshot.
 * @param filePath file location
 */
  public static void captureScreenshot(String filePath) throws Exception {
    Date d = new Date();
    Timestamp t = new Timestamp(d.getTime());
    String timeStamp = t.toString();
    timeStamp = timeStamp.replace(" ", "_");
    timeStamp = timeStamp.replace(":", "_");

    File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    try {
      FileUtils.copyFile(file, new File("./screenshots\\" + filePath + "_" + timeStamp + ".png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
