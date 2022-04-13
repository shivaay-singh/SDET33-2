package com.crm.practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExcelTest {

	public static void main(String[] args) throws Throwable {
  
		//Step1: we should convert the physical file into java readable object
		FileInputStream fis = new FileInputStream("./src/test/resources/testDataRead.xlsx");
		//Step:2 open the excel file using "WorkbookFactory" class and create(..);
	    Workbook wb=WorkbookFactory.create(fis);
	    
	    //Step: 3 we should get the control of particular Sheet by using "getSheet(--)"
	    Sheet sh = wb.getSheet("SDET33");
	    
	    //Step4: we should get the control of particular row by using "getRow(--)";
	    Row row = sh.getRow(1);
	    //Step 5: we should create particular cell by using "createCell(--)";
	    Cell cell=row.createCell(1);
	    
	    //Step6: stor/write the data by using setStringCellValue();
        cell.setCellValue("pass");
        
        //Step7: we should specify the path of excel by using FileOutputStream
        FileOutputStream fos = new FileOutputStream("./src/test/resources/testDataWrite.xlsx");
        
        //Step8: We shoul save/flush the data by using write(fos):--> belongs to WorkBook
        wb.write(fos);// write() will write in excel as well save it
        
        //Step9: close the Workbook by using "close()"--> belongs to WorkBook interface
        wb.close();
        System.out.println("Data is stored in Excel");
        
	}

}
