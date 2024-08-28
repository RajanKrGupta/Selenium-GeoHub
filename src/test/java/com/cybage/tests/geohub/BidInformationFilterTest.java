package com.cybage.tests.geohub;

import com.cybage.pages.geohub.FiltersPopUpBidInfo;
import com.cybage.pages.geohub.HomePage;
import com.cybage.pages.geohub.LoginPage;
import com.cybage.pages.geohub.TensarHomePage;
import com.cybage.tests.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BidInformationFilterTest extends AbstractTest {

    @Test
    public void UrlTest(){
        HomePage homePage = new HomePage(driver);
        homePage.goTo("https://geohubdev-hzgkhtf7fkh5d5e9.z01.azurefd.net/");
        Assert.assertTrue(homePage.isAt());
        homePage.clickLoginButton();
    }

    @Test(dependsOnMethods = "UrlTest")
    public void LoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.isAt();
        loginPage.enterUserCredentials("rajangu@cybage.com", "Cybage@12");
        loginPage.login();

    }

    @Test(dependsOnMethods = "LoginTest")
    public void TensarHomePageTest() throws InterruptedException {
        TensarHomePage thPage = new TensarHomePage(driver);
        thPage.isAt();
        thPage.geopierLink();
        thPage.geoHubLink();
        //Thread.sleep(3000);
    }

    @Test(dependsOnMethods = "TensarHomePageTest")
    public void ContractValueMinTest() throws InterruptedException {

        FiltersPopUpBidInfo bi = new FiltersPopUpBidInfo(driver);
        bi.openFilterPopUp();
        Thread.sleep(1000);
        Assert.assertTrue(bi.isAt());
        bi.ContractValueMinimum(10);
        Thread.sleep(1000);
        bi.applyFilter();

    }


    @Test(dependsOnMethods = "ContractValueMinTest")
    public void ContractValueMaxTest() throws InterruptedException {

        FiltersPopUpBidInfo bi = new FiltersPopUpBidInfo(driver);
        bi.openFilterPopUp();
        Thread.sleep(1000);
        Assert.assertTrue(bi.isAt());
        bi.ContractValueMaximum(100);
        Thread.sleep(1000);
        bi.applyFilter();

    }
    @Test(dependsOnMethods = "ContractValueMaxTest")
    public void SelectPierSystemTest() throws InterruptedException {
        FiltersPopUpBidInfo bi = new FiltersPopUpBidInfo(driver);
        bi.openFilterPopUp();
        Thread.sleep(1000);
        Assert.assertTrue(bi.isAt());
        bi.setPierSystem("DX1");
        Thread.sleep(1000);
        bi.applyFilter();

    }

    @Test(dependsOnMethods = "SelectPierSystemTest")
    public void PierCountTest() throws InterruptedException {
        FiltersPopUpBidInfo bi = new FiltersPopUpBidInfo(driver);
        bi.openFilterPopUp();
        Thread.sleep(1000);
        Assert.assertTrue(bi.isAt());
        bi.setPierCount(99);
        Thread.sleep(1000);
        bi.applyFilter();

    }
    @Test(dependsOnMethods = "PierCountTest")
    public void MinimumPierPriceTest() throws InterruptedException {
        FiltersPopUpBidInfo bi = new FiltersPopUpBidInfo(driver);
        bi.openFilterPopUp();
        Thread.sleep(1000);
        Assert.assertTrue(bi.isAt());
        bi.setPricePierMin(100);
        Thread.sleep(1000);
        bi.applyFilter();

    }

    @Test(dependsOnMethods = "MinimumPierPriceTest")
    public void MaximumPierPriceTest() throws InterruptedException {
        FiltersPopUpBidInfo bi = new FiltersPopUpBidInfo(driver);
        Thread.sleep(2000);
        bi.openFilterPopUp();
        Thread.sleep(1000);
        Assert.assertTrue(bi.isAt());
        bi.setPricePierMax(11000);
        Thread.sleep(1000);
        bi.applyFilter();

    }

    @Test(dependsOnMethods = "MaximumPierPriceTest")
    public void MinimumFootPriceTest() throws InterruptedException {
        FiltersPopUpBidInfo bi = new FiltersPopUpBidInfo(driver);
        bi.openFilterPopUp();
        Thread.sleep(1000);
        Assert.assertTrue(bi.isAt());
        bi.setPriceFootMin(11);
        Thread.sleep(1000);
        bi.applyFilter();

    }

    @Test(dependsOnMethods = "MinimumFootPriceTest")
    public void MaximumFootPriceTest() throws InterruptedException {
        FiltersPopUpBidInfo bi = new FiltersPopUpBidInfo(driver);
        bi.openFilterPopUp();
        Thread.sleep(1000);
        Assert.assertTrue(bi.isAt());
        bi.setPriceFootMax(11111);
        Thread.sleep(1000);
        bi.applyFilter();

    }


    @Test(dependsOnMethods = "MaximumFootPriceTest")
        public void BidInformationClearFilterTest() throws InterruptedException {

            FiltersPopUpBidInfo bi = new FiltersPopUpBidInfo(driver);
            Thread.sleep(2000);
            bi.openFilterPopUp();
            Thread.sleep(1000);
            bi.clearFilter();

       }



}

