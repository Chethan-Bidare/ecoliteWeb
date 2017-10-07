package com.Ecolite_Web.Ecolite_Sales;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Ecolite_Web.TestBase.TestBase;
import com.Ecolite_Web.UI_Actions.CartPage;
import com.Ecolite_Web.UI_Actions.Dashboard;
import com.Ecolite_Web.UI_Actions.LoginPage;

public class TC_013_VerifyAddNewBatch extends TestBase{
	
	public static Logger log = Logger.getLogger(TC_013_VerifyAddNewBatch.class.getName());

	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void VerifyAddNewBatch() throws InterruptedException{
		log.info(" ===== Started TC012 =====");
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(OR.getProperty("Mobile"),OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.StartSale("veena");
		CartPage cp = new CartPage();
		cp.Select_Item_By_Name("Arvindasav Liquid 200ml");
		String CreatedBatchName = cp.AddNewBatch("2019","May","18");
	}
}
