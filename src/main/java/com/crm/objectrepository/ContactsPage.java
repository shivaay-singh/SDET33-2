package com.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement createContactLookUpImage;
	
	public ContactsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getCreateContactsLookUpImage() {
		return createContactLookUpImage;
	}

	public void clickOncreateContactLookUpImage() {
		createContactLookUpImage.click();
	}
}
