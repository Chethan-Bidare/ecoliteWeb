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


public class TC_002_AddNew_Supplier extends TestBase {

	public static final Logger log = Logger.getLogger(TC_001_AddNew_Customer.class.getName());
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void AddNewSupplier_Test() throws InterruptedException{
		
		log.info("====== Starting AddNew Supplier_Test =======");
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(OR.getProperty("Mobile"),OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.SelectAnOptionFromHeader("my store");
		MyStorePage msp = new MyStorePage();
		msp.SelectFromMyStorePage("masters");
		MastersPage mp = new MastersPage(driver);
		mp.SelectMasterFromMasterPage("supplier-master");
		
			String NewSupplierNameCreated = mp.AddNewSupplier();
			NewSupplierNameCreated = NewSupplierNameCreated.toUpperCase();
			String SupplierNameFound = mp.SearchSupplierName();
			SupplierNameFound = SupplierNameFound.toUpperCase();
			try{
			Assert.assertEquals(NewSupplierNameCreated, SupplierNameFound);
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

