package com.Ecolite_Web.UI_Actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Ecolite_Web.TestBase.TestBase;

public class CheckOutPage extends TestBase{

	public static final Logger log = Logger.getLogger(CheckOutPage.class.getName());
	
	@FindBy(id="customername")
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
	
	@FindBy(id="inlineRadio2")
	WebElement Card ;
	
	@FindBy(id="inlineRadio1")
	WebElement Cash ;
	
	@FindBy(id="inlineRadio3")
	WebElement JioMoney ;
	
	@FindBy(xpath="//span[@id='discamount']")
	WebElement DiscAmt ;
	
	@FindBy(id="total_amount")
	WebElement total_amount ;
	
	@FindBy(xpath="//button[@class='col-lg-12 col-md-12 col-sm-12 col-xs-12 confirmdiv']")
	WebElement ConfirmBtn ;
	
	public String getCustomerName(){
		customername.getText();
		if(customername.getText()==null){
			return "" ;
		}
		else
		return customername.getText() ;
		
	}
	
}
