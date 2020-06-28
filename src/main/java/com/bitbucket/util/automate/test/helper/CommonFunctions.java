package com.bitbucket.util.automate.test.helper;

import com.bitbucket.util.automate.webdriver.Driver;
import com.bitbucket.util.screen.BitBucketUI;
import java.util.ArrayList;
import com.bitbucket.util.screen.PullRequestInformation;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ritika.Ghosh
 */
public class CommonFunctions {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitBucketUI.class);

    BitbucketDashboard bitbucket = new BitbucketDashboard();

    LoginPage loginPage = new LoginPage();

    public void navigateToAndLoginToUserAccount(String url, String username, String password) {
        LOGGER.info("Navigate to user account");

        if (Driver.webDriver == null) {
            Driver.getDriver();
        }
        Driver.webDriver.get(url);
        Driver.webDriver.manage().window().maximize();

        LOGGER.info("Enter username and password");
        loginPage.signInAs(username, password);

    }

    public void navigateToSearchandRepository(List<String> repodetails) {
        LOGGER.info("Click on Search tab for every repository");
        for (String repodetail : repodetails) {
            LOGGER.info("Search Repository");
            bitbucket.repositorySearch(repodetail);
            LOGGER.info("Complete the flow of Pull Request");
            bitbucket.checkPullRequest("test", "master", repodetail);

        }

    }

    public List<String> repodetails() {
        String RepositoryName = "TrialRepo";
        String source = "dev";
        String destination = "dev";
        List<String> repodetails = new ArrayList<String>();
        repodetails.add(RepositoryName);
        repodetails.add(source);
        repodetails.add(destination);

        return repodetails;
    }

    public void generatePullRequest(List<String> projects, PullRequestInformation pullRequestInformation) {

        String fromBranch = pullRequestInformation.getFromBranch();
        String toBranch = pullRequestInformation.getToBranch();
        String usrPwd = pullRequestInformation.getPassword();
        String usrName = pullRequestInformation.getUserName();

    }

}
