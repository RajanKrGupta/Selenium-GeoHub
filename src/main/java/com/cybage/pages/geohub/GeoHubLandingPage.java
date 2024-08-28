package com.cybage.pages.geohub;
import com.cybage.pages.AbstractPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class GeoHubLandingPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(GeoHubLandingPage.class);

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchElement;    // Search Input Field

    @FindBy(xpath = "//button[@class='style_projectRecentsBtn__93c92']")
    private WebElement recentProjectBtnElement;      // Recent Projects Button Field

    @FindBy(css = ".style_container__R5PTM")
    private List<WebElement> recentProjectsElements;       //List of all recent projects

    @FindBy(xpath = "//button[normalize-space(text())='Clear Recents']")
    private WebElement clearRecentProjectCTAElement;
    @FindBy(xpath = "//button[@aria-label='Close Panel']")
    private WebElement closePanelElement;          // close button present on Recent Projects card panel

    @FindBy(xpath = "//button[@class='style_switch__jOzmE ']")
    private WebElement unlockSearchAreaElement;           //Search Area locked toggle button

    @FindBy(xpath = "//button[@class='style_switch__jOzmE style_on__eKwSY']")
    private WebElement lockSearchAreaElement;       //Search Area lock toggle button
    @FindBy(xpath= "//span[normalize-space(text())='Search Area Lock']")
    private WebElement searchAreaTxtElement;

    @FindBy(xpath = "//button[@aria-label='Zoom In']")
    private WebElement zoomInElement;                  // Zoom in (+)

    @FindBy(xpath = "//button[@aria-label='Zoom Out']")
    private WebElement zoomOutElement;                 // Zoom out (-)

    @FindBy(xpath = "//div[@class='style_message__LpdfU']")
    private WebElement dismissMsgPopup;                  // Dismiss message pop up

    @FindBy(xpath = "//div[@class='style_message__LpdfU']/h4")
    private WebElement projectCountDismissMsg;            // Project count on Dismiss message pop up
    @FindBy(xpath = "//div[@class='style_message__LpdfU']/p")
    private WebElement dismissMsgTxt;          // Project message text on Dismiss message pop up

    @FindBy(xpath = "//button[@aria-label='Open Cards Panel']")
    private WebElement openCardPanelElement;          // Open arrow displayed on the Zoomed in state

    @FindBy(xpath = "//button[@aria-label='Close Cards Panel']")
    private WebElement closeCardPanelElement;          // Close arrow displayed on the Zoomed in state

    @FindBy(css = "div.style_results__AdCIZ span")
    private List<WebElement> projectCountCardPanel;            // Project count on card panel

    @FindBy(css = ".style_projectCard__UkLk6 ")
    private List<WebElement> projectListCardPanel;            // List of Projects on card panel

    @FindBy(xpath = "//button[normalize-space(text())='Back']")
    private WebElement projectDetailPageBackBtn;                   //Back button present on Project Detail page

    @FindBy(css = ".style_projectCard__UkLk6 .style_header__5QGSM h4")
    private List<WebElement> projectNamesCardPanel;            // Names of all project displayed on card panel on 1st load

    @FindBy(css = ".style_projectDetailView__FPqhK h2")
    private WebElement projectNameProjectDetailPage;            // Name of the project displayed on Project Detail page

    @FindBy(xpath = "//button[normalize-space(text())='Project Summary']")
    private WebElement projectDetailPageProjectSummaryTab;                   //Project Summary tab on Project Detail page

    @FindBy(xpath = "//button[normalize-space(text())='Design']")
    private WebElement projectDetailPageDesignTab;                   //Design tab on Project Detail page

    @FindBy(xpath = "//button[normalize-space(text())='Soils']")
    private WebElement projectDetailPageSoilsTab;                   //Soils tab on Project Detail page

    @FindBy(xpath = "//button[normalize-space(text())='Structure']")
    private WebElement projectDetailPageStructureTab;                   //Structure tab on Project Detail page


    @FindBy(xpath = "//button[normalize-space(text())='Bid']")
    private WebElement projectDetailPageBidTab;                   //Bid tab on Project Detail page


    @FindBy(css = ".style_geohubSearchPanel__N5w6p .style_resultsSection__W5gkd:nth-child(2) button")
    private List<WebElement> projectNameSuggestions;                //Search suggestion Project Name

    @FindBy(css = ".style_geohubSearchPanel__N5w6p .style_resultsSection__W5gkd:nth-child(4) button")
    private List<WebElement> recentItemSuggestions;                //Recent searched suggestion list

    @FindBy(xpath = "//button[@aria-label='Layers']")
    private WebElement layerElement;


    //*********************************************************************************

    public GeoHubLandingPage(WebDriver driver) {
        super(driver);
    }

   @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.dismissMsgTxt));
        return this.dismissMsgTxt.isDisplayed();
    }
    public void enterLocationSearchElement(String searchKey) throws InterruptedException {
        this.searchElement.sendKeys(searchKey, Keys.ENTER);
        Thread.sleep(5000);
        this.searchElement.sendKeys(Keys.ENTER);
    }

    public void enterProjectSearchElement(String searchKey) throws InterruptedException {
        this.searchElement.sendKeys(searchKey, Keys.ENTER);
        Thread.sleep(5000);
        this.searchElement.sendKeys(Keys.ENTER);
    }

    public void enterRecentProjectSearchElement(String searchKey) throws InterruptedException {
        this.searchElement.sendKeys(searchKey, Keys.ENTER);
        Thread.sleep(5000);

    }

    public void recentProjectCTA()
    {
        this.recentProjectBtnElement.click();
    }

    public int recentProjectsList() {
        int num = this.recentProjectsElements.size();
        log.info("Recent Projects Count is {}", num);
        return num;

    }

    public void clickRecentProject() {
        this.recentProjectsElements.get(0).click();
    }

    public void clearRecentProjectList() {
        this.wait.until(ExpectedConditions.visibilityOf(this.clearRecentProjectCTAElement));
        this.clearRecentProjectCTAElement.click();
    }

    public void closeRecentProjectPanel() {
        this.closePanelElement.click();
    }

    public boolean defaultZoomOut()   // return True in Aggregated state
    {
        return(!this.zoomOutElement.isEnabled());
    }

    public boolean defaultZoomIn()   //return True
    {
        return this.zoomInElement.isEnabled();
    }


    public boolean lockSearchArea() throws InterruptedException {
        boolean elementVisible = false;

        try {
            while (!elementVisible) {
                if (this.lockSearchAreaElement.isDisplayed()) {
                    elementVisible = true;  // The element is now visible
                } else {
                    // Scroll to the element using JavaScript Executor
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", this.layerElement);

                    // Optionally, wait to ensure the element is now visible and interactable
                    Thread.sleep(2000);  // Adjust the sleep time as needed
                }
            }
            // Additional wait to ensure element interaction
            Thread.sleep(3000);
            return this.lockSearchAreaElement.isEnabled();  // Return true if the element is enabled
        }
        catch (NoSuchElementException e) {
            return false;  // Element not found or some other exception, return false
        }
        catch (Exception e) {
            return false;  // Catch any other exceptions and return false
        }
    }


    public void clickLockSearchArea() throws InterruptedException {
        boolean elementVisible = false;

        try {
            while (!elementVisible) {
                if (this.lockSearchAreaElement.isDisplayed()) {
                    elementVisible = true;  // The element is now visible
                } else {
                    // Scroll to the element using JavaScript Executor
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", this.searchAreaTxtElement);

                    // Optionally, wait to ensure the element is now visible and interactable
                    Thread.sleep(1000);  // Adjust the sleep time as needed
                }
            }
            // Click the element once it's visible
            this.lockSearchAreaElement.click();
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException: Element not found.");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public boolean unLockSearchArea() throws InterruptedException {
        boolean elementVisible = false;

        try {
            while (!elementVisible) {
                if (this.unlockSearchAreaElement.isDisplayed()) {
                    elementVisible = true;  // The element is now visible
                } else {
                    // Scroll to the element using JavaScript Executor
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", this.searchAreaTxtElement);

                    // Optionally, wait to ensure the element is now visible and interactable
                    Thread.sleep(1000);  // Adjust the sleep time as needed
                }
            }
            // Return true if the element is enabled
            return this.unlockSearchAreaElement.isEnabled();
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException: Element not found.");
            return false;  // Element not found, return false
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return false;  // Catch any other exceptions and return false
        }
    }

    public boolean seachAreaText()
    {
        return this.searchAreaTxtElement.isDisplayed();
    }


    public boolean popupMessage() {
        this.wait.until(ExpectedConditions.visibilityOf(this.dismissMsgPopup));
        return this.dismissMsgPopup.isDisplayed();
    }

    public int getPopupMsgProjectCount() {
        int count = 0;
        try {
            this.wait.until(ExpectedConditions.visibilityOf(this.projectCountDismissMsg));
            String text = this.projectCountDismissMsg.getText();
            // Split the text into parts based on spaces
            String[] arr = text.split(" ");
            int size = arr.length;
            if (size >= 4) {
                count = Integer.parseInt(arr[3]);
                log.info("Project counts on Dismissible Popup Message: {}", count);
            } else {
                log.info("There are zero Projects on Dismissible Popup Message: {}", count);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("ArrayIndexOutOfBoundsException occurred: Not enough elements in the split text array", e);
            // Handle the exception (e.g., set count to 0 or take other action)
            count = 0;
        } catch (Exception e) {
            log.error("An unexpected error occurred while getting the project count on the card panel", e);
            // Handle other unexpected exceptions
        }

        return count;

    }

    public String getPopupMessage() {
        this.wait.until(ExpectedConditions.visibilityOf(this.dismissMsgTxt));
        return this.dismissMsgTxt.getText();
    }


    public int getCardPanelProjectCount() {
        int count = 0;
        try {
            this.wait.until(ExpectedConditions.visibilityOf(this.projectCountCardPanel.get(1)));
            String text = this.projectCountCardPanel.get(1).getText();
            System.out.println(text);

            // Split the text into parts based on spaces
            String[] arr = text.split(" ");
            int size = arr.length;
            if (size >= 3) {
                count = Integer.parseInt(arr[2]);
                log.info("Project counts on Card Panel: {}", count);
            } else {
                log.info("There are zero Projects on Card Panel: {}", count);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("ArrayIndexOutOfBoundsException occurred: Not enough elements in the split text array", e);
            // Handle the exception (e.g., set count to 0 or take other action)
            count = 0;
        } catch (Exception e) {
            log.error("An unexpected error occurred while getting the project count on the card panel", e);
            // Handle other unexpected exceptions
        }

        return count;
    }


    public String getProjectNameCardPanel(int n) {
        String pName = this.projectNamesCardPanel.get(n).getText();
        log.info("Project Name on Card Panel: {}", pName);
        return pName;
    }

    public boolean projectNameDisplayed()
    {
        return (!this.projectNamesCardPanel.isEmpty());
    }

    public void clickProjectCardPanel(int n) {
        this.projectListCardPanel.get(n).click();
    }

    public String getProjectNameTitleDetailPage() {
        String pNameDP = this.projectNameProjectDetailPage.getText();
        log.info("Project Name on Detail Page: {}", pNameDP);
        return pNameDP;

    }

    public boolean projectSummaryTab()   //Project Summary Tab on Project Detail page
    {
        this.wait.until(ExpectedConditions.visibilityOf(this.projectDetailPageProjectSummaryTab));
        return this.projectDetailPageProjectSummaryTab.isEnabled();
    }

    public boolean DesignTab()   //Design Tab on Project Detail page
    {
        return this.projectDetailPageDesignTab.isEnabled();
    }

    public boolean SoilsTab()   //Soils Tab on Project Detail page
    {
        return this.projectDetailPageSoilsTab.isEnabled();
    }

    public boolean StructureTab()   //Structure Tab on Project Detail page
    {
        return this.projectDetailPageStructureTab.isEnabled();
    }

    public boolean BidTab()   //Bid Tab on Project Detail page
    {
        return this.projectDetailPageBidTab.isEnabled();
    }

    public void clickBackButton() {

        this.projectDetailPageBackBtn.click();
    }

    public void projectSearchSuggestion(String pName) {
        // Ensure there are suggestions to iterate through
        int suggestionCount = this.projectNameSuggestions.size();
        try {
            for (int i = 0; i < suggestionCount; i++) {
                String txt = this.projectNameSuggestions.get(i).getText();

                // Use .equals() for string content comparison
                if (pName.equals(txt)) {
                    this.projectNameSuggestions.get(i).click();
                    break;  // Exit the loop once the match is found
                }
            }
        }
        catch(Exception e)
        {
           System.out.println("There is no Project suggested "+ e);
        }

    }

    public void recentSuggestedItemExecution(int recentItemNo) throws InterruptedException {
        // Ensure there are suggestions to iterate through
        int suggestionCount = this.recentItemSuggestions.size();
        log.info("Recent Searched Suggestion Count: {}", suggestionCount);
        this.recentItemSuggestions.get(recentItemNo).click();
        //Thread.sleep(3000);
    }


    public boolean presenceOfLayer()
    {
        return this.layerElement.isDisplayed();
    }

    public boolean presenceOfRecentItemSuggestion() throws InterruptedException {
        this.wait.until(ExpectedConditions.visibilityOfAllElements(recentItemSuggestions));
        return this.recentItemSuggestions.get(0).isDisplayed();


    }


}




