package com.Ecolite_Web.Ecolite_Sales;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
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
	public void VerifyTotalAmtinCheckOutPage() throws InterruptedException{
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(OR.getProperty("Mobile"), OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.StartSale("Chethan");
		CartPage cp = new CartPage();
		cp.Select_Single_Item();
		float ItemPrice = cp.getItemPriceFromSelectedBatch();
		System.out.println(ItemPrice);
		cp.Add_Qty("5");
		cp.Proceed();
		float TotalItemPrice = ItemPrice * 5 ;
		CheckOutPage checkoutpage = new CheckOutPage();
		checkoutpage.ConfirmSale();
		float AmountPaid = checkoutpage.AmountPaid();
		Assert.assertEquals(AmountPaid, TotalItemPrice);
	}

}
