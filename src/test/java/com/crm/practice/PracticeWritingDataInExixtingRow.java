package com.crm.practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class PracticeWritingDataInExixtingRow {

	public static void main(String[] args) throws Throwable {
		String path="./src/test/resources/testDataRead.xlsx";
		writingDataInExcel(path,"SDET33",1,1,"pass");
		

	}
	/**
	 * Interview question
	 * THis is generic method is used to write data in excel file 
	 */
	public static void writingDataInExcel(String path,String sheetName,int rowNumber,int cellNumber,String data) throws Throwable {
		FileInputStream fisExcel = new FileInputStream(path);
        Workbook wb=WorkbookFactory.create(fisExcel);
        wb.getSheet(sheetName).getRow(rowNumber).createCell(cellNumber).setCellValue(data);	
        FileOutputStream fos = new FileOutputStream(path);
        wb.write(fos);
		wb.close();
	}

}
