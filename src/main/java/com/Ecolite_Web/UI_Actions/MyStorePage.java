package com.Ecolite_Web.UI_Actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.Ecolite_Web.TestBase.TestBase;

public class MyStorePage extends TestBase{
	
	public static final Logger log = Logger.getLogger(MyStorePage.class.getName());
	//a[@href='/ecolite/supplier-payment/supplier-payment']
	public void SelectFromMyStorePage(String Name){
		driver.findElement(By.xpath("//a[@href='/ecolite/"+Name+"/"+Name+"']")).click();
		log.info("Selecting "+Name +"from My Store Page");
	}
}
