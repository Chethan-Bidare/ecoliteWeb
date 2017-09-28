package com.Ecolite_Web.Ecolite_Masters;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.Ecolite_Web.TestBase.TestBase;
import com.Ecolite_Web.UI_Actions.Dashboard;
import com.Ecolite_Web.UI_Actions.LoginPage;
import com.Ecolite_Web.UI_Actions.MastersPage;
import com.Ecolite_Web.UI_Actions.MyStorePage;

public class TC_001_AddNew_Customer extends TestBase{

	public static final Logger log = Logger.getLogger(TC_001_AddNew_Customer.class.getName());
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void AddNewCustomer_Test(){
		
		log.info("====== Starting AddNewCustomer_Test =======");
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(OR.getProperty("Mobile"),OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.SelectAnOptionFromHeader("my store");
		MyStorePage msp = new MyStorePage();
		msp.SelectFromMyStorePage("masters");
		MastersPage mp = new MastersPage(driver);
		mp.SelectMasterFromMasterPage("customer-master");
		
		try{
			String NewCustomerNameCreated = mp.AddNewCustomer();
			NewCustomerNameCreated = NewCustomerNameCreated.toUpperCase();
			String CustomerNameFound = mp.SearchCustomerName1();
			CustomerNameFound = CustomerNameFound.toUpperCase();
			Assert.assertEquals(NewCustomerNameCreated, CustomerNameFound);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@AfterClass
	public void closeBrowser(){
		CloseBrowser();
		
	}
}
