package com.crm.contacts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateContact01Test {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Shibu PC\\Desktop\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("manager");
		driver.findElement(By.id("submitButton")).click();
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
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		  	String expectedContactsTitle = "Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
		String actualContactsTile = driver.getTitle();
		System.out.println(actualContactsTile);
		if(actualContactsTile.contains(expectedContactsTitle))
		{
			System.out.println("Pass Contacts page is Displayed :  "+actualContactsTile);
		}
		else {
			System.out.println("Fail Contacts page is not Displayed :  "+actualContactsTile);
		}
		driver.findElement(By.cssSelector("[alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys("sdet");
		driver.findElement(By.name("button")).click();
		WebElement administratorlogo = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		administratorlogo.click();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();

}
}
