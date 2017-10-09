package com.Ecolite_Web.Ecolite_SalesReturn;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Ecolite_Web.TestBase.TestBase;
import com.Ecolite_Web.UI_Actions.CartPage;
import com.Ecolite_Web.UI_Actions.Dashboard;
import com.Ecolite_Web.UI_Actions.LoginPage;

public class TC_013_VerifyStockDisplayedIsCorrect_SalesReturn extends TestBase{
	
public static Logger log = Logger.getLogger(TC_013_VerifyStockDisplayedIsCorrect_SalesReturn.class.getName());
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@DataProvider(name="TestdataforStockCheck")
	public String[][] getResult(){
		String[][] Testdata = ReadExcel("ItemNames", "TestData.xlsx");
		return Testdata ;
	}
	
	@Test(dataProvider="TestdataforStockCheck")
	public void VerifyStockDisplayedIsCorrect(String itemname,String ItemNo) throws InterruptedException{
		log.info(" ===== TC_013_VerifyStockDisplayedIsCorrect_SalesReturn Started =====");
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(OR.getProperty("Mobile"),OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.StartSalesReturn("Veena");
		CartPage cp = new CartPage();
		int DisplayedStockofAnItem = cp.getDisplayedStockforAnItem(ItemNo);
		cp.Select_Item_By_Name(itemname);
		int StockDisplayedInBatchList = cp.getCountOfAllBatchesStockForAnItem();
		try {
			Assert.assertEquals( DisplayedStockofAnItem, StockDisplayedInBatchList);
			log.info(" ===== TC_013_VerifyStockDisplayedIsCorrect_SalesReturn Finished =====");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(" ===== TC_013_VerifyStockDisplayedIsCorrect_SalesReturn Skipped =====");
		}
		
	}

}
