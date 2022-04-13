package com.crm.testngpractice;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.crm.genericutility.ConstantPath;
import com.crm.genericutility.ExcelUtility;
import com.crm.genericutility.FileUtility;

public class PracticeDataProviderTestNgReadingMultipleDataExcelSimultaneouslyTest {
	
	@Test(dataProvider="dataProvider_Excel")
	public void dataProviderTest(String userName, String password) {
		Reporter.log(userName+"===="+password, true);
	}
	
	
	@DataProvider
	public Object[][] dataProvider_Excel() throws Throwable{
		ExcelUtility.openExcel(ConstantPath.LoginDataExcelPath);
		FileUtility.initializePropertyFile(ConstantPath.propertyVtigerFilePath);
		String LoginDataexcelSheetName1 = FileUtility.fetchDataFromProperty("LoginDataexcelSheetName1");
		Object[][] arr = ExcelUtility.fetchMultipleData(LoginDataexcelSheetName1);
		
		return arr;
		
		
	}

}
