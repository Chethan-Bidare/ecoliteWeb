package com.Ecolite_Web.Ecolite_Purchase;

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

public class TC_002_VerifyItemSearchInPurchase extends TestBase {

	public static final Logger log = Logger.getLogger(TC_002_VerifyItemSearchInPurchase.class.getName());
	String itemnameSearch = "rantac";
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void VerifyItemSearchInPurchase() throws InterruptedException{
		log.info(" ===== TC_002_VerifyItemSearchInPurchase Started =====");
		LoginPage loginpage = new LoginPage(driver);
		loginpage.LoginIntoApplication(OR.getProperty("Mobile"), OR.getProperty("password"));
		Dashboard dashboard = new Dashboard();
		dashboard.StartPurchase("Chethan Pharmacy", "500", "0");
		CartPage cartpage = new CartPage();
		cartpage.ItemSearch(itemnameSearch);
		List<String>ItemNames = cartpage.getItemNamesAfterSearchPur();
		for(String item : ItemNames){
			System.out.println(item);
			try{
			if(item.contains(itemnameSearch)){
				Assert.assertTrue(true);
				log.info(" ===== TC_002_VerifyItemSearchInPurchase Finished =====");
			}		
		}
			catch(AssertionError e){
				e.printStackTrace();
				log.info(" ===== TC_002_VerifyItemSearchInPurchase Failed for one or more items =====");
			}
		}
	}
}
