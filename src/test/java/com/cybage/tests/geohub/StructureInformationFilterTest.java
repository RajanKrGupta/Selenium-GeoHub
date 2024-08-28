package com.cybage.tests.geohub;

import com.cybage.pages.geohub.*;
import com.cybage.tests.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StructureInformationFilterTest extends AbstractTest {
    @Test
    public void TensarHomePageTest1(){

        HomePage homePage = new HomePage(driver);
        homePage.goTo("https://geohubdev-hzgkhtf7fkh5d5e9.z01.azurefd.net/");
        Assert.assertTrue(homePage.isAt());
        homePage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.isAt();
        loginPage.enterUserCredentials("rajangu@cybage.com", "Cybage@12");
        loginPage.login();
        TensarHomePage thPage = new TensarHomePage(driver);
        thPage.isAt();
        thPage.geopierLink();
        thPage.geoHubLink();
        //Thread.sleep(3000);
    }



    @Test(dependsOnMethods = "TensarHomePageTest1")
    public void ColumnLoadTest() throws InterruptedException {
        FiltersPopUpStructureInfo si = new  FiltersPopUpStructureInfo(driver);
        si.openFilterPopUp();
        Thread.sleep(2000);
        si.scrollFunction();
        Thread.sleep(1000);
        Assert.assertTrue(si.isAt());
        si.setColLoad(999);
        si.applyFilter();

    }




}

