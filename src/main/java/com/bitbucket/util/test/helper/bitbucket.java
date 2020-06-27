/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitbucket.util.test.helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.bitbucket.webdriver.Driver;

/**
 *
 * @author Ritika.Ghosh
 */
public class bitbucket {
    static {
        PageFactory.initElements(Driver.getWebDriver(), bitbucket.class);
	}
    
    @FindBy(how = How.ID, using = "quickSearchGlobalItem")
    private static WebElement searchBox;
    
    @FindBy(how = How.XPATH, using = "//*[@class='sc-bsbRJL fcmKRZ']")
    private static WebElement searchRepository;
   
    
    @FindBy(how = How.ID, using = "pullrequest-form")
    private static WebElement pullRequestForm;
    
    @FindBy(how = How.XPATH, using = "//a[contains(@class,'Item-z6qfkt-1 ituNSk')]")
    private static WebElement repositorySearch;
    
    @FindBy(how = How.XPATH, using = "//*[contains(@name,'source')]")
    private static WebElement sourceRepository;
    
    @FindBy(how = How.XPATH, using = "//*[contains(@id,'s2id_autogen5')]")
    private static WebElement destinationRepository;
    
    @FindBy(how = How.XPATH, using = "//*[contains(@id,'id_close_anchor_branch')]")
    private static WebElement closeBranchCheckbox;
    
    @FindBy(how = How.LINK_TEXT, using = "Create pull request")
    private static WebElement createPullRequest;
    
    @FindBy(how = How.LINK_TEXT, using = "Diff")
    private static WebElement difference;
    
    @FindBy(how = How.LINK_TEXT, using = "Commits")
    private static WebElement commits;
    
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'iterable-item file file-added')]/span")
    private static WebElement diffStatus;
    
}
