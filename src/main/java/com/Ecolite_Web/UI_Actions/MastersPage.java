package com.Ecolite_Web.UI_Actions;


import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.Ecolite_Web.TestBase.TestBase;


public class MastersPage extends TestBase{

public static final Logger log = Logger.getLogger(MastersPage.class.getName());
	
	public String CustName =null ;
	public String SupplierName = null ;
	public String ItemName = null ;
	public String AccName= null ;
	public String BankName = null ;
	
	
	public MastersPage(WebDriver driver){
		
		PageFactory.initElements(driver, this);
	}
	

	
	@FindBy(id="add_newcustmr")
	WebElement  AddNewCustomer;
	
	@FindBy(name="cust_name")
	WebElement  CustomerName;
	
	@FindBy(name="door_no")
	WebElement  DoorNo;
	
	@FindBy(name="street")
	WebElement  street;
	
	@FindBy(name="loction")
	WebElement  location;
	
	@FindBy(name="city")
	WebElement  city;
	
	@FindBy(name="pin_code")
	WebElement  pincode;
	
	@FindBy(name="phnum1")
	WebElement  phnum1;
	
	@FindBy(id="phnum2")
	WebElement  phnum2;
	
	@FindBy(name="mob_num")
	WebElement  mobnum;
	
	@FindBy(name="email_id")
	WebElement  emailid;
	
	@FindBy(xpath="//*[@class='form-control gst_type']")
	WebElement GSTType ;
	
	@FindBy(xpath="//*[@class='btn waves-effect waves-light save_btn']")
	WebElement Save ;
	
	@FindBy(id="item-search")
	WebElement CustomerSearch ;
	
	
	@FindBy(id="addnew_supp")
	WebElement AddNewSupplierBtn ;
	
	@FindBy(id="licens_no1")
	WebElement licens_no1 ;
	
	@FindBy(id="licens_no2")
	WebElement licens_no2 ;
	
	@FindBy(id="item_name")
	WebElement item_name ;
	
	@FindBy(id="addNewBut")
	WebElement AddNewItemBtn ;
	
	@FindBy(id="pack_name")
	WebElement pack_name ;
	
	@FindBy(id="qty_pack")
	WebElement qty_pack ;
	
	@FindBy(id="manuf_name")
	WebElement manuf_name ;
	
	@FindBy(id="stockQty")
	WebElement stockQty;
	
	@FindBy(id="nextBtn")
	WebElement nextBtn ;
	
	@FindBy(id="hsn_code")
	WebElement hsn_code ;
	
	@FindBy(id="gstType")
	WebElement ItemgstType ;
	
	@FindBy(id="btn_next")
	WebElement btn_next ;
	
	@FindBy(id="min_qty")
	WebElement min_qty ;
	
	@FindBy(id="max_qty")
	WebElement max_qty ;
	
	@FindBy(id="updateBtn")
	WebElement ItemSaveBtn ;
	
	@FindBy(id="add_btn")
	WebElement TaxAddbtn ;
	
	
	public void SelectMasterFromMasterPage(String MasterName){
		
		driver.findElement(By.xpath("//a[@href='/ecolite/"+MasterName+"/"+MasterName+"']")).click();
	}
	
	/*  START OF CUSTOMER MASTER */	
	
	/* Add New Customer in Customer Master */
	
