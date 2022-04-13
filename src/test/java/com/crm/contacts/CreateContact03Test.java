package com.crm.contacts;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * this class is used to create new contact and verify whether the new contact is created or not
 * and sign out
 * @author Shivaay PC
 *
 */
public class CreateContact03Test {
	public static void main(String[] args) throws Throwable {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Shibu PC\\Desktop\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		/**
		 * login to vtiger
		 */
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("manager");
		driver.findElement(By.id("submitButton")).click();
		
		/**
		 * verifying whether home page is displayed or not
		 */
		String expectedHomeTitle = "Administrator - Home - vtiger CRM 5 - Commercial Open Source CRM";
		String actualHomeTile = driver.getTitle();
		System.out.println(actualHomeTile);
		if(actualHomeTile.contains(expectedHomeTitle))
		{
			System.out.println("Pass Home page is Displayed :  "+actualHomeTile);
		}
		else {
			System.out.println("Fail Home page is not Displayed :  "+actualHomeTile);
		}
		/**
		 * clicking on Contacts Module
		 */
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		
		/**
		 *  verifying whether Contacts page is displayed or not
		 */
	  	String expectedContactsTitle = "Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
		String actualContactsTitle = driver.getTitle();
		System.out.println(actualContactsTitle);
		if(actualContactsTitle.contains(expectedContactsTitle))
		{
			System.out.println("Pass Contacts page is Displayed :  "+actualContactsTitle);
		}
		else {
			System.out.println("Fail Contacts page is not Displayed :  "+actualContactsTitle);
		}
		
		/**
		 * clicking on create new contact plus icon
		 */
		
		driver.findElement(By.cssSelector("[alt='Create Contact...']")).click();
		
		/**
		 *  verifying whether create new contact page is displayed or not
		 */
		String expectedCreatedNewContact = "Creating New Contact";
		WebElement actualCreatedNewContact = driver.findElement(By.xpath("//span[text()='Creating New Contact']"));
		
		Thread.sleep(4000);
		if (actualCreatedNewContact.getText().contains(expectedCreatedNewContact)) {
			System.out.println("Pass Create New Contacts Page is Displayed : "+actualCreatedNewContact);
		} else {
			System.out.println("Fail Create New Contacts Page is not Displayed  : "+actualCreatedNewContact);

		}
		/**
		 *  entering the data in the lastName text field
		 */
		driver.findElement(By.name("lastname")).sendKeys("sdet4");
		
		/**
		 *  clicking on save button
		 */
		driver.findElement(By.name("button")).click();
		
		/**
		 *  verifying whether the data is saved or not
		 */
		String expectedSavedNewContact = "sdet4";
		WebElement actualSavedNewContact = driver.findElement(By.xpath("//span[text()='sdet4']"));
		if (actualSavedNewContact.getText().contains(expectedSavedNewContact)) {
			System.out.println("Pass New Contact is Saved and created");
		} else {
			System.out.println("Fail New Contact is Not Saved");

		}
		
		/**
		 * mousehovering on Administrator logo to signout
		 */

	 WebElement administratorlogo = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	 administratorlogo.click();
		WebElement signout = driver.findElement(By.xpath("//a[text()='Sign Out']"));

		Actions action = new Actions(driver);
		action.moveToElement(signout).click().perform();
		
		//driver.quit();
        

	}

}
