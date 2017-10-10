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

public class TC_010_VerifyTotalAmountInSalesReturnCheckOut extends TestBase {

public static final Logger log = Logger.getLogger(TC_010_VerifyTotalAmountInSalesReturnCheckOut.class.getName());
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void VerifyTotalAmount() throws InterruptedException{
		log.info(" ===== TC_010_VerifyTotalAmountInSalesReturnCheckOut Started =====");
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(OR.getProperty("Mobile"),OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.StartSalesReturn("Veena");
		CartPage cp = new CartPage();
		cp.Select_5_ItemsAndAddQty();
		cp.Proceed();
		CheckOutPage checkout = new CheckOutPage();
		checkout.CustomerDetails("Chethan", "Bengaluru");
		checkout.ProfessionalDetails("doctor");
		checkout.TurnOffHomeDelivery();
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
			log.info(" ===== TC_010_VerifyTotalAmountInSalesReturnCheckOut Finished =====");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(" ===== TC_010_VerifyTotalAmountInSalesReturnCheckOut Skipped =====");
		}
	}

}
