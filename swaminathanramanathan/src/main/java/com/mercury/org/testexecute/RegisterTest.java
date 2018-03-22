package com.mercury.org.testexecute;

import org.testng.annotations.Test;

public class RegisterTest extends BaseClass {

	public RegisterTest() {
		
	}
	
	/**
	 * Verifies User Credentials.
	 */
	@Test
	public void verifyValidCredentials() {
		register.verifyInvalidCredentials(userName);
	}
	
}
