package com.crm.practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class PracticeExcelReading {

	public static void main(String[] args) throws Throwable {
		String path="./src/test/resources/testDataRead.xlsx";
		fethingDataFromExcel(path,"SDET33",1,0);
	}
	/**
	 * Interview question 
	 * This is generic method used to read the data from excel
	 * @param path
	 * @param sheetName
	 * @param rowNumber
	 * @param columnNumber
	 * @return
	 * @throws Throwable
	 */
	public static String fethingDataFromExcel(String path , String sheetName,int rowNumber,int columnNumber) throws Throwable {
		
		FileInputStream fisExcel= new FileInputStream(path);
		Workbook wb=WorkbookFactory.create(fisExcel);
		String data = wb.getSheet(sheetName).getRow(rowNumber).getCell(columnNumber).getStringCellValue();
        wb.close(); 
		return data;
		
		 
	}

	

}
