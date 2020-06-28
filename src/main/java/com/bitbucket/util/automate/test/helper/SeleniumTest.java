package com.bitbucket.util.automate.test.helper;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Ritika.Ghosh
 */
public class SeleniumTest {

    public static int waitForElementTimeout = 15;

    public static boolean selectByValueFromDropDown(
            WebElement dropdown, final String valueToSelect, int timeOut, int retry) {

        final Select selection = new Select(dropdown);

        for (WebElement we : selection.getOptions()) {
            if (we.getAttribute("value").equals(valueToSelect)) {
                selection.selectByValue(we.getAttribute("value"));
                return true;
            }
        }
        return true;

    }

    public static WebElement click(WebElement element) {
        JavaScriptHelper.click(element);
        return element;
    }

    public static List<WebElement> getSelectOptions(WebElement dropDown) {
        return new Select(dropDown).getOptions();
    }

    public static List<String> getSelectOptionStrings(WebElement dropDown) {
        return getSelectOptions(dropDown).stream().map(SeleniumTest::getTextByAttributeValue).map(String::trim).collect(Collectors.toList());
    }

    public static String getTextByAttributeValue(WebElement element) {
        String text = element.getText();
        if ((null == text) || "".equals(text)) {
            text = element.getAttribute("value");
        }

        return text;
    }

    public static void clearAndSetText(WebElement element, String text) {
        if (text != null) {
            element.clear();
            element.sendKeys(text);
        }
    }

    public static boolean isElementVisible(WebElement element) {
        try {
            element.isDisplayed();
        } catch (WebDriverException exc) {
            return false;
        }
        return false;
    }

    public static void waitMs(long milliseconds) {
        final long endTime = System.currentTimeMillis() + milliseconds;

        while (System.currentTimeMillis() < endTime) {
            try {
                Thread.sleep(Math.max(0L, endTime - System.currentTimeMillis()));
            } catch (InterruptedException ie) {

            }
        }
    }

    /**
     * Waits for the Page Load to complete before returning.
     */
    public static void waitForPageLoadToComplete() {
        SeleniumTest.waitMs(1000);
    }

    /**
     * Finds an element by the starting text
     *
     * @param dropDown
     * @param text
     * @return true if a match is found
     */
    public static boolean isElementWithContainingTextInDropDown(WebElement dropDown, String text) {
        List<WebElement> dropDownOptions = SeleniumTest.getSelectOptions(dropDown);
        for (WebElement option : dropDownOptions) {
            if (option.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }

}
