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

public class TC_008_VerifyHighestMRPisDisplayed extends TestBase{

	public static final Logger log = Logger.getLogger(TC_008_VerifyHighestMRPisDisplayed.class.getName());
	
	@BeforeClass
		public void Setup() throws IOException{
			init();
			log.info("Initialising Setup Config");
		}
		
		@Test
		public void VerifyHighestMRPisDisplayed() throws InterruptedException{
			log.info(" ===== TC_008_VerifyHighestMRPisDisplayed Started =====");
			LoginPage loginpage = new LoginPage(driver);
			loginpage.LoginIntoApplication(OR.getProperty("Mobile"), OR.getProperty("password"));
			Dashboard db = new Dashboard();
			db.StartSalesReturn("Chethan");
			CartPage cp = new CartPage();
			double DisplayedHighestMRP = cp.getMRPDisplayedforItems();
			cp.Select_Single_Item();
			double ExpectedHighestMRP = cp.getHighestMrpOfBatches();
			try {
				Assert.assertEquals(DisplayedHighestMRP, ExpectedHighestMRP);
				log.info(" ===== TC_008_VerifyHighestMRPisDisplayed Finished =====");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info(" ===== TC_008_VerifyHighestMRPisDisplayed Skipped =====");
			}
		}
}
