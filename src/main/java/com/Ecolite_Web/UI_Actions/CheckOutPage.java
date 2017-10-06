package com.Ecolite_Web.UI_Actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Ecolite_Web.TestBase.TestBase;

public class CheckOutPage extends TestBase{

	public static final Logger log = Logger.getLogger(CheckOutPage.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 40);
	
	@FindBy(name="customername")
	WebElement customername ;
	
	@FindBy(id="city")
	WebElement city ;
	
	@FindBy(id="professionalname")
	WebElement professionalname ;
	
	@FindBy(xpath="//*[@class='slider round']")
	WebElement HomeDeliveryCheckbox ;
	
	@FindBy(id="address1")
	WebElement address1 ;
	
	@FindBy(id="address2")
	WebElement address2 ;
	
	@FindBy(xpath="//input[@id='inlineRadio2' and @name='radioInline1']")
	WebElement Card ;
	
	@FindBy(id="refncenum")
	WebElement ReferenceNum ;
	
	@FindBy(id="inlineRadio1")
	WebElement Cash ;
	
	@FindBy(id="inlineRadio3")
	WebElement JioMoney ;
	
	@FindBy(xpath="//span[@id='discamount']")
	WebElement DiscAmt ;
	
	@FindBy(id="total_amount")
	WebElement total_amount ;
	
	@FindBy(xpath="//h4[contains(text(),'Confirm')]")
	WebElement ConfirmBtn ;
	
	@FindBy(xpath="//button[contains(text(),'New Sale')]")
	WebElement NewSaleBtn ;
	
	public CheckOutPage(){
		PageFactory.initElements(driver, this);
	}
	
	public String getCustomerName(){

		try{
			JavascriptExecutor jse = (JavascriptExecutor)driver ;
			jse.executeScript("window.scrollBy(0,-550)", "");
			customername.sendKeys("Chethan");
			return customername.getText().toString();
		}
		catch(NullPointerException ne){
			return " " ;
		}
		
	}
	
	public void ConfirmSale(){
		JavascriptExecutor jse = (JavascriptExecutor)driver ;
		jse.executeScript("window.scrollBy(0,550)", "");
		ConfirmBtn.click();
	}
	
	public void CustomerDetails(String CustomerName,String City){
		wait.until(ExpectedConditions.elementToBeClickable(customername));
		JavascriptExecutor jse = (JavascriptExecutor)driver ;
		jse.executeScript("window.scrollBy(0,-550)", "");
		//customername.getText();
		customername.clear();
		customername.sendKeys(CustomerName);
		city.clear();
		city.sendKeys(City);
	}
	
	public void ProfessionalDetails(String ProfName){
		professionalname.sendKeys(ProfName);
	}
	
	public void HomeDelivery(){
		HomeDeliveryCheckbox.click();
	}
	
	public void NewSaleInSuccessPage(){
		NewSaleBtn.click();
	}
	
	public double AmountPaid(){
		WebElement AmtPaid = driver.findElement(By.xpath("(//*[ @class='col-lg-3 col-md-3 col-sm-6 col-xs-6 text-right details-value' ])[5]"));
		String amt = AmtPaid.getText();
		//amt = amt.substring(1);
		double AmountPaid = Double.parseDouble(amt);
		return AmountPaid ;
		
	}
	
	public int getItemCountInCheckOutPage(){
		WebElement AmtPaid = driver.findElement(By.xpath("(//*[ @class='col-lg-3 col-md-3 col-sm-6 col-xs-6 text-right details-value' ])[1]"));
		String itmcnt = AmtPaid.getText();
		//amt = amt.substring(1);
		int ItemCount = Integer.parseInt(itmcnt);
		return ItemCount ;
	}
	
	public double getDiscountValueInCheckOutPage(){
		WebElement Disc = driver.findElement(By.xpath("(//*[ @class='col-lg-3 col-md-3 col-sm-6 col-xs-6 text-right details-value' ])[4]"));
		String disc = Disc.getText();
		//amt = amt.substring(1);
		double Discount = Double.parseDouble((disc));
		return Discount ;
	}
	
	
	public double getInvoiceValueInCheckOutPage(){
		WebElement Inv = driver.findElement(By.xpath("(//*[ @class='col-lg-3 col-md-3 col-sm-6 col-xs-6 text-right details-value' ])[2]"));
		String Invval = Inv.getText();
		//amt = amt.substring(1);
		double InvoiceValue = Double.parseDouble((Invval));
		return InvoiceValue ;
		
	}
	
	public double getTaxValueInCheckOutPage(){
		WebElement Tax = driver.findElement(By.xpath("(//*[ @class='col-lg-3 col-md-3 col-sm-6 col-xs-6 text-right details-value' ])[3]"));
		String Taxval = Tax.getText();
		//amt = amt.substring(1);
		double TaxValue =Double.parseDouble((Taxval));
		return TaxValue ;
		
	}
	
	public void SelectCardPayment(){
		Card.click();
		wait.until(ExpectedConditions.elementToBeClickable(ReferenceNum));
		ReferenceNum.sendKeys("321321");
	}
	
	public void SelectCashPayment(){
		if (Cash.isSelected()== true){
			System.out.println("By default cash is selected");
		}
		else
			Cash.click();
	}
	
	public String getPaymentModeInCheckOutPage(){
		WebElement PayMode = driver.findElement(By.xpath("(//*[ @class='col-lg-3 col-md-3 col-sm-6 col-xs-6 text-right details-value' ])[6]"));
		String PaymentType = PayMode.getText();
		return PaymentType ;
	}

	
}
