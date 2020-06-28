
import org.testng.annotations.Test;
import com.bitbucket.util.automate.test.helper.BitbucketDashboard;
import com.bitbucket.util.screen.BitBucketUI;
import com.bitbucket.util.automate.test.helper.CommonFunctions;
import com.bitbucket.util.screen.PullRequestInformation;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ritika.Ghosh
 */
public class BitBucketTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitBucketUI.class);

    @Test
    public void completeBitBucketFlow() {

        LOGGER.info("Create objects of all classes to be used");
        BitbucketDashboard bitbucket = new BitbucketDashboard();
        CommonFunctions commonFunctions = new CommonFunctions();
        TestData td = new TestData();

        LOGGER.info("Enter Url of the bit bucket and vigate to bit bucket account");

        String username = "ghoshritika15@gmail.com";
        String password = "Starlight@911";

        LOGGER.info("Navigate to search tab and search for reporsitory");
        List<String> repos = new ArrayList<>();

        PullRequestInformation pullRequestInformation = new PullRequestInformation(username,
                password, "toBranch", "fromBranch", "reviewer");

        commonFunctions.generatePullRequest(repos, pullRequestInformation);
    }
}
