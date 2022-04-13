package com.crm.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchDataFromExcelTest {

	public static void main(String[] args) throws Exception {
		//Step1: we should convert the physical file into java readable object 
    FileInputStream fis= new FileInputStream("./src/test/resources/testDataRead.xlsx");
    
    //Step:2 :  open the excel file using "WorkbookFactory" class and create(--);
    Workbook wb = WorkbookFactory.create(fis);
    
    //Step3: we should get the control of particular sheet by using  "getSheet(---)";
    Sheet sh = wb.getSheet("SDET33");
    
    //Step4: we should get the control of particular row bys using "getRow(--)";
    Row row = sh.getRow(1);
    
    //Step:5 we should get the control of particular cell by using "getCell(--)";
    Cell cell = row.getCell(0);
    
    //Step6: read/fetch the data by using "getStringCellValue", "toString()"
    String data = cell.getStringCellValue();
    System.out.println(data);
    
    //Step 7: close the workbook by using "close()"---> belongs to WorkBook interface
    wb.close();
    
	}

}
