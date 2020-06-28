/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitbucket.util.automate.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Ritika.Ghosh
 */
public class Driver {

    public static void setProperty() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ritika.ghosh\\Documents\\Software\\chromedriver.exe");
    }

    public static WebDriver getDriver() {
        setProperty();
        WebDriver driver = new ChromeDriver();
        return driver;
    }

}
