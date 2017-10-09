package com.Ecolite_Web.UI_Actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Ecolite_Web.TestBase.TestBase;

public class Dashboard extends TestBase{
	
	@FindBy(id="new-purchase")
	WebElement SupplierName ;
	
	@FindBy(id="bill-no")
	WebElement bill_no ;
	
	@FindBy(id="bill-amt")
	WebElement bill_amt ;
	
	@FindBy(id="bill-disc")
	WebElement bill_disc ;
	
	@FindBy(id="btn-purchase")
	WebElement StartPurchase_Btn ;
	
	@FindBy(id="new-sale")
	WebElement CustomerName ;
	
	@FindBy(id="btn-sale")
	WebElement Start_Btn ;
	
	@FindBy(id="item-search")
	WebElement item_search ;
	
	@FindBy(id="inlineRadio2")
	WebElement SaleReturnCheckbox ;
	
	public Dashboard(){
		PageFactory.initElements(driver, this);
	}

	public void SelectAnOptionFromHeader(String headerNames){
		
		if(headerNames.equalsIgnoreCase("Dashboard")){
		driver.findElement(By.xpath(".//*[@id='navigation']/ul/li[1]/a/span")).click();
		}
		else if (headerNames.equalsIgnoreCase("My Store")){
			driver.findElement(By.xpath(".//*[@id='navigation']/ul/li[2]/a/span")).click();
			}
		else if(headerNames.equalsIgnoreCase("Reports")){
			driver.findElement(By.xpath(".//*[@id='navigation']/ul/li[3]/a/span")).click();
			}
	}
	
	public void SignOut(){
		Dashboard dashboard = new Dashboard();
		WaitforElementsToLoad();
		dashboard.SelectAnOptionFromHeader("Dashboard");
		driver.findElement(By.xpath(".//*[@id='topnav']/div[2]/div/div[2]/ul/li[2]/a")).click();
		WaitforElementsToLoad();
		driver.findElement(By.xpath(".//*[@id='logout']")).click();
		/*Alert alert = driver.switchTo().alert();
		alert.accept();*/
		driver.findElement(By.xpath(".//button[contains(text(),'Confirm')]")).click();
	}
	
	
	
	public void StartPurchase(String SupplierName,String BillAmt,String BillDisc){
		this.SupplierName.sendKeys(SupplierName);
		SelectOptionfromAutoCompleteSearch(SupplierName);
		bill_no.sendKeys(generateRandomNumber(3));
		bill_amt.sendKeys(BillAmt);
		bill_disc.sendKeys(BillDisc);
		StartPurchase_Btn.click();
	}
	
	public void StartSale(String CustomerName){
		this.CustomerName.sendKeys(CustomerName);
		try{
			WebElement AutoOptions = driver.findElement(By.id("ui-id-2"));
			Thread.sleep(3000);
			if(AutoOptions.isDisplayed()==true){
				List<WebElement> CustNames = driver.findElements(By.tagName("li"));
				for(WebElement we : CustNames){
					if(we.getText().equalsIgnoreCase(CustomerName)){
						we.click();
					}
				}
			}
			Start_Btn.click();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void StartSalesReturn(String CustomerName){
		this.CustomerName.sendKeys(CustomerName);
		try {
			WebElement AutoOptions = driver.findElement(By.id("ui-id-2"));
			Thread.sleep(3000);
			if(AutoOptions.isDisplayed()==true){
				List<WebElement> CustNames = driver.findElements(By.tagName("li"));
				for(WebElement we : CustNames){
					if(we.getText().equalsIgnoreCase(CustomerName)){
						we.click();
					}
				}
			}
			SaleReturnCheckbox.click();
			Start_Btn.click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
