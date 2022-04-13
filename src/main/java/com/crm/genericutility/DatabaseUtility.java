package com.crm.genericutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;
/**
 * This class contains all the Generic methods of Database
 * @author Shivaay PC
 *
 */
public class DatabaseUtility {

	static Connection connection;
	static ArrayList<String> list= new ArrayList<String>();
	/**
	 * This method is used to established the connection of MYSQL Database
	 * @param dbUrl
	 * @param dbUserName
	 * @param dbPassword
	 * @throws SQLException
	 */
	public static void getMysqlDatabaseConnection(String dbUrl , String dbUserName , String dbPassword)  throws SQLException{
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		connection=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

	}
	/**
	 * This method is used to get the data from database based on the column number
	 * @param query
	 * @param columnNumber
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String> getDataFromDatabase(String query, int columnNumber) throws SQLException{
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		while (result.next()) {
			list.add(result.getString(columnNumber));
		}
		statement.close();
		return list;

	}
	/**
	 * This method is used to fetch the data int the database based on the column name
	 * @param query
	 * @param columnName
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String> getDataFromDatabase(String query, String columnName) throws SQLException{
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		while (result.next()) {
			list.add(result.getString(columnName));
		}
		statement.close();
		return list;

	}


/**
 * This method is used to get rthe data fromthe database based on the column name
 * @param query
 * @param columnNameOrColumnNumber
 * @return
 * @throws SQLException
 */
/*
	public static ArrayList<String> getDataFromDatabase(String query, String columnNameOrColumnNumber) throws SQLException{

		String num="";
		for (int i = 0; i < columnNameOrColumnNumber.length(); i++) {
			char ch=columnNameOrColumnNumber.charAt(i);
			if(Character.isDigit(ch)){
				num=num+columnNameOrColumnNumber.charAt(i);	
			}else {
				break;
			}

		}
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		int columnNumber=0;
		String  columnName=null;
		if (num.equalsIgnoreCase(columnNameOrColumnNumber)) 
		{
			columnNumber=Integer.parseInt(num);
			while(result.next()) {
				list.add(result.getString(columnNumber));
			}

		} else {
			columnName=columnNameOrColumnNumber;
			while(result.next()) {
				list.add(result.getString(columnName));
			}

		}
		statement.close();
		return list;
	}
	
	*/
	
	/**
	 * This method is used to update/write/modify the data into database
	 * @param query
	 * @throws SQLException
	 */
	public static void updateDataIntoDatabase(String query) throws SQLException{
		Statement statement = connection.createStatement();
		 statement.executeUpdate(query);
		System.out.println("Query executed successfully");
		statement.close();
	}
	/**
	 * This method is used to close the database connection
	 * @throws SQLException
	 */
	public static void closeDatabaseConnection() throws SQLException {
		connection.close();
	}
	
	/**
	 * This method is used to verify the whether the data is present in the database or not
	 * @param query
	 * @param columnNameOrColumnNumber
	 * @param expData
	 * @return
	 * @throws SQLException
	 */
	public static boolean verifyData(String query,String columnNameOrColumnNumber,String expData) throws SQLException {
		ArrayList<String> listData = getDataFromDatabase(query,columnNameOrColumnNumber);
		boolean flag=false;
		for(String data:listData)
		{
			if(data.equalsIgnoreCase(expData))
			{
				flag=true;
			    break;
			}
		}
		return flag;
		
	}
	
}






















