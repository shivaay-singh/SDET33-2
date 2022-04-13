package com.crm.contacts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.genericutility.ConstantPath;
import com.crm.genericutility.ExcelUtility;
import com.crm.genericutility.FileUtility;
import com.crm.genericutility.JavaUtility;
import com.crm.genericutility.WebDriverUtility;
import com.crm.objectrepository.HomePage;
import com.crm.objectrepository.LoginPage;
import com.crm.objectrepository.OpportunitiesPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_18_CreateOpportunity {
	static WebDriver driver = null;

	public static void main(String[] args) throws Throwable {
		
		//properties file open and load and fetch
				FileUtility.initializePropertyFile(ConstantPath.propertyVtigerFilePath);
				String url = FileUtility.fetchDataFromProperty("url");
				String userName = FileUtility.fetchDataFromProperty("userName");
				String password = FileUtility.fetchDataFromProperty("password");
				String timeouts = FileUtility.fetchDataFromProperty("timeouts");
				String browser = FileUtility.fetchDataFromProperty("browser");
				String excelSheetName2 = FileUtility.fetchDataFromProperty("excelSheetName2");
				String timeout = FileUtility.fetchDataFromProperty("timeouts");
				long timeoutLong = Long.parseLong(timeout);
				
				//generting random number
				int randomNumber = JavaUtility.generateRandomNumber(1000);

				//excel open and getting the data from excel
				ExcelUtility.openExcel(ConstantPath.ExcelPath);
				String expectedLoginTitle = ExcelUtility.fetchData(excelSheetName2, 5, 4);
				String expectedHomeTitle = ExcelUtility.fetchData(excelSheetName2, 6,4);
				String expectedContactsPageLink = ExcelUtility.fetchData(excelSheetName2, 7, 4);
				String expectedCreatingNewContactText = ExcelUtility.fetchData(excelSheetName2, 8, 4);


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
				
				//input[@id='jscal_field_closingdate']
				
				LoginPage loginPage=new LoginPage(driver);
		         loginPage.loginAction();
		         
		         HomePage homepage = new HomePage(driver);
		         homepage.getOpportunitiesTab().click();
		         
		         OpportunitiesPage opportunitiesPage = new OpportunitiesPage(driver);
		         opportunitiesPage.getCreateOpportunityPlusImage().click();
		         



	}

}
