package commonLibs;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import drivers.RunManager;

public class CommonDriver {

	private WebDriver oDriver;
	private long lngPageLoadTimeOut;
	private long lngElementDetectionTimeOut;

	public CommonDriver() {
		lngPageLoadTimeOut = 60L;
		lngElementDetectionTimeOut = 30L;
	}

	public void setPageLoadTimeOut(long lngPageLoadTimeOut) {
		this.lngPageLoadTimeOut = lngPageLoadTimeOut;
	}

	public void setElementDetectionTimeOut(long lngElementDetectionTimeOut) {
		this.lngElementDetectionTimeOut = lngElementDetectionTimeOut;
	}

	public void openBrowser(String sBrowserType, String sUrl) throws Exception {
		try {

			switch (getBrowserTypeIndexed(sBrowserType)) {
			case 1:
				oDriver = new FirefoxDriver();
				break;
			case 2:

				System.setProperty("webdriver.ie.driver",
						"./IEDriverServer.exe");
				oDriver = new InternetExplorerDriver();
				break;

			case 3:
				System.setProperty("webdriver.chrome.driver",
						"./chromedriver.exe");
				oDriver = new ChromeDriver();
				break;
			default:
				throw new Exception("Unknow Browser Type = " + sBrowserType);

			}

			if (sUrl.isEmpty()) {

				sUrl = "about:blank";
			}

			oDriver.manage().window().maximize();
			oDriver.manage().deleteAllCookies();

			oDriver.manage().timeouts()
					.pageLoadTimeout(lngPageLoadTimeOut, TimeUnit.SECONDS);

			oDriver.manage()
					.timeouts()
					.implicitlyWait(lngElementDetectionTimeOut,
							TimeUnit.SECONDS);

			oDriver.get(sUrl);

			Thread.sleep(2000);

		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}
	}

	// -----------------------------------------------------------------

	public void closeBrowser() {
		try {
			if (oDriver != null) {
				Thread.sleep(3000);
				oDriver.quit();
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	// ------------------------------------------------------------------

	public WebDriver getDriver() {

		return oDriver;
	}

	// ------------------------------------------------------------------
	private int getBrowserTypeIndexed(String sBrowserType) {
		sBrowserType = sBrowserType.toLowerCase().trim();

		if (sBrowserType.isEmpty()) {
			return -1;
		}

		if (sBrowserType.equals("ff") || sBrowserType.equals("firefox")
				|| sBrowserType.equals("mozilla")
				|| sBrowserType.equals("mozilla firefox")) {
			return 1;
		}

		if (sBrowserType.equals("ie") || sBrowserType.equals("explorer")
				|| sBrowserType.equals("internet explorer")) {
			return 2;
		}

		if (sBrowserType.equals("chrome") || sBrowserType.equals("google")
				|| sBrowserType.equals("google chrome")) {
			return 3;
		}

		return -1;
	}

	// ---------------------------------------------------------------------

	public void waitTillElementIsVisible(By oBy, long timeoutSeconds)
			throws Exception {
		try {

			WebDriverWait oWait = new WebDriverWait(oDriver, timeoutSeconds);

			oWait.until(ExpectedConditions.visibilityOfElementLocated(oBy));

		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}
	}

	// -------------------------------------------------------------------

	public void savePageSnapshot(String sImagePath) throws Exception {
		try {
			TakesScreenshot oCamera;
			File oTmpFile, oImageFile;
			String sDateTimeStamp = Utils.getDateTimeStamp();
			sImagePath = RunManager.sFolder + "\\" + sImagePath + "_"
					+ sDateTimeStamp + ".png";

			oImageFile = new File(sImagePath);

			if (new File(sImagePath).exists()) {
				throw new Exception("Image File already Exists");
			}

			oCamera = (TakesScreenshot) oDriver;
			oTmpFile = oCamera.getScreenshotAs(OutputType.FILE);
			oCamera.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(oTmpFile, oImageFile);

		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}
	}

	// ---------------------------------------------------------------------

	public void setText(By oBy, String sText) throws Exception {
		try {

			oDriver.findElement(oBy).sendKeys(sText);

		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}
	}

	public String getAttributeTextField(By oBy) throws Exception {
		try {

			return oDriver.findElement(oBy).getAttribute("value");
		} catch (Exception t) {
			System.out.println(t.getMessage());
			throw t;
		}
	}

	// ----------------------------------------------------------------------

	public String getText(By oBy) throws Exception {
		try {

			return oDriver.findElement(oBy).getText();

		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}
	}

	// ---------------------------------------
	public void clickElement(By oBy) throws Exception {
		try {
			oDriver.findElement(oBy).click();

		} catch (Exception t) {
			t.printStackTrace();
			throw t;
		}
	}

	public void selectItemInListBox(By oBy, String sItemVisibleText)
			throws Exception {
		try {

			Select oListBox;

			oListBox = new Select(oDriver.findElement(oBy));

			oListBox.selectByVisibleText(sItemVisibleText);

		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}
	}

	// -----------------------------------------------------------------

	public void switchInFrame(By oBy) throws Exception {
		try {
			oDriver.switchTo().frame(oDriver.findElement(oBy));
		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}
	}

	// -----------------------------------------------------------------
	public void closeTab() throws Exception {
		try {
			String parentWindow = oDriver.getWindowHandle();
			for (String childWindows : oDriver.getWindowHandles()) {
				oDriver.switchTo().window(childWindows);
			}
			oDriver.close();
			oDriver.switchTo().window(parentWindow);

		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}
	}

	// -----------------------------------------------------------------
	public void scrollUp() throws Exception {

		try {
			JavascriptExecutor jse = (JavascriptExecutor) oDriver;
			jse.executeScript("scroll(0,-250);");
		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}

	}

	// -----------------------------------------------------------------

	public void scrollDown() throws Exception {

		try {
			JavascriptExecutor jse = (JavascriptExecutor) oDriver;
			jse.executeScript("scroll(0,500);");
		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}

	}

	// -----------------------------------------------------------------

	public String verifyTagsContent(By oBy, String sValue) throws Exception {

		try {
			List<String> allTags = new ArrayList<String>(), tagName = Arrays
					.asList(sValue.split(","));

			List<WebElement> elem = oDriver.findElements(oBy);
			for (int j = 0; j < elem.size(); j++) {
				allTags.add(elem.get(j).getAttribute("text"));

			}
			Thread.sleep(3000);
			if (allTags.equals(tagName)) {
				return "Tags verified";
			} else
				return "Tags not verified";

		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}

	}

	// -----------------------------------------------------------------

	public void hoverToElement(By oBy) throws Exception {

		try {
			Actions builder = new Actions(oDriver);
			WebElement logoutBtn = oDriver.findElement(oBy);
			builder.moveToElement(logoutBtn).build().perform();
			Thread.sleep(5000);
		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}

	}

	// -----------------------------------------------------------------
}
