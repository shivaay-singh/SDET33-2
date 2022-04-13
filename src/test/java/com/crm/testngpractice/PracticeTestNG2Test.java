package com.crm.testngpractice;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PracticeTestNG2Test {
	
	@BeforeTest
	public void test1() {
		Reporter.log("from test 1", true);
	}
	@AfterTest
	public void test2() {
		Reporter.log("from test 2 ", true);
	}
	@Test
	public void test() {
		Reporter.log("from test " , true);
	}

}
