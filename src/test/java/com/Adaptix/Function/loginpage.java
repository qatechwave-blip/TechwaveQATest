package com.Adaptix.Function;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Adaptix.base.BasePage;
import com.Adaptix.utilities.ElementUtils;
import com.Adaptix.utilities.Waits;

public class loginpage extends BasePage {

    private ElementUtils element;

    public loginpage(WebDriver driver) {
        super(driver);
        element = new ElementUtils(driver);
    }

    @FindBy(name = "username")
    WebElement txt_userid;

    @FindBy(name = "password")
    WebElement txt_password;

    @FindBy(xpath = "(//button[normalize-space()='Sign in'])[1]")
    WebElement btn_login;

    @FindBy(xpath = "//div[contains(@class,'dashboard')]")
    WebElement dashboard;

    public void login(String user, String pass) {

        Waits.waitForVisibleElement(driver, txt_userid, 10);
        element.sendKeys(txt_userid, user);

        element.sendKeys(txt_password, pass);

        Waits.waitForClickable(driver, btn_login, 10);
        btn_login.click();
    }

    public boolean isDashboardVisible() {
        try {
            Waits.waitForVisibleElement(driver, dashboard, 10);
            return dashboard.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}