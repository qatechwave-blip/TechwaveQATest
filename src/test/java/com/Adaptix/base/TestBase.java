package com.Adaptix.base;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.Adaptix.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log= Logger.getLogger("devpinoyLogger");
	public static WebDriverWait Wait;
	public static  ExtentReports rep= ExtentManager.getInstance();
	public static ExtentTest test;

	@BeforeSuite
	public void detUp() throws IOException {
		if (driver == null) {
			
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "//src//test//resources//properties//Config.properties");
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

			try {
				config.load(fis);
				log.debug("config file loaded !!!");

			} catch (IOException e) {

			}

			// Load OR.properties
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "//src//test//resources//properties//OR.properties");

			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			try {
			OR.load(fis);
			log.debug("OR file loaded !!!");
			
			} catch (IOException e) {

			}
			if(config.getProperty("browser").equals("forefox")) {
				
			//	System.getProperty("webdriver.gecko.driver","gecko.exe");
				driver =new FirefoxDriver();
			}
			else if(config.getProperty("browser").equals("chrome")) {
				
					System.getProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "\\src\\test\\resources\\executables\\chromedriver.exe");
					driver =new ChromeDriver();
					log.debug("Chrome Launched !!!");
				}
			else if(config.getProperty("browser").equals("ie")) {
				
				System.getProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver =new InternetExplorerDriver();
			}
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to: "+ config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
		}
	}

	@AfterSuite
	public void tearDown() {
		if(driver!=null) {
		driver.quit();
		}
		log.debug("Test executed completed !!!");
	}

}
