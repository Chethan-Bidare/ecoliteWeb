package com.Ecolite_Web.Ecolite_SalesReturn;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Ecolite_Web.TestBase.TestBase;
import com.Ecolite_Web.UI_Actions.CartPage;
import com.Ecolite_Web.UI_Actions.Dashboard;
import com.Ecolite_Web.UI_Actions.LoginPage;

public class TC_014_VerifyAddNewBatch_SalesReturn extends TestBase{
	
	public static Logger log = Logger.getLogger(TC_014_VerifyAddNewBatch_SalesReturn.class.getName());

	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void VerifyAddNewBatch() throws InterruptedException{
		log.info(" ===== TC_014_VerifyAddNewBatch_SalesReturn Started =====");
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(OR.getProperty("Mobile"),OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.StartSalesReturn("Veena");
		CartPage cp = new CartPage();
		cp.Select_Item_By_Name("Arvindasav Liquid 200ml");
		String CreatedBatchName = cp.AddNewBatch("2018","May","20");
		RefreshPage();
		cp.Select_Item_By_Name("Arvindasav Liquid 200ml");
		Thread.sleep(4000);
		List<String> BatchNames = cp.getBatchNamesFromBatchList();
		for(String batches : BatchNames){
			System.out.println(batches);
			if(batches.equalsIgnoreCase(CreatedBatchName)){
				
				Assert.assertEquals(CreatedBatchName, batches);
				log.info(" ===== TC_014_VerifyAddNewBatch_SalesReturn Finished =====");
			}
		}
	}
}
