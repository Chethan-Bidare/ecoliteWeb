package com.Ecolite_Web.Ecolite_Accounts;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Ecolite_Web.TestBase.TestBase;
import com.Ecolite_Web.UI_Actions.Dashboard;
import com.Ecolite_Web.UI_Actions.LoginPage;
import com.Ecolite_Web.UI_Actions.MyStorePage;
import com.Ecolite_Web.UI_Actions.Supplier_Payment_Page;

public class TC_001_SupplierPayment extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC_001_SupplierPayment.class.getName());
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@DataProvider(name = "SupplierPaymentDetails")
	
	public String[][] TestData(){
		String[][] TestResult = ReadExcel("SupplierPayment","TestData.xlsx");
		
		
		return TestResult;
		
	}
	
	
	@Test(dataProvider="SupplierPaymentDetails")
	public void Check_SupplierPaymentAfterPurchase( String PaymentMode, String Mobile, String pwd,String SupplierName) throws InterruptedException{
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.LoginIntoApplication(Mobile,pwd);
		Dashboard dashboard = new Dashboard();
		dashboard.SelectAnOptionFromHeader("my Store");
		MyStorePage msp = new MyStorePage();
		msp.SelectFromMyStorePage("supplier-payment");
		Supplier_Payment_Page supplierPayment = new Supplier_Payment_Page();
		supplierPayment.SelectSupplierFromSearch(SupplierName);
		supplierPayment.SelectTransFromUnsettledVouchers();
		supplierPayment.SelectPaymentMethod(PaymentMode);
		supplierPayment.ConfirmPayment();
		
		Assert.assertEquals("Payment Success", supplierPayment.PaymentSuccessMsg());
		dashboard.SignOut();
		
		
	}
	
	@AfterClass
	public void closeBrowser(){
		CloseBrowser();
	}

}
