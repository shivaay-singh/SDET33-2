package com.crm.genericutility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * Thi class is collection of webdriver actions
 * @author Shivaay PC
 *
 */
public class WebDriverUtility {
	/**
	 * This method is used for implicit wait  for specified period of time
	 * @param driver
	 * @param timeout
	 */
	public static void waitforPageLoad(WebDriver driver, long timeoutLong)
	{
		driver.manage().timeouts().implicitlyWait(timeoutLong, TimeUnit.SECONDS);
	}
	
	
	/**
	 * This method is used to wait until element visible
	 * @param driver
	 * @param timeout
	 * @param element
	 */
	public static void waitUntilElementVisible(WebDriver driver,long timeout,WebElement  element)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method is used to wait element visible with specified polling time
	 * @param driver
	 * @param timeout
	 * @param element
	 * @param pollingTime
	 */
	public static void waitUntilElementVisibleWithCustomPoll(WebDriver driver, long timeout , WebElement element , long pollingTime)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.pollingEvery(Duration.ofSeconds(pollingTime));
		wait.ignoring(Throwable.class);		
	}
	/**
	 * This method will wait until element is clickable with customize time and polling period
	 * @param element
	 * @param timeout
	 * @param pollingTime
	 * @throws InterruptedException
	 */
	public static void customWaitTillElementClickable( WebElement element ,int timeout, int pollingTime) throws InterruptedException
	{
		int count=0;
		while(count<=timeout)
		{
			try {
				element.click();
				break;
			}catch(NoSuchElementException e)
			{
				Thread.sleep(pollingTime);
				count++;
			}
			
		}
	}
	
	/**
	 * Tis method will maximize the browser
	 * @param driver
	 */
	public static void maximizeBrowser(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	

	/**
	 * This method is used to open the application with maximize
	 * @param driver
	 * @param url
	 * @param timeout
	 */
	public static void launchApplicationWithMaximize(WebDriver driver , String url , long timeout)
	{
		driver.get(url);
		maximizeBrowser(driver);
		waitforPageLoad(driver,timeout);
	}
	
	/**
	 * This method will  be used to open the application
	 * @param driver
	 * @param url
	 * @param timeout
	 */
	public static void launchApplication(WebDriver driver , String url , long timeout)
	{
		driver.get(url);
		waitforPageLoad(driver,timeout);
	}
	
	
	/**
	 * This method is used to switch to the particular window
	 * @param driver
	 * @param partialTitleText
	 */
	public static void switchToWindow(WebDriver driver, String partialTitleText)
	{
		Set<String> winIDs2 = driver.getWindowHandles();
	    for (String id : winIDs2) {
			driver.switchTo().window(id);
			if(driver.getTitle().contains(partialTitleText)) {
				break;
			}
		}
	}
	/**
	 * This method is used to move to the cursor on element
	 * @param driver
	 * @param element
	 */
	public static void moveToElement(WebDriver driver, WebElement element)
	{
		Actions act= new Actions(driver);
		act.moveToElement(element).perform();
	}
	/**
	 * This method is used to right click on  element
	 * @param driver
	 * @param element
	 */
	public static void rightClickonElement(WebDriver driver, WebElement element)
	{
		Actions act= new Actions(driver);
		act.contextClick(element).perform();
	}
	/**
	 * This method is used to double click on element
	 * @param driver
	 * @param element
	 */
	public static void doubleClickonElement(WebDriver driver, WebElement element)
	{
		Actions act= new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	
	public static void doubleClickonElement(WebDriver driver)
	{
		Actions act= new Actions(driver);
		act.doubleClick().perform();
	}
	
	
	
	//This method is used to select the dropdown option by index
	/**
	 * This method is used to select the dropdown option by index
	 * @param element
	 * @param index
	 */
	public static void select(WebElement element, int index)
	{
		Select select = new Select (element);
		select.selectByIndex(index);
	}
	/**
	 * This methods is used to select the dropdown option by VisibleText
	 * @param element
	 * @param visisbleText
	 */
	public static void select(WebElement element,String visisbleText)
	{
		Select select = new Select (element);
		select.selectByVisibleText(visisbleText);
	} 
	
	
	/**
	 * This method is used to select the dropdown option by value
	 * @param value
	 * @param element
	 */
	public static void select(String value, WebElement element)
	{
		Select select = new Select (element);
		select.selectByValue(value);
	} 
	
	/**
	 * THis method is used to quit the browser instance
	 * @param driver
	 */
	public static void closeBrowser(WebDriver driver) {
		driver.quit();
	}
	
	/**
	 * This method is used to switch to the frame by index
	 * @param driver
	 * @param index
	 */
	public static void frame(WebDriver driver , int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method is used to switch to the frame by nameOrID
	 * @param driver
	 * @param nameOrID
	 */
	public static void frame(WebDriver driver , String nameOrID)
	{
		driver.switchTo().frame(nameOrID);
	}
	
	
	/**
	 * This method is used to switch to the frame by element
	 * @param driver
	 * @param element
	 */
	public static void frame(WebDriver driver , WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method is used to take the screenshot of failed test script
	 * @param driver
	 * @param fileName
	 * @throws IOException
	 */
	public static void takeScreenShotOfFailedScript(WebDriver driver , String fileName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File("./screenShot/"+fileName+"_"+JavaUtility.getCurrentTimeAndDate()+".png");
		FileUtils.copyFile(src, dst);
	}
	
	/**
	 * This method is used to take the screenshot of failed test script and also it will return the
	 * absolute path of screen shot where it store
	 * @param driver
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String takeScreenShotandgetPath(WebDriver driver , String fileName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File("./screenShot/"+fileName+"_"+JavaUtility.getCurrentTimeAndDate()+".png");
		FileUtils.copyFile(src, dst);
		String absolutePath = dst.getAbsolutePath();
		return absolutePath;
	}
	
	/**
	 * This method is created to open the application through Javascript
	 * @param driver
	 * @param url
	 */
	public static void openApplicationThroughJs(WebDriver driver , String url) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.location='"+url+"'");
	}
	/**
	 * This method is used to send the data into particular textfield
	 * @param driver
	 * @param element
	 * @param input
	 */
	public static void sendKeysThroughJs(WebDriver driver,WebElement element,String input) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+input+"",element);
	}
	
	/**
	 * This method is used to click on the button/element
	 * @param driver
	 * @param element
	 */
	public static void clickActionThroughJs(WebDriver driver,WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",element);
	}
	
	/**
	 * This method is used to scroll the webpage until the element is present
	 * @param driver
	 * @param element
	 */
	public static void scrollTillElementThroughJs(WebDriver driver,WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",element);
	}
	/**
	 * This method is used to ScrollDown to end of web page or Scrollup if it is in down based on user
	 * @param driver
	 * @param upOrDown
	 */
	public static void scrollDownToPageThroughJs(WebDriver driver, String upOrDown) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,"+upOrDown+"document.body.scrollHeight)");
	}
	
	public static String getTextofAlertPopup(WebDriver driver)
	{
		String popupText =driver.switchTo().alert().getText();
		return popupText;
		
	}
	public static void acceptAlertPopup(WebDriver driver)
	{
		driver.switchTo().alert().accept();
		
	}
	public static void dismissAlertPopup(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
		
	}
	public static String getCurrentWebUrl(WebDriver driver) {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
		
	}
	public static String getWebPageTitle(WebDriver driver) {
		String currentTitle = driver.getTitle();
		return currentTitle;
	}
	

}
