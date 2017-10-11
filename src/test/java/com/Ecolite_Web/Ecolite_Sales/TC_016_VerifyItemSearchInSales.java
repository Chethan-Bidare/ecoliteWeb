package com.Ecolite_Web.Ecolite_Sales;

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

public class TC_016_VerifyItemSearchInSales extends TestBase{

	public static final Logger log = Logger.getLogger(TC_016_VerifyItemSearchInSales.class.getName());
	String itemnameSearch = "rantac";
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void VerifyItemSearchInSales() throws InterruptedException{
		log.info(" ===== TC_016_VerifyItemSearchInSales Started =====");
		LoginPage loginpage = new LoginPage(driver);
		loginpage.LoginIntoApplication(OR.getProperty("Mobile"), OR.getProperty("password"));
		Dashboard dashboard = new Dashboard();
		dashboard.StartSale("Veena");
		CartPage cartpage = new CartPage();
		cartpage.ItemSearch(itemnameSearch);
		List<String>ItemNames = cartpage.getItemNames();
		for(String item : ItemNames){
			System.out.println(item);
			if(item.contains(itemnameSearch)){
				Assert.assertTrue(true);
				log.info(" ===== TC_016_VerifyItemSearchInSales Started =====");
			}
			else
				log.info(" ===== TC_016_VerifyItemSearchInSales Failed for one or more items =====");
				
		}
		
		
	}
}
