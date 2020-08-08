package com.bitbucket.util.automate.test.helper;

import com.bitbucket.util.automate.webdriver.Driver;
import com.bitbucket.util.screen.BitBucketUI;
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

    String bitBucketUrl = "https://bitbucket.org/product/";

    public void initializeBitBucket(String username, String password) {
        LOGGER.info("Navigate to user account");

        if (Driver.webDriver == null) {
            Driver.getDriver();
        }
        Driver.webDriver.get(bitBucketUrl);
        Driver.webDriver.manage().window().maximize();

        LOGGER.info("Enter username and password");
        loginPage.signInAs(username, password);

    }

    public void generatePullRequest(List<String> repos, PullRequestInformation pullRequestInformation) {
        LOGGER.info("Navigate to user account");

        initializeBitBucket(pullRequestInformation.getUserName(), pullRequestInformation.getPassword());

        LOGGER.info("Click on Search tab for every repository");

        String fromBranch = pullRequestInformation.getFromBranch();
        String toBranch = pullRequestInformation.getToBranch();

        repos.add("trialrepo");
        repos.add("bitbucket-util_new");
        String reviewerName = pullRequestInformation.getReviewer();

        repos.forEach((var repoName) -> {
            LOGGER.info("Search Repository");
            bitbucket.repositorySearch(repoName);
            LOGGER.info("Complete the flow of Pull Request");
            bitbucket.checkPullRequest(fromBranch, toBranch, repoName, reviewerName);
        });
    }
}
