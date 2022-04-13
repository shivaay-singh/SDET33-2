package com.crm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

public class PracticeWritingDataIntoDataBase {
	public static void main(String[] args) throws Throwable {
		String dbUrl="jdbc:mysql://localhost:3306/sdet33";
		String updateQuery="insert into sdet33 values(6,'shiva',1631431661,'USA');";

		writeDataIntoDatabase(dbUrl,"root","root",updateQuery);
	}

	public static void writeDataIntoDatabase(String dbUrl , String dbUserName , String dbPassword,String updateQuery)  throws SQLException{

		Driver driver1=new Driver();
		DriverManager.registerDriver(driver1);
		Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		Statement statement = connection.createStatement();
		int result = statement.executeUpdate(updateQuery);
		if(result==1)
		{
			System.out.println("user is created");
		}
		else {
			System.out.println("user is not created");
		}

	}

}
