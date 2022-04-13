package com.crm.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PracticePropertyReadGenericMethod {

	public static void main(String[] args) throws Throwable {
		initializePropertyFile("./src/test/resources/commonDataRmgyantra.properties","userName");

	}

	public static String initializePropertyFile(String path,String key) throws IOException
	{
		FileInputStream fis = new FileInputStream(path);
		Properties properties = new Properties();
		properties.load(fis);
		String value =properties.getProperty(key);
		System.out.println(value);
		return value;

	}

}
