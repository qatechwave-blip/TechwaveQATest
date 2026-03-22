package com.Adaptix.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.Adaptix.base.TestBase;

public class LoginTest extends TestBase {
	@Test
	public void loginASPsg() throws InterruptedException 
	{
		 
		log.debug("Inside Login Test");
		driver.findElement(By.xpath(OR.getProperty("logBtn"))).click();
		Thread.sleep(3000);
		
		log.debug("Login successfuly executed");
		
	}

}
