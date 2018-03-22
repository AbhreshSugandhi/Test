package drivers;

import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commonLibs.ExcelDriver;
import commonLibs.KeywordUtility;
import commonLibs.Utils;

public class RunManager {

	private static KeywordUtility oKwDriver;
	private static ExcelDriver oExcelDriver;
	private static String sDriverPropertyFile = "src//conf//AutomationInput.properties";
	private static Properties oDriverProperties;
	private static String sInputFileFolder;
	@SuppressWarnings("unused")
	private static String sResultFolder;
	private static String sMainDriverInputFile;
	private static String sCurrentTestCaseStatus;
	private static SoftAssert softAssert = new SoftAssert();
	private static String sDateTimeStamp;
	private static String sOutputFileName;
	public static String sFolder;

	@BeforeSuite
	public void initialize() {
		System.out.println("Initializing....");
		try {
			Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
			oDriverProperties = Utils.getProperties(sDriverPropertyFile);
			sInputFileFolder = oDriverProperties.getProperty("InputFileFolder").trim();
			sMainDriverInputFile = oDriverProperties.getProperty(
					"DriverInputFile").trim();
			sResultFolder = oDriverProperties.getProperty("ResultFolder")
					.trim();
			sFolder = Utils.saveResult();
		} catch (Exception e) {
			System.out.println("Error Initializing the Property Files" + e);
		}

	}

	@Test(priority = 1)
	public void exportToExcel() {
		System.out.println("Generating Report....");
		sDateTimeStamp = Utils.getDateTimeStamp();
		sOutputFileName = sFolder + "\\Result as on " + sDateTimeStamp
				+ ".xlsx";
		Assert.assertTrue(oExcelDriver.saveAs(sOutputFileName),
				"Report Generated Successfully");

	}

	@Test(priority = 0)
	private static void TestCase() {
		System.out.println("Executing Test Case....");
		String sTestCaseSheetName, sRunFlag, sRunStatus, sComment;
		String sDriverExcelFile;
		int iRow, iRowCount;

		sDriverExcelFile = sInputFileFolder + "\\" + sMainDriverInputFile;
		oExcelDriver = new ExcelDriver();
		oExcelDriver.openExcelWorkbook(sDriverExcelFile);

		iRowCount = oExcelDriver.getRowCountOfSheet("TestSuite");

		for (iRow = 2; iRow <= iRowCount + 1; iRow++) {
			sTestCaseSheetName = "";
			sRunFlag = "";
			sRunStatus = "";
			sComment = "";
			sCurrentTestCaseStatus = "Pass";
			boolean flag = false;

			sTestCaseSheetName = oExcelDriver
					.getCellCData("TestSuite", iRow, 2);
			sRunFlag = oExcelDriver.getCellCData("TestSuite", iRow, 3);

			sTestCaseSheetName = sTestCaseSheetName.trim();

			sRunFlag = sRunFlag.toLowerCase().trim();

			if (sRunFlag.equals("yes")) {
				oKwDriver = null;
				sRunStatus = TestCaseDriver(sTestCaseSheetName);

				if (sRunStatus == "") {
					if (sCurrentTestCaseStatus == "Pass") {
						flag = true;
						sRunStatus = "Pass";
						sComment = "Test Case Passed";
					} else {
						sRunStatus = "Fail";
						sComment = "One or more steps got Failed";
						softAssert.assertTrue(flag, "Test Case Failed");

					}

				} else {
					sComment = sRunStatus;
					sRunStatus = "Fail";

				}

			} else {
				sRunStatus = "Skipped";
				sComment = "Because, Run Flag was set to " + sRunFlag;
			}

			oExcelDriver.setCellCData("testSuite", iRow, 4, sRunStatus);
			oExcelDriver.setCellCData("testSuite", iRow, 5, sComment);

		}
		softAssert.assertAll();
	}

	private static String TestCaseDriver(String sSheetName) {
		int iRow, iRowCount;

		String sTestCaseDriverreturnvalue = "";

		String sActionKeyword;
		String sObjectLocator;
		String sArgumentValue;
		String sRunStatus;
		String sComment;
		String sReturnValue;
		By oBy;

		try {

			oKwDriver = new KeywordUtility();
			iRowCount = oExcelDriver.getRowCountOfSheet(sSheetName);
			System.out.println("The row count is: " + iRowCount);

			for (iRow = 2; iRow <= iRowCount + 1; iRow++) {
				sActionKeyword = "";
				sObjectLocator = "";
				sArgumentValue = "";
				sRunStatus = "";
				sComment = "";
				sReturnValue = "";
				oBy = null;

				sActionKeyword = oExcelDriver.getCellCData(sSheetName, iRow, 2)
						.trim();
				sObjectLocator = oExcelDriver.getCellCData(sSheetName, iRow, 3)
						.trim();
				sArgumentValue = oExcelDriver.getCellCData(sSheetName, iRow, 4)
						.trim();

				if (sObjectLocator != "" && !sObjectLocator.equals("")) {
					oBy = Utils.getLocatorBy(sObjectLocator);
				}

				if (sActionKeyword == "") {
					sRunStatus = "Skipped";
					sComment = "No Action Keyword";
				} else {
					try {

						sReturnValue = oKwDriver.performAction(sActionKeyword,
								oBy, sArgumentValue);

						if (sReturnValue.toLowerCase().contains("error")) {
							sRunStatus = "Fail";
							sComment = sReturnValue;
							sReturnValue = "Failure";
						} else {
							sRunStatus = sReturnValue;
							sReturnValue = "Pass";
						}

					} catch (Exception e) {
						sRunStatus = "Exception";
						sReturnValue = "Fail";
						sComment = e.getMessage();
						sCurrentTestCaseStatus = "Fail";
					}
				}

				oExcelDriver.setCellCData(sSheetName, iRow, 5, sRunStatus);
				oExcelDriver.setCellCData(sSheetName, iRow, 6, sReturnValue);
				oExcelDriver.setCellCData(sSheetName, iRow, 7, sComment);

			}

		} catch (Exception e) {
			sTestCaseDriverreturnvalue = e.getMessage();
			sCurrentTestCaseStatus = "Fail";
		}

		return sTestCaseDriverreturnvalue;
	}

}
