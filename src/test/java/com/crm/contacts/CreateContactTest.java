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
import com.crm.objectrepository.ContactInformationPage;
import com.crm.objectrepository.ContactsPage;
import com.crm.objectrepository.CreatingNewContactPage;
import com.crm.objectrepository.HomePage;
import com.crm.objectrepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactTest {

	public static void main(String[] args) throws Throwable {
		//Step :1 Fetch data from external file(commonData  from Properties file) and store it in variable
// practice github
		FileUtility.initializePropertyFile(ConstantPath.propertyVtigerFilePath);
		FileUtility.fetchDataFromProperty("url");

		String url = FileUtility.fetchDataFromProperty("url");
        String userName = FileUtility.fetchDataFromProperty("userName");
		String password = FileUtility.fetchDataFromProperty("password");
		String timeouts = FileUtility.fetchDataFromProperty("timeouts");
		String browser = FileUtility.fetchDataFromProperty("browser");
        String excelPath=ConstantPath.ExcelPath;
		String excelSheetName = FileUtility.fetchDataFromProperty("excelSheetName");

		long timeoutLong = Long.parseLong(timeouts);

        JavaUtility.generateRandomNumber(1000);

		//fetch the data from excel
		ExcelUtility.openExcel(excelPath);

		//fetch the number from excel
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
		//Step3: Do BAsic Configuration for browser open the url and navigate to application
		WebDriverUtility.launchApplicationWithMaximize(driver, url, timeoutLong);

		//step5: login to the application
		//driver.findElement(By.name("user_name")).sendKeys(userName);
		
		LoginPage loginpage=new LoginPage(driver);
		loginpage.getUserNameTextField().sendKeys(userName);
		loginpage.getPasswordTextField().sendKeys(password);
		loginpage.getLoginButton().click();
		//driver.findElement(By.name("user_password")).sendKeys(password);
		//driver.findElement(By.id("submitButton")).click();

		// create contact
		//driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		HomePage homePage= new HomePage(driver);
		homePage.getContactsTab().click();
		
		//driver.findElement(By.cssSelector("[alt='Create Contact...']")).click();
		ContactsPage contactPage= new ContactsPage(driver);
		contactPage.getCreateContactsLookUpImage().click();
		
		String expContactName=contactName;
		//driver.findElement(By.name("lastname")).sendKeys(expContactName);
		//driver.findElement(By.name("button")).click();
        
		CreatingNewContactPage creatingNewContacts=new CreatingNewContactPage(driver);
		creatingNewContacts.getLastNameTextField().sendKeys(contactName);
		creatingNewContacts.getSaveButton().click();
		
		// step 7 : verify the contact
		
		//String actcontactName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		
		ContactInformationPage contactInformationPage= new ContactInformationPage(driver);
		String actcontactName = contactInformationPage.getLastNameTextField().getText();

		if(actcontactName.equalsIgnoreCase(expContactName)) 
		{
			System.out.println("Contact is created successfully");
			
			ExcelUtility.writeDataInExistingRow(excelPath,excelSheetName, 2, 4, "pass");
		}
		Thread.sleep(5000);
		
		//Step 8: Sign-Out/loqout from application
		//driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		//driver.findElement(By.linkText("Sign Out")).click();
		homePage.getLogoutMenu().click();
		homePage.getSignoutLink();
	
		// Step9: Close the Application
		//wb.close();
		ExcelUtility.closeExcel();
		//driver.quit();		
		WebDriverUtility.closeBrowser(driver);

	}
}
