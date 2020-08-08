package com.bitbucket.util.automate.test.helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.bitbucket.util.automate.webdriver.Driver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ritika.Ghosh
 */
public class BitbucketDashboard {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitbucketDashboard.class);

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

    @FindBy(how = How.XPATH, using = "//a[starts-with(@class,'Item-')]/span/span[1]")
    private static WebElement repositorySearchSelected;

    @FindBy(how = How.XPATH, using = "//*[contains(@name,'source')]")
    private static WebElement sourceRepository;

    @FindBy(how = How.XPATH, using = "(//*[contains(@class,'select2-input')])[4]")
    private static WebElement sourceOrDestinationInputFeild;

    @FindBy(how = How.ID, using = "id_source_group")
    private static WebElement sourceDialougueBox;

    @FindBy(how = How.ID, using = "id_dest_group")
    private static WebElement destinationDialougueBox;

    @FindBy(how = How.ID, using = "s2id_autogen2")
    private static WebElement sourceDropDown;

    @FindBy(how = How.XPATH, using = "//*[contains(@class,'select2-match')]")
    private static WebElement sourceHighlighDropDown;

    @FindBy(how = How.XPATH, using = "(//*[contains(@class,'select2-arrow')])[3]")
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

    @FindBy(how = How.ID, using = "s2id_autogen1")
    private static WebElement addReviewer;

//    @FindBy(how = How.ID, using = "select2-drop")
//    private static WebElement reviewerDropDown;
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

        Boolean isSrcElementVisible = sourceDialougueBox.isDisplayed();
        if (isSrcElementVisible) {
            Actions action = new Actions(Driver.getDriver());
            action.moveToElement(sourceDropDown).click().perform();
            List<WebElement> options = Driver.getDriver().findElements(By.xpath("//div[@class = 'select2-result-label']"));
            for (WebElement option : options) {
                if (option.getText().matches(source)) {
                    option.click();
                    break;
                }
            }
        }

    }

    public void selectDestinationRepositoryFromDropdown(String destination) {
        LOGGER.info("Click source drop down");

        Boolean isDestElementVisible = destinationDialougueBox.isDisplayed();
        if (isDestElementVisible) {
            Actions action = new Actions(Driver.getDriver());
            action.moveToElement(destinationDropDown).click().perform();
            List<WebElement> options = Driver.getDriver().findElements(By.xpath("//div[@class = 'select2-result-label']"));
            for (WebElement option : options) {
                if (option.getText().matches(destination)) {
                    option.click();
                    break;
                }
            }
        }
    }

    public void selectReviewerFromDropdown(String ReviewerListName) {
        LOGGER.info("Select the rewier from drop down");
        String[] ReviewerList = ReviewerListName.split(",");
        Actions action = new Actions(Driver.getDriver());
        for (String name : ReviewerList) {
            SeleniumTest.clearAndSetText(addReviewer, name);
            List<WebElement> options = Driver.getDriver().findElements(By.xpath("//div[@id = 'select2-drop']"));
            for (WebElement option : options) {
                if (option.getText().matches(name)) {
                    option.click();
                    break;
                }
            }
        }
    }

    public void repositorySearch(String repository) {
        SeleniumTest.waitForPageLoadToComplete();

        clickSearchBox();
        SeleniumTest.waitForPageLoadToComplete();

        searchRepository(repository);
        SeleniumTest.waitForPageLoadToComplete();

        if (repositorySearchSelected.isDisplayed()) {
            LOGGER.error("Repository {} is displayed ", repository);
            clickSearchResult();
        } else {
            LOGGER.error("Repository {} not found ", repository);
        }
        SeleniumTest.waitForPageLoadToComplete();
    }

    public List<String> checkPullRequest(String source, String destination, String repoName, String ReviewerNames) {
        List<String> conflictProjects = new ArrayList<>();
        SeleniumTest.waitForPageLoadToComplete();
        if (pullRequest.isDisplayed()) {
            clickPullRequest();
        } else {
            SeleniumTest.click(navigationButton);
        }

        SeleniumTest.waitForPageLoadToComplete();

        SeleniumTest.click(createPullRequest);
        SeleniumTest.waitForPageLoadToComplete();

        LOGGER.info("Click on source");
        selectSourceRepositoryFromDropdown(source);

        LOGGER.info("Click on destination");
        selectDestinationRepositoryFromDropdown(destination);
        SeleniumTest.waitForPageLoadToComplete();

        LOGGER.info("Add Review");
        selectReviewerFromDropdown(ReviewerNames);

        SeleniumTest.click(difference);
        String diffStatus = getDifferenceSTatus();

        if (diffStatus.contains("C")) {
            conflictProjects.add(repoName);
        } else {
            SeleniumTest.waitForPageLoadToComplete();
            SeleniumTest.click(closeBranchCheckbox);

            SeleniumTest.waitForPageLoadToComplete();
            SeleniumTest.click(createPR);

            SeleniumTest.waitMs(10000);
        }
        return conflictProjects;
    }
}
