package com.cybage.tests.geohub;
import com.aventstack.extentreports.Status;
import com.cybage.listener.TestListener;
import com.cybage.pages.geohub.*;
import com.cybage.tests.AbstractTest;
//import com.cybage.util.JsonUtil;
import com.cybage.util.Config;
import com.cybage.util.Report;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.cybage.util.Report.test;

@Listeners(TestListener.class)
public class CoreInformationFilterTest extends AbstractTest
{

    @Test
    public void UrlTest() {
        HomePage homePage = new HomePage(driver);
        homePage.goTo(Config.get("url"));
        Assert.assertTrue(homePage.isAt());
        test = Report.extent.createTest("Enter Application URL");
        test.log(Status.INFO,"Navigating to the Login Page");
        homePage.clickLoginButton();
    }

    @Test(dependsOnMethods = "UrlTest")
    public void LoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isAt());
        loginPage.enterUserCredentials(Config.get("userName"), Config.get("password"));
        loginPage.login();
        test = Report.extent.createTest("Logged into the Application");
        test.log(Status.INFO,"Login Successfully");

    }

    @Test(dependsOnMethods = "LoginTest")
    public void TensarHomePageTest() throws InterruptedException {
        TensarHomePage thPage = new TensarHomePage(driver);
        Assert.assertTrue(thPage.isAt());
        thPage.geopierLink();
        thPage.geoHubLink();
        //Thread.sleep(3000);
        test = Report.extent.createTest("GeoHub Module");
        test.log(Status.INFO,"Navigating to the GeoHub Page");
    }
    @Test(dependsOnMethods = "TensarHomePageTest")
    public void EnableFilterTest() throws InterruptedException {
        FiltersPopUpCoreInfo ci = new FiltersPopUpCoreInfo(driver);
        ci.activeFiltersElement();
        Assert.assertTrue(ci.filterEnabled(),"Filters visible on the GeoHub page");
    }

    @Test(dependsOnMethods = "EnableFilterTest")
    public void LostReasonFilterTest() throws InterruptedException {
        
        FiltersPopUpCoreInfo ci = new FiltersPopUpCoreInfo(driver);
        ci.openFilterPopUp();
        Thread.sleep(1000);
        Assert.assertTrue(ci.isAt());

        ci.lostReason("Didn't bid");
        Thread.sleep(1000);
        ci.applyFilter();

    }

    @Test(dependsOnMethods = "EnableFilterTest")
    public void YearCompletedFilterTest() throws InterruptedException {

        FiltersPopUpCoreInfo ci = new FiltersPopUpCoreInfo(driver);
        Thread.sleep(1000);
        ci.openFilterPopUp();
        Thread.sleep(1000);
        Assert.assertTrue(ci.isAt());
        ci.projectYearCompleted("2008");
        ci.applyFilter();

    }
    @Test(dependsOnMethods = "EnableFilterTest")
    public void ProjectStartDateFilterTest() throws InterruptedException {

        FiltersPopUpCoreInfo ci = new FiltersPopUpCoreInfo(driver);
        Thread.sleep(1000);
        ci.openFilterPopUp();
        Thread.sleep(1000);
        Assert.assertTrue(ci.isAt());
        ci.projectStartDate("06/21/2024");
        ci.applyFilter();
    }
    @Test(dependsOnMethods = "ProjectStartDateFilterTest")
    public void ProjectEndDateFilterTest() throws InterruptedException {

        FiltersPopUpCoreInfo ci = new FiltersPopUpCoreInfo(driver);
        Thread.sleep(1000);
        ci.openFilterPopUp();
        Thread.sleep(1000);
        Assert.assertTrue(ci.isAt());
        ci.projectEndDate("06/22/2024");
        ci.applyFilter();
    }

    @Test(dependsOnMethods = "ProjectEndDateFilterTest")
    public void MarketGroupFilterTest() throws InterruptedException {

        FiltersPopUpCoreInfo ci = new FiltersPopUpCoreInfo(driver);
        Thread.sleep(1000);
        ci.openFilterPopUp();
        Thread.sleep(1000);
        Assert.assertTrue(ci.isAt());
        ci.marketGroup("Airports");
        //ci.marketSegment("Option 2");
        ci.applyFilter();
    }
    
    @Test(dependsOnMethods = "MarketGroupFilterTest")
    public void MarketSegmentFilterTest() throws InterruptedException {

        FiltersPopUpCoreInfo ci = new FiltersPopUpCoreInfo(driver);
        Thread.sleep(1000);
        ci.openFilterPopUp();
        Thread.sleep(1000);
        //Assert.assertTrue(ci.isAt());
        ci.marketSegment("Agricultural");
        ci.applyFilter();
    }

    @Test(dependsOnMethods = "MarketSegmentFilterTest")
        public void CoreInformationClearFilterTest() throws InterruptedException {

            FiltersPopUpCoreInfo ci = new FiltersPopUpCoreInfo(driver);
            Thread.sleep(2000);
            ci.openFilterPopUp();
            Thread.sleep(1000);
            ci.clearFilter();

       }



}

