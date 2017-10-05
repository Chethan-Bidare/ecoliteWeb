package com.Ecolite_Web.Ecolite_Sales;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Ecolite_Web.TestBase.TestBase;
import com.Ecolite_Web.UI_Actions.CartPage;
import com.Ecolite_Web.UI_Actions.CheckOutPage;
import com.Ecolite_Web.UI_Actions.Dashboard;
import com.Ecolite_Web.UI_Actions.LoginPage;

public class TC_006_VerifyTotalAmtWithItemPrice extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC_006_VerifyTotalAmtWithItemPrice.class.getName());
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void VerifyTotalAmtinCheckOutPage() throws InterruptedException {
		log.info("===== Started TC006 =====");
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(OR.getProperty("Mobile"), OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.StartSale("Chethan");
		CartPage cp = new CartPage();
		String totalprice = cp.Select_5_ItemsAndAddQty().get(5);
		System.out.println(totalprice);
		float ItemPrice = Float.parseFloat(totalprice);
		int TotalItemPrice = (int) ItemPrice ;
		System.out.println(TotalItemPrice);
		//cp.Add_Qty("5");
		cp.Proceed();
		//int TotalItemPrice = ItemPrice * 5 ;
		CheckOutPage checkoutpage = new CheckOutPage();
		checkoutpage.ConfirmSale();
		int AmountPaid = (int) checkoutpage.AmountPaid();
		try {
			Assert.assertEquals(AmountPaid, TotalItemPrice);
			log.info("===== TC006 Finished=====");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("===== TC006 Skipped =====");
		}
	}
	
	
	@AfterClass
	public void closeBrowser(){
		CloseBrowser();
		
	}

}
