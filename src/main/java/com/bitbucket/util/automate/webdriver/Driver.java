/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitbucket.util.automate.webdriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 *
 * @author Ritika.Ghosh
 */
public class Driver {
    private static List<WebDriverThread> webDriverThreadPool = Collections.synchronizedList(new ArrayList<WebDriverThread>());
   private static List<ThreadLocal<WebDriverThread>> driverThreads = Collections.synchronizedList(new ArrayList<ThreadLocal<WebDriverThread>>());
   private static ThreadLocal<WebDriverThread> activeThread;

//    public static void setProperty() {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ritika.ghosh\\Documents\\Software\\chromedriver.exe");
//    }
//
//    public static WebDriver getDriver() {
//        setProperty();
//        WebDriver driver = new ChromeDriver();
//        return driver;
//    }
    
    public static EventFiringWebDriver getDriver() {
      try {
         return activeThread.get().getDriver();
      } catch (Exception e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      }
   }

   public static void quitBrowser() {
      WebDriver driver = getDriver();
      if (driver != null) {
         driver.close();
         driver.quit();
      }
   }
   public static DriverType getBrowserType() {
      return activeThread.get().getSelectedDriverType();
   }

}
