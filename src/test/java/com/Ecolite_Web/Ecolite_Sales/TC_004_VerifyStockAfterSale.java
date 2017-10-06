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

public class TC_004_VerifyStockAfterSale extends TestBase {
	
public static final Logger log = Logger.getLogger(TC_002_VerifyCustomerNameInCartPage.class.getName());
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	@Test
	public void VerifyStockAfterSale() throws InterruptedException{
log.info("===== Starting Test VerifyStockAfterSale =======");
		
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(OR.getProperty("Mobile"), OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.StartSale("Chethan");
		CartPage cp = new CartPage();
		int StockBeforeSale = cp.getStock();
		cp.Select_Single_Item();
		cp.Add_Qty("5");
		cp.Proceed();
		CheckOutPage checkout = new CheckOutPage();
		checkout.CustomerDetails("Chethan", "Bengaluru");
		checkout.ProfessionalDetails("doctor");
		checkout.HomeDelivery();
		checkout.ConfirmSale();
		checkout.NewSaleInSuccessPage();
		int AvailableStockAfterSale = cp.getStock();
		int ReducedStockAfterSale = AvailableStockAfterSale + 5 ;
		System.out.println("Stock before Sales : "+StockBeforeSale);
		System.out.println("Stock After Sales : "+AvailableStockAfterSale);
		Assert.assertEquals(StockBeforeSale, ReducedStockAfterSale);
		
		log.info("===== Finished Test VerifyStockAfterSale =======");
		
		
	}
	
	
	
	@AfterClass
	public void closeBrowser(){
		CloseBrowser();
		
	}

}
