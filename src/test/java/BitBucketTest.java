
import org.testng.annotations.Test;
import com.bitbucket.util.automate.test.helper.BitbucketDashboard;
import com.bitbucket.util.screen.BitBucketUI;
import com.bitbucket.util.automate.test.helper.CommonFunctions;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Ignore;

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
        commonFunctions.navigateToAndLoginToUserAccount(td.url, td.username, td.password);

        LOGGER.info("Navigate to search tab and search for reporsitory");
        List repoList = commonFunctions.repodetails();

        commonFunctions.navigateToSearchandRepository(repoList);
    }
}
