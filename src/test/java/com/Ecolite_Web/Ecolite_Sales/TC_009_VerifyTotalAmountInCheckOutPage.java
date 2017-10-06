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

public class TC_009_VerifyTotalAmountInCheckOutPage extends TestBase {

	public static final Logger log = Logger.getLogger(TC_009_VerifyTotalAmountInCheckOutPage.class.getName());
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void VerifyTotalAmount() throws InterruptedException{
		log.info(" ===== Started TC009 =====");
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(OR.getProperty("Mobile"),OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.StartSale("Chethan");
		CartPage cp = new CartPage();
		cp.Select_5_ItemsAndAddQty();
		cp.Proceed();
		CheckOutPage checkout = new CheckOutPage();
		checkout.CustomerDetails("Chethan", "Bengaluru");
		checkout.ProfessionalDetails("doctor");
		checkout.HomeDelivery();
		checkout.ConfirmSale();
		double TotalAmount = checkout.AmountPaid();
		int ActualTotalAmount = (int) TotalAmount ;
		double disc = checkout.getDiscountValueInCheckOutPage();
		System.out.println("disc:"+disc);
		double InvoiceValue = checkout.getInvoiceValueInCheckOutPage();
		System.out.println("InvoiceValue:"+InvoiceValue);
		double TaxValue = checkout.getTaxValueInCheckOutPage();
		System.out.println("TaxValue:"+TaxValue);
		double TotalAmont = InvoiceValue + TaxValue - disc ;
		int ExpectedTotalAmount = (int) TotalAmont ;
		try {
			Assert.assertEquals(ActualTotalAmount, ExpectedTotalAmount);
			log.info(" ===== TC009 Finished =====");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(" ===== TC009 Skipped =====");
		}
	}
	
	@AfterClass
	public void closeBrowser(){
		CloseBrowser();
		
	}
	
}
