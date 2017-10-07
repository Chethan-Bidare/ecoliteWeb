package com.Ecolite_Web.UI_Actions;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Ecolite_Web.TestBase.TestBase;

public class Supplier_Payment_Page extends TestBase {

	@FindBy(id="supplier-search")
	WebElement supplier_search ;
	
	@FindBy(xpath=".//*[@id='secDiv']/div/div/div/div[2]/div[1]/div/div[2]/div/div/div[1]/div/div[1]/i")
	WebElement  Cash ;
	
	@FindBy(xpath=".//*[@id='secDiv']/div/div/div/div[2]/div[1]/div/div[2]/div/div/div[2]/div/div[1]/i")
	WebElement  Cheque ;
	
	@FindBy(xpath=".//*[@id='secDiv']/div/div/div/div[2]/div[1]/div/div[2]/div/div/div[3]/div/div[1]/i")
	WebElement  JioMoney ;
	
	@FindBy(id="continuebtn")
	WebElement ContinueBtn;
	
	@FindBy(id="remarks")
	WebElement remarks ;
	
	@FindBy(id="paydate")
	WebElement paydate ;
	
	@FindBy(id="paydiv")
	WebElement PayBtn ;
	
	@FindBy(id="vCount")
	WebElement  VoucherCount;
	
	@FindBy(xpath="//*[@class='today day' or @class='day']")
	WebElement  CalendarDates;
	
	@FindBy(id="bankname")
	WebElement bankname ;
	
	@FindBy(id="chequenum")
	WebElement chequenum ;
	
	@FindBy(id="chequedate")
	WebElement chequedate ;
	
	
	
	public Supplier_Payment_Page(){
		PageFactory.initElements(driver, this);
	}
	
	public void SelectSupplierFromSearch(String Name){
		supplier_search.sendKeys(Name);
		WaitforElementsToLoad();
		//*[@id='listSup']/div[1]/span
		driver.findElement(By.xpath(".//span[contains(text(),'"+Name+"')]")).click();
		WaitforElementsToLoad();
	}
	
	public void ConfirmPayment() throws InterruptedException{
		JavascriptExecutor jse = (JavascriptExecutor)driver ;
		jse.executeScript("window.scrollBy(0,-550)","");
		remarks.sendKeys("Testing");
		paydate.click();
		Thread.sleep(10000);
		/*WebElement d1 = driver.findElement(By.xpath("//td[@class='today active day']"));
		JavascriptExecutor jse3 = (JavascriptExecutor) driver ;
		jse3.executeScript("arguments[0].click;",d1 );
		//Selects current date from calendar
		DateFormat df = new SimpleDateFormat("dd");
		Date date = new Date();
		String CurrentDate = df.format(date);
		System.out.println(CurrentDate);	
		List<WebElement> Datelist = d1.findElements(By.tagName("td"));
		System.out.println(Datelist);
		for(WebElement we : Datelist){
			if(we.getText().equals(CurrentDate)){
				we.click();
				break ;
			}
		}*/
		
		jse.executeScript("window.scrollBy(0,550)","");
		PayBtn.click();
		
		
	}
	
	public void SelectPaymentMethod(String paymentMode) throws InterruptedException{
		
			if(paymentMode.equalsIgnoreCase("cheque")){
				Cheque.click();
				bankname.sendKeys("Axis");
				chequenum.sendKeys("123456");
				Thread.sleep(7000);
				
				/*//chequedate.sendKeys(Keys.TAB);
				chequedate.click();
				DateFormat def = new SimpleDateFormat("dd");
				Date dt = new Date();
				String curentdate = def.format(dt);
				WebElement d2 = driver.findElement(By.xpath("//*[@class='today active day']"));
				Thread.sleep(7000);
				JavascriptExecutor jse4 = (JavascriptExecutor) driver ;
				jse4.executeScript("arguments[0].click;",d2 );
				List<WebElement> dates = d2.findElements(By.tagName("td"));
				for(WebElement we1 :dates){
					if(we1.getText().equals(curentdate)){
						we1.click();
						break ;
					}
				}*/
			}
		
			else if(paymentMode.equalsIgnoreCase("cash")){
				if(Cash.isSelected()==true){
					System.out.println("");
				}
				
			}
			
		}
	
	public void SelectTransFromUnsettledVouchers() throws InterruptedException{
		Thread.sleep(15000);
		List<WebElement> rowcount = driver.findElements(By.xpath(".//*[@id='supplierdetailsdiv' and @class='row']/div"));
		for(int i =0; i<rowcount.size(); i++){
			WebElement checkbox = driver.findElement(By.xpath(".//*[@id='ind"+i+"']"));
				if(checkbox.isEnabled()){
				checkbox.click();
			}
		}
		
		driver.findElement(By.id("ind0")).click();
		JavascriptExecutor jse = (JavascriptExecutor)driver ;
		jse.executeScript("window.scrollBy(0,550)","");
		ContinueBtn.click();
	}
	
	public String PaymentSuccessMsg(){
		String Success = driver.findElement(By.xpath(".//*[@id='firstDiv']/div/div/div/div/div[2]/h2")).getText();
		return Success ;
	}
	
	
	
}
