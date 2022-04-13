package com.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	
	@FindBy(id="dtlview_Last Name")
	private WebElement lastNameTextField;
	
	@FindBy(xpath="//td[contains(.,'Organization Name') and @class='dvtCellLabel']/following-sibling::td[1]/img[@src='themes/softed/images/select.gif']")
	private WebElement organizationNamePlusImage;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(linkText="Sdet13")
	private WebElement organizationNameTextField;
	

	public WebElement getOrganizationNameTextField() {
		return organizationNameTextField;
	}


	public WebElement getSaveButton() {
		return saveButton;
	}
public void clickOnSaveButton() {
	saveButton.click();
}

	public  ContactInformationPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}


	public WebElement getLastNameTextField() {
		return lastNameTextField;
	}

	public String getlastNameText() {
		String lastName = lastNameTextField.getText();
		return lastName;
	}

	public String getOrganizationNameText() {
		String organizationNameText = organizationNameTextField.getText();
		return organizationNameText;
	}
	public WebElement getOrganizationNamePlusImage() {
		return organizationNamePlusImage;
	}
	
	

}
