package com.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	@FindBy(name="lastname")
	private WebElement lastNameTextField;
	
	@FindBy(name="button")
	private WebElement saveButton;
	@FindBy(xpath="//td[contains(.,'Organization Name') and @class='dvtCellLabel']/following-sibling::td[1]/img[@src='themes/softed/images/select.gif']")
	private WebElement addOrganizationNamePlusImage;
	

	
	public WebElement getAddOrganizationNamePlusImage() {
		return addOrganizationNamePlusImage;
	}

	public CreatingNewContactPage(WebDriver driver){
		PageFactory.initElements(driver,this);	
	}
	
	public WebElement getLastNameTextField()
	{
		return lastNameTextField;
		
	}
	public WebElement getSaveButton(){
		return saveButton;
		
	}
	public void typeInLastNameTextField(String contactName) {
		lastNameTextField.sendKeys(contactName);
		
	}
	public void clickOnAddOrganizationNamePlusImage() {
		addOrganizationNamePlusImage.click();
	}
	



}
