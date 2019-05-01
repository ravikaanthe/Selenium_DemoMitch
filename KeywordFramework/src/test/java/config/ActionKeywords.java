package config;

import java.util.concurrent.TimeUnit;
import static executionEngine.DriverScriptTest.OR;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import executionEngine.DriverScriptTest;
import utility.Log;

public class ActionKeywords {
	
	public static WebDriver driver;
	public static ExtentReports reports;
	public static ExtentTest logger;
	
	//Constructor to initialize the object of class ExtentReports whenever we initialize object
			public ActionKeywords(){
				//Create an object of ExtentReports class

				reports=new ExtentReports(Constants.Extent_Reports);
			}
	 
	//method to open browser
	//Test Mutli team changes, testing
	public static void openBrowser(String object, String data){	
		Log.info("Opening Browser");
		try{
			
			//If value of the parameter is Mozilla, this will execute
			if(data.equals("Mozilla")){
				System.setProperty("webdriver.gecko.driver", "C:\\Selenium Automation\\Software\\geckodriver-v0.21.0-win64\\geckodriver.exe");
				driver=new FirefoxDriver();
				logger.log(LogStatus.PASS, "opened Browser -"+ data);
				Log.info("Mozilla browser started");
				}
			else if(data.equals("IE")){
				//You may need to change the code here to start IE Driver
				driver=new InternetExplorerDriver();
				logger.log(LogStatus.PASS, "opened Browser -"+ data);
				Log.info("IE browser started");
				}
			else if(data.equals("Chrome")){
				//System.setProperty("webdriver.chrome.driver", Constants.Driver_Path);
				//Removed above path by introducing maven dependencies ChromeWebDriverManager and Chrome WebDriver
				WebDriverManager.getInstance(CHROME).setup();
				driver=new ChromeDriver();
				logger.log(LogStatus.PASS, "opened Browser -"+ data);
				Log.info("Chrome browser started");
				}
 
			int implicitWaitTime=(10);
			driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		}catch(Exception e){
			//This is to print the logs - Method Name & Error description/stack
			Log.info("Not able to open Browser --- " + e.getMessage());
			logger.log(LogStatus.FAIL, "Unable to opened Browser -"+ data);
			//Set the value of result variable to false
			DriverScriptTest.bResult = false;
		}
	}
 
	public static void navigate(String object, String data){	
		try{
		Log.info("Navigating to URL");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.log(LogStatus.PASS, "Navigated to URL - "+ Constants.URL);
		//Constant Variable is used in place of URL
		//As it was declared as 'static', it can be used by referring the class name
		//Type the class name 'Constants' and press '.' dot, it will display all the memebers of the class Constants

		driver.get(Constants.URL);
		}catch(Exception e){
			Log.info("Not able to navigate --- " + e.getMessage());
			logger.log(LogStatus.FAIL, "Unable to Navigate to URL - "+ Constants.URL);
			DriverScriptTest.bResult = false;
			}
	}
 
	public static void click(String object, String data){
		try{
		Log.info("Clicking on Webelement "+ object);
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		logger.log(LogStatus.PASS, "Succefully Clicked on button "+ object);
		}catch(Exception e){
 			Log.error("Not able to click --- " + e.getMessage());
 			logger.log(LogStatus.FAIL, "Unable to Click on button "+ object);
 			DriverScriptTest.bResult = false;
         	}
	}
 
	public static void input(String object, String data){
		try{
		//Constant Variable is used in place of UserName
		Log.info("Entering the text in " + object);
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data); 
		logger.log(LogStatus.PASS, "Entered the text in "+ object);
	}catch(Exception e){
		 Log.error("Not able to Enter "+ object +"--- " + e.getMessage());
		 logger.log(LogStatus.FAIL, "Not able to enter text in "+ object);
		 DriverScriptTest.bResult = false;
	 	}
	}
 
 
	public static void waitFor(String object, String data) throws Exception{
		try{
		Log.info("Wait for 5 seconds");
		Thread.sleep(5000);
		 }catch(Exception e){
			 Log.error("Not able to Wait --- " + e.getMessage());
			 DriverScriptTest.bResult = false;
         }
	}
 
	public static void closeBrowser(String object, String data){
		try{
		Log.info("Closing the browser-- " + data);
		driver.quit();
		logger.log(LogStatus.PASS, "Succefully closed the browser -"+ object);
		}catch(Exception e){
			 Log.error("Not able to Close the Browser --- " + e.getMessage());
			 logger.log(LogStatus.FAIL, "Unable to close the browser -"+ object);
			 DriverScriptTest.bResult = false;
        }
			
	}
	
	public static String GetText(String object, String data){

		try{

			Log.info("Getting the text of '"+object+"'");
			String Text = driver.findElement(By.xpath(OR.getProperty(object))).getText();
			return Text;
		}catch (Exception e){
			Log.error("Not able read the text --- " + e.getMessage());
			DriverScriptTest.bResult = false;
			return null;

		}

	}

	public static void compareGetText(String object, String data){

		try{
			Log.info("Comparing the text '" +data+ "' with '"+object+"'" );
			String acutalText = driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("innerText");
			if(acutalText.equals(data));
			logger.log(LogStatus.PASS, "Expected text '"+data+ "'is same as actual text'"+ object);
		}catch(Exception e){
			Log.error("Not able to compare the text --- " + e.getMessage());
			logger.log(LogStatus.FAIL, "Unable to compare the text "+ object);
			DriverScriptTest.bResult = false;
		}	

	}
	
	public static void mouseOver(String object, String data){

		try{
			Log.info("Moved over to '"+object+"'" );
			driver.switchTo().frame(data);
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath(OR.getProperty(object)))).build().perform();
			logger.log(LogStatus.PASS, "Able to mouse over the element"+ object);
		}catch(Exception e){
			Log.error("Not able to mouseover --- " + e.getMessage());
			DriverScriptTest.bResult = false;
			logger.log(LogStatus.PASS, "Unble to mouse over the element"+ object);
		}	

	}
	
	public static void findTextHTMLtable(String object, String data) {
		try{
			Log.info("Searching for contact details in table '"+object+"'" );
			// find cell data using CSS Selector
			//Here we are storing the value from the cell in to the string variable
			String sCellValue = driver.findElement(By.xpath(OR.getProperty(object))).getText();
			if (sCellValue.equals(data));
			logger.log(LogStatus.PASS, "Expected text '"+data+ "'is same as actual text in HTML Table'"+ object);
			
		}catch(Exception e){
			Log.error("Not able to find the contact name in database --- " + e.getMessage());
			logger.log(LogStatus.FAIL, "Unable to compare the text in HTML Table "+ object);
			DriverScriptTest.bResult = false;
		}	
		
	}
	
	public static void verifyElement(String object, String data){
		try{
			Log.info("Verifying the element '"+ object);
			driver.findElement(By.xpath(OR.getProperty(object))).isDisplayed();
			elementHighlight(driver.findElement(By.xpath(OR.getProperty(object))));
			Thread.sleep(600);
			logger.log(LogStatus.PASS, "Webelement '"+ object+"'displayed on page");
		}catch(Exception e){
			Log.error("Unable to find Webelement --- " + e.getMessage());
			logger.log(LogStatus.FAIL, "Unable to find Webelement "+ object);
 			DriverScriptTest.bResult = false;
		}
	}
	
	public static void elementHighlight(WebElement element) {
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "color: blue; border: 3px solid blue;");
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "");
		}
	}
	
	public static void voidAccept(String object, String data) throws Exception{
		driver.switchTo().alert().accept();
	
	}
	
 
}