	public String AddNewCustomer() throws InterruptedException{
		JavascriptExecutor jse = (JavascriptExecutor) driver ;
		jse.executeScript("window.scrollBy(0,550)","");
		AddNewCustomer.click();
		jse.executeScript("window.scrollBy(0,-550)","");
		CustName = generateRandomData(6);
		System.out.println("Customer name after save"+CustName);
		CustomerName.sendKeys(CustName);
		log.info("Customer name entered and the object is:"+CustomerName.toString());
		DoorNo.sendKeys(generateRandomNumber(3));
		log.info("Door No entered and the object is:"+DoorNo.toString());
		street.sendKeys(generateRandomData(5)+" Street");
		log.info("Door No entered and the object is:"+DoorNo.toString());
		location.sendKeys(generateRandomData(8));
		log.info("street entered and the object is:"+street.toString());
		city.sendKeys("Bangalore");
		log.info("city entered and the object is:"+city.toString());
		pincode.sendKeys("560027");
		log.info("pincode entered and the object is:"+pincode.toString());
		phnum1.sendKeys(generateRandomNumber(8));
		log.info("phnum1 entered and the object is:"+phnum1.toString());
		phnum2.sendKeys(generateRandomNumber(8));
		log.info("phnum2 entered and the object is:"+phnum2.toString());
		mobnum.sendKeys(generateRandomNumber(10));
		log.info("mob num entered and the object is:"+mobnum.toString());
		emailid.sendKeys(generateEmailID(15));
		log.info("emailid entered and the object is:"+emailid.toString());
		jse.executeScript("window.scrollBy(0,550)","");
		Select select = new Select(GSTType);
		select.selectByVisibleText("Unregistered");
		log.info("GST type selected as Unregistered and the object is:"+emailid.toString());
		Save.click();
		log.info("Clicked on Save button and the object is:"+Save.toString());
		//Thread.sleep(8000);
		log.info("Refreshing page after saving Customer ");
		WaitforElementsToLoad();
		
		return CustName ;
		
	}
	
