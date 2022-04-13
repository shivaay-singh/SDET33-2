package com.crm.practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.genericutility.ConstantPath;
import com.crm.genericutility.ExcelUtility;
import com.crm.genericutility.FileUtility;
import com.crm.genericutility.JavaUtility;
import com.crm.genericutility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticeGenericUtilityCreateContact {

	public static void main(String[] args) throws Throwable {
		//Step :1 Fetch data from external file(commonData  from Properties file) and store it in variable
		//FileInputStream fis = new FileInputStream("./src/test/resources/commonDataVtiger.properties");
		//Properties properties = new Properties();
		//properties.load(fis);

		FileUtility.initializePropertyFile(ConstantPath.propertyVtigerFilePath);
		FileUtility.fetchDataFromProperty("url");



		//String url = properties.getProperty("url");
		String url = FileUtility.fetchDataFromProperty("url");


		//String userName = properties.getProperty("userName");
		String userName = FileUtility.fetchDataFromProperty("userName");


		//String password = properties.getProperty("password");
		String password = FileUtility.fetchDataFromProperty("password");


		//String timeouts = properties.getProperty("timeouts");
		String timeouts = FileUtility.fetchDataFromProperty("timeouts");


		//String browser = properties.getProperty("browser");
		String browser = FileUtility.fetchDataFromProperty("browser");

		//String excelPath = properties.getProperty("excelPath");
		String excelPath = FileUtility.fetchDataFromProperty("excelPath");

		//String excelSheetName = properties.getProperty("excelSheetName");
		String excelSheetName = FileUtility.fetchDataFromProperty("excelSheetName");

		long timeoutLong = Long.parseLong(timeouts);

		//generate random number
		//Random ran = new Random();
		//int randomNumber = ran.nextInt(1000);

		JavaUtility.generateRandomNumber(1000);

		//fetch the data from excel
		//FileInputStream fis1= new FileInputStream(excelPath);
		//Workbook wb = WorkbookFactory.create(fis1);
		ExcelUtility.openExcel(excelPath);


		//fetch the number from excel
		//FileInputStream fis1= new FileInputStream(excelPath);
		//Workbook wb = WorkbookFactory.create(fis1);
		//String contactName = wb.getSheet(excelSheetName).getRow(2).getCell(2).getStringCellValue();
		String contactName =ExcelUtility.fetchData(excelSheetName,2,2);

		// Step 2: launch the browser
		WebDriver driver=null;
		if(browser.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firfox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		else {
			System.out.println("Browser is not specified properly");
		}
		//Step3: Do BAsic Configuration for browser
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(timeoutLong, TimeUnit.SECONDS);
		WebDriverUtility.launchApplicationWithMaximize(driver, url, timeoutLong);

		//step4: open the url and navigate to application
		//driver.get(url);

		//step5: login to the application
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		// create contact
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.cssSelector("[alt='Create Contact...']")).click();

		String expContactName=contactName;
		driver.findElement(By.name("lastname")).sendKeys(expContactName);
		driver.findElement(By.name("button")).click();

		// step 7 : verify the contact
		String actcontactName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();

		if(actcontactName.equalsIgnoreCase(expContactName)) 
		{
			System.out.println("COntact is created successfully");
			//wb.getSheet(excelSheetName).getRow(2).createCell(4).setCellValue("pass");
			//FileOutputStream fos= new FileOutputStream(excelPath);
			//wb.write(fos);
			ExcelUtility.writeDataInExistingRow(excelPath,excelSheetName, 2, 2, "pass");
		}
		Thread.sleep(5000);
		//Step 8: Sign-Out/loqout from application
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		driver.findElement(By.linkText("Sign Out")).click();

		// Step9: Close the Application
		//wb.close();
		ExcelUtility.closeExcel();
		//driver.quit();		
		WebDriverUtility.closeBrowser(driver);



	}
}