package com.crm.practice;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.genericutility.ConstantPath;
import com.crm.genericutility.DatabaseUtility;
import com.crm.genericutility.ExcelUtility;
import com.crm.genericutility.FileUtility;
import com.crm.genericutility.JavaUtility;
import com.crm.genericutility.WebDriverUtility;
import com.crm.objectrepository.HomePage;
import com.crm.objectrepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticeBaseClass {
	
	
			public WebDriver driver;
	       public HomePage homePage ;
		
		
		/**
		 * This method is used to open Properties file and  open the DataBase connection
		 * @throws Throwable
		 */
		@BeforeSuite(groups={"smoke","regression"})
		public void openDataBase() throws Throwable {
			FileUtility.initializePropertyFile(ConstantPath.propertyVtigerFilePath);
			DatabaseUtility.getMysqlDatabaseConnection(ConstantPath.dbPath,FileUtility.fetchDataFromProperty("dbUserName"), FileUtility.fetchDataFromProperty("dbPassword"));
			
		}
		
		/**
		 * This method is used to to launch browser , maximize the browser along with implicitly wait
		 */
	
		@BeforeClass(groups={"smoke","regression"})
		public void launchBrowser() {
			long timeout = JavaUtility.convertStringToLong(FileUtility.fetchDataFromProperty("timeouts"));
			String browser = FileUtility.fetchDataFromProperty("browser");
					
			// launching browser
			if(browser.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();

			}
			else if(browser.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
			}else {
				Reporter.log("Browser is not specified properly", true);
			}
			WebDriverUtility.launchApplicationWithMaximize(driver,FileUtility.fetchDataFromProperty("url"), timeout);
		}
		/**
		 * This method is used to login to application and created POM HomePage class object and also open the Excel workBook
		 * @throws Throwable
		 */
			@BeforeMethod(groups={"smoke","regression"})
			public void loginToApplication() throws Throwable {
				LoginPage loginPage=new LoginPage(driver);
				loginPage.loginAction(FileUtility.fetchDataFromProperty("userName"),FileUtility.fetchDataFromProperty("password"));
				homePage = new HomePage(driver);
				ExcelUtility.openExcel(ConstantPath.ExcelPath);
				
			}	
			/**
			 * THis method is used to logout from the application and close the Excel workBook
			 * @throws Throwable
			 */
			@AfterMethod(groups={"smoke","regression"})
			public void logoutFromApplication() throws Throwable {
				homePage.logoutAction();
				ExcelUtility.closeExcel();
				
			}
			/**
			 * This method is used to close the Browser
			 */
			@AfterClass(groups={"smoke","regression"})
			public void closeBrowser() {
				WebDriverUtility.closeBrowser(driver);
				
			}
			/**
			 * This method is used to close the DataBase Connection
			 * @throws SQLException
			 */
			@AfterSuite(groups={"smoke","regression"})
			public void closeDBconnection() throws SQLException {
				
				DatabaseUtility.closeDatabaseConnection();
			}
				

	}

