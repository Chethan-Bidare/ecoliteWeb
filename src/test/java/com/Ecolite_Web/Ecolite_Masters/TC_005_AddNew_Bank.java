package com.Ecolite_Web.Ecolite_Masters;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Ecolite_Web.TestBase.TestBase;
import com.Ecolite_Web.UI_Actions.Dashboard;
import com.Ecolite_Web.UI_Actions.LoginPage;
import com.Ecolite_Web.UI_Actions.MastersPage;
import com.Ecolite_Web.UI_Actions.MyStorePage;

public class TC_005_AddNew_Bank extends TestBase {

	public static final Logger log = Logger.getLogger(TC_001_AddNew_Customer.class.getName());
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void AddNewBank_Test() throws InterruptedException{
		
		log.info("====== Starting AddNew Account Name Test =======");
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(OR.getProperty("Mobile"),OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.SelectAnOptionFromHeader("my store");
		MyStorePage msp = new MyStorePage();
		msp.SelectFromMyStorePage("masters");
		MastersPage mp = new MastersPage(driver);
		mp.SelectMasterFromMasterPage("bank-master");
		
			String NewBankNameCreated = mp.AddNewBankMaster().toString();
			NewBankNameCreated = NewBankNameCreated.toUpperCase();
			String BankNameFound = mp.SearchBankName().toString();
			BankNameFound = BankNameFound.toUpperCase();
			
			try{
			Assert.assertEquals(BankNameFound,NewBankNameCreated);
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
