package com.crm.testngpractice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class PracticeTestNGParameterPriorityTest {
	
	
	@Test
	public void test1() {
		Reporter.log("test1", true);
	}
	
	

	
	@Test
	public void test2() {
		
		
		Reporter.log("test2", true);
	}
	
	
	@Test(priority=1)
	public void test3() {
		Reporter.log("test3", true);
	}
	
	
	@Test
	public void test4() {
		Reporter.log("test4", true);
	}
	


}
