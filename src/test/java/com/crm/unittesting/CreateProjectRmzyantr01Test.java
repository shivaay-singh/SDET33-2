package com.crm.unittesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 *through program write command in mysql to insert desired data values into the database name projects of rmgyantra appln and table name is project
 *then immediatly go and launch the browser and navigate to RMGYantra abd login and click on projects . list of projects will be displayed.
 *now verify that the project you have create through mysql databse are present and displayed or not
 * 
 */

public class CreateProjectRmzyantr01Test {

		public static void main(String[] args) throws SQLException {
			
			Connection connection=null; 
			WebDriver driver=null;	
			try {	
			    //  step1: we should create the object for the driver and register the driver
				 	
		        Driver driver1=new Driver();
		         // register
		         
		        DriverManager.registerDriver(driver1);
		 
		       
		       //  Step2: get connection                                                                          
		       connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		 
		      //Step3: create statement
		   
		      Statement statement = connection.createStatement();
		      //Step4: execute query
		  		      
		      //  for fetching the data(DQL) execute the query
		      String updateQuery="insert into project values('TY_PROJ_00','Tyson','15/03/2022','SDET35','completed',8);";
		      int result = statement.executeUpdate(updateQuery);
		 
		     if(result==1)
		     {
		    	 System.out.println("user is created");
		      }
		     else {
		    	 System.out.println("user is not created");
		     }
		     WebDriverManager.chromedriver().setup();   // this statement is used to set the system property and driver executable internally.
             driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	        driver.get("http://localhost:8084/");
	        driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
	        driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
			driver.findElement(By.xpath("//button[text()='Sign in']")).click();
			
			driver.findElement(By.xpath("//a[text()='Projects']")).click();
			
			String expectedProjectNameText = "SDET35";
			String actualProjectNameText = driver.findElement(By.xpath("//td[text()='SDET35']")).getText();
			if(actualProjectNameText.equals(expectedProjectNameText))
			{
				System.out.println("project is created "+actualProjectNameText);
			}
			else
			{
				System.out.println("project is not created"+actualProjectNameText);
				
			}
		     }
			  finally {
				 // Step5: close connection
				 
				 connection.close();
				 System.out.println("connection is closed");
					}
	        }

         }
