
package com.bitbucket.util.automate.webdriver;

/**
 *
 * @author Ritika.Ghosh
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Proxy;
public interface DriverSetup {
    
    WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities);

    DesiredCapabilities getDesiredCapabilities();
}
