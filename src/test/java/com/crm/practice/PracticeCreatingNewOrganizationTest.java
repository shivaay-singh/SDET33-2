package com.crm.practice;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.genericutility.BaseClass;
import com.crm.genericutility.ExcelUtility;
import com.crm.genericutility.FileUtility;
import com.crm.genericutility.JavaUtility;
import com.crm.objectrepository.CreatingNewOrganizationPage;
import com.crm.objectrepository.HomePage;
import com.crm.objectrepository.LoginPage;
import com.crm.objectrepository.OrganizationInformationPage;
import com.crm.objectrepository.OrganizationPage;


public class PracticeCreatingNewOrganizationTest extends BaseClass {
	@Test
	public void practicecreateNewOrganizatinTest() throws Throwable {
		// create object of POM class
		OrganizationPage  organizationPage=new OrganizationPage(driver);
		CreatingNewOrganizationPage  creatingNewOrganizationPage= new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage OrganizationInformationPage= new OrganizationInformationPage(driver);
				
		
       //Store variable 
		String expectedOrgName = ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheetName"), 5, 1)+JavaUtility.generateRandomNumber(1000);
		
		//click on organization TAB
		homePage.clickOnorganizationsTab();
		
		//click on create organization PLus Image
		organizationPage.clickOnCreateOrganizationPulsImage();


		creatingNewOrganizationPage.createOrganizationName(expectedOrgName);
		
		String actOrgName = OrganizationInformationPage.fetchOrganizationNameText();
		
		//verify the contact
		if(actOrgName.equals(expectedOrgName)) {
			Reporter.log("Organization created successfully",true);
		}
			
		}
		

}
