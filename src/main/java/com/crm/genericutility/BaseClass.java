package com.crm.genericutility;

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

import com.crm.objectrepository.HomePage;
import com.crm.objectrepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * This class is used to do Basic Configurations Before performing any Actions on the application
 * @author Shivaay PC
 *
 */
public class BaseClass {
	public WebDriver driver;
       public HomePage homePage ;
       public static WebDriver sdriver;
	
	
	/**
	 * This method is used for Basic Configuration like open Properties file and  open the DataBase connection and open Excel file
	 * @throws Throwable
	 */
	@BeforeSuite(groups={"smoke","regression"})
	public void openDataBase() throws Throwable {
		FileUtility.initializePropertyFile(ConstantPath.propertyVtigerFilePath);
		DatabaseUtility.getMysqlDatabaseConnection(ConstantPath.dbPath,FileUtility.fetchDataFromProperty("dbUserName"), FileUtility.fetchDataFromProperty("dbPassword"));
		ExcelUtility.openExcel(ConstantPath.ExcelPath);

	}
	
	/**
	 * This method is used to to launch browser , maximize the browser along with implicitly wait
	 */
	
	/**@Parameters(value="browser")
	@BeforeClass(groups={"smoke","regression"})
	public void launchBrowser(String browser) {
	
	**/
	@BeforeClass(groups={"smoke","regression"})
	public void launchBrowser(/*String browser*/) {
		//String browser=FileUtility.fetchDataFromProperty("browser");	
		String browser = System.getProperty("browser");
		long timeout = JavaUtility.convertStringToLong(FileUtility.fetchDataFromProperty("timeouts"));
				
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
		//String url=FileUtility.fetchDataFromProperty("url");
		String url = System.getProperty("url");

		WebDriverUtility.launchApplicationWithMaximize(driver,url, timeout);
		sdriver=driver;
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
			
		}	
		/**
		 * THis method is used to logout from the application and close the Excel workBook
		 * @throws Throwable
		 */
		@AfterMethod(groups={"smoke","regression"})
		public void logoutFromApplication() throws Throwable {
			homePage.logoutAction();
			
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
		 * @throws Throwable 
		 */
		@AfterSuite(groups={"smoke","regression"})
		public void closeDBconnection() throws Throwable {
			
			DatabaseUtility.closeDatabaseConnection();
			ExcelUtility.closeExcel();

		}
			

}
