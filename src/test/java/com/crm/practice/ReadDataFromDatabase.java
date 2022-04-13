package com.crm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

public class ReadDataFromDatabase {

	public static void main(String[] args) throws Throwable {
		String dbUrl="jdbc:mysql://localhost:3306/sdet33";
		
		readDataFromDatabase(dbUrl,"root","root","Select * from sdet33;","name");
	}
	
	public static ArrayList<String> readDataFromDatabase(String dbUrl , String dbUserName , String dbPassword,String query,String columnName)  throws SQLException{
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		Connection connection=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		ArrayList<String> list= new ArrayList<String>();
		while (result.next()) {
			list.add(result.getString(columnName));
		}
		System.out.println(list);
		statement.close();
		return list;

	}


}
