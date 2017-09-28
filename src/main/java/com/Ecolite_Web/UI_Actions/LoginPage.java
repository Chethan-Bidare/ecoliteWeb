package com.Ecolite_Web.UI_Actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Ecolite_Web.TestBase.TestBase;


public class LoginPage extends TestBase{

	public static final Logger log = Logger.getLogger(LoginPage.class.getName());
	
	public String Storename ;
	
	@FindBy(id="mobile")
	WebElement mobile;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="login_btn")
	WebElement login_btn;
	
	@FindBy(xpath=".//*[Contains(text(),'Sign Up')]")
	WebElement SignUp ;
	
	@FindBy(xpath=".//*[Contains(text(),'Forgot password')]")
	WebElement ForgotPassword ;
	
	@FindBy(xpath=".//*[@id='topnav']/div[2]/div/div[2]/ul/li[2]/a/b")
	WebElement StoreName ;
	
	public LoginPage(WebDriver driver){
		PageFactory.initElements(driver, this);
		
	}
	
	public void LoginIntoApplication(String mobile,String password){
		
		this.mobile.clear();
		log.info("Clearing the input field ");
		this.mobile.sendKeys(mobile);
		log.info("Entered the input data"+mobile+" and object is "+mobile.toString());
		this.password.clear();
		log.info("Clearing the input field ");
		this.password.sendKeys(password);
		log.info("Entered the input data"+password+" and object is "+password.toString());
		login_btn.click();
		log.info("Clicked on Login button ");
	}
	
	public String getStoreNameAfterLogin(){
		log.info("Waiting for the page to load all the elements");
		Storename = StoreName.getText();
		log.info("Fetching the Store Name after login" +Storename.toString());
		return Storename ;
	}
	
}

