package com.Ecolite_Web.Ecolite_Accounts;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Ecolite_Web.TestBase.TestBase;
import com.Ecolite_Web.UI_Actions.CartPage;
import com.Ecolite_Web.UI_Actions.Dashboard;
import com.Ecolite_Web.UI_Actions.LoginPage;

public class test extends TestBase{
public static final Logger log = Logger.getLogger(test.class.getName());
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@DataProvider(name="StartPurchaseDetails")
	public String[][] getdata(){
		String[][] Testdata = ReadExcel("StartPurchase", "TestData.xlsx");
		return Testdata ;
	}
	
	@DataProvider(name="StartSaleDetails")
	public String[][] getSaledata(){
		String[][] Testdata1 = ReadExcel("StartSale", "TestData.xlsx");
		return Testdata1 ;
	}
	
	@Test(dataProvider = "StartSaleDetails")
	public void checkSales(String CustomerName,String ItemName,String Qty,String BatchNo,String Disc) throws InterruptedException{
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(OR.getProperty("Mobile"),OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.StartSale(CustomerName);
		CartPage cartpage = new CartPage();
		cartpage.Select_5_ItemsAndAddQty();
		cartpage.deleteItemfromCart();
		
	}
	
	/*@Test(dataProvider="StartPurchaseDetails")
	public void check(String SupplierName,String BillAmt,String BillDisc){
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(OR.getProperty("Mobile"),OR.getProperty("password"));
		Dashboard db = new Dashboard();
		db.StartPurchase(SupplierName, BillAmt, BillDisc);
		db.SignOut();
		
	}*/
	
}
