package com.Ecolite_Web.UI_Actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.Ecolite_Web.TestBase.TestBase;

public class AccountsPage extends TestBase{

	public static final Logger log = Logger.getLogger(AccountsPage.class.getName());
	
	public void SelectOptionFromAccounts(String Name){
		driver.findElement(By.xpath("//a[@href='/ecolite/"+Name+"/"+Name+"']")).click();
		log.info("Selecting "+Name +" from Accounts in Mystore page");
	}
}
