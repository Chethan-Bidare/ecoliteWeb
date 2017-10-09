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

public class TC_011_VerifyPaymentMode_Card extends TestBase{

public static Logger log = Logger.getLogger(TC_011_VerifyPaymentMode_Card.class.getName());
	
@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void VerifyPaymentMode_Card() throws InterruptedException{
		log.info(" ===== TC_011_VerifyPaymentMode_Card Started =====");
		LoginPage loginpage = new LoginPage(driver);
		loginpage.LoginIntoApplication(OR.getProperty("Mobile"), OR.getProperty("password"));
		Dashboard dashboard = new Dashboard();
		dashboard.StartSale("Chethan");
		CartPage cartpage = new CartPage();
		cartpage.Select_Single_Item();
		cartpage.Add_Qty("5");
		cartpage.Proceed();
		CheckOutPage checkoutpage = new CheckOutPage();
		checkoutpage.CustomerDetails("Chethan","Bengaluru");
		checkoutpage.ProfessionalDetails("doctor");
		checkoutpage.HomeDelivery();
		checkoutpage.SelectCardPayment();
		checkoutpage.ConfirmSale();
		String PaymentMode = checkoutpage.getPaymentModeInCheckOutPage();
		try {
			Assert.assertEquals(PaymentMode, "Card");
			log.info(" ===== TC_011_VerifyPaymentMode_Card Finished =====");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(" ===== TC_011_VerifyPaymentMode_Card Skipped =====");
		}
	}
	
	
}
