package com.crm.testngpractice;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PracticeTestNG1Test {
	
	
	@Test
	public void method1() {
		
	Reporter.log( "print Method 1 ", true);
				
	}
    @BeforeMethod
	public void method2() {
		Reporter.log("Print method 2 ", true);
	}
    @AfterMethod
    public void  method3() {
    	Reporter.log("Print method 3" ,true);
    }
}
