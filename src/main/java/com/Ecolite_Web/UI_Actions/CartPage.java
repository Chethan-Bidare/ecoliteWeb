package com.Ecolite_Web.UI_Actions;

import java.util.ArrayList;
import java.util.Collections;
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
WebDriverWait wait = new WebDriverWait(driver, 30);


@FindBy(id="qty")
WebElement Qty ;

@FindBy(xpath="//button[@class='btn btn-custom addbatchbtn']")
WebElement AddQtyBtn ;

@FindBy(xpath="//button[text()='OK']")
WebElement Ok_Btn ;

@FindBy(xpath="//h4[contains(text(),'Cart')]")
WebElement Cart ;

@FindBy(xpath="//button[@class='confirm btn btn-lg btn-info']")
WebElement DeleteCart ;

@FindBy(xpath=".//h5[@class='card-title itemname']")
WebElement Item ;

@FindBy(xpath="//h4[contains(text(),'Proceed')]")
WebElement ProceedBtn ;

@FindBy(xpath="//span[@id='customername']")
WebElement CustomerName ;

@FindBy(xpath="//span[@class='grey']")
WebElement Stock ;

@FindBy(xpath=".//*[@id='count']")
WebElement ItemCount ;

@FindBy(xpath="//*[@class='col-lg-3 col-md-3 col-sm-12 col-xs-12 rate']")
WebElement ItemRate ;

  public CartPage(){
	PageFactory.initElements(driver, this);
}


  public ArrayList<String> Select_5_ItemsAndAddQty() throws InterruptedException{
	float rate = 0 ;
	List<WebElement> Items = driver.findElements(By.xpath(".//h5[@class='card-title itemname']"));
	log.info("Fetching all the elements of items");
	ArrayList<String> arr = new ArrayList<String>();
	for(int i=0;i<5;i++){
		float itemrate;
		WebElement item = Items.get(i) ;
		
		arr.add(Items.get(i).getText());
		log.info("Storing the item name into an arraylist" +arr.get(i));
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(item));
		item.click();
		log.info("Clicked on item and object is :"+item.toString());
		itemrate = getItemPriceFromSelectedBatch();
		itemrate = itemrate * 5 ;
		System.out.println(itemrate);
		wait.until(ExpectedConditions.elementToBeClickable(Qty));
		Qty.clear();
		log.info("Clearing the Qty field and the object is :"+Qty);
		Qty.sendKeys("5");
		log.info("Qty 5 is entered");
		rate+= itemrate ;
		System.out.println(rate); 
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(AddQtyBtn)); 
		AddQtyBtn.click(); 
		log.info("Clicked on Add button and the object is :"+AddQtyBtn); 
		
		
	}
	String TotalItemRate = String.valueOf(rate);
	arr.add(TotalItemRate);
	return arr ;
  }
	
	public void Select_Single_Item(){
		List<WebElement> Items = driver.findElements(By.xpath("//*[@class='waves-effect waves-light additembatch']"));
		Items.get(0).click();
		log.info("Clicked on first item and the object is :"+Items);
	}
	
	public void Select_Item_By_Name(String ItemName) throws InterruptedException{
		Thread.sleep(3000);
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
		//wait.until(ExpectedConditions.elementToBeClickable(Ok_Btn));
		//Ok_Btn.click();
		//log.info("Clicked on OK button from popup and the object is :"+Ok_Btn);
		
		
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
		
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//i[@class='fa fa-trash']")));
		JavascriptExecutor jse = (JavascriptExecutor) driver ;
		jse.executeScript("window.scrollBy(0,-550)","");
		List<WebElement> Delete = driver.findElements(By.xpath(".//i[@class='fa fa-trash']"));
		for(WebElement we : Delete){
			Thread.sleep(3000);
			we.click();
			log.info("Clicked on delete icon for the item to delete and object is "+we);
			wait.until(ExpectedConditions.elementToBeClickable(DeleteCart));
			Thread.sleep(3000);
			DeleteCart.click();
			log.info("Deleting the item from the cart and the object is :"+DeleteCart);
			Thread.sleep(3000);
		}
		
	}
	
	public ArrayList<String> getItemNamesFromCartList() throws InterruptedException{
		ArrayList<String> arr1 = new ArrayList<String>();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Cart));
		Cart.click();
		log.info("Clicked on Cart and object is :"+Cart);
		JavascriptExecutor jse = (JavascriptExecutor)driver ;
		jse.executeScript("window.scrollBy(0,-550)", "");
		Thread.sleep(3000);
		List<WebElement> ItemNames = driver.findElements(By.xpath(".//h5[@class='card-title itemname']"));
		for( int i=100;i<ItemNames.size();i++){
			arr1.add(ItemNames.get(i).getText());
			log.info("Fetching the item name and storing it in an array");
		}
		return arr1 ;
	}
	
	public void Proceed() throws InterruptedException{
		JavascriptExecutor jse = (JavascriptExecutor)driver ;
		jse.executeScript("window.scrollBy(0,550)", "");
		Thread.sleep(6000);
		wait.until(ExpectedConditions.elementToBeClickable(ProceedBtn));
		ProceedBtn.click();
		Thread.sleep(3000);
		log.info("Clicked on Proceed button and the object is "+ProceedBtn);
	}
	
	public String getCustNameFromCartPage(){
		wait.until(ExpectedConditions.elementToBeClickable(CustomerName));
		String customerName = this.CustomerName.getText();
		log.info("Fetching customer name from Cart Page "+customerName);
		return customerName ;
	}
  
	public int getStock(){
		List<WebElement> CurrentStock = driver.findElements(By.xpath("//span[@class='grey']"));
		String Stock = CurrentStock.get(0).getText().toString();
		Stock = Stock.substring(1);
		int stock = Integer.parseInt(Stock);
		log.info("Fetching current stock :"+stock);
		return stock ;
	}
	
	public int getItemCountFromCart() throws InterruptedException{
		wait.until(ExpectedConditions.elementToBeClickable(Cart));
		String ItemCount = this.ItemCount.getText();
		int ItemCountInCartPage = Integer.parseInt(ItemCount);
		return ItemCountInCartPage ;
	}
	
	public void ClickOnCartButton() throws InterruptedException{
		JavascriptExecutor jse = (JavascriptExecutor)driver ;
		jse.executeScript("window.scrollBy(0,550)", "");
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.elementToBeSelected(Cart));
		Cart.click();
		log.info("Clicked on Cart and object is :"+Cart);
	}
	
	public float getItemPriceFromSelectedBatch() throws InterruptedException{
		Thread.sleep(3000);
	List<WebElement> Batchlist = driver.findElements(By.xpath(".//*[@id='table']/tr"));	
		for(int i=1; i<=Batchlist.size(); i++){
			if(driver.findElement(By.xpath(".//*[@id='inlineRadio"+i+"']")).isSelected()==true){
				String Batchprice = driver.findElement(By.xpath(".//*[@id='table']/tr[1]/td[4]/div")).getText();
				//Batchprice = Batchprice.substring(1);
				float ItemBatchrate = Float.parseFloat(Batchprice);
				return ItemBatchrate ;
			}
		}
		return 0;
		
	}
	
	public double getHighestMrpOfBatches() throws InterruptedException{
		Thread.sleep(3000);
		List<WebElement> Batchlist = driver.findElements(By.xpath(".//*[@id='table']/tr"));	
		ArrayList<Double> arr = new ArrayList<Double>();
		for(int i=1; i<=Batchlist.size(); i++){
			String temp = driver.findElement(By.xpath(".//*[@id='table']/tr["+i+"]/td[4]/div")).getText();
			Double value = Double.parseDouble(temp);
			arr.add(value);
		}
		
		return Collections.max(arr);
		
	}
	
	
	public double getMRPDisplayedforItems() throws InterruptedException{
		Thread.sleep(3000);
		List<WebElement> itemrate = driver.findElements(By.xpath("//*[@class='col-lg-3 col-md-3 col-sm-12 col-xs-12 rate']"));
		double rate = Double.parseDouble(itemrate.get(0).getText());
		return rate ;
	}
	
	
	public int getDisplayedStockforAnItem(String itemNum){
		List<WebElement> stock = driver.findElements(By.xpath("//span[@class='grey']"));
		int itemNo = Integer.parseInt(itemNum); 
		
			String stk = stock.get(itemNo).getText();
			stk = stk.substring(1);
			int Itemstock = Integer.parseInt(stk);
		return Itemstock ;
	}
	
	public int getCountOfAllBatchesStockForAnItem() throws InterruptedException{
		int ItemStock = 0 ;
		Thread.sleep(4000);
		List<WebElement> BatchTable = driver.findElements(By.xpath(".//*[@id='table']/tr"));
		log.info("Fetching the number of batches available :"+BatchTable.size());
		for(int i=1; i<=BatchTable.size();i++){
			String temp = driver.findElement(By.xpath(".//*[@id='table']/tr["+i+"]/td[2]/div")).getText(); 
			temp = temp.substring(7);
			int tempStk = Integer.parseInt(temp);
			System.out.println("stock of individual batch : "+tempStk);
			ItemStock+= tempStk ;
			
			
		}
		log.info("Total Count of Stock available of all batches : "+ItemStock);
		return ItemStock ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}