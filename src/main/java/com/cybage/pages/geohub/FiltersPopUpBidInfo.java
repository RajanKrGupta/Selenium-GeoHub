package com.cybage.pages.geohub;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FiltersPopUpBidInfo extends FiltersPopUpCoreInfo{

    // BID INFORMATION SECTION ELEMENTS

    @FindBy(xpath = "//input[@name='contractValueMin']")
    private WebElement concValueMinElement;

    @FindBy(xpath = "//input[@name='contractValueMax']")
    private WebElement concValueMaxElement;

    //(//input)[17]
    @FindBy(xpath = "(//input)[16]")
    private WebElement pierSysElement;

    @FindBy(xpath = "//input[@name='numOfPiers']")
    private WebElement noOfPierElement;

    @FindBy(xpath = "//input[@name='costPerPierMin']")
    private WebElement pricePerPierMinElement;

    @FindBy(xpath = "//input[@name='costPerPierMax']")
    private WebElement pricePerPierMaxElement;

    @FindBy(xpath = "//input[@name='costPerFootMin']")
    private WebElement pricePerFootMinElement;


    @FindBy(xpath = "//input[@name='costPerFootMax']")
    private WebElement pricePerFootMaxElement;

    public FiltersPopUpBidInfo(WebDriver driver)
    {
        super(driver);
    }

    public void ContractValueMinimum(int min) {

        this.concValueMinElement.sendKeys(String.valueOf(min), Keys.ENTER);
    }

    public void ContractValueMaximum(int max) {
        this.concValueMaxElement.sendKeys(String.valueOf(max), Keys.ENTER);
    }

    public void setPierSystem(String pierSys) {
        this.pierSysElement.sendKeys(pierSys,Keys.ENTER);
    }

    public void setPierCount(int pierCount) {
        this.noOfPierElement.sendKeys(String.valueOf(pierCount), Keys.ENTER);
      }



    public void setPricePierMin(int minPierPrice) {
        this.pricePerPierMinElement.sendKeys(String.valueOf(minPierPrice), Keys.ENTER);

    }

    public void setPricePierMax(int maxPierPrice) throws InterruptedException {
        this.pricePerPierMaxElement.sendKeys(Keys.PAGE_DOWN);
        Thread.sleep(2000);
        this.pricePerPierMaxElement.sendKeys(String.valueOf(maxPierPrice), Keys.ENTER);

    }
    public void setPriceFootMin(int minFootPrice) throws InterruptedException {
        this.pricePerPierMaxElement.sendKeys(Keys.PAGE_DOWN);
        Thread.sleep(2000);
        this.pricePerFootMinElement.sendKeys(String.valueOf(minFootPrice), Keys.ENTER);
    }

    public void setPriceFootMax(int maxFootPrice) throws InterruptedException{
        this.pricePerPierMaxElement.sendKeys(Keys.PAGE_DOWN);
        Thread.sleep(2000);
        this.pricePerFootMaxElement.sendKeys(String.valueOf(maxFootPrice), Keys.ENTER);
    }


    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.concValueMinElement));
        return this.concValueMinElement.isDisplayed();


}   }
