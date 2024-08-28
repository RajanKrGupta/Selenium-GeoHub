package com.cybage.pages.geohub;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FiltersPopUpSoilInfo extends FiltersPopUpCoreInfo{

    // SOIL INFORMATION SECTION ELEMENTS
    @FindBy(xpath = "(//div[@class=' css-hlgwow'])[9]")
    private WebElement prmySoilTypeElement;

    @FindBy(xpath = "(//div[@class=' css-hlgwow'])[10]")
    private WebElement prmySoilSPTNElement;

   @FindBy(xpath = "(//div[@class=' css-hlgwow'])[11]")
    private WebElement secSoilTypeElement;

    @FindBy(xpath = "(//div[@class=' css-hlgwow'])[12]")
    private WebElement secSoilSPTNElement;


    public FiltersPopUpSoilInfo(WebDriver driver) {
        super(driver);
    }

    public void getPrimarySoilType(String pSoil) {

        this.prmySoilTypeElement.sendKeys(pSoil, Keys.ENTER);
    }

    public void getPrimarySoilSPTN(String pSoilSPTN) {

        this.prmySoilSPTNElement.sendKeys(pSoilSPTN, Keys.ENTER);
    }

    public void getSecondSoilSPTN(String sSoilSPTN) {

        this.secSoilSPTNElement.sendKeys(sSoilSPTN, Keys.ENTER);
    }


}
