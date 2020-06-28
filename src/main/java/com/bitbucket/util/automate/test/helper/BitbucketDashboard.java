package com.bitbucket.util.automate.test.helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.bitbucket.util.automate.webdriver.Driver;

/**
 *
 * @author Ritika.Ghosh
 */
public class BitbucketDashboard {

    static {
        PageFactory.initElements(Driver.getDriver(), BitbucketDashboard.class);
    }

    @FindBy(how = How.ID, using = "quickSearchGlobalItem")
    private WebElement searchBox;

    @FindBy(how = How.XPATH, using = "//*[@class='sc-bsbRJL fcmKRZ']")
    private WebElement searchRepository;

    @FindBy(how = How.ID, using = "pullrequest-form")
    private WebElement pullRequestForm;

    @FindBy(how = How.XPATH, using = "//a[contains(@class,'Item-z6qfkt-1 ituNSk')]")
    private WebElement repositorySearch;

    @FindBy(how = How.XPATH, using = "//*[contains(@name,'source')]")
    private WebElement sourceRepository;

    @FindBy(how = How.XPATH, using = "//*[contains(@id,'s2id_autogen5')]")
    private WebElement destinationRepository;

    @FindBy(how = How.XPATH, using = "//*[contains(@id,'id_close_anchor_branch')]")
    private WebElement closeBranchCheckbox;

    @FindBy(how = How.LINK_TEXT, using = "Create pull request")
    private WebElement createPullRequest;

    @FindBy(how = How.LINK_TEXT, using = "Pull requests")
    private WebElement pullRequest;

    @FindBy(how = How.LINK_TEXT, using = "Diff")
    private WebElement difference;

    @FindBy(how = How.LINK_TEXT, using = "Commits")
    private WebElement commits;

    @FindBy(how = How.XPATH, using = "//*[contains(@class,'iterable-item file file-added')]/span")
    private WebElement diffStatus;

    @FindBy(how = How.LINK_TEXT, using = "Log in")
    private WebElement login;

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
        SeleniumTest.click(searchBox);
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

    public void clickLoginBox() {
        SeleniumTest.click(login);
    }

}
