package com.crm.contacts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.genericutility.ConstantPath;
import com.crm.genericutility.ExcelUtility;
import com.crm.genericutility.FileUtility;
import com.crm.genericutility.JavaUtility;
import com.crm.genericutility.WebDriverUtility;
import com.crm.objectrepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_7_CreateContact {
	static WebDriver driver = null;

	public static void main(String[] args) throws Throwable {
		//properties file open and load and fetch
		FileUtility.initializePropertyFile(ConstantPath.propertyVtigerFilePath);
		String url = FileUtility.fetchDataFromProperty("url");
		String userName = FileUtility.fetchDataFromProperty("userName");
		String password = FileUtility.fetchDataFromProperty("password");
		String timeouts = FileUtility.fetchDataFromProperty("timeouts");
		String browser = FileUtility.fetchDataFromProperty("browser");
		String excelSheetName1 = FileUtility.fetchDataFromProperty("excelSheetName1");
		String timeout = FileUtility.fetchDataFromProperty("timeouts");
		long timeoutLong = Long.parseLong(timeout);

		//generting random number
		int randomNumber = JavaUtility.generateRandomNumber(1000);

		//excel open and getting the data from excel
		ExcelUtility.openExcel(ConstantPath.ExcelPath);
		String expectedLoginTitle = ExcelUtility.fetchData(excelSheetName1, 5, 4);
		String expectedHomeTitle = ExcelUtility.fetchData(excelSheetName1, 6,4);
		String expectedContactsPageLink = ExcelUtility.fetchData(excelSheetName1, 7, 4);
		String expectedCreatingNewContactText = ExcelUtility.fetchData(excelSheetName1, 8, 4);


		// launching browser
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();

		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new FirefoxDriver();
		}

		WebDriverUtility.launchApplicationWithMaximize(driver, url, timeoutLong);

		//verifying login page is displayed or not
		String actualLoginTitle = driver.getTitle();
		if(actualLoginTitle.equalsIgnoreCase(expectedLoginTitle))
		{
			System.out.println("Pass: login page is displayed");
		}
		else {
			System.out.println("Fail:login page is not displayed");
		}
    
		//driver.findElement(By.name("user_name")).sendKeys(userName);
	//	driver.findElement(By.name("user_password")).sendKeys(password);
		//driver.findElement(By.id("submitButton")).click();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.loginAction();


		//String actualHomeTitle =driver.getCurrentUrl();
		String actualHomeTitle = WebDriverUtility.getWebPageTitle(driver);

		if(actualHomeTitle.equalsIgnoreCase(expectedHomeTitle)) 
		{

			System.out.println("Pass : Home page is displayed");
			ExcelUtility.writeDataInExistingRow(ConstantPath.ExcelPath, excelSheetName1, 5, 5, "Pass");	
		} else {

			System.out.println("Fail : Home page is not displayed"); 
			ExcelUtility.writeDataInExistingRow(ConstantPath.ExcelPath, excelSheetName1, 5, 5, "fail");	

		}
		driver.findElement(By.linkText("Contacts")).click();
		String actualContactsPageLink = driver.findElement(By.xpath("//a[@class='hdrLink']")).getText(); 

		if(actualContactsPageLink.equalsIgnoreCase(expectedContactsPageLink))
		{
			System.out.println("Pass : Contacts page is displayed");
			ExcelUtility.writeDataInExistingRow(ConstantPath.ExcelPath, excelSheetName1, 6, 5, "Pass");	


		}else {
			System.out.println("Fail : contacts page is not displayed");
			ExcelUtility.writeDataInExistingRow(ConstantPath.ExcelPath, excelSheetName1, 6, 5, "fail");	

		}

		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		String actualCreatingNewContactText = driver.findElement(By.xpath("//span[text()='Creating New Contact']")).getText();
		if(expectedCreatingNewContactText.equalsIgnoreCase(actualCreatingNewContactText))
		{
			System.out.println("pass : Create New Contact page is displayed");
			ExcelUtility.writeDataInExistingRow(ConstantPath.ExcelPath, excelSheetName1, 7, 5, "Pass");	


		}
		else {
			System.out.println("fail : Create new contact is not displayed");
			ExcelUtility.writeDataInExistingRow(ConstantPath.ExcelPath, excelSheetName1, 7, 5, "fail");	
         }
		String contactLastName = ExcelUtility.fetchData(excelSheetName1,5,6 );
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		
		driver.findElement(By.xpath("//input[@name='contact_name']/following-sibling::img[@title='Select']")).click();
		

		
		WebDriverUtility.closeBrowser(driver);
	}

}
