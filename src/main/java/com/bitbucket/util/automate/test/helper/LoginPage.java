/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitbucket.util.automate.test.helper;

import com.bitbucket.util.automate.webdriver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Ritika.Ghosh
 */
public class LoginPage {

    static {
        PageFactory.initElements(Driver.getDriver(), LoginPage.class);
    }

    @FindBy(how = How.ID, using = "username")
    private static WebElement username;

    @FindBy(how = How.ID, using = "password")
    private static WebElement password;

    @FindBy(how = How.LINK_TEXT, using = "Continue")
    private static WebElement continueButton;

    @FindBy(how = How.ID, using = "login-submit")
    private static WebElement loginButton;

    public void typeUserName(String user) {
        SeleniumTest.clearAndSetText(username, user);
    }

    public void typePassword(String pass) {
        SeleniumTest.clearAndSetText(password, pass);
    }

    public void clickContinueButton() {
        SeleniumTest.click(continueButton);
    }

    public BitbucketDashboard clickLoginButton() {
        SeleniumTest.click(loginButton);
        SeleniumTest.waitMs((1000 * SeleniumTest.waitForElementTimeout) / 20);
        return PageFactory.initElements(Driver.getDriver(), BitbucketDashboard.class);
    }

    public BitbucketDashboard signInAs(String email, String password) {
        SeleniumTest.waitMs((1000 * SeleniumTest.waitForElementTimeout) / 20);
        typeUserName(email);
        typePassword(password);
        return clickLoginButton();
    }
}
