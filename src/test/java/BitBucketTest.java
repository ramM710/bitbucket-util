
import org.testng.annotations.Test;
import com.bitbucket.util.automate.test.helper.BitbucketDashboard;
import com.bitbucket.util.screen.BitBucketUI;
import com.bitbucket.util.automate.test.helper.CommonFunctions;
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

        LOGGER.info("Enter Url of the bit bucket");
        commonFunctions.navigateToAndLoginToUserAccount(td.url, td.username, td.password);
    }
}
