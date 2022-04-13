package com.crm.genericutility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class conmtains all the java specfic common methods
 * @author Shivaay PC
 *
 */
public class JavaUtility {

	/**
	 *  This method is used to generate Random Numbers
	 * @param limit
	 * @return
	 */
	public static int generateRandomNumber(int limit) {
		Random ran= new Random();
		int randomNumber=ran.nextInt(limit);
		return randomNumber;
	}
	
	
	public static String getCurrentTimeAndDate() {
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		Date date = new Date();
		String requireFormatData = sdf.format(date);
		return requireFormatData;
	}
	public static long convertStringToLong(String value) {

		long timeouts = Long.parseLong(value);
		return timeouts;
}
}