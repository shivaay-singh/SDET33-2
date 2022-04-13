package com.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsChildWindowPage {

	@FindBy(linkText="Sdet13")
	private WebElement organizationDesiredNameLink;

	public WebElement getOrganizationDesiredNameLink() {
		return organizationDesiredNameLink;
	}
	
	public OrganizationsChildWindowPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	public void clickOnRrganizationDesiredNameLink() {
		organizationDesiredNameLink.click();
	}
}
