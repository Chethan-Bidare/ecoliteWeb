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

public class TC_015_VerifySalesReturnAfterAddNewBatch extends TestBase{

	public static final Logger log = Logger.getLogger(TC_015_VerifySalesReturnAfterAddNewBatch.class.getName());
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void VerifySalesReturnAfterAddNewBatch() throws InterruptedException{
		log.info(" ===== TC_015_VerifySalesReturnAfterAddNewBatch Started =====");
		LoginPage loginpage = new LoginPage(driver);
		loginpage.LoginIntoApplication(OR.getProperty("Mobile"), OR.getProperty("password"));
		Dashboard dashboard = new Dashboard();
		dashboard.StartSalesReturn("Veena");
		CartPage cartpage = new CartPage();
		cartpage.Select_Item_By_Name("Arvindasav Liquid 200ml");
		String CreatedBatch = cartpage.AddNewBatch("2018", "May","18");
		cartpage.Add_Qty("5");
		cartpage.ClickOnCartButton();
		String SelectedBatch = cartpage.getBatchfromCartlist();
		cartpage.Proceed();
		CheckOutPage checkout = new CheckOutPage();
		checkout.CustomerDetails("Veena", "Bengaluru");
		checkout.ProfessionalDetails("mnb");
		checkout.TurnOffHomeDelivery();
		checkout.ConfirmSale();
		try {
			Assert.assertEquals(SelectedBatch, CreatedBatch);
			Assert.assertEquals(checkout.getSuccess(), true);
			log.info(" ===== TC_015_VerifySalesReturnAfterAddNewBatch Finished =====");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(" ===== TC_015_VerifySalesReturnAfterAddNewBatch Skipped =====");
		}
		
	}
}
