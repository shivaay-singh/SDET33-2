package com.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createOrganizationPlusImage;

	public WebElement getCreateOrganizationPlusImage() {
		return createOrganizationPlusImage;

	}
	public  OrganizationPage (WebDriver driver) {
	
			PageFactory.initElements(driver,this);
			
	}
	
	public void clickOnCreateOrganizationPulsImage() {
		createOrganizationPlusImage.click();
	}
	
}
