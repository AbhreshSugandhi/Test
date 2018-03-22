package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserHelper {

  static WebDriver driver;
  
  public BrowserHelper() {
	  System.out.println("Empty constructor.");
}

  /**
 * This method opens the browser.
 * @param browser type
 * @param url webpage
 * @return
 */
  public static WebDriver startBrowser(String browser, String url) {

    if (browser.equalsIgnoreCase("chrome")) {
      System.setProperty("webdriver.chrome.driver", "./src/resources/chromedriver.exe");
      driver = new ChromeDriver();

    } else if (browser.equalsIgnoreCase("firefox")) {
      System.setProperty("webdriver.gecko.driver", "./src/resources/geckodriver.exe");
      driver = new FirefoxDriver();

    } else {
      System.setProperty("webdriver.ie.driver", "./src/resources/IEDriverServer.exe");
      driver = new InternetExplorerDriver();
    }
    driver.manage().window().maximize();
    driver.get(url);
    return driver;
  }

}
