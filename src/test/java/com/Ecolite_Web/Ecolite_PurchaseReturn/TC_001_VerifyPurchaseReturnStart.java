package com.Ecolite_Web.Ecolite_PurchaseReturn;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Ecolite_Web.TestBase.TestBase;
import com.Ecolite_Web.UI_Actions.CartPage;
import com.Ecolite_Web.UI_Actions.Dashboard;
import com.Ecolite_Web.UI_Actions.LoginPage;

public class TC_001_VerifyPurchaseReturnStart extends TestBase {

public static final Logger log = Logger.getLogger(TC_001_VerifyPurchaseReturnStart.class.getName());
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void VerifyPurchaseReturnStart() throws InterruptedException{
		log.info("===== TC_001_VerifyPurchaseReturnStart Started =====");
		LoginPage loginpage = new LoginPage(driver);
		loginpage.LoginIntoApplication(OR.getProperty("Mobile"), OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.StartPurchaseReturn("Chethan pharmacy", "500", "0");
		CartPage cartpage = new CartPage();
		try {
			String ActualHeaderTitle = cartpage.getHeaderTitle();
			String ExpectedHeaderTitle = "Sales" ;
			Assert.assertEquals(ActualHeaderTitle, ExpectedHeaderTitle);
			log.info("===== TC_001_VerifyPurchaseReturnStart Finished =====");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("===== TC_001_VerifyPurchaseReturnStart Skipped =====");
		}
	}
}
