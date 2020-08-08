package com.bitbucket.util.automate.test.helper;

import com.bitbucket.util.automate.webdriver.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Ritika.Ghosh
 */
public class JavaScriptHelper {
    
    public static void scrollElementIntoView(WebElement element) {
        int extraPixels = 5;
        int yCoordinate = element.getLocation().y + extraPixels;
        int xCoordinate = element.getLocation().x + extraPixels;
        JavascriptExecutor executor = (JavascriptExecutor) Driver.getDriver();
        executor.executeScript("window.scrollTo(arguments[0],arguments[1])", xCoordinate, yCoordinate);
    }
    
    public static void click(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) Driver.getDriver();
        executor.executeScript("arguments[0].click();", webElement);
    }

    /**
     * Sets the value of an element, text for a text field.
     *
     * @param element
     * @param newValue
     */
    public static void setTextValue(WebElement element, String newValue) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].value =arguments[1];", element, newValue);
    }
    
    
}
