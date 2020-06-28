package com.bitbucket.util.automate.test.helper;

import com.bitbucket.util.automate.webdriver.Driver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

/**
 *
 * @author Ritika.Ghosh
 */
public class WaitUntil {

    private static final int DEFAULT_POLLING_TIMEOUT_SECONDS = 2;

    public static <V> V waitUntil(String message, int timeOutInSeconds, int retryInSeconds,
            final ExpectedCondition<V> expectedCondition,
            Class<? extends Exception>... ignoreExceptions) {

        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(Driver.getDriver())
                .withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
                .pollingEvery(retryInSeconds, TimeUnit.SECONDS);

        if (!message.isEmpty()) {
            fluentWait.withMessage(message);
        }
        return fluentWait.until(expectedCondition);
    }

    public static <V> V waitUntil(ExpectedCondition<V> expectedCondition, Class<? extends Exception>... ignoreExceptions) {
        return waitUntil("", SeleniumTest.waitForElementTimeout, DEFAULT_POLLING_TIMEOUT_SECONDS, expectedCondition, ignoreExceptions);
    }

}
