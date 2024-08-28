package com.cybage.pages.geohub;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FiltersPopUpStructureInfo extends FiltersPopUpCoreInfo{

    // STRUCTURE INFORMATION SECTION ELEMENTS

    @FindBy(xpath = "//input[@name='columnLoad']")
    private WebElement colLoadElement;

    @FindBy(xpath = "(//button[contains(text(),'Yes')])[1]")
    private WebElement slbSupportYesSelection;
    @FindBy(xpath = "(//button[contains(text(),'No')])[1]")
    private WebElement slbSupportNoSelection;

    @FindBy(xpath = "(//div[@class=' css-hlgwow'])[7]")
    private WebElement slbLoadElement;

    @FindBy(xpath = "//input[@name='totalSettlement']")
    private WebElement totalStlmntElement;

    @FindBy(xpath = "(//div[@class=' css-hlgwow'])[8]")
    private WebElement structureBearingPrs;

   // ***********************For Scrolling************************x
   @FindBy(xpath = "//h3[normalize-space()='Bid Information']")
   private WebElement BidInfoTitle;


    public FiltersPopUpStructureInfo(WebDriver driver)
    {
        super(driver);
    }

    public void setColLoad(int colLoad)
    {
        this.colLoadElement.sendKeys(String.valueOf(colLoad), Keys.ENTER);
    }

    public void selSlabSupportYes()
    {
       this.slbSupportYesSelection.click();
    }

    public void selSlabSupportNo()
    {
        this.slbSupportNoSelection.click();
    }

    public void getSlabLoad(int slabLoad)
    {
        this.slbLoadElement.sendKeys(String.valueOf(slabLoad), Keys.ENTER);
    }

     public void getTotalSettlementAmount(int ttlStlAmnt)
    {
        this.totalStlmntElement.sendKeys(String.valueOf(ttlStlAmnt), Keys.ENTER);
    }


    public void getStructureBearingPressure(int bPres)
    {
        this.structureBearingPrs.sendKeys(String.valueOf(bPres), Keys.ENTER);
    }

    public void scrollFunction() {
         this.BidInfoTitle.sendKeys(Keys.PAGE_DOWN);
         this.BidInfoTitle.sendKeys(Keys.PAGE_DOWN);

    }

}
