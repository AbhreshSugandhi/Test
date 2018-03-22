package commonLibs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class KeywordUtility {

	CommonDriver oDriver;

	public KeywordUtility() {
		oDriver = new CommonDriver();
	}

	public String performAction(String sActionName, By oBy, String sValue)
			throws Exception {

		try {
			sActionName = sActionName.trim();

			if (sActionName.isEmpty()) {
				return "";
			}

			if (sActionName.equalsIgnoreCase("click")) {
				oDriver.clickElement(oBy);

				return "Element is Clicked";
			}

			if (sActionName.equalsIgnoreCase("openbrowser")) {

				oDriver.openBrowser(sValue, "about:blank");

				return "Browser is Fired";
			}

			if (sActionName.equalsIgnoreCase("setPageLoadTimeOut")) {

				oDriver.setPageLoadTimeOut(Long.valueOf(sValue));

				return "";
			}

			if (sActionName.equalsIgnoreCase("setElementDetectionTimeout")) {

				oDriver.setElementDetectionTimeOut(Long.valueOf(sValue));

				return "";
			}

			if (sActionName.equalsIgnoreCase("navigateToUrl")) {

				oDriver.getDriver().get(sValue);

				return "Successfully Navigated to " + sValue;
			}

			if (sActionName.equalsIgnoreCase("navigateBack")) {

				oDriver.getDriver().navigate().back();

				return "Successfully Navigated Back";
			}

			if (sActionName.equalsIgnoreCase("navigateForward")) {

				oDriver.getDriver().navigate().forward();

				return "Successfully Navigated Forward";
			}

			if (sActionName.equalsIgnoreCase("closeAllBrowser")) {

				oDriver.getDriver().quit();

				return "All Browsers are Closed";
			}

			if (sActionName.equalsIgnoreCase("closeCurrentBrowser")) {

				oDriver.getDriver().close();

				return "Current Browser is Closed";
			}

			if (sActionName.equalsIgnoreCase("closeTab")) {

				oDriver.closeTab();

				return "Child Tab is closed";
			}

			if (sActionName.equalsIgnoreCase("waitTillElementIsVisible")) {

				oDriver.waitTillElementIsVisible(oBy, Long.valueOf(sValue));

				return "";
			}

			if (sActionName.equalsIgnoreCase("savepagesnapshot")) {

				oDriver.savePageSnapshot(sValue);

				return "Screenshot Saved";
			}

			if (sActionName.equalsIgnoreCase("submit")) {

				oDriver.getDriver().findElement(oBy).submit();
				return "Submit is Clicked";
			}
			if (sActionName.equalsIgnoreCase("clear")) {

				oDriver.getDriver().findElement(oBy).clear();
				return "TextBox Values are cleared";
			}

			if (sActionName.equalsIgnoreCase("selectParentWindow")) {

				oDriver.getDriver()
						.switchTo()
						.window(oDriver.getDriver().getWindowHandles()
								.toArray()[0].toString());
				return "Parent Window selected";
			}

			if (sActionName.equalsIgnoreCase("acceptAlert")) {

				oDriver.getDriver().switchTo().alert().accept();
				return "Alert is accepted";
			}

			if (sActionName.equalsIgnoreCase("rejectAlert")) {

				oDriver.getDriver().switchTo().alert().dismiss();
				return "Alert is dismissed";
			}

			if (sActionName.equalsIgnoreCase("selectDefaultframe")) {
				oDriver.getDriver().switchTo().defaultContent();
				return "Switch to default frame successfully";
			}

			if (sActionName.equalsIgnoreCase("switchInFrame")) {
				oDriver.switchInFrame(oBy);
				return "Switch to the frame";
			}

			if (sActionName.equalsIgnoreCase("gettext")) {
				String textValue = oDriver.getDriver().findElement(oBy)
						.getText();
				if (textValue.equals(sValue)) {
					return "Text Verified";
				}

			}

			if (sActionName.equalsIgnoreCase("getTitle")) {
				return oDriver.getDriver().getTitle();

			}

			if (sActionName.equalsIgnoreCase("getUrl")) {
				return oDriver.getDriver().getCurrentUrl();

			}

			if (sActionName.equalsIgnoreCase("setText")) {
				oDriver.setText(oBy, sValue);
				return "TextBox value Entered";
			}

			if (sActionName.equalsIgnoreCase("getstatus")) {
				return String.valueOf(oDriver.getDriver().findElement(oBy)
						.isSelected());

			}

			if (sActionName.equalsIgnoreCase("checkUncheckBox")) {

				if (!oDriver.getDriver().findElement(oBy).isSelected()) {
					return "Uncheck Successfully";
				} else
					oDriver.clickElement(oBy);
				return "Uncheck Successfully";

			}

			if (sActionName.equalsIgnoreCase("getSelectedItem")) {
				Select olist;
				WebElement oElement;

				oElement = oDriver.getDriver().findElement(oBy);
				olist = new Select(oElement);
				return olist.getFirstSelectedOption().getText();

			}

			if (sActionName.equalsIgnoreCase("selectitem")) {
				Select olist;
				WebElement oElement;

				oElement = oDriver.getDriver().findElement(oBy);
				olist = new Select(oElement);
				olist.selectByVisibleText(sValue);
				return "Item(s) selected";

			}

			if (sActionName.equalsIgnoreCase("getItemsCount")) {
				Select olist;
				WebElement oElement;

				oElement = oDriver.getDriver().findElement(oBy);
				olist = new Select(oElement);

				return String.valueOf(olist.getOptions().size());

			}

			if (sActionName.equalsIgnoreCase("isVisible")) {
				oDriver.getDriver().findElement(oBy).isEnabled();
				return "Element is Visible as Expected";
			}

			if (sActionName.equalsIgnoreCase("isElementDisplayed")) {

				oDriver.getDriver().findElement(oBy).isDisplayed();
				return "Element is Displayed as Expected";

			}

			if (sActionName.equalsIgnoreCase("verifyText")) {

				if (oDriver.getAttributeTextField(oBy).equals(sValue)) {
					return "Text is verified";
				}
			}

			if (sActionName.equalsIgnoreCase("verifyTags")) {

				oDriver.verifyTagsContent(oBy, sValue);
				return "Tags are verified";

			}

			if (sActionName.equalsIgnoreCase("sleep")) {

				Thread.sleep(Long.valueOf(sValue));
				return "wait..for " + sValue;

			}

			if (sActionName.equalsIgnoreCase("scrollUp")) {

				oDriver.scrollUp();
				return "Page is Scrolled Up";

			}

			if (sActionName.equalsIgnoreCase("scrollDown")) {

				oDriver.scrollDown();
				return "Page is Scrolled Down";

			}

			if (sActionName.equalsIgnoreCase("hover")) {

				oDriver.hoverToElement(oBy);
				return "Element hovered successfully";

			}

		} catch (Exception e) {
			oDriver.savePageSnapshot("Exception_" + sValue);
			throw (e);

		}
		oDriver.savePageSnapshot("Fail_" + sValue);
		return "Error: Unknown keyword..";

	}

}
