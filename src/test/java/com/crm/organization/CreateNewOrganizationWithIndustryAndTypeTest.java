package com.crm.organization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.crm.genericutility.ConstantPath;
import com.crm.genericutility.ExcelUtility;
import com.crm.genericutility.FileUtility;
import com.crm.genericutility.JavaUtility;
import com.crm.genericutility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewOrganizationWithIndustryAndTypeTest {
	public static void main(String[] args) throws Throwable {
		//Step :1 Fetch data from external file(commonData  from Properties file) and store it in variable
		//FileInputStream fis = new FileInputStream("./src/test/resources/commonDataVtiger.properties");
		//Properties properties = new Properties();
		//properties.load(fis);
		FileUtility.initializePropertyFile(ConstantPath.propertyVtigerFilePath);
		
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
		//String excelPath = FileUtility.fetchDataFromProperty(ConstantPath.ExcelPath);
		String excelPath=ConstantPath.ExcelPath;

		//String excelSheetName = properties.getProperty("excelSheetName");
		String excelSheetName = FileUtility.fetchDataFromProperty("excelSheetName");

		long timeoutLong = Long.parseLong(timeouts);

		//generate random number
		//Random ran = new Random();
		//int randomNumber = ran.nextInt(1000);
		int randomNumber = JavaUtility.generateRandomNumber(1000);
		
		//fetch the Data from excel
		//FileInputStream fis1= new FileInputStream(excelPath);
		//Workbook wb = WorkbookFactory.create(fis1);
		ExcelUtility.openExcel(excelPath);
		//String orgName = wb.getSheet(excelSheetName).getRow(11).getCell(1).getStringCellValue();
		String orgName = ExcelUtility.fetchData(excelSheetName, 11, 1);
		
		//String industry = wb.getSheet(excelSheetName).getRow(11).getCell(2).getStringCellValue();
		String industry = ExcelUtility.fetchData(excelSheetName, 11, 2);

       // String type = wb.getSheet(excelSheetName).getRow(11).getCell(3).getStringCellValue();
		String type = ExcelUtility.fetchData(excelSheetName, 11, 3);

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
        //step4: open the url and navigate to application
		//driver.get(url);
        WebDriverUtility.launchApplicationWithMaximize(driver, url, timeoutLong);

		//step5: login to the application99
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		// Step6: create organization
		driver.findElement(By.xpath("//a[@href='index.php?module=Accounts&action=index']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		String expOrgName=orgName+randomNumber;
		driver.findElement(By.name("accountname")).sendKeys(expOrgName);
		WebElement industryDropDown = driver.findElement(By.xpath("//select[@name='industry']"));
		
		Select sIndustry = new Select(industryDropDown);
		sIndustry.selectByValue(industry);
		WebElement typeDropDown = driver.findElement(By.xpath("//select[@name='accounttype']"));
		
		//Select sType = new Select(typeDropDown);
		//sType.selectByValue("Analyst");
		WebDriverUtility.select("Analyst", typeDropDown);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// step 7 : verify the contact
		String actOrgName = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		String actIndustry = driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
        String actType = driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();

		if(actOrgName.equalsIgnoreCase(expOrgName) && actIndustry.equalsIgnoreCase(industry) && actType.equalsIgnoreCase(type)) 
		{
			System.out.println("Organization is created successfully with Industry and Type");
          // wb.getSheet(excelSheetName).getRow(11).createCell(4).setCellValue("pass");
          // FileOutputStream fos= new FileOutputStream(excelPath);
		   //wb.write(fos);
			ExcelUtility.writeDataInExistingRow(excelPath, excelSheetName, 11, 4,"pass");
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
