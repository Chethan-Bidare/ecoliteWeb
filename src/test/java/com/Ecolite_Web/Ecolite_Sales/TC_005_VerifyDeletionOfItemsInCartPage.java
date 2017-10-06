package com.Ecolite_Web.Ecolite_Sales;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.Ecolite_Web.TestBase.TestBase;
import com.Ecolite_Web.UI_Actions.CartPage;
import com.Ecolite_Web.UI_Actions.Dashboard;
import com.Ecolite_Web.UI_Actions.LoginPage;

public class TC_005_VerifyDeletionOfItemsInCartPage extends TestBase {
	
	public static final Logger log = Logger.getLogger(TC_005_VerifyDeletionOfItemsInCartPage.class.getName());
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@Test
	public void VerifyDeleteOptionInCart() throws InterruptedException{
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(OR.getProperty("Mobile"), OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.StartSale("Chethan");
		CartPage cp = new CartPage();
		cp.Select_Single_Item();
		cp.Add_Qty("5");
		cp.ClickOnCartButton();
		int ItemCountAfterAddingtoCart = cp.getItemCountFromCart();
		cp.deleteItemfromCart();
		int ItemCountAfterDeletingCart = cp.getItemCountFromCart();
		Assert.assertEquals(ItemCountAfterDeletingCart, 0);
		
	}
	

	@AfterClass
	public void closeBrowser(){
		CloseBrowser();
		
	}
	
}
