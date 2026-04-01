package com.Adaptix.testcases;

import org.testng.annotations.Test;
import com.Adaptix.Function.FIBCModule;
import com.Adaptix.Function.loginpage;
import com.Adaptix.base.BaseTest;

public class LoginTest extends BaseTest {

    loginpage login;
    FIBCModule fibc;

    @Test(priority = 1)
    public void loginPage() {
        login = new loginpage(driver);
        login.login("SHIVAM.GUPTA", "SHIVAM.GUPTA");
    }

    @Test(priority = 2, dependsOnMethods = "loginPage")
    public void moduleTest() {
        fibc = new FIBCModule(driver);
        fibc.clickFIBCModule();
    }
}