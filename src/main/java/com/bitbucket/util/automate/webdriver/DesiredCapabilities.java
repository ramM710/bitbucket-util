/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitbucket.util.automate.webdriver;

import java.util.Map;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;

/**
 *
 * @author Ritika.Ghosh
 */
public class DesiredCapabilities implements Capabilities{
   

  public DesiredCapabilities() {
    // no-arg constructor
  }
  
   public DesiredCapabilities(String browser, String version, Platform platform) {
    setCapability(BROWSER_NAME, browser);
//    setCapability(VERSION, version);
    setCapability(PLATFORM, platform);
  }
  
   public static DesiredCapabilities chrome() {
    return new DesiredCapabilities(BrowserType.CHROME, "", Platform.ANY);
  }

    @Override
    public Map<String, Object> asMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getCapability(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
