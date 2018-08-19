package config;

import java.util.concurrent.TimeUnit;
import static executionEngine.DriverScriptTest.OR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import executionEngine.DriverScriptTest;
import utility.Log;

public class ActionKeywords {
	
	public static WebDriver driver;
	 
	//method to open browser
	public static void openBrowser(String object, String data){	
		Log.info("Opening Browser");
		try{
			
			//If value of the parameter is Mozilla, this will execute
			if(data.equals("Mozilla")){
				System.setProperty("webdriver.gecko.driver", "C:\\Selenium Automation\\Software\\geckodriver-v0.21.0-win64\\geckodriver.exe");
				driver=new FirefoxDriver();
				Log.info("Mozilla browser started");
				}
			else if(data.equals("IE")){
				//You may need to change the code here to start IE Driver
				driver=new InternetExplorerDriver();
				Log.info("IE browser started");
				}
			else if(data.equals("Chrome")){
				System.setProperty("webdriver.chrome.driver", "C:\\Selenium Automation\\Software\\chromedriver_win32\\chromedriver.exe");
				driver=new ChromeDriver();
				Log.info("Chrome browser started");
				}
 
			int implicitWaitTime=(10);
			driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		}catch(Exception e){
			//This is to print the logs - Method Name & Error description/stack
			Log.info("Not able to open Browser --- " + e.getMessage());
			//Set the value of result variable to false
			DriverScriptTest.bResult = false;
		}
	}
 
	public static void navigate(String object, String data){	
		try{
		Log.info("Navigating to URL");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Constant Variable is used in place of URL
		//As it was declared as 'static', it can be used by referring the class name
		//Type the class name 'Constants' and press '.' dot, it will display all the memebers of the class Constants

		driver.get(Constants.URL);
		}catch(Exception e){
			Log.info("Not able to navigate --- " + e.getMessage());
			DriverScriptTest.bResult = false;
			}
	}
 
	public static void click(String object, String data){
		try{
		Log.info("Clicking on Webelement "+ object);
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		}catch(Exception e){
 			Log.error("Not able to click --- " + e.getMessage());
 			DriverScriptTest.bResult = false;
         	}
	}
 
	public static void input(String object, String data){
		try{
		//Constant Variable is used in place of UserName
		Log.info("Entering the text in " + object);
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data); 
	}catch(Exception e){
		 Log.error("Not able to Enter "+ object +"--- " + e.getMessage());
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
		}catch(Exception e){
			 Log.error("Not able to Close the Browser --- " + e.getMessage());
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
		}catch(Exception e){
			Log.error("Not able to compare the text --- " + e.getMessage());
			DriverScriptTest.bResult = false;
		}	

	}
	
	public static void mouseOver(String object, String data){

		try{
			Log.info("Moved over to '"+object+"'" );
			driver.switchTo().frame(data);
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath(OR.getProperty(object)))).build().perform();
		}catch(Exception e){
			Log.error("Not able to mouseover --- " + e.getMessage());
			DriverScriptTest.bResult = false;
		}	

	}
	
	public static void findTextHTMLtable(String object, String data) {
		try{
			Log.info("Searching for contact details in table '"+object+"'" );
			// find cell data using CSS Selector
			//Here we are storing the value from the cell in to the string variable
			String sCellValue = driver.findElement(By.xpath(OR.getProperty(object))).getText();
			if (sCellValue.equals(data));			
			
		}catch(Exception e){
			Log.error("Not able to find the contact name in database --- " + e.getMessage());
			DriverScriptTest.bResult = false;
		}	
		
	}
	
 
}