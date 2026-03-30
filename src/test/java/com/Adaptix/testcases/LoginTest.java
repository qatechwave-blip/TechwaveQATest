package com.Adaptix.testcases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Adaptix.Function.loginpage;
import com.Adaptix.base.BaseTest;
import com.Adaptix.listeners.ExtentListeners;

@Listeners(ExtentListeners.class)
public class LoginTest extends BaseTest {

    @Test
    public void loginPage() {

        loginpage login = new loginpage(driver);

        login.login("SHIVAM.GUPTA", "SHIVAM.GUPTA");

        Assert.assertTrue(login.isDashboardVisible(), "Login Failed");
    }
}