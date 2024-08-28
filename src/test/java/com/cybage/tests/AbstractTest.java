package com.cybage.tests;
import com.cybage.util.Config;
import com.cybage.util.Report;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


import java.time.Duration;

public abstract class AbstractTest {
    public static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
        Report.initializeReport();
    }
    @BeforeTest
    public void setDriver(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quitDriver()
    {
        this.driver.quit();
        log.info("quit driver");
        Report.endReport();
    }

}
