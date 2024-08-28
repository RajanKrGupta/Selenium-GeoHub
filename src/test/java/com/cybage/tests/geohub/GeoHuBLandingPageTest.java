package com.cybage.tests.geohub;
import com.aventstack.extentreports.Status;
import com.cybage.listener.TestListener;
import com.cybage.pages.geohub.*;
import com.cybage.tests.AbstractTest;
import com.cybage.util.Config;
import com.cybage.util.Report;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.time.Duration;
import static com.cybage.util.Report.test;

@Listeners(TestListener.class)
public class GeoHuBLandingPageTest extends AbstractTest
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
    public void AggregatedState_RecentProjectsTest() throws InterruptedException {
        GeoHubLandingPage ghLandPage = new GeoHubLandingPage(driver);
        ghLandPage.isAt();
        ghLandPage.recentProjectCTA();
        int rpc = ghLandPage.recentProjectsList();
        if (rpc == 0) {
            System.out.println("There are no projects visited");
            Thread.sleep(2000);
            Assert.assertEquals(rpc,0,"PASS - On Aggregated State No Projects are present");
            test = Report.extent.createTest("Recent Projects on Aggregated State");
            test.log(Status.INFO,"On Aggregated State No Recent Project exist");
            ghLandPage.closeRecentProjectPanel();

        } else {
            System.out.println("Recent Projects are present on Card Panel");
            ghLandPage.clearRecentProjectList();
            Thread.sleep(2000);
            ghLandPage.closeRecentProjectPanel();
            Assert.fail("Fail - On Aggregated State Recent Projects are present");
        }
    }

    @Test(dependsOnMethods = "TensarHomePageTest")
    public void AggregatedState_ZoomInTest() {
        GeoHubLandingPage ghLandPage = new GeoHubLandingPage(driver);
        Assert.assertTrue(ghLandPage.defaultZoomIn(),"Zoom in (+) is enabled on default landing page");
        test = Report.extent.createTest("Zoom in (+) CTA on Aggregated State");
        test.log(Status.INFO,"Zoom in (+) is enabled on default landing page");

    }

    @Test(dependsOnMethods = "TensarHomePageTest")
    public void AggregatedState_ZoomOutTest() {
        GeoHubLandingPage ghLandPage = new GeoHubLandingPage(driver);
        Assert.assertTrue(ghLandPage.defaultZoomOut(),"Zoom Out (-) is disabled on default landing page");
        test = Report.extent.createTest("Zoom Out (-) CTA on Aggregated State");
        test.log(Status.INFO,"Zoom Out (-) is disabled on default landing page");
    }

    @Test(dependsOnMethods = "TensarHomePageTest")
    public void AggregatedState_DismissiblePopUpTest() {
        GeoHubLandingPage ghLandPage = new GeoHubLandingPage(driver);
        Assert.assertTrue(ghLandPage.popupMessage());
        //Thread.sleep(5000);
        int pcnt = ghLandPage.getPopupMsgProjectCount();
        System.out.println("Projects count is :"+pcnt);
        String msg= "Please search or zoom to see individual location data.";
        Assert.assertEquals(ghLandPage.getPopupMessage(),msg);
        test = Report.extent.createTest("Project count and Dismissible message on Aggregated State");
        test.log(Status.INFO,"Project count and Dismissible message are displayed on Aggregated State");
    }

    @Test(dependsOnMethods = "TensarHomePageTest")
    public void DefaultSearchAreaUnLockTest() throws InterruptedException {
        GeoHubLandingPage ghLandPage = new GeoHubLandingPage(driver);
        Assert.assertTrue(ghLandPage.unLockSearchArea(),"Search Area Toggle button is Unlocked on default landing page");
        test = Report.extent.createTest("Search Area Toggle CTA on default Aggregated State");
        test.log(Status.INFO,"Search Area Toggle button is Unlocked on default landing page or Aggregated State");
    }

    @Test(dependsOnMethods = "DefaultSearchAreaUnLockTest")
    public void AggregatedState_LocationSearchTest() throws InterruptedException {
        GeoHubLandingPage ghLandPage = new GeoHubLandingPage(driver);
        ghLandPage.enterLocationSearchElement(Config.get("location"));

        // Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // 15 seconds timeout
        wait.until(driver -> ghLandPage.projectNameDisplayed());
        int cntDM = ghLandPage.getPopupMsgProjectCount();
        int cntCP = ghLandPage.getCardPanelProjectCount();

        if (cntDM == cntCP) {
            // Positive case: Counts match
            Assert.assertEquals(cntDM, cntCP, "Project count matches on both the Dismissible popup and on card panel");
            test = Report.extent.createTest("Location Search Project count on Dismissible popup and on card panel");
            test.log(Status.INFO,"Project count matches on both the Dismissible popup and on card panel when location search done from Aggregated state");

        } else {
            // Negative case: Counts do not match
            Assert.fail("Project count does not match. Popup count: " + cntDM + ", Card panel count: " + cntCP);
        }
    }


    @Test(dependsOnMethods = "AggregatedState_LocationSearchTest")
    public void LocationSearch_LockedSearchAreaTest() throws InterruptedException {
        GeoHubLandingPage ghLandPage = new GeoHubLandingPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // 15 seconds timeout
        wait.until(driver -> ghLandPage.projectNameDisplayed());
        if(ghLandPage.lockSearchArea()) {
            Assert.assertTrue(ghLandPage.lockSearchArea(),"Location Search execution locked the Search Area Lock Toggle Button");
            test = Report.extent.createTest("Search Area Lock Toggle CTA locked - Location search");
            test.log(Status.INFO,"Location Search execution locked the Search Area Lock Toggle Button");
        }
        else {
            Assert.fail("The Search Area Lock Toggle Button is not locked after Location Search execution");
            test = Report.extent.createTest("Search Area Lock Toggle CTA locked - Location search");
            test.log(Status.INFO,"Location Search execution do not locked the Search Area Lock Toggle Button");
        }

    }

    @Test(dependsOnMethods = "LocationSearch_LockedSearchAreaTest")
    public void ProjectDetailPageTest() throws InterruptedException {
        GeoHubLandingPage ghLandPage = new GeoHubLandingPage(driver);

        String pNameCP = ghLandPage.getProjectNameCardPanel(1);
        ghLandPage.clickProjectCardPanel(1);

        Assert.assertTrue(ghLandPage.projectSummaryTab(),"Project Summary Tab is present on the Project detail page");
        Assert.assertTrue(ghLandPage.DesignTab(),"Design Tab is present on the Project detail page");
        Assert.assertTrue(ghLandPage.SoilsTab(),"Soils Tab is present on the Project detail page");
        Assert.assertTrue(ghLandPage.StructureTab(),"Structure Tab is present on the Project detail page");
        Assert.assertTrue(ghLandPage.BidTab(),"Bid Tab is present on the Project detail page");
        String pNameDP = ghLandPage.getProjectNameTitleDetailPage();
        if(pNameCP.equals(pNameDP))
        {
            Assert.assertEquals(pNameCP,pNameDP,"Project Names matching on both the CardPanel and on Project Detail Page");
            test = Report.extent.createTest("Project Name matching on CardPanel and on Project Detail Page");
            test.log(Status.INFO,"Project Names matching on both the CardPanel and on Project Detail Page");
        }
        else
        {
            Assert.fail("Project Names Not matching on the CardPanel and on the Project Detail Page");
        }

        ghLandPage.clickBackButton();
        ghLandPage.clickLockSearchArea();
    }

    @Test(dependsOnMethods = "ProjectDetailPageTest")
    public void RecentProjectDetailPageTest() throws InterruptedException {
        GeoHubLandingPage ghLandPage = new GeoHubLandingPage(driver);
        ghLandPage.recentProjectCTA();
        int rPC= ghLandPage.recentProjectsList();
        System.out.println("Recent Project count is :"+rPC);
        Thread.sleep(1000);
        ghLandPage.clickRecentProject();
        Assert.assertTrue(ghLandPage.projectSummaryTab(),"Project Summary Tab is present on the Project detail page");
        Assert.assertTrue(ghLandPage.DesignTab(),"Design Tab is present on the Project detail page");
        Assert.assertTrue(ghLandPage.SoilsTab(),"Soils Tab is present on the Project detail page");
        Assert.assertTrue(ghLandPage.StructureTab(),"Structure Tab is present on the Project detail page");
        Assert.assertTrue(ghLandPage.BidTab(),"Bid Tab is present on the Project detail page");
        test = Report.extent.createTest("Recent Project detail page Tabs");
        test.log(Status.INFO,"The Recent Project contains all the required Tabs");
        ghLandPage.clickBackButton();
    }


    @Test(dependsOnMethods = "RecentProjectDetailPageTest")
    public void ClearRecentProjectListTest() {
        GeoHubLandingPage ghLandPage = new GeoHubLandingPage(driver);
        ghLandPage.recentProjectCTA();
        ghLandPage.clearRecentProjectList();
        int n = ghLandPage.recentProjectsList();
        Assert.assertEquals(n,0,"No project is present after clicking on Clear Recent link");
        test = Report.extent.createTest("Clear Recent Project Link Functionality");
        test.log(Status.INFO,"Clear Recent Project Link Functionality working fine");
        ghLandPage.closeRecentProjectPanel();
    }

    @Test(dependsOnMethods = "ClearRecentProjectListTest")
    public void ProjectSearchExecutionTest() throws InterruptedException {
        GeoHubLandingPage ghLandPage = new GeoHubLandingPage(driver);

        ghLandPage.enterProjectSearchElement(Config.get("projectName"));
 //       Thread.sleep(5000);
//        ghLandPage.projectSearchSuggestion(Config.get("projectName"));
//        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // 20 seconds timeout
        wait.until(driver -> ghLandPage.projectNameDisplayed());
        //Thread.sleep(4000);
        test = Report.extent.createTest("Project Search Execution");
        test.log(Status.INFO,"Project Search Execution passed");
    }
    @Test(dependsOnMethods = "ProjectSearchExecutionTest")
    public void ProjectSearch_LockedSearchAreaTest() throws InterruptedException {
        GeoHubLandingPage ghLandPage = new GeoHubLandingPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // 15 seconds timeout
        wait.until(driver -> ghLandPage.projectNameDisplayed());
        if(ghLandPage.lockSearchArea())
        {
            Assert.assertTrue(ghLandPage.lockSearchArea(),"Project Search execution locked the Search Area Lock Toggle Button");
            // Thread.sleep(3000);
            test = Report.extent.createTest("Search Area Lock Toggle CTA locked - Project search");
            test.log(Status.INFO,"Project Search execution locked the Search Area Lock Toggle Button");
        }
        else {
            Assert.fail("The Search Area Lock Toggle Button is not locked after Project Search execution");
            test = Report.extent.createTest("Search Area Lock Toggle CTA locked - Project search");
            test.log(Status.INFO,"Project Search execution do not locked the Search Area Lock Toggle Button");
        }
        ghLandPage.clickLockSearchArea();
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "ProjectSearchExecutionTest")
    public void RecentSearchExecutionTest() throws InterruptedException {
        GeoHubLandingPage ghLandPage = new GeoHubLandingPage(driver);
        ghLandPage.enterRecentProjectSearchElement("test");
        if(ghLandPage.presenceOfRecentItemSuggestion())
        {
            ghLandPage.recentSuggestedItemExecution(0);
             //Assert.assertTrue(ghLandPage.presenceOfRecentItemSuggestion(),"Recent project/location search execution");
            test = Report.extent.createTest("Recent Search Execution");
            test.log(Status.INFO,"Recent Search Execution passed");
        }
        else {
            Assert.fail("There are no recent item displayed");
            
        }

        Thread.sleep(7000);
    }

    @Test(dependsOnMethods = "RecentSearchExecutionTest")
    public void RecentProjectSearch_LockedSearchAreaTest() throws InterruptedException {
        GeoHubLandingPage ghLandPage = new GeoHubLandingPage(driver);
        if(ghLandPage.lockSearchArea())
        {
            Assert.assertTrue(ghLandPage.lockSearchArea(),"Recent Project Search execution locked the Search Area Lock Toggle Button");
            Thread.sleep(5000);
            test = Report.extent.createTest("Recent Search Area Lock Toggle CTA locked - Recent Project search");
            test.log(Status.INFO,"Recent Project Search execution locked the Search Area Lock Toggle Button");
        }
        else {
            Assert.fail("The Recent Search Area Lock Toggle Button is not locked after Project Search execution");
            test = Report.extent.createTest("Recent Search Area Lock Toggle CTA locked - Project search");
            test.log(Status.INFO,"Recent Project Search execution do not locked the Search Area Lock Toggle Button");
        }
        ghLandPage.clickLockSearchArea();
        Thread.sleep(6000);
    }

    @Test(dependsOnMethods = "RecentProjectSearch_LockedSearchAreaTest")
    public void FilterPresenceTest() throws InterruptedException {
        FiltersPopUpCoreInfo filterCoreInfo = new FiltersPopUpCoreInfo(driver);


        if(filterCoreInfo.filterEnabled())
        {
            Assert.assertTrue(filterCoreInfo.filterEnabled(),"Filter Element is enabled on the page");
            // Thread.sleep(3000);
            test = Report.extent.createTest("Filter Element is enabled on the page");
            test.log(Status.INFO,"Filter Element is enabled on the page");
        }
        else {
            Assert.fail("Filter Element is not displayed on the page");
            test = Report.extent.createTest("Filter Element is not displayed on the page");
            test.log(Status.INFO,"Filter Element is not displayed on the page");
        }


    }

    @Test(dependsOnMethods = "FilterPresenceTest")
    public void LostReasonFilterTest() throws InterruptedException {
        GeoHubLandingPage ghLandPage = new GeoHubLandingPage(driver);
        FiltersPopUpCoreInfo ci = new FiltersPopUpCoreInfo(driver);
        ci.openFilterPopUp();
        Thread.sleep(1000);
        Assert.assertTrue(ci.isAt());

        ci.lostReason("Unknown");
        Thread.sleep(2000);
        ci.applyFilter();
        Thread.sleep(5000);
        int projectCountPopUP= ghLandPage.getPopupMsgProjectCount();
        int projectCountCardPanel= ghLandPage.getCardPanelProjectCount();
        Assert.assertEquals(projectCountPopUP,projectCountCardPanel,"Project count are same are Filter Application");
        test = Report.extent.createTest("Lost Reason Filter applied");
        test.log(Status.INFO,"Lost Reason Filter applied");

    }



}
