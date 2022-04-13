package com.crm.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	@FindBy (xpath="//a[text()='Contacts']")
    private WebElement contactsTab;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement logoutMenu;
	
	@FindBy(linkText="Sign Out")
	private WebElement signoutLink;
	
	@FindBy(linkText="Organizations")
	private WebElement organizationsTab;
	
	@FindBy(linkText="Opportunities")
	private WebElement opportunitiesTab;
	
	public WebElement getOrganizationsTab() {
		return organizationsTab;
	}
	public void logoutAction() {
		logoutMenu.click();
		signoutLink.click();
	}
	
	public WebElement getLogoutMenu() {
		return logoutMenu;
	}
	public WebElement getSignoutLink() {
		return signoutLink;
	}
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	 public WebElement getContactsTab(){
		 return contactsTab;
	 }
	 public void clickOnContactsTab() {
		 
		 contactsTab.click();
	 }
 public void clickOnorganizationsTab() {
		 
	 organizationsTab.click();
	 }
 public WebElement getOpportunitiesTab() {
		return opportunitiesTab;
	}
	
	
	
	 
	

}
