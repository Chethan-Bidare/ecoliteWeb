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

public class TC_005_VerifyStockAfterSaleInSalesReturn extends TestBase{

public static final Logger log = Logger.getLogger(TC_005_VerifyStockAfterSaleInSalesReturn.class.getName());
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	@Test
	public void VerifyStockAfterSale() throws InterruptedException{
		log.info("===== TC_005_VerifyStockAfterSaleInSalesReturn Started =======");
		
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(OR.getProperty("Mobile"), OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.StartSale("Veena");
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
		int AvailableStockAfterSalesReturn = cp.getStock();
		int IncreasedStockAfterSalesReturn = AvailableStockAfterSalesReturn - 5 ;
		System.out.println("Stock before Sales : "+StockBeforeSale);
		System.out.println("Stock After Sales : "+AvailableStockAfterSalesReturn);
		Assert.assertEquals(StockBeforeSale, IncreasedStockAfterSalesReturn);
		
		log.info("===== TC_005_VerifyStockAfterSaleInSalesReturn Finished =======");
		
		
	}
	
}
