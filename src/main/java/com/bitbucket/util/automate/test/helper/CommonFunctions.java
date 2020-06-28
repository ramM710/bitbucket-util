/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitbucket.util.automate.test.helper;

import com.bitbucket.util.automate.webdriver.Driver;
import com.bitbucket.util.screen.BitBucketUI;
import com.bitbucket.util.automate.test.helper.BitbucketDashboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ritika.Ghosh
 */
public class CommonFunctions {
     private static final Logger LOGGER = LoggerFactory.getLogger(BitBucketUI.class);
     BitbucketDashboard bitbucket = new BitbucketDashboard();
     LoginPage loginPage= new LoginPage();
    
    public void navigateToAndLoginToUserAccount(String url,String username, String password){
        LOGGER.info("Navigate to user account");
        Driver.getDriver().get(url);
        
        LOGGER.info("Enter username and password");
        loginPage.signInAs(username, password);
               
    }
    
    
    
    
}
