package com.Ecolite_Web.Ecolite_Sales;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Ecolite_Web.TestBase.TestBase;
import com.Ecolite_Web.UI_Actions.CartPage;
import com.Ecolite_Web.UI_Actions.Dashboard;
import com.Ecolite_Web.UI_Actions.LoginPage;

public class TC_002_VerifyCustomerNameInCartPage extends TestBase {

	public static final Logger log = Logger.getLogger(TC_002_VerifyCustomerNameInCartPage.class.getName());
	
	@BeforeClass
	public void Setup() throws IOException{
		init();
		log.info("Initialising Setup Config");
	}
	
	@DataProvider(name="TC_002_VerifyCustomerName")
	public String[][] getdata(){
		String[][] testresult = ReadExcel("TC_002_VerifyCustomerName", "TestData.xlsx");
		return testresult ;
	}
	
	@Test(dataProvider = "TC_002_VerifyCustomerName")
	public void VerifyCustomerNameInCartPag(String Mobile,String password,String CustomerName){
		log.info("===== Starting Test VerifyCustomerNameInCartPage =======");
		
		LoginPage lp = new LoginPage(driver);
		lp.LoginIntoApplication(Mobile, password);
		Dashboard db = new Dashboard();
		db.StartSale(CustomerName);
		CartPage cp = new CartPage();
		String customerName = cp.getCustNameFromCartPage();
		try {
			String CustomerNameEntered = CustomerName.toUpperCase();
			String CustomerNameFoundInCartPage = customerName.toUpperCase();
			Assert.assertEquals(CustomerNameFoundInCartPage, CustomerNameEntered);
			log.info("===== Test VerifyCustomerNameInCartPage Finished =======");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
