package com.rmgyantra;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 *thorugh program itself launch browser and navigate to RMGYantra application and login to the application
 * click on  projects the click on create on create projects and create new project by filling necessary data and save it.
 * now go to mysql database and fetch the data (project which you have created is present or not). 
 * Sir program (25/03/2022)
 */

public class VerifyDatabaseWRTGUITest {

	public static void main(String[] args) throws IOException, SQLException {
		Connection connection = null;
		try {

		  Driver d=new Driver();
		  DriverManager.registerDriver(d);
		  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		 //convert the physical file into java readable object
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
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		String projName="gggg";
		String projManager="Edwin";
		driver.findElement(By.name("projectName")).sendKeys(projName);
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys(projManager);
		WebElement status = driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
		Select select = new Select(status);
		select.selectByVisibleText("On Goging");
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		
		Statement statement = connection.createStatement();
		
		// Step: execute query
       // for fetch data DQL
		ResultSet result = statement.executeQuery("Select * from project;");
		
		while(result.next())
		{
			String projectName = result.getString("project_name");
			if(projectName.equals(projName))
			{
				System.out.println("Data is stored in Database");		
		    }
		
		}
		
	driver.close();
		}
		finally {
			connection.close();
            System.out.println("Connectio is closed");    
        }
	}
		
}