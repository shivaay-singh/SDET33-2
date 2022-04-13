package com.crm.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReadTest {

	public static void main(String[] args) throws IOException {
        //convert the physical file into java readable object
		FileInputStream fis= new FileInputStream("./src/test/resources/commonData.properties");
		
		//Step 2: crate the object of properties
		Properties properties=new Properties();
	    
		// Step 3: load all the keys;
		properties.load(fis);
		
		//Step 4: fetch the data
		String url = properties.getProperty("url");
		System.out.println(url);
	}

}
