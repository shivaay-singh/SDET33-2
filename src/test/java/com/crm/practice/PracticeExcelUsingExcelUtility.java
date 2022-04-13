package com.crm.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.genericutility.ExcelUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticeExcelUsingExcelUtility {

	public static void main(String[] args) throws Throwable {
    
		// Excel1
		ExcelUtility.openExcel("./src/test/resources/testDataExcel.xlsx");
		ExcelUtility.writeDataInNewRow("./src/test/resources/testDataExcel.xlsx","SDET33",19,1,"status");
		String orgName = ExcelUtility.fetchData("SDET33",8,2);
		System.out.println(orgName);
		ExcelUtility.writeDataInNewRow("./src/test/resources/testDataExcel.xlsx","SDET33",8,2, "pass");
		String lastName = ExcelUtility.fetchData("SDET33",8, 2);
		System.out.println(lastName);
		
		//Excel2
		ExcelUtility.openExcel("./src/test/resources/testDataExcel.xlsx");
		ExcelUtility.writeDataInNewRow("./src/test/resources/testDataExcel.xlsx","SDET33",19,1, "status");
		String orgName1 = ExcelUtility.fetchData("SDET33",3, 2);

		System.out.println(orgName1);
		ExcelUtility.writeDataInNewRow("./src/test/resources/testDataExcel.xlsx","SDET33",20,3,"pass");
		String lastName1 = ExcelUtility.fetchData("SDET33",3,2);
		System.out.println(lastName1);
		ExcelUtility.closeExcel();
		
	}

}
