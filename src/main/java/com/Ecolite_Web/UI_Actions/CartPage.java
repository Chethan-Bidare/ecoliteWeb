package com.Ecolite_Web.UI_Actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Ecolite_Web.TestBase.TestBase;

public class CartPage extends TestBase{
	
public static final Logger log = Logger.getLogger(CartPage.class.getName());

WebDriverWait wait = new WebDriverWait(driver, 60);

@FindBy(id="qty")
WebElement Qty ;

@FindBy(xpath="//button[@class='btn btn-custom addbatchbtn']")
WebElement AddQtyBtn ;

@FindBy(xpath="//button[text()='OK']")
WebElement Ok_Btn ;

@FindBy(xpath="//*[@class='col-lg-6 col-md-6 col-sm-12 col-xs-12 text-center cartdetailsdiv']")
WebElement Cart ;

@FindBy(xpath="//button[@class='confirm btn btn-lg btn-info']")
WebElement DeleteCart ;

@FindBy(xpath=".//h5[@class='card-title itemname']")
WebElement Item ;

  public CartPage(){
	PageFactory.initElements(driver, this);
}


  public ArrayList<String> Select_5_ItemsAndAddQty() throws InterruptedException{
	  
	List<WebElement> Items = driver.findElements(By.xpath(".//h5[@class='card-title itemname']"));
	log.info("Fetching all the elements of items");
	ArrayList<String> arr = new ArrayList<String>();
	for(int i=0;i<5;i++){
		WebElement item = Items.get(i) ;
		
		arr.add(Items.get(i).getText());
		log.info("Storing the item name into an arraylist" +Items.get(i).getText());
		item.click();
		log.info("Clicked on item and object is :"+item.toString());
		wait.until(ExpectedConditions.elementToBeClickable(Qty));
		Qty.clear();
		log.info("Clearing the Qty field and the object is :"+Qty);
		Qty.sendKeys("5");
		log.info("Qty 5 is entered");
		wait.until(ExpectedConditions.elementToBeClickable(AddQtyBtn));
		Thread.sleep(3000);
		AddQtyBtn.click();
		log.info("Clicked on Add button and the object is :"+AddQtyBtn);
		wait.until(ExpectedConditions.elementToBeClickable(Ok_Btn));
		Ok_Btn.click();
		log.info("Clicked on OK button from popup and the object is :"+Ok_Btn);
		
	}
	return arr ;
  }
	
	public void Select_Single_Item(){
		List<WebElement> Items = driver.findElements(By.xpath("//*[@class='waves-effect waves-light additembatch']"));
		Items.get(0).click();
		log.info("Clicked on first item and the object is :"+Items);
	}
	
	public void Select_Item_By_Name(String ItemName) throws InterruptedException{
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='card-title itemname' and contains(text(),'"+ItemName+"')]")));
		driver.findElement(By.xpath("//*[@class='card-title itemname' and contains(text(),'"+ItemName+"')]")).click();
		log.info("Clicked on the item and the object is :"+ItemName);
		
	}
	
	public void Add_Qty(String Qty) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(this.Qty));
		this.Qty.clear();
		log.info("Clearing the Qty field and the object is :"+Qty);
		this.Qty.sendKeys(Qty);
		log.info("Qty is entered");
		wait.until(ExpectedConditions.elementToBeClickable(AddQtyBtn));
		Thread.sleep(3000);
		AddQtyBtn.click();
		log.info("Clicked on Add button and the object is :"+AddQtyBtn);
		wait.until(ExpectedConditions.elementToBeClickable(Ok_Btn));
		Ok_Btn.click();
		log.info("Clicked on OK button from popup and the object is :"+Ok_Btn);
		
		
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
	
	
	public void deleteItemfromCart() throws InterruptedException{
		Cart.click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//span[@class='card-title delicon']")));
		JavascriptExecutor jse = (JavascriptExecutor) driver ;
		jse.executeScript("window.scrollBy(0,-550)","");
		List<WebElement> Delete = driver.findElements(By.xpath(".//span[@class='card-title delicon']"));
		for(WebElement we : Delete){
			we.click();
			wait.until(ExpectedConditions.elementToBeClickable(DeleteCart));
			DeleteCart.click();
			Thread.sleep(4000);
		}
		
	}
	
	public ArrayList<String> getItemNamesFromCartList() throws InterruptedException{
		ArrayList<String> arr1 = new ArrayList<String>();
		Cart.click();
		
		JavascriptExecutor jse = (JavascriptExecutor)driver ;
		jse.executeScript("window.scrollBy(0,-550)", "");
		Thread.sleep(3000);
		List<WebElement> ItemNames = driver.findElements(By.xpath(".//h5[@class='card-title itemname']"));
		for( int i=100;i<ItemNames.size();i++){
			arr1.add(ItemNames.get(i).getText());
			System.out.println("itm name found in cart :"+ItemNames.get(i).getText());
		}
		return arr1 ;
	}
  

}