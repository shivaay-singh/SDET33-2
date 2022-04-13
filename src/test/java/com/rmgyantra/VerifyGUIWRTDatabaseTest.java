package com.rmgyantra;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 *through program write command in mysql to insert desired data values into the database name projects of rmgyantra appln and table name is project
 *then immediatly go and launch the browser and navigate to RMGYantra abd login and click on projects . list of projects will be displayed.
 *now verify that the project you have create through mysql databse are present and displayed or not
 * 
 *mohan sir program 25/03/2022
 *
 */

public class VerifyGUIWRTDatabaseTest {

		public static void main(String[] args) throws IOException, SQLException {
			Connection connection = null;
			try {

			  Driver d=new Driver();
			  DriverManager.registerDriver(d);
			  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
			  Statement statement = connection.createStatement();
			  String projID="TY_PROJ_026";
				
				// Step: execute query
		       // for fetch data DQL
				int result = statement.executeUpdate("insert into project values('"+projID+"','shivaay','24/03/2022','punjab','On Going', 7);");
				FileInputStream fis= new FileInputStream("./src/test/resources/commonData.properties");
				
				//Step 2: crate the object of properties
				Properties properties=new Properties();
			    
				// Step 3: load all the keys;
				properties.load(fis);
				
				//Step 4: fetch the data
				String url = properties.getProperty("url");
				String userName = properties.getProperty("userName");
				String password = properties.getProperty("password");
				String browser = properties.getProperty("browser");
				String timeout = properties.getProperty("timeouts");
		        long timeoutLong = Long.parseLong(timeout);
		        
		        WebDriver driver=null;
		        if(browser.equalsIgnoreCase("Chrome"))
		        {
		        	WebDriverManager.chromedriver().setup();
		        	driver= new ChromeDriver();
		        }else if(browser.equalsIgnoreCase("firefox"))
		        {
		        	WebDriverManager.firefoxdriver().setup();
		        	driver=new FirefoxDriver();
		        }
		        else {
		        
		        System.out.println("Browser is not specified properly");
		        }
		        driver.manage().window().maximize();
		        driver.manage().timeouts().implicitlyWait(timeoutLong,TimeUnit.SECONDS);
		        driver.get(url);
		        driver.findElement(By.id("usernmae")).sendKeys(userName);
		        driver.findElement(By.id("inputPassword")).sendKeys(password);
				driver.findElement(By.xpath("//button[text()='Sign in']")).click();
				
				driver.findElement(By.linkText("Projects")).click();
				 List<WebElement> list = driver.findElements(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr/td[1]"));
				for(WebElement projElement:list)
				{
					String projectID = projElement.getText();
					if(projectID.equals(projID))
					{
						System.out.println("Test Case Pass");
					}
               
				}
				driver.close();
					
				}
			finally {
				connection.close();
				System.out.println("connection is closed");
			}
				

	}

}
