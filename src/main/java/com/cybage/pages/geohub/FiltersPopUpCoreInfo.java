package com.cybage.pages.geohub;

import com.cybage.pages.AbstractPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

public class FiltersPopUpCoreInfo extends AbstractPage
{
    @FindBy(xpath = "//button[@class='style_zoomOut__Kl2PF']")
    private WebElement zoomOutFilterElement;                 // Zoom out (-)

    @FindBy(xpath = "//button[@class='style_zoomIn__uYLIg']")
    private WebElement zoomInFilterElement;                  // Zoom in (+)
   @FindBy(xpath="//button[@class='style_searchFilterBtn__84Oe4']")
   private WebElement filterElement;

   @FindBy(xpath="//button[@class='style_closeBtn__VdJn1']")
   private WebElement dismissMsgElement;

    //@FindBy(xpath ="(//div[@class=' css-19bb58m'])[1]")
    //@FindBy(xpath ="(//div[contains(@class,'css-10sl92e-control')]//div[contains(@class,'css-19bb58m')]")
    @FindBy(xpath = "(//input)[6]")
    private WebElement lostReasonElement;

    @FindBy(xpath = "//input[@placeholder='YYYY']")
    private WebElement yearCompletedElement;

    //xpath=//input[8]
    @FindBy(xpath = "(//input)[9]")    //working
    private WebElement startDateElement;

    @FindBy(xpath = "(//input)[10]")      //working
    private WebElement endDateElement;

    @FindBy(xpath = "(//input)[11]")
    private WebElement mktGrpElement;

    @FindBy(xpath = "(//input)[13]")
    private WebElement mktSegElement;

    @FindBy(xpath = "//button[contains(text(),'Clear Filters')]")
    private WebElement clrFilterElement;
    @FindBy(xpath = "//button[contains(text(),'Apply Filters')]")
    private WebElement applyFilterElement;

    public FiltersPopUpCoreInfo(WebDriver driver)
    {
        super(driver);
    }
    public void openFilterPopUp()
    {
        this.filterElement.click();
    }
    public void lostReason(String lostReason)
    {
        this.lostReasonElement.sendKeys(lostReason, Keys.ENTER);

    }

    public void projectYearCompleted(String compYear) {
          this.yearCompletedElement.sendKeys(compYear, Keys.ENTER);
    }

    public void projectStartDate(String sDate) {

        this.startDateElement.sendKeys(sDate, Keys.ENTER);
    }

    public void projectEndDate(String eDate) {
    this.endDateElement.sendKeys(eDate, Keys.ENTER);
    }

    public void marketGroup(String mGrp) throws InterruptedException {
        this.mktGrpElement.sendKeys(mGrp,Keys.ENTER);

    }

    public void marketSegment(String mSeg) {
        this.mktSegElement.sendKeys(mSeg,Keys.ENTER);
    }

   
    public void applyFilter()
    {
        this.applyFilterElement.click();
    }

    public void clearFilter()
    {
        if(clrFilterElement.isEnabled())
        {
            this.clrFilterElement.click();
        }
        else
            System.out.println("Filter is not Enabled, select any value to enable the Clear filter");

    }
    
    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.applyFilterElement));
        return this.applyFilterElement.isDisplayed();
    }



    public void activeFiltersElement() {
        boolean elementVisible = false;
        while (!elementVisible) {
            try {
                if (this.filterElement.isDisplayed()) {
                    elementVisible = true;  // The element is found and visible
                } else {
                    this.zoomInFilterElement.click();  // Continue zooming out
                    Thread.sleep(500);
                }
            }
            catch (Exception e) {
                System.out.println("Error message "+e);
            }
        }
    }


    public boolean filterEnabled()
    {
        return filterElement.isEnabled();
    }


}
