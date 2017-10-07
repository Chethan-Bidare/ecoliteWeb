package com.Ecolite_Web.Ecolite_Sales;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Ecolite_Web.TestBase.TestBase;
import com.Ecolite_Web.UI_Actions.CartPage;
import com.Ecolite_Web.UI_Actions.CheckOutPage;
import com.Ecolite_Web.UI_Actions.Dashboard;
import com.Ecolite_Web.UI_Actions.LoginPage;

public class TC_010_VerifyPaymentMode_Cash extends TestBase {

	public static Logger log = Logger.getLogger(TC_010_VerifyPaymentMode_Cash.class.getName());
	
	@BeforeTest
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void VerifyPaymentMode_Cash() throws InterruptedException{
		log.info(" ===== Started TC010 =====");
		LoginPage loginpage = new LoginPage(driver);
		loginpage.LoginIntoApplication(OR.getProperty("Mobile"), OR.getProperty("password"));
		Dashboard dashboard = new Dashboard();
		dashboard.StartSale("Chethan");
		CartPage cartpage = new CartPage();
		cartpage.Select_Single_Item();
		cartpage.Add_Qty("5");
		cartpage.Proceed();
		CheckOutPage checkoutpage = new CheckOutPage();
		checkoutpage.CustomerDetails("Chethan", "Bengaluru");
		checkoutpage.ProfessionalDetails("doctor");
		checkoutpage.HomeDelivery();
		checkoutpage.SelectCashPayment();
		checkoutpage.ConfirmSale();
		String PaymentMode = checkoutpage.getPaymentModeInCheckOutPage();
		try {
			Assert.assertEquals(PaymentMode, "Cash");
			log.info(" ===== TC010 Finished =====");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(" ===== TC010 Skipped =====");
		}
	}
	
	
}
