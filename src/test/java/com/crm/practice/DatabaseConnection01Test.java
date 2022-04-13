package com.crm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;
/**
 * script to fetch the data from mysql database using jdbc
 */
public class DatabaseConnection01Test {

	public static void main(String[] args) throws SQLException {
		
		/**
		 *  step1: we should create the object for the driver and register the driver
		 */
		
 Driver driver=new Driver();
 /**
  * register
  */
 DriverManager.registerDriver(driver);
 
 /**
  * Step2: get connection                                                                          //un     pwd
  */
 Connection connection =
		 DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet33", "root", "root");
 
 /**
  * Step3: create statement
  */
 Statement statement = connection.createStatement();

 /**
  * Step4: execute query
  */
 /**
  * for fetching the data(DQL) execute the query
  */
 ResultSet result = statement.executeQuery("select * from sdet33;");
 
 while(result.next())
 {
	 System.out.println(result.getString(2)); 
 }
 /**
  * Step5: close connection
  */
 connection.close();
 
	}

}
