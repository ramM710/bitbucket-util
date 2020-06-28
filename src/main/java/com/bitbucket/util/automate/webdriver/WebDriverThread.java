package com.bitbucket.util.automate.webdriver;

import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 *
 * @author Ritika.Ghosh
 */
public class WebDriverThread {

    private static final Integer TIMES_TO_TRY = 5;

    private final DriverType defaultDriverType = DriverType.CHROME;

    private final String browser = System.getProperty("browser", defaultDriverType.toString()).toUpperCase();

    private final String operatingSystem = System.getProperty("os.name").toUpperCase();

    private final String systemArchitecture = System.getProperty("os.arch");

    private final boolean useRemoteWebDriver = Boolean.getBoolean("remoteDriver");

    private final boolean proxyEnabled = Boolean.getBoolean("proxyEnabled");

    private final String proxyHostname = System.getProperty("proxyHost");

    private final Integer proxyPort = Integer.getInteger("proxyPort");

    private final String proxyDetails = String.format("%s:%d", proxyHostname, proxyPort);

    private EventFiringWebDriver webdriver;

    private DriverType selectedDriverType;

    public DriverType getSelectedDriverType() {
        return selectedDriverType;
    }

    public EventFiringWebDriver getDriver() throws Exception {
        if (null == webdriver) {
            determineEffectiveDriverType();
            final DesiredCapabilities desiredCapabilities = selectedDriverType.getDesiredCapabilities();

            for (int i = 1; i <= TIMES_TO_TRY; i++) {
                //Make sure we are working with a "new" thread each time
                Thread thread = new Thread(
                        new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //Surround this code in order to set timeout
                            instantiateWebDriver(desiredCapabilities);
                        } catch (Exception e) {
                            //TODO Catch more specific exceptions
//                                    logger.error("Failed to instantiate WebDriver", e);
                        }
                    }
                }
                );
                thread.start();
                try {
                    thread.join(60 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (thread.isAlive()) {
//                    logger.debug("Stopping timed-out thread");
                    thread.interrupt();
//                    BasicTest.waitMs(1000);
                } else if (webdriver != null) {
//                    logger.debug("Got a driver after {} attempt(s)", i);
                    break;
                }
                if (thread.isAlive() && DriverType.valueOf(browser) == DriverType.valueOf("CHROME")) {
//                    logger.debug("Chrome thread didn't stop, killing process");
                    DriverType.killAllChrome();
                }
            }
        }
        return webdriver;
    }

    public void quitDriver() {
        if (null != webdriver) {
            webdriver.quit();
        }
    }

    private void determineEffectiveDriverType() {
        DriverType driverType = defaultDriverType;
        try {
            driverType = DriverType.valueOf(browser);
        } catch (IllegalArgumentException ignored) {
//            logger.error("Unknown driver specified, defaulting to '{}'...", driverType);
        } catch (NullPointerException ignored) {
//            logger.error("No driver specified, defaulting to '{}'...", driverType);
        }
        selectedDriverType = driverType;
    }

    private void instantiateWebDriver(DesiredCapabilities desiredCapabilities) {
//        logger.info(" ");
//        logger.info("Current Operating System: {}", operatingSystem);
//        logger.info("Current Architecture: {}", systemArchitecture);
//        logger.info("Current Browser Selection: {}", selectedDriverType);
//        logger.info(" ");

//        if (useRemoteWebDriver) {
//            logger.info("Using RemoteWebDriver... ");
//            URL seleniumGridURL = new URL(System.getProperty("gridURL"));
//            String desiredBrowserVersion = System.getProperty("desiredBrowserVersion");
//            logger.info("Using desiredBrowserVersion of  " + desiredBrowserVersion);
//            String desiredPlatform = System.getProperty("desiredPlatform");
//
//            logger.info("Using remote driver, Grid: {}", seleniumGridURL);
//
//            if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
//                desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
//            }
//
//            if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty()) {
//                desiredCapabilities.setVersion(desiredBrowserVersion);
//            }
//            logger.info("Using desiredCapabilities of {} ", desiredCapabilities);
//            webdriver = new EventFiringWebDriver(new RemoteWebDriver(seleniumGridURL, desiredCapabilities));
//        } else {
//            webdriver = new EventFiringWebDriver(selectedDriverType.getWebDriverObject(desiredCapabilities));
//        }
//        webdriver.register(new HighlightWebElementOnFind())
//                .register(new WaitForElement())
//                .register(new ScrollToElement());
        //Don't maximize this way in chrome, we do it in the options when starting
        if (DriverType.valueOf(browser) != DriverType.valueOf("CHROME")) {
            webdriver.manage().window().maximize();
        }
    }

    private static class Proxy {

        public Proxy() {
        }
    }
}
