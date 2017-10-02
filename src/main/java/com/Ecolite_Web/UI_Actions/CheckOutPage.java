package com.Ecolite_Web.UI_Actions;

import org.apache.log4j.Logger;
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
		wait.until(ExpectedConditions.elementToBeClickable(customername));
		JavascriptExecutor jse = (JavascriptExecutor)driver ;
		jse.executeScript("window.scrollBy(0,-550)", "");
		customername.click();
		customername.sendKeys("Chethan");
		city.sendKeys("Bengaluru");
		professionalname.sendKeys("bidare");
		HomeDeliveryCheckbox.click();
		jse.executeScript("window.scrollBy(0,550)", "");
		ConfirmBtn.click();
	}
	
	public void NewSaleInSuccessPage(){
		NewSaleBtn.click();
	}
	
}
