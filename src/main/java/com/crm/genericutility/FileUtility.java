package com.crm.genericutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.mysql.cj.jdbc.Driver;

public class FileUtility {
	
	static Properties properties;

	/**
	 * This method is used to get data from Property file
	 * @param key
	 * @return
	 */
	public static String fetchDataFromProperty(String key)
	{
		String value =properties.getProperty(key);
		return value;
		
	}
	
	/**
	 * This method is used to initialize the property file
	 * @param path
	 * @throws IOException
	 */
	public static void initializePropertyFile(String path) throws IOException
	{
		FileInputStream fis = new FileInputStream(path);
		properties=new Properties();
		properties.load(fis);
	}
	
}
