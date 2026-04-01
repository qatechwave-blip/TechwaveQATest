package com.Adaptix.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected static WebDriver driver;
    protected Properties config = new Properties();

    public Logger log = Logger.getLogger(BaseTest.class);

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeClass   // ✅ Browser sirf ek baar open hoga
    public void setUp() {

        PropertyConfigurator.configure(
                System.getProperty("user.dir") + "/src/test/resources/properties/log4j.properties"
        );

        try {
            FileInputStream fis = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test/resources/properties/config.properties");
            config.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);

        driver.get(config.getProperty("testsiteurl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass   // ✅ End me close hoga
    public void tearDown() {
        if (driver != null) {
         //   driver.quit();
        }
    
    /*    try {
            DbManager.setMysqlDbConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


    
/*
    @AfterSuite
    public void closeDB() {
        DbManager.closeConnection();
    }
    */
}