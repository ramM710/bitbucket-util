/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitbucket.util.automate.webdriver;

import static com.bitbucket.util.automate.webdriver.CapabilityType.BROWSER_NAME;
import static com.bitbucket.util.automate.webdriver.CapabilityType.PLATFORM;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.BrowserType;

/**
 *
 * @author Ritika.Ghosh
 */
public class DesiredCapabilities implements Capabilities{
   

  public DesiredCapabilities() {
    // no-arg constructor
  }
   private final Map<String, Object> capabilities = new HashMap<>();
  
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
    
    public void setCapability(String capabilityName, String value) {
    if (CapabilityType.PLATFORM.equals(capabilityName)) {
      try {
        capabilities.put(capabilityName, Platform.fromString(value));
      } catch (WebDriverException ex) {
        capabilities.put(capabilityName, value);
      }
    } else {
      capabilities.put(capabilityName, value);
    }
  }
    
    public void setCapability(String capabilityName, Platform value) {
    capabilities.put(capabilityName, value);
  }
    
//    public void setCapability(String capabilityName, Platform value) {
//    capabilities.put(capabilityName, value);
//  }
}
