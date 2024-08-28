package com.cybage.pages.geohub;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FiltersPopUpDesignInfo extends FiltersPopUpCoreInfo{

    //DESIGN INFORMATION SECTION ELEMENTS
    @FindBy(xpath = "(//div[@class=' css-hlgwow'])[13]")
    private WebElement geopierDsgnSoilTypeElement;

    @FindBy(xpath = "(//div[@class=' css-hlgwow'])[14]")
    private WebElement geopierDsgnSPTN_Element;

    @FindBy(xpath = "(//div[@class=' css-hlgwow'])[15]")
    private WebElement geopierDsgnBPElement;

    @FindBy(xpath = "(//button[contains(text(),'Yes')])[2]")
    private WebElement modTestReqdYesElement;
    @FindBy(xpath = "(//button[contains(text(),'No')])[2]")
    private WebElement modTestReqdNoElement;

    @FindBy(xpath = "(//div[@class=' css-hlgwow'])[16]")
    private WebElement riskLevelElement;

    @FindBy(xpath = "(//div[@class=' css-hlgwow'])[17]")
    private WebElement stagePElement;


    public FiltersPopUpDesignInfo(WebDriver driver) {
        super(driver);
    }

    public void getDesignSoilType(String sType) {

        this.geopierDsgnSoilTypeElement.sendKeys(sType, Keys.ENTER);
    }

    public void getDesignSPTN(String sptnSoil) {

        this.geopierDsgnSPTN_Element.sendKeys(sptnSoil, Keys.ENTER);
    }

    public void getDesignBearingPressure(String bPressure) {

        this.geopierDsgnBPElement.sendKeys(bPressure, Keys.ENTER);
    }

    public void setModTestYes() {

        this.modTestReqdYesElement.click();
    }

    public void setModTestNo() {

        this.modTestReqdNoElement.click();
    }


    public void getRiskLevel(String rLevel) {

        this.geopierDsgnBPElement.sendKeys(rLevel, Keys.ENTER);
    }

    public void getStage(String stage) {

        this.geopierDsgnBPElement.sendKeys(stage, Keys.ENTER);
    }

}
