package com.Ecolite_Web.Ecolite_SalesReturn;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Ecolite_Web.TestBase.TestBase;
import com.Ecolite_Web.UI_Actions.CartPage;
import com.Ecolite_Web.UI_Actions.CheckOutPage;
import com.Ecolite_Web.UI_Actions.Dashboard;
import com.Ecolite_Web.UI_Actions.LoginPage;

public class TC_004_VerifyCustomerNameInSalesReturnCheckOut extends TestBase{

public static final Logger log = Logger.getLogger(TC_004_VerifyCustomerNameInSalesReturnCheckOut.class.getName());
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@DataProvider(name="TC_004_VerifyCustomerName")
	public String[][] getdata(){
		String[][] testresult = ReadExcel("TC_002_VerifyCustomerName", "TestData.xlsx");
		return testresult ;
	}
	
	@Test(dataProvider = "TC_004_VerifyCustomerName")
	public void VerifyCustomerNameInCartPag(String Mobile,String password,String CustomerName) throws InterruptedException{
		log.info("===== TC_004_VerifyCustomerNameInSalesReturnCheckOut Started =======");
		
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(Mobile, password);
		Dashboard db = new Dashboard();
		db.StartSalesReturn(CustomerName);
		CartPage cp = new CartPage();
		cp.Select_Single_Item();
		cp.Add_Qty("5");
		cp.Proceed();
		CheckOutPage checkoutpage = new CheckOutPage();
		String customerName = checkoutpage.getCustomerName();
		System.out.println("Cust name :"+customerName);
		try {
			String CustomerNameEntered = CustomerName.toUpperCase();
			String CustomerNameFound = customerName.toUpperCase();
			Assert.assertEquals(CustomerNameFound, CustomerNameEntered);
			log.info("===== TC_004_VerifyCustomerNameInSalesReturnCheckOut Finished =======");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("===== TC_004_VerifyCustomerNameInSalesReturnCheckOut Skipped =======");
		}
		
	}

}
