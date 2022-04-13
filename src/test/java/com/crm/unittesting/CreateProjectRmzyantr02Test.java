package com.crm.unittesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * thorugh program itself launch browser and navigate to RMGYantra appln and login to the application
 * click on  projects the click on create on create projects and create new project by filling necessary data and save it.
 * now go to mysql database and fetch the data (project which you have created is present or not).
 */

public class CreateProjectRmzyantr02Test {
 public static void main(String[] args) throws Throwable{
	 Connection connection=null;
		WebDriver driver=null;	
		String project_id= null, created_by=null, created_on=null, project_name=null , status=null;
		int team_size=0;
	 try {
	  driver=null;
	 WebDriverManager.chromedriver().setup();   // this statement is used to set the system property and driver executable internally.
     driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    driver.get("http://localhost:8084/");
    driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
    driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
	driver.findElement(By.xpath("//button[text()='Sign in']")).click();
	driver.findElement(By.xpath("//a[text()='Projects']")).click();
	driver.findElement(By.xpath("//span[text()='Create Project']")).click();
	driver.findElement(By.name("projectName")).sendKeys("SDET40");
	 WebElement projectManagerTF = driver.findElement(By.name("createdBy"));
	 projectManagerTF.sendKeys("Shankaran");
	WebElement projectStatusDropDown = driver.findElement(By.xpath("//label[text()='Project Status ']/../select"));
	projectStatusDropDown.click();
	 Select select=new Select(projectStatusDropDown);
	 select.selectByValue("Created");
	 projectManagerTF.click();
	 driver.findElement(By.xpath("//input[@value='Add Project']")).click();
			//step1: we should create the object for the driver and register the driver
			 Driver driver1=new Driver();
	        //register
	        DriverManager.registerDriver(driver1);
	        // Step2: get connection                                                                        
	        connection =
		   DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
	       //Step3: create statement
	       Statement statement = connection.createStatement();
        //Step4: execute query
	       // for fetching the data(DQL) execute the query
	       ResultSet result = statement.executeQuery("select * from project");
	       while(result.next())
	     {
	    	System.out.println(result.getString(1) + "\t"+result.getString(2) + "\t"+result.getString(3) + "\t"+result.getString(4) + "\t"+result.getString(5) + "\t"+result.getInt(6));
	    	project_id=result.getString(1);
	    	created_by=result.getString(2);
	    	created_on=result.getString(3);
	    	project_name=result.getString(4);
		    status=result.getString(5);
            team_size=result.getInt(6);
		  }
	       String expectedProjectId="SDET40";
	       if(project_name.equals(expectedProjectId))
	       {
	    	   System.out.println("Project is created and data is stored in the database : Project Name = "+project_name);
	       }
	       else {
	    	   System.out.println("Project is Not created and data is not stored in the database "+project_name);	   
	       }
	 }
	  finally {
			 // Step5: close connection
			 connection.close();
			 System.out.println("connection is closed");
				}
              driver.quit();
               }
             }
