package com.Ecolite_Web.Ecolite_SalesReturn;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Ecolite_Web.TestBase.TestBase;
import com.Ecolite_Web.UI_Actions.CartPage;
import com.Ecolite_Web.UI_Actions.Dashboard;
import com.Ecolite_Web.UI_Actions.LoginPage;

public class TC_001_VerifySalesReturnStart extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC_001_VerifySalesReturnStart.class.getName());
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void VerifySalesReturnStart(){
		log.info("===== TC_001_VerifySalesReturnStart Started =====");
		LoginPage loginpage = new LoginPage(driver);
		loginpage.LoginIntoApplication(OR.getProperty("Mobile"), OR.getProperty("password"));
		Dashboard dashboard = new Dashboard();
		dashboard.StartSalesReturn("Veena");
		CartPage cartpage = new CartPage();
		try {
			String ActualHeaderTitle = cartpage.getHeaderTitle();
			String ExpectedHeaderTitle = "Sales Return" ;
			Assert.assertEquals(ActualHeaderTitle, ExpectedHeaderTitle);
			log.info("===== TC_001_VerifySalesReturnStart Finished =====");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("===== TC_001_VerifySalesReturnStart Skipped =====");
		}
	}

}
