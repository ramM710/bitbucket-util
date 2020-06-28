package com.bitbucket.util.automate.test.helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.bitbucket.util.automate.webdriver.Driver;
import com.bitbucket.util.screen.BitBucketUI;
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

    @FindBy(how = How.XPATH, using = "//*[contains(@placeholder,'Search for repositories, code')]")
    private static WebElement repositorySearch;

    @FindBy(how = How.XPATH, using = "//a[contains(@class,'Item-z6qfkt-0 ilBuyM')]/span/span")
    private static WebElement repositorySearchSelected;

    @FindBy(how = How.XPATH, using = "//*[contains(@name,'source')]")
    private static WebElement sourceRepository;

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

    @FindBy(how = How.XPATH, using = "//*[contains(@class,'iterable-item file file-added')]/span")
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
        SeleniumTest.click(repositorySearch);
    }

    public void clickCheckbox() {
        SeleniumTest.click(closeBranchCheckbox);
    }
    
    public void clickSearchResult() {
        SeleniumTest.click(repositorySearchSelected);
    }

    public void repositorySearch(String repository) {
        clickSearchBox();
        SeleniumTest.waitForPageLoadToComplete();
        searchRepository(repository);
        clickSearchResult();

    }

}
