package com.crm.testngpractice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class PracticeTestNGParameterEnableTest {
	@Test
	public void test1() {
		Reporter.log("test1", true);
	}
	
	

	
	@Test(enabled=false)
	public void test2() {
		
		
		Reporter.log("test2", true);
	}
	
	
	@Test
	public void test3() {
		Reporter.log("test3", true);
	}
	
	
	@Test
	public void test4() {
		Reporter.log("test4", true);
	}
	

}
