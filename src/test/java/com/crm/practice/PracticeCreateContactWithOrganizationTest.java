package com.crm.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.genericutility.BaseClass;
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
import com.crm.objectrepository.OrganizationsChildWindowPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticeCreateContactWithOrganizationTest extends BaseClass{

	@Test(groups= {"smoke","regression"})
	public void practiceCreateContactWithOrganizationTest() throws Throwable{

		int randomNumber = JavaUtility.generateRandomNumber(1000);

		String expContactName =ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheetName"), 8, 2);

		// create contact
		homePage.clickOnContactsTab();

		ContactsPage contactPage= new ContactsPage(driver);
		contactPage.clickOncreateContactLookUpImage();

		CreatingNewContactPage creatingNewContacts=new CreatingNewContactPage(driver);
		creatingNewContacts.typeInLastNameTextField(expContactName);

		//Step9: select/ Add organization
		creatingNewContacts.clickOnAddOrganizationNamePlusImage();

		String accountChildWindowName =ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheetName"), 1, 5);
		WebDriverUtility.switchToWindow(driver,accountChildWindowName);

		OrganizationsChildWindowPage organizationsChildWindow=new OrganizationsChildWindowPage(driver);
		organizationsChildWindow.clickOnRrganizationDesiredNameLink();

		String contactsChildWindowName =ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheetName"), 2, 5);

		WebDriverUtility.switchToWindow(driver, contactsChildWindowName);

		ContactInformationPage  contactInformationPage = new ContactInformationPage(driver);
		contactInformationPage.clickOnSaveButton();

		String actualContactName = contactInformationPage.getlastNameText();
		if(actualContactName.equals(expContactName) ) 
		{
			Reporter.log("Pass :contact is created successfully ",true);

		}else {
			Reporter.log("Fail : contact is not created  ",true);
		}
		
		
		String expOrgName =ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheetName"), 8, 1);
        String actualOrgName = contactInformationPage.getOrganizationNameText();
		Reporter.log(expOrgName,true);
		Reporter.log(expOrgName,true);


		if (actualOrgName.equals(expOrgName) )
		{
			Reporter.log("Pass  :Organization  is created successfully ",true);

		}else {
			Reporter.log("Fail : Organization is not created  ",true);
		}
	}
}
