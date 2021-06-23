package com.miro.utils;

import static com.miro.properties.TestConfig.BASE_CONFIG;

import java.net.MalformedURLException;
import java.net.URL;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

@Log4j2
public class RemoteDriverManager {
    
    public WebDriver createInstance() {
        RemoteWebDriver remoteWebDriver = null;
        try {
            // a composition of the target grid address and port
            String gridURL = String.format("http://%s:%s/wd/hub", BASE_CONFIG.gridUrl(), BASE_CONFIG.gridPort());

            remoteWebDriver = new RemoteWebDriver(new URL(gridURL), defaultChromeOptions());
        } catch (MalformedURLException e) {
            log.error("Grid URL is invalid or Grid is not available");
        } catch (IllegalArgumentException e) {
            log.error(e);
        }

        return remoteWebDriver;
    }
    
    private static MutableCapabilities defaultChromeOptions() {
        ChromeOptions capabilities = new ChromeOptions();
        capabilities.setHeadless(true);
        capabilities.addArguments("start-maximized");

        return capabilities;
    }
}
