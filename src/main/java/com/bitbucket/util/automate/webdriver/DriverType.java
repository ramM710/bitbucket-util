package com.bitbucket.util.automate.webdriver;

import com.bitbucket.util.automate.test.helper.SeleniumTest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

/**
 *
 * @author Ritika.Ghosh
 */
public enum DriverType implements DriverSetup {
    CHROME {
        public DesiredCapabilities getDesiredCapabilities() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            options.addArguments("start-maximized");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--no-sandbox");
            options.addArguments("enable-features=NetworkServiceInProcess");
            //options.addArguments("--disable-print-preview");

            Map<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
            chromePrefs.put("download.prompt_for_download", false);
            options.setExperimentalOption("prefs", chromePrefs);

            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));

            HashMap<String, String> chromePreferences = new HashMap<>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            chromePreferences.put("browser.download.dir", System.getProperty("user.home") + "/Downloads");

            capabilities.setCapability("chrome.prefs", chromePreferences);
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//            capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

            return addProxySettings(capabilities);
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
            if (System.getProperty("webdriver.chrome.driver") == null) {
                System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
                System.setProperty("webdriver.chrome.driver",
                        "C:\\Users\\ritika.ghosh\\Documents\\Software\\chromedriver.exe");
            }

            try {
                WebDriver driver = new ChromeDriver((Capabilities) capabilities);
                return driver;
            } catch (Exception e) {
                //TODO Catch more specific exceptions
//                logger.error("Exception while instantiating Chrome WebDriver", e);
                //Retry
                SeleniumTest.waitMs(5 * 1000);
                WebDriver driver = new ChromeDriver((Capabilities) capabilities);
                return driver;
            }
        }

        @Override
        public String toString() {
            return "Chrome";
        }

        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    /**
     *
     * @param capabilities
     * @return
     */
    protected DesiredCapabilities addProxySettings(DesiredCapabilities capabilities) {
        return capabilities;
    }

    public static void killAllChrome() {
        if (!Boolean.getBoolean("remoteDriver")) {
            //New chrome driver, attempt to kill old instances of chrome and chromedriver
//            logger.debug("Attempting to kill all chrome and driver instances");
            try {

                // T means kill process and children, F means force, FI is for filter
                Runtime.getRuntime().exec("taskkill /T /F /FI \"IMAGENAME eq chromedriver*\"");

                // Without a pause here the executable is still in use the majority of the time
                SeleniumTest.waitMs(1000);
            } catch (Exception e) {
//                logger.error("Exception while killing chrome and driver", e);
            }
        }
    }

}
