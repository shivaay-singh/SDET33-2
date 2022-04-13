package com.crm.contacts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

 // script to create contact using data from the database my sql

public class CreateContactUsingJdbc01 {
	public static void main(String[] args) throws SQLException {
		Connection connection=null;
		WebDriver driver=null;	
		String url= null, userName=null, password=null, lastName=null;
		try {	
			//step1: we should create the object for the driver and register the driver
			 Driver driver1=new Driver();
	        //register
	        DriverManager.registerDriver(driver1);
	        // Step2: get connection                                                                        
	        connection =
		   DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet33", "root", "root");
	       //Step3: create statement
	       Statement statement = connection.createStatement();
           //Step4: execute query
	       // for fetching the data(DQL) execute the query
	       ResultSet result = statement.executeQuery("select * from vtiger");
	       while(result.next())
	     {
	    	 url=result.getString(1);
	    	 userName=result.getString(2);
	    	 password=result.getString(3);
	    	 lastName=result.getString(4);
		  }
			WebDriverManager.chromedriver().setup();   // this statement is used to set the system property and driver executable internally.
             driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	        driver.get(url);
	        driver.findElement(By.name("user_name")).sendKeys(userName);
	        driver.findElement(By.name("user_password")).sendKeys(password);
			driver.findElement(By.id("submitButton")).click();
			driver.findElement(By.xpath("//a[text()='Contacts']")).click();
			driver.findElement(By.cssSelector("[alt='Create Contact...']")).click();
			driver.findElement(By.name("lastname")).sendKeys(lastName);
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			String actResult = driver.findElement(By.id("dtlview_Last Name")).getText();	
			if(actResult.equals(lastName))
			{
				System.out.println("TC Pass");
			}
			Actions action=new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
			driver.findElement(By.linkText("Sign Out")).click();
	     }
		  finally {
			 // Step5: close connection
			 connection.close();
			 System.out.println("connection is closed");
				}
		driver.quit();
	}
}