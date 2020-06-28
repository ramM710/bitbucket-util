package com.bitbucket.util.automate.test.helper;

import com.bitbucket.util.automate.webdriver.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Ritika.Ghosh
 */
public class JavaScriptHelper {

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
    
    
//    public static String getDocumentReadyState() {
//        return (String)JavaScriptHelper.runScript("return document.readyState");
//    }
    
//    public static Object runScript(String scriptToRun, Object... args) {
//        if (args != null) {
//            for (int argNumber = 0; argNumber < args.length; argNumber++) {
//                if (!scriptToRun.contains("arguments[" + argNumber + "]")) {
//                    throw new IllegalArgumentException("scriptToRun does not contain \"arguments[" + argNumber + "]\"");
//                }
//            }
//        }
//        return Driver.getDriver().executeScript(scriptToRun, args);
//    }
}
