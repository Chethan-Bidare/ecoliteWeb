package com.Ecolite_Web.UI_Actions;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Ecolite_Web.TestBase.TestBase;

public class CartPage extends TestBase{
	
public static final Logger log = Logger.getLogger(CartPage.class.getName());

@FindBy(id="qty")
WebElement Qty ;

@FindBy(xpath="//button[@class='btn btn-custom addbatchbtn']")
WebElement AddQtyBtn ;

@FindBy(xpath="//button[text()='OK']")
WebElement Ok_Btn ;
/*
@FindBy(id="qty")
WebElement Qty ;*/

  public CartPage(){
	PageFactory.initElements(driver, this);
}


  public void Select_5_ItemsAndAddQty() throws InterruptedException{
	List<WebElement> Items = driver.findElements(By.xpath("//*[@class='waves-effect waves-light additembatch']"));
	log.info("Fetching all the elements of items");
	for(int i=0;i<5;i++){
		WebElement item = Items.get(i) ; 
		item.click();
		log.info("Clicked on item and object is :"+item.toString());
		Qty.clear();
		log.info("Clearing the Qty field and the object is :"+Qty);
		Qty.sendKeys("5");
		log.info("Qty 5 is entered");
		Thread.sleep(3000);
		AddQtyBtn.click();
		log.info("Clicked on Add button and the object is :"+AddQtyBtn);
		Thread.sleep(3000);
		Ok_Btn.click();
		log.info("Clicked on OK button from popup and the object is :"+Ok_Btn);
		Thread.sleep(3000);
		
	}
  }
	
	public void Select_Single_Item(){
		List<WebElement> Items = driver.findElements(By.xpath("//*[@class='waves-effect waves-light additembatch']"));
		Items.get(0).click();
		log.info("Clicked on first item and the object is :"+Items);
	}
	
	public void Select_Item_By_Name(String ItemName) throws InterruptedException{
		driver.findElement(By.xpath("//*[@class='card-title itemname' and contains(text(),'"+ItemName+"')]")).click();
		log.info("Clicked on the item and the object is :"+ItemName);
		Thread.sleep(5000);
	}
	
	public void Add_Qty(String Qty) throws InterruptedException {
		this.Qty.clear();
		log.info("Clearing the Qty field and the object is :"+Qty);
		this.Qty.sendKeys(Qty);
		log.info("Qty is entered");
		Thread.sleep(3000);
		AddQtyBtn.click();
		log.info("Clicked on Add button and the object is :"+AddQtyBtn);
		Thread.sleep(3000);
		Ok_Btn.click();
		log.info("Clicked on OK button from popup and the object is :"+Ok_Btn);
		Thread.sleep(3000);
		
	}

	
	public void select_Batch(String BatchNo) throws InterruptedException{
		List<WebElement> Batch_rows = driver.findElements(By.xpath(".//*[@id='table']/tr"));
		int BatchCount = Batch_rows.size() ;
		log.info("No of batches avaiable :"+BatchCount);
		for(int i=1;i<=BatchCount;i++){
			String BatchName = driver.findElement(By.xpath(".//*[@id='table']/tr["+i+"]/td[2]/b")).getText();
			log.info("Fetching the batch names :"+BatchName.toString());
			if(BatchName.equalsIgnoreCase(BatchNo)){
				driver.findElement(By.id("inlineRadio"+i+"")).click();
				log.info("Clicked on the batch based on the batch name provided");
			}
		}
		Thread.sleep(4000);
	}
	
	
	public void deleteItemfromCart(){
		
	}
  

}