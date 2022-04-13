package com.crm.genericutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * This class contains Excel specific methods
 * @author Shivaay PC
 *
 */
public class ExcelUtility {
	static Workbook wb;
	/**
	 * This method is used to fetch the single data from Excel
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @return
	 * @throws Throwable
	 */
	public static String fetchData (String sheetName ,int rowNumber ,int cellNumber) throws Throwable {
	
	Sheet sh = wb.getSheet(sheetName);
	String data = sh.getRow(rowNumber).getCell(cellNumber).getStringCellValue();
	return data;

    }
	/**
	 * This method is used to create/write the data to new row into specified excel sheet
	 * @param path
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @param data
	 * @throws Throwable
	 */
	public static void writeDataInNewRow (String path,String sheetName ,int rowNumber ,int cellNumber , String data) throws Throwable {
		Sheet sh = wb.getSheet(sheetName);
		sh.createRow(rowNumber).createCell(cellNumber).setCellValue(data);
		FileOutputStream fosExcell= new FileOutputStream(path);
		wb.write(fosExcell);
		System.out.println("Data is written successfully");
	    }
	
	/**
	 * This method is to create/write the data into existing row into specified excel sheet
	 * @param path
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @throws Throwable
	 */
	public static void writeDataInExistingRow (String path,String sheetName ,int rowNumber ,int cellNumber , String data) throws Throwable {
		Sheet sh = wb.getSheet(sheetName);
		sh.getRow(rowNumber).createCell(cellNumber).setCellValue(data);
		FileOutputStream fosExcell= new FileOutputStream(path);
		wb.write(fosExcell);
		System.out.println("Data is written successfully");
	    }
	/**
	 * This method is used to open the Excel file
	 * @param path
	 * @throws Throwable
	 */
	public static void openExcel(String path) throws Throwable
	{
		FileInputStream fisExcel= new FileInputStream(path);
		wb=WorkbookFactory.create(fisExcel);
		System.out.println("Excel opened successfully ");
		
	}
	
	/**
	 * This method is to close the Excel file
	 * @throws Throwable
	 */
	public static void closeExcel() throws Throwable
	{
		wb.close();
		System.out.println("Excel closed successfully ");
		
	}
	/**
	 * This method is used to fetch multiple data from excel like two data at a time 
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	public static Object[][]  fetchMultipleData(String sheetName) throws Throwable {
		 Sheet sh = wb.getSheet(sheetName);
		 Object [][] arr = new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
		 for (int i = 0; i <sh.getLastRowNum(); i++) {
			 for (int j = 0; j < sh.getRow(0).getLastCellNum(); j++) {
				arr[i][j]=sh.getRow(i+1).getCell(j).toString();
			}
			
		}
		 return arr;
		
	}
	
	
}
