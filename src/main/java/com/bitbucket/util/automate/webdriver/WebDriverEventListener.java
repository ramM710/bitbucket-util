package com.bitbucket.util.automate.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Ritika.Ghosh
 */
public interface WebDriverEventListener {

    void beforeNavigateTo(String url, WebDriver driver);

    /**
     * Called after {@link org.openqa.selenium.WebDriver#get get(String url)}
     * respectively
     * {@link org.openqa.selenium.WebDriver.Navigation#to navigate().to(String url)}.
     * Not called, if an exception is thrown.
     *
     * @param url URL
     * @param driver WebDriver
     */
    void afterNavigateTo(String url, WebDriver driver);

    /**
     * Called before
     * {@link org.openqa.selenium.WebDriver.Navigation#back navigate().back()}.
     *
     * @param driver WebDriver
     */
    void beforeNavigateBack(WebDriver driver);

    /**
     * Called after
     * {@link org.openqa.selenium.WebDriver.Navigation navigate().back()}. Not
     * called, if an exception is thrown.
     *
     * @param driver WebDriver
     */
    void afterNavigateBack(WebDriver driver);

    /**
     * Called before
     * {@link org.openqa.selenium.WebDriver.Navigation#forward navigate().forward()}.
     *
     * @param driver WebDriver
     */
    void beforeNavigateForward(WebDriver driver);

    /**
     * Called after
     * {@link org.openqa.selenium.WebDriver.Navigation#forward navigate().forward()}.
     * Not called, if an exception is thrown.
     *
     * @param driver WebDriver
     */
    void afterNavigateForward(WebDriver driver);

    /**
     * Called before
     * {@link org.openqa.selenium.WebDriver.Navigation#refresh navigate().refresh()}.
     *
     * @param driver WebDriver
     */
    void beforeNavigateRefresh(WebDriver driver);

    /**
     * Called after
     * {@link org.openqa.selenium.WebDriver.Navigation#refresh navigate().refresh()}.
     * Not called, if an exception is thrown.
     *
     * @param driver WebDriver
     */
    void afterNavigateRefresh(WebDriver driver);

    /**
     * Called before {@link WebDriver#findElement WebDriver.findElement(...)},
     * or {@link WebDriver#findElements WebDriver.findElements(...)}, or {@link WebElement#findElement
     * WebElement.findElement(...)}, or
     * {@link WebElement#findElement WebElement.findElements(...)}.
     *
     * @param element will be <code>null</code>, if a find method of
     * <code>WebDriver</code> is called.
     * @param by locator being used
     * @param driver WebDriver
     */
    void beforeFindBy(By by, WebElement element, WebDriver driver);

    /**
     * Called after {@link WebDriver#findElement WebDriver.findElement(...)}, or
     * {@link WebDriver#findElements WebDriver.findElements(...)}, or {@link WebElement#findElement
     * WebElement.findElement(...)}, or
     * {@link WebElement#findElement WebElement.findElements(...)}.
     *
     * @param element will be <code>null</code>, if a find method of
     * <code>WebDriver</code> is called.
     * @param by locator being used
     * @param driver WebDriver
     */
    void afterFindBy(By by, WebElement element, WebDriver driver);

    /**
     * Called before {@link WebElement#click WebElement.click()}.
     *
     * @param driver WebDriver
     * @param element the WebElement being used for the action
     */
    void beforeClickOn(WebElement element, WebDriver driver);

    /**
     * Called after {@link WebElement#click WebElement.click()}. Not called, if
     * an exception is thrown.
     *
     * @param driver WebDriver
     * @param element the WebElement being used for the action
     */
    void afterClickOn(WebElement element, WebDriver driver);

    /**
     * Called before {@link WebElement#clear WebElement.clear()}, {@link WebElement#sendKeys
     * WebElement.sendKeys(...)}.
     *
     * @param driver WebDriver
     * @param element the WebElement being used for the action
     */
    void beforeChangeValueOf(WebElement element, WebDriver driver);

    /**
     * Called after {@link WebElement#clear WebElement.clear()}, {@link WebElement#sendKeys
     * WebElement.sendKeys(...)}}. Not called, if an exception is thrown.
     *
     * @param driver WebDriver
     * @param element the WebElement being used for the action
     */
    void afterChangeValueOf(WebElement element, WebDriver driver);

    /**
     * Called before {@link org.openqa.selenium.remote.RemoteWebDriver#executeScript(java.lang.String, java.lang.Object[])
     * }
     *
     * @param driver WebDriver
     * @param script the script to be executed
     */
    // Previously: Called before {@link WebDriver#executeScript(String)}
    // See the same issue below.
    void beforeScript(String script, WebDriver driver);

    /**
     * Called after {@link org.openqa.selenium.remote.RemoteWebDriver#executeScript(java.lang.String, java.lang.Object[])
     * }. Not called if an exception is thrown
     *
     * @param driver WebDriver
     * @param script the script that was executed
     */
    // Previously: Called after {@link WebDriver#executeScript(String)}. Not called if an exception is thrown
    // So someone should check if this is right.  There is no executeScript method
    // in WebDriver, but there is in several other places, like this one
    void afterScript(String script, WebDriver driver);

    /**
     * Called whenever an exception would be thrown.
     *
     * @param driver WebDriver
     * @param throwable the exception that will be thrown
     */
    void onException(Throwable throwable, WebDriver driver);
}
