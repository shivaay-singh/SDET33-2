package com.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewOrganizationPage {
	@FindBy(name="accountname")
	private WebElement organizationNameTextField;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	public WebElement getOrganizationNameTextField() {
		return organizationNameTextField;
	}
	public WebElement getSaveButton() {
		return saveButton;
	}
	public WebElement getCreateOrganizationPlusImage() {
		return organizationNameTextField;

	}
	public  CreatingNewOrganizationPage (WebDriver driver) {
	
			PageFactory.initElements(driver,this);
		
	}
	public void createOrganizationName(String data) {
		organizationNameTextField.sendKeys(data);
		saveButton.click();
		
		
	}
	

}
