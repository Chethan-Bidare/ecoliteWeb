package com.Ecolite_Web.Ecolite_Login;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;

import org.testng.annotations.Test;

import com.Ecolite_Web.TestBase.TestBase;
import com.Ecolite_Web.UI_Actions.LoginPage;

public class TC_001_LoginTest_WithValidData extends TestBase{

	public static final Logger log = Logger.getLogger(TC_001_LoginTest_WithValidData.class.getName());
	
	public String StoreNameVerification="Chethan Stores";
	
	/*@DataProvider(name="LoginData")
	public String[][] getTestData(){
		String[][] TestData = ReadExcel("LoginTestData","TestData.xlsx");
		return TestData ;
	}*/
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void LoginTest() throws Exception{
		
			log.info("====== Starting Login Test =======");
			LoginPage loginpage = new LoginPage(driver);
			loginpage.LoginIntoApplication(OR.getProperty("Mobile"),OR.getProperty("password"));
			Assert.assertEquals(loginpage.getStoreNameAfterLogin(), StoreNameVerification);
			log.info("====== Login Test Finished =======");
			
		}
	
	
	@AfterClass
	public void closeBrowser(){
		CloseBrowser();
		
	}
	
	
}

