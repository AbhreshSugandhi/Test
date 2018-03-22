package com.mercury.org.testexecute;

import java.util.Hashtable;
import org.testng.annotations.Test;

import com.mercury.org.framework.dataprovider.ExcelDataProvider;

public class BookingTest extends BaseClass {
	
	public BookingTest() {
		
	}
	/**
	 * Books Flight Ticket Based on the data present in Excel.
	 * @param data
	 */
	@Test(dataProvider = ExcelDataProvider.DATA_PROVIDER, dataProviderClass = ExcelDataProvider.class)
	public void bookingTest(Hashtable<String, String> data) {
		register.logIn(userName, password);
		flightBooking.bookFlightTicket(data);
		flightBooking.logOut();
	}

}
