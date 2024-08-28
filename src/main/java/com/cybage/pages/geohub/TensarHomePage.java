package com.cybage.pages.geohub;
import com.cybage.pages.geohub.GeoHubLandingPage;

import com.cybage.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class TensarHomePage extends AbstractPage
{

    @FindBy(xpath = "//div[normalize-space()='Rajan']")
    private WebElement loggedinUserElement;

    @FindBy(xpath = "//div[@class='nav-item-label'][normalize-space()='Geopier']")
    private WebElement geopierElement;

    @FindBy(xpath = "//div[contains(text(),'GeoHub')]")
    private WebElement geohubElement;

    public TensarHomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.loggedinUserElement));
        return this.loggedinUserElement.isDisplayed();
    }

    public void geopierLink()
    {
        this.geopierElement.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.geohubElement));

    }

    public void geoHubLink()
    {
        this.geohubElement.click();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            // Handle the exception (e.g., log it)
            e.printStackTrace();
        }
    }
}

