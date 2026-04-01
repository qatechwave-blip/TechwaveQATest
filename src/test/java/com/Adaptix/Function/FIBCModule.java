package com.Adaptix.Function;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Adaptix.base.BasePage;
import com.Adaptix.utilities.Waits;

public class FIBCModule extends BasePage {

    public FIBCModule(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//div[@class='grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6']/div[3]/div/div")
    WebElement moduleBTN;

    public void clickFIBCModule() {
        Waits.waitForVisibleElement(driver, moduleBTN, 10);
        Waits.clickElementSafely(driver, moduleBTN);
    }
}