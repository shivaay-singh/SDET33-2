package com.crm.objectrepository;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Step1: we should create a public class and give the class name as web page name
public class LoginPage {


		//Step2: Declaration ---> we wil declare the loacators using @FindBy and declare as private
		@FindBy(name= "user_name")
		private WebElement userNameTextField;

		@FindBy(name= "user_password")
		private WebElement passwordTextField;

		@FindBy (id= "submitButton")
		private WebElement loginButton;
		//Step3: Initiallization --> we we will create public constructor and initialize the elements/variables

		public LoginPage(WebDriver driver) {
			PageFactory.initElements(driver,this);
		}		
		//Step4: Utilization --> by developing public getters or/Business library
		//way 1: by creating public getters
		public WebElement getUserNameTextField(){
			return userNameTextField;
		}
		public WebElement getPasswordTextField(){
			return passwordTextField;
		}
		public WebElement getLoginButton(){
			return loginButton;
		}
		// way 2: by creating business library
		public void loginAction(){
			enterUN_Pwd();
			loginButton.click();
		}

		public void enterUN_Pwd() {
			userNameTextField.sendKeys("admin");
			passwordTextField.sendKeys("manager");
		}
		public void loginAction(String userName,String password){
			userNameTextField.sendKeys(userName);
			passwordTextField.sendKeys(password);
			loginButton.click();
		}
		
		
		
		
	}


