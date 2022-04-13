package com.crm.testngpractice;

import org.testng.Reporter;
import org.testng.annotations.Test;
/**
 * This program is to understand that @Test will run in alphabetical order followed for method name
 * @author Shivaay PC
 *
 */
public class PracticeTestNGParameterAlphabetical {
	
	
	@Test
	public void Atest() {
		Reporter.log("Atest", true);
	}
	
	@Test
	public void Dtest() {
		Reporter.log("Dtest", true);
	}
	@Test
	public void Ctest() {
		Reporter.log("Ctest", true);
	}
	@Test
	public void Btest() {
		Reporter.log("Btest", true);
	}

}
