package com.crm.contacts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
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

public class CreateContactWithOrganizationTest extends BaseClass{	/**
	 * -> login to vtiger-> click on organization-> click on create organization-> enter some value into Orgnaization textfield
	-> click on save-> click on Contacts-> enter somevalue in lastname textfiled->click on Organiaztion name + symbol-> new window will open
	-> switch to that window -> go to Search textfield and Enter orgnaiztio name-> click on searchnow-> Switch back to Parent window
	-> verify organization name & Last name -> Logout
	 * 
	 * mohan sir program 25/03/2022 
	 */
		
	@Test(groups= {"smoke","regression"})
	public void createContactWithOrganizationTest() throws Throwable{

		int randomNumber = JavaUtility.generateRandomNumber(1000);

		String expContactName =ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheetName"), 8, 2)+randomNumber;

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
		
		Assert.assertEquals(actualContactName, expContactName,"Error Contact not created");
		Reporter.log("TC pass Contact created",true);

		String expOrgName =ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheetName"), 8, 1)+randomNumber;
        String actualOrgName = contactInformationPage.getOrganizationNameText();
		Reporter.log(expOrgName,true);
		Reporter.log(expOrgName,true);
		
		Assert.assertEquals(actualOrgName, expOrgName,"Error Organization not created");
		Reporter.log("TC pass Organization created",true);
		}

	}
	

