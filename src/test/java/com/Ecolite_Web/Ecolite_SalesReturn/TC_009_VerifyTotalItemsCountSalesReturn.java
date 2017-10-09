package com.Ecolite_Web.Ecolite_SalesReturn;

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

public class TC_009_VerifyTotalItemsCountSalesReturn extends TestBase{

public static final Logger log = Logger.getLogger(TC_009_VerifyTotalItemsCountSalesReturn.class.getName());
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void VerifyItemCount() throws InterruptedException{
		log.info(" ===== TC_009_VerifyTotalItemsCountSalesReturn Started =====");
		LoginPage loginpage = new LoginPage(driver);
		loginpage.LoginIntoApplication(OR.getProperty("Mobile"), OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.StartSalesReturn("Veena ");
		CartPage cp = new CartPage();
		cp.Select_5_ItemsAndAddQty();
		Thread.sleep(3000);
		int ItemCountinCartPage = cp.getItemCountFromCart();
		cp.Proceed();
		CheckOutPage checkout = new CheckOutPage();
		checkout.CustomerDetails("Chethan", "Bengaluru");
		checkout.ProfessionalDetails("doctor");
		checkout.HomeDelivery();
		checkout.ConfirmSale();
		int ItemCountinCheckOutPage = checkout.getItemCountInCheckOutPage();
		try {
			Assert.assertEquals(ItemCountinCheckOutPage, ItemCountinCartPage);
			log.info(" ===== TC_009_VerifyTotalItemsCountSalesReturn Finished =====");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(" ===== TC_009_VerifyTotalItemsCountSalesReturn Skipped =====");
		}
	}
}
