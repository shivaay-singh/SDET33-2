package com.crm.testngpractice;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.genericutility.BaseClass;
import com.crm.genericutility.ExcelUtility;
import com.crm.genericutility.FileUtility;
import com.crm.genericutility.JavaUtility;
import com.crm.objectrepository.CreatingNewOrganizationPage;
import com.crm.objectrepository.OrganizationInformationPage;
import com.crm.objectrepository.OrganizationPage;

public class PracticeRertyAnalyzerListener1 extends BaseClass {
	
	@Test(groups={"smoke","regression"})
	public void createNewOrganizatinTest() throws Throwable {
		
		// create object of POM class
		OrganizationPage  organizationPage=new OrganizationPage(driver);
		CreatingNewOrganizationPage  creatingNewOrganizationPage= new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage OrganizationInformationPage= new OrganizationInformationPage(driver);
				
		
       //Store variable 
		String expectedOrgName = ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheetName"), 5, 1)+JavaUtility.generateRandomNumber(1000);
		
		//click on organization TAB
		homePage.clickOnorganizationsTab();
		
		Assert.fail();
		
		//click on create organization PLus Image
		organizationPage.clickOnCreateOrganizationPulsImage();


		creatingNewOrganizationPage.createOrganizationName(expectedOrgName);
		
		String actOrgName = OrganizationInformationPage.fetchOrganizationNameText();
		
		//verify the contact
		Reporter.log("Intentionally failing the script",true);
		
		Assert.assertEquals(actOrgName, expectedOrgName, "Error Organization not created");
		//Assert.assertTrue(false, "sdd");	to check taking screen shot on failur is working or not	
		Reporter.log("Organization created successfully",true);
		
	}

}
