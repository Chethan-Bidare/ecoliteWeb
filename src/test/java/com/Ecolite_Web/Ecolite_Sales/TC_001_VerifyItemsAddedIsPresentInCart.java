package com.Ecolite_Web.Ecolite_Sales;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Ecolite_Web.TestBase.TestBase;
import com.Ecolite_Web.UI_Actions.CartPage;
import com.Ecolite_Web.UI_Actions.Dashboard;
import com.Ecolite_Web.UI_Actions.LoginPage;

public class TC_001_VerifyItemsAddedIsPresentInCart extends TestBase{

	public static final Logger log = Logger.getLogger(TC_001_VerifyItemsAddedIsPresentInCart.class.getName());
	
	
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@DataProvider(name="Details")
	public String[][] getdata(){
		String[][] result = ReadExcel("StartSale", "TestData.xlsx");
		return result ;
	}
	
	@Test
	public void CheckItemsAddedToCart() throws InterruptedException{
		log.info("===== TC_001_VerifyItemsAddedIsPresentInCart Started =====");
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(OR.getProperty("Mobile"), OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.StartSale("Chethan");
		CartPage cp = new CartPage();
		ArrayList<String> itemNamesAddedToCart = cp.Select_5_ItemsAndAddQty();
		ArrayList<String> itemNamesFoundInCart = cp.getItemNamesFromCartList();
		
		
		for(int i=0; i<itemNamesAddedToCart.size();i++){
			System.out.println("Items added to cart "+itemNamesAddedToCart.get(i).toString());
			System.out.println("Items found in cart "+itemNamesFoundInCart.get(i).toString());
		Assert.assertEquals(itemNamesAddedToCart.get(i).toString(), itemNamesFoundInCart.get(i).toString());
		
		}
		log.info("===== TC_001_VerifyItemsAddedIsPresentInCart Finished =====");
	}
	
	@AfterClass
	public void closeBrowser(){
		CloseBrowser();
		
	}
	
}
