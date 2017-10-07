package com.Ecolite_Web.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.Ecolite_Web.ExcelReader.ExcelReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TestBase {

	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
	public static WebDriver driver ;
	public Properties OR = new Properties();
	public String CheckMst ;
	public static ExtentReports extent ;
	public static ExtentTest test ;
	public static ITestResult Result; 
	//public WebDriverWait wait = new WebDriverWait(driver, 60);
	@FindBy(id="datepicker-autoclose")
	WebElement datefield ;
	
	public void LoadFromProperties() throws IOException{
		File path= new File(System.getProperty("user.dir")+"//src//main//java//com//Ecolite_Web//Config//Config.properties");
		FileInputStream fis = new FileInputStream(path);
		OR.load(fis);
		
	}
	
	static{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("YYYY_MM_DD_HH_MM_SS");
		extent = new ExtentReports(System.getProperty("user.dir")+"//src//main//java//com//Ecolite_Web//Reports//TestReport"+formater.format(calendar.getTime())+".html",false);
	}
	
	public String[][] ReadExcel(String SheetName,String ExcelName){
		String path = System.getProperty("user.dir")+"//src//main//java//com//Ecolite_Web//Data//TestData.xlsx" ;
		ExcelReader Excel = new ExcelReader(path);
		String[][] TestData = Excel.getDataFromSheet(SheetName, ExcelName);
		return TestData ;
	}
	
	
	public void init() throws IOException{
		LoadFromProperties();
		SelectBrowser(OR.getProperty("Browser"));
		getURL(OR.getProperty("BaseURL"));
		String Configlog4jpath = System.getProperty("user.dir")+"//log4j.properties";
		PropertyConfigurator.configure(Configlog4jpath);
	}
	
	
	
	
	public void SelectBrowser(String BrowserName){
		
		if(BrowserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//Drivers//geckodriver.exe");
			driver = new FirefoxDriver();
			
		}
		else if(BrowserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
			driver = new ChromeDriver();
			
		}
	}
	
	public void getURL(String BaseUrl){
		driver.get(BaseUrl);
		try{
			driver.manage().window().maximize();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void CloseBrowser(){
		driver.close();
	}
	
	public void getScreenshot(String methodname){
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("YYYY_MM_DD_HH_MM_SS");
		
		methodname = driver.getClass().getName();
		
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			String ReportDirectory = System.getProperty("user.dir")+"//src//main//java//com//Ecolite_Web//ScreenShots//" ;
			File destFile = new File(ReportDirectory+"_"+methodname+"_"+formater.format(calendar.getTime())+".png") ;
			
			
			FileUtils.copyFile(srcFile, destFile);
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	
	public void WaitforElementsToLoad(){
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
	}
	
	public String generateRandomNumber(int length){
		String allowedChars = "0987654321";
		String Number = RandomStringUtils.random(length, allowedChars);
		return Number ;
	}
	
	public String generateRandomData(int length){
		String Data = RandomStringUtils.randomAlphabetic(length);
		return Data;
	}
	
	public String generateEmailID(int length){
		String allowedChars="abcdefghijklmnopqrstuv" + "0987654321"+"_-";
		String temp = RandomStringUtils.random(length, allowedChars);
		String email = temp.substring(0, temp.length()-8)+"@csquare.com";
		return email ;
	}
	
public String SelectOptionfromAutoCompleteSearch(String name){
		
		try {
			WebElement autoOptions = driver.findElement(By.id("ui-id-1"));
			Thread.sleep(4000);
			if(autoOptions.isDisplayed()== true){
			List<WebElement> OptionstoSelect = driver.findElements(By.tagName("li"));
			for(WebElement we : OptionstoSelect){
				if(we.getText().equalsIgnoreCase(name)){
					System.out.println(we.getText());
					we.click();
				}
			 }
			}
			else{
				CheckMst =driver.findElement(By.xpath(".//*[@class='card-title']")).getText().toString();
				return CheckMst ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CheckMst;
	}
	

	public void SetCalendarDate(String year,String month,String date){
		datefield.click();
		driver.findElement(By.xpath("//*[@class='datepicker-switch']")).click();
		driver.findElement(By.xpath("//*[@class='datepicker-months']//*[@class='datepicker-switch']")).click();
		driver.findElement(By.xpath("//*[@class='year' and contains(text(),'"+year+"')]")).click();
		driver.findElement(By.xpath("//*[@class='month' and contains(text(),'"+month+"')]")).click();
		driver.findElement(By.xpath("//*[@class='day' and contains(text(),'"+date+"')]")).click();
	}
	
	public void RefreshPage(){
		driver.navigate().refresh();
	}
	
	
	public void getResult(ITestResult Result){
		if(Result.getStatus()==ITestResult.SUCCESS){
			test.log(LogStatus.PASS, Result.getName()+"Test is Passed");
		}
		else if(Result.getStatus()==ITestResult.FAILURE){
			test.log(LogStatus.FAIL, Result.getName()+"Test is Failed");
		}
		else if(Result.getStatus()==ITestResult.SKIP){
			test.log(LogStatus.SKIP, Result.getName()+"Test is skipped");
		}
		else if(Result.getStatus()==ITestResult.STARTED){
			test.log(LogStatus.INFO, Result.getName()+"Test is Started");
		}
		
	}
	
	@BeforeMethod()
	public void beforeMethod(Method Result){
		test = extent.startTest(Result.getName());
		test.log(LogStatus.INFO, Result.getName()+"Test Started");
	}
	
	@AfterMethod()
	public void getMethod(ITestResult Result){
		getResult(Result);
	}
	@AfterClass(alwaysRun=true)
	public void endTest(){
		driver.close();
		extent.endTest(test);
		extent.flush();
	}
	
	
	
	
	
	
	
	
	
	
	
}
