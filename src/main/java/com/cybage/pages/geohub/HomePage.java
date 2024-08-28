package com.cybage.pages.geohub;

import com.cybage.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends AbstractPage
{
    @FindBy(xpath = "//button[normalize-space()='Log In']")
    private WebElement loginBtnElement;


    public HomePage(WebDriver driver) {

        super(driver);
    }

    public void goTo(String url){

        this.driver.get(url);
    }
    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.loginBtnElement));
        return this.loginBtnElement.isDisplayed();
    }

    public void clickLoginButton()
    {
        this.loginBtnElement.click();
    }

}