	/* Search Customer name[After saving new Customer] from the Customer list displayed in LHS grid*/
	public String SearchCustomerName(String CustName) throws InterruptedException{
		
		String CustNameCheck = null ;
		try {
			JavascriptExecutor jse1 = (JavascriptExecutor) driver ;
			jse1.executeScript("window.scrollBy(0,-550)","");
			List<WebElement> CheckCust = driver.findElements(By.xpath(".//*[@class='card-title']"));
			
			for(WebElement we : CheckCust){
				CustNameCheck = we.getText().toString();
				System.out.println("Cust names :"+CustNameCheck);
				
				if(CustName.equalsIgnoreCase(CustNameCheck)){
					break ;
				}
			}
			System.out.println(CustNameCheck);
			return CustNameCheck ;
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return CustNameCheck;
	}
	
	/* Search Customer name[After saving new Customer] from the Customer Search and selecting the customer*/
	public String SearchCustomerName1() throws InterruptedException{
		WaitforElementsToLoad();
		CustomerSearch.sendKeys(CustName);
		log.info("Searching the Customer name from Customer Search"+CustName.toString());
		Thread.sleep(3000);
		String CustNameCheck = SelectOptionfromAutoCompleteSearch(CustName);
		System.out.println(CustNameCheck);
		if(CustNameCheck == null){
		log.info("Selecting the Customer name from Customer search");
		Thread.sleep(3000);
		 CustNameCheck = CustomerName.getAttribute("value").toString();
		 System.out.println("cust = null so " +CustNameCheck);
		log.info("Fetching Customer Name to verify");
		}
		return CustNameCheck ;
		
	}
	
	/*  END OF CUSTOMER MASTER */
	
	/* START OF SUPPLIER MASTER */
	public String AddNewSupplier() throws InterruptedException{
		JavascriptExecutor jse = (JavascriptExecutor) driver ;
		jse.executeScript("window.scrollBy(0,550)","");
		AddNewSupplierBtn.click();
		jse.executeScript("window.scrollBy(0,-550)","");
		SupplierName = generateRandomData(6);
		System.out.println("Supplier name after save "+SupplierName);
		CustomerName.sendKeys(SupplierName);
		log.info("Supplier name entered and the object is:"+CustomerName.toString());
		DoorNo.sendKeys(generateRandomNumber(3));
		log.info("Door No entered and the object is:"+DoorNo.toString());
		street.sendKeys(generateRandomData(5)+" Street");
		log.info("Door No entered and the object is:"+DoorNo.toString());
		location.sendKeys(generateRandomData(8));
		log.info("street entered and the object is:"+street.toString());
		city.sendKeys("Bangalore");
		log.info("city entered and the object is:"+city.toString());
		pincode.sendKeys("560027");
		log.info("pincode entered and the object is:"+pincode.toString());
		phnum1.sendKeys(generateRandomNumber(8));
		log.info("phnum1 entered and the object is:"+phnum1.toString());
		phnum2.sendKeys(generateRandomNumber(8));
		log.info("phnum2 entered and the object is:"+phnum2.toString());
		mobnum.sendKeys(generateRandomNumber(10));
		log.info("mob num entered and the object is:"+mobnum.toString());
		emailid.sendKeys(generateEmailID(15));
		log.info("emailid entered and the object is:"+emailid.toString());
		jse.executeScript("window.scrollBy(0,550)","");
		licens_no1.sendKeys(generateRandomNumber(10));
		licens_no2.sendKeys(generateRandomNumber(10));
		Select select = new Select(GSTType);
		select.selectByVisibleText("Unregistered");
		log.info("GST type selected as Unregistered and the object is:"+emailid.toString());
		Save.click();
		log.info("Clicked on Save button and the object is:"+Save.toString());
		//Thread.sleep(8000);
		WaitforElementsToLoad();
		
		return SupplierName ;
		
	}

	public String SearchSupplierName() throws InterruptedException{
		WaitforElementsToLoad();
		CustomerSearch.sendKeys(SupplierName);
		log.info("Searching the Supplier name from Supplier Search"+SupplierName.toString());
		Thread.sleep(3000);
		String SuppNameCheck = SelectOptionfromAutoCompleteSearch(SupplierName);
		System.out.println(SuppNameCheck);
		if(SuppNameCheck == null){
		log.info("Selecting the Supplier name from Customer search");
		Thread.sleep(3000);
		SuppNameCheck = CustomerName.getAttribute("value").toString();
		 System.out.println("cust = null so " +SuppNameCheck);
		log.info("Fetching Supplier Name to verify");
		}
		return SuppNameCheck ;
		
	}
	/* END OF SUPPLIER MASTER */
	
	/* START OF ITEM MASTER */
	public String AddNewItem() throws InterruptedException{
		
		
		JavascriptExecutor jse = (JavascriptExecutor) driver ;
		jse.executeScript("window.scrollBy(0,650)","");
		AddNewItemBtn.click();
		jse.executeScript("window.scrollBy(0,-650)","");
		ItemName = "Test "+generateRandomData(3)+"_"+generateRandomNumber(2);
		item_name.sendKeys(ItemName);
		log.info("Item name entered"+ItemName.toString());
		pack_name.sendKeys("Box");
		log.info("Pack Name entered"+pack_name);
		qty_pack.sendKeys(generateRandomNumber(1));
		log.info("Pack Qty entered"+qty_pack);
		manuf_name.sendKeys("albatross");
		log.info("Mfac Name entered"+manuf_name);
		Thread.sleep(4000);
		SelectOptionfromAutoCompleteSearch("albatross");
		log.info("Mfac name selected from dropdown");
		stockQty.sendKeys("0");
		log.info("Closing Stock set as zero");
		nextBtn.click();
		log.info("Clicked on Next Button");
		WaitforElementsToLoad();
		hsn_code.sendKeys("96190090");
		log.info("HSN code entered");
		Select select = new Select(ItemgstType);
		select.selectByIndex(1);
		driver.findElement(By.xpath(".//*[@id='effective_date']")).click();
		Thread.sleep(6000);
		List<WebElement> AllDates = driver.findElements(By.xpath("//*[@class='datepicker-days']/table/tbody/tr[1]"));
		
		for(WebElement we : AllDates){
			System.out.println(we.getText());
			String value = we.getText().toString();
			if(value.equalsIgnoreCase("1")){
				System.out.println(we.getText());
				we.click();
				break ;
			}
		}
		TaxAddbtn.click();
		btn_next.click();
		Thread.sleep(5000);
		try{
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(5000);
		}
		catch(Exception e){
			driver.findElement(By.id("okBtn")).click();
			Thread.sleep(5000);
		}
		min_qty.sendKeys("1");
		log.info("Min Qty entered");
		max_qty.sendKeys("10");
		log.info("Max Qty is entered");
		ItemSaveBtn.click();
		Thread.sleep(20000);
		log.info("Clicked on Save Button to save the item");
		return ItemName;
		
	}
	
	public String SearchItemName() throws InterruptedException{
		WaitforElementsToLoad();
		CustomerSearch.sendKeys(ItemName);
		log.info("Searching the Supplier name from Supplier Search"+ItemName.toString());
		Thread.sleep(3000);
		String ItemNameCheck = SelectOptionfromAutoCompleteSearch(ItemName);
		System.out.println(ItemNameCheck);
		if(ItemNameCheck == null){
		log.info("Selecting the Supplier name from Customer search");
		Thread.sleep(3000);
		ItemNameCheck = CustomerName.getAttribute("value").toString();
		 System.out.println("cust = null so " +ItemNameCheck);
		log.info("Fetching Supplier Name to verify");
		}
		return ItemNameCheck ;
		
	}
	
	
	
	/* START OF ACCOUNT MASTER */
	@FindBy(id="createacc")
	WebElement CreateAccBtn ;
	
	@FindBy(id="acc_name")
	WebElement AccountName ;
	
	@FindBy(id="acType")
	WebElement AccountType ;
	
	@FindBy(id="checkbox-10")
	WebElement MoreInfo ;
	
	@FindBy(id="perm_gst_no")
	WebElement perm_gst_no ;
	
	@FindBy(id="state")
	WebElement state ;
	
	@FindBy(xpath="html/body/div[1]/div/div[2]/div[2]/div/div/div/form/div/div[2]/button")
	WebElement AccSubmit ;
	
	public String AddNewAccountName() throws InterruptedException{
		Thread.sleep(3000);
		CreateAccBtn.click();
		AccName = generateRandomData(5);
		AccountName.sendKeys(AccName);
		Select select = new Select(AccountType);
		select.selectByValue("4");
		Thread.sleep(5000);
		MoreInfo.click();
		DoorNo.sendKeys(generateRandomNumber(3));
		log.info("Door No entered and the object is:"+DoorNo.toString());
		street.sendKeys(generateRandomData(5)+" Street");
		log.info("Door No entered and the object is:"+DoorNo.toString());
		location.sendKeys(generateRandomData(8));
		log.info("street entered and the object is:"+street.toString());
		city.sendKeys("Bangalore");
		log.info("city entered and the object is:"+city.toString());
		state.sendKeys("Karnataka");
		pincode.sendKeys("560027");
		log.info("pincode entered and the object is:"+pincode.toString());
		mobnum.sendKeys(generateRandomNumber(10));
		log.info("mob num entered and the object is:"+mobnum.toString());
		emailid.sendKeys(generateEmailID(15));
		log.info("emailid entered and the object is:"+emailid.toString());
		String GSTNO = "29"+generateRandomData(5)+generateRandomNumber(4)+generateRandomData(1)+generateRandomNumber(1)+generateRandomData(1)+generateRandomNumber(1) ;
		System.out.println(GSTNO);
		perm_gst_no.sendKeys(GSTNO);
		JavascriptExecutor jse = (JavascriptExecutor) driver ;
		jse.executeScript("window.scrollBy(0,650)","");
		AccSubmit.click();
		return AccName;
		
	}
	public String SearchAccName() throws InterruptedException{
		WaitforElementsToLoad();
		CustomerSearch.sendKeys(AccName);
		log.info("Searching the Supplier name from Supplier Search"+AccName.toString());
		Thread.sleep(5000);
		String AccNameCheck= null ;
		log.info("Selecting the Acc name from Account search");
		driver.findElement(By.id("edit_icon")).click();
		log.info("Edit Acc is Clicked");
		System.out.println("AccNameCheck"+AccNameCheck);
		AccNameCheck = CustomerName.getAttribute("value").toString();
		 System.out.println("cust = null so " +AccNameCheck);
		log.info("Fetching Supplier Name to verify");
		
		return AccNameCheck ;
		
	}
	
	/* END OF ACCOUNT MASTER */
	
	/* START OF BANK MASTER */
	@FindBy(id="create_bank")
	WebElement  create_bank_Btn ;
	
	@FindBy(id="bank_name")
	WebElement bank_name;
	
	@FindBy(name="accountgroup")
	WebElement  accountgroup ;
	
	@FindBy(id="checkbox-10")
	WebElement BankMoreInfo ;
	
	@FindBy(xpath=".//button[Contains(text(),'Submit')]")
	WebElement BankSubmitBtn;
	
	@FindBy(id="acc_num")
	WebElement acc_num ;
	
	@FindBy(id="micr_code")
	WebElement micr_code ;
	
	@FindBy(id="ifsc_code")
	WebElement ifsc_code ;
    
	
	public String AddNewBankMaster() throws InterruptedException{
		create_bank_Btn.click();
		log.info("Clicked on Create Bank button");
		WaitforElementsToLoad();
		BankName = generateRandomData(5)+" Bank";
		bank_name.sendKeys(BankName);
		log.info("Bank Name is enetered and the object is" +bank_name);
		Select select = new Select(accountgroup);
		select.deselectByIndex(1);
		log.info("Account type is selected as Bank Balance"+accountgroup);
		acc_num.sendKeys(generateRandomNumber(15));
		log.info("Acc Num is entered and object is "+acc_num);
		micr_code.sendKeys(generateRandomNumber(7));
		log.info("micr_code is entered and object is "+micr_code);
		ifsc_code.sendKeys(generateRandomData(4).toUpperCase()+generateRandomNumber(6));
		log.info("ifsc_code is entered and object is "+ifsc_code);
		BankMoreInfo.click();
		log.info("Clicked on More Info checkbox and object is"+BankMoreInfo);
		DoorNo.sendKeys(generateRandomNumber(3));
		log.info("Door No is entered and object is "+DoorNo);
		street.sendKeys(generateRandomData(4)+" Street");
		log.info("street is entered and object is "+street);
		location.sendKeys(generateRandomData(6));
		log.info("location is entered and object is "+location);
		city.sendKeys("Bengaluru");
		log.info("city is entered and object is "+city);
		state.sendKeys("Karnataka");
		log.info("state is entered and object is "+state);
		pincode.sendKeys("560027");
		log.info("pincode is entered and object is "+pincode);
		mobnum.sendKeys(generateRandomNumber(10));
		log.info("mobnum is entered and object is "+mobnum);
		emailid.sendKeys(generateEmailID(15));
		log.info("emailid is entered and object is "+emailid);
		perm_gst_no.sendKeys("29"+generateRandomData(5).toUpperCase()+generateRandomNumber(4)+"A1B1");
		log.info("perm_gst_no is entered and object is "+perm_gst_no);
		BankSubmitBtn.click();
		log.info("Clicked on Submit button and object is "+BankSubmitBtn);
		Thread.sleep(5000);
		return BankName;
		
	}
	
	
	public String SearchBankName() throws InterruptedException{
		WaitforElementsToLoad();
		CustomerSearch.sendKeys(BankName);
		log.info("Searching the Bank name from Search"+BankName.toString());
		Thread.sleep(3000);
		String BankNameCheck = SelectOptionfromAutoCompleteSearch(BankName);
		System.out.println(BankNameCheck);
		if(BankNameCheck == null){
		log.info("Selecting the Bank name from search");
		Thread.sleep(3000);
		BankNameCheck = CustomerName.getAttribute("value").toString();
		 System.out.println("cust = null so " +BankNameCheck);
		log.info("Fetching Supplier Name to verify");
		}
		return BankNameCheck ;
		
	}
}


