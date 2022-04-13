package com.crm.testngpractice;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PracticeTestNG3Test {
	
	@BeforeTest
	public void Atest1() {
		Reporter.log("from test A1", true);
	}
	@AfterTest
	public void Btest2() {
		Reporter.log("from test B2 ", true);
	}
	@Test
	public void Ctest() {
		Reporter.log("from test C " , true);
	}
	
	

}
