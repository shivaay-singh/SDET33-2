package com.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	
	@FindBy(xpath="//span[@id='dtlview_Organization Name']")
	private WebElement organizationNameText;
	
	@FindBy(xpath="//select[@name='industry']")
	private WebElement industryDropdown;
	
	@FindBy(xpath="//select[@name='accounttype']")
	private WebElement typeDropdown;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	public WebElement getOrganizationNameText() {
		return organizationNameText;
	}
	public WebElement getIndustryDropdown() {
		return industryDropdown;
	}
	public WebElement getTypeDropdown() {
		return typeDropdown;
	}
	public WebElement getSaveButton() {
		return saveButton;
	}
	public WebElement getOrganizationInformation() {
		return organizationNameText;

	}
	public  OrganizationInformationPage (WebDriver driver) {
	
			PageFactory.initElements(driver,this);

	}
	public  String fetchOrganizationNameText() {
		String orgName = organizationNameText.getText();
		return orgName;
		}

}
