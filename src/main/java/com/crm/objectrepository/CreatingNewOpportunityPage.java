package com.crm.objectrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatingNewOpportunityPage {
	@FindBy (name="potentialname")
	private WebElement opportunityNameTextField;
	
	@FindBy (xpath="//input[@name='related_to_display']//following-sibling::img[@alt='Select']")
	private WebElement relatedToOrganizationPlusImage;
	
	@FindBy(xpath="//input[@value='U']")
	private WebElement assignedToUserRadioButton;
	
	@FindBy(xpath="//input[@value='T']")
	private WebElement assignedToGroupRadioButton;

}
