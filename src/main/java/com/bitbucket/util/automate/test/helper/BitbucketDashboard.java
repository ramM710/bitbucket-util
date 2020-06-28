package com.bitbucket.util.automate.test.helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.bitbucket.util.automate.webdriver.Driver;
import com.bitbucket.util.screen.BitBucketUI;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ritika.Ghosh
 */
public class BitbucketDashboard {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitBucketUI.class);

    static {
        PageFactory.initElements(Driver.getDriver(), BitbucketDashboard.class);
    }

    @FindBy(how = How.ID, using = "quickSearchGlobalItem")
    private static WebElement searchBox;

    @FindBy(how = How.XPATH, using = "//*[@class='sc-bsbRJL fcmKRZ']")
    private static WebElement searchRepository;

    @FindBy(how = How.ID, using = "pullrequest-form")
    private static WebElement pullRequestForm;

    @FindBy(how = How.ID, using = "submitPrButton")
    private static WebElement createPR;

    @FindBy(how = How.XPATH, using = "//*[contains(@placeholder,'Search for repositories, code')]")
    private static WebElement repositorySearch;

    @FindBy(how = How.XPATH, using = "//a[contains(@class,'Item-z6qfkt-0 ilBuyM')]/span/span")
    private static WebElement repositorySearchSelected;

    @FindBy(how = How.XPATH, using = "//*[contains(@name,'source')]")
    private static WebElement sourceRepository;

    @FindBy(how = How.XPATH, using = "(//*[contains(@class,'select2-input')])[4]")
    private static WebElement sourceOrDestinationInputFeild;

    @FindBy(how = How.XPATH, using = "//*[contains(@id,'s2id_autogen2')]]")
    private static WebElement sourceDropDown;

    @FindBy(how = How.XPATH, using = "//*[contains(@class,'select2-match')]")
    private static WebElement sourceHighlighDropDown;

    @FindBy(how = How.XPATH, using = "(//*[contains(@class,'select2-arrow')])[3]]")
    private static WebElement destinationDropDown;

    @FindBy(how = How.XPATH, using = "//*[contains(@class,'ak-navigation-resize-button css-889cso')]")
    private static WebElement navigationButton;

    @FindBy(how = How.XPATH, using = "//*[contains(@id,'s2id_autogen5')]")
    private static WebElement destinationRepository;

    @FindBy(how = How.XPATH, using = "//*[contains(@id,'id_close_anchor_branch')]")
    private static WebElement closeBranchCheckbox;

    @FindBy(how = How.LINK_TEXT, using = "Create pull request")
    private static WebElement createPullRequest;

    @FindBy(how = How.LINK_TEXT, using = "Pull requests")
    private static WebElement pullRequest;

    @FindBy(how = How.LINK_TEXT, using = "Diff")
    private static WebElement difference;

    @FindBy(how = How.LINK_TEXT, using = "Commits")
    private static WebElement commits;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'diff-summary-lozenge')]")
    private static WebElement diffStatus;

    public void searchRepository(String repository) {
        SeleniumTest.clearAndSetText(repositorySearch, repository);
    }

    public void sourceRepository(String source) {
        SeleniumTest.clearAndSetText(sourceRepository, source);
    }

    public void destinationRepository(String destination) {
        SeleniumTest.clearAndSetText(destinationRepository, destination);
    }

    public void clickSearchBox() {
        SeleniumTest.waitForPageLoadToComplete();
        if (searchBox.isDisplayed()) {
            SeleniumTest.click(searchBox);
        } else {
            LOGGER.info("Element not visible");
        }

    }

    public String getDifferenceSTatus() {
        return diffStatus.getText().trim();
    }

    public void clickPullRequest() {
        SeleniumTest.click(pullRequest);
    }

    public void clickCheckbox() {
        SeleniumTest.click(closeBranchCheckbox);
    }

    public void clickSearchResult() {
        SeleniumTest.click(repositorySearchSelected);
    }

    public void selectSourceRepositoryFromDropdown(String source) {
        LOGGER.info("Click source drop down");
        Boolean value = SeleniumTest.isElementWithContainingTextInDropDown(sourceDropDown, source);
        if (value) {
            SeleniumTest.click(sourceDropDown);
            SeleniumTest.waitForPageLoadToComplete();
            SeleniumTest.clearAndSetText(sourceOrDestinationInputFeild, source);
            SeleniumTest.waitForPageLoadToComplete();
            SeleniumTest.click(sourceHighlighDropDown);
            SeleniumTest.waitForPageLoadToComplete();
        }

    }

    public void selectDestinationRepositoryFromDropdown(String destination) {
        LOGGER.info("Click source drop down");
        Boolean value = SeleniumTest.isElementWithContainingTextInDropDown(sourceDropDown, destination);
        if (value) {
            SeleniumTest.click(destinationDropDown);
            SeleniumTest.waitForPageLoadToComplete();
            SeleniumTest.clearAndSetText(sourceOrDestinationInputFeild, destination);
            SeleniumTest.waitForPageLoadToComplete();
            SeleniumTest.click(sourceHighlighDropDown);
            SeleniumTest.waitForPageLoadToComplete();
        }
    }

    public void repositorySearch(String repository) {
        SeleniumTest.waitForPageLoadToComplete();
        clickSearchBox();
        SeleniumTest.waitForPageLoadToComplete();
        searchRepository(repository);
        SeleniumTest.waitForPageLoadToComplete();
        if (repositorySearchSelected.isDisplayed()) {
            clickSearchResult();
        } else {
            LOGGER.info("Repository " + repository + " not found ");
        }
        SeleniumTest.waitForPageLoadToComplete();
    }

    public void checkPullRequest(String source, String destination, String Repository) {
        List<String> conflictProjects = new ArrayList<String>();
        if (pullRequest.isDisplayed()) {
            clickPullRequest();
        } else {
            SeleniumTest.click(navigationButton);
        }

        SeleniumTest.waitForPageLoadToComplete();
        SeleniumTest.click(createPullRequest);
        SeleniumTest.waitForPageLoadToComplete();
        LOGGER.info("Click on source");
//        selectSourceRepositoryFromDropdown(source);
        LOGGER.info("Click on destination");
        SeleniumTest.waitForPageLoadToComplete();
//        selectDestinationRepositoryFromDropdown(destination);
        SeleniumTest.waitForPageLoadToComplete();
        SeleniumTest.click(difference);
        String diffStatus = getDifferenceSTatus();
        if (diffStatus.contains("C")) {
            conflictProjects.add(Repository);
            Driver.quitBrowser();
        }
        SeleniumTest.waitForPageLoadToComplete();
        SeleniumTest.click(closeBranchCheckbox);
        SeleniumTest.waitForPageLoadToComplete();
        SeleniumTest.click(createPR);
        SeleniumTest.waitForPageLoadToComplete();
    }
}
