package com.epam.driverCapabilities;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.MalformedURLException;
import java.net.URI;

public class SelenoidWebDriverProvider implements WebDriverProvider {

    private String remoteDriverHost = System.getProperty("selenoid.remote.driver.host");

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        Configuration.browserSize = "1920x1080";

        try {
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create(remoteDriverHost + "/wd/hub").toURL(),
                    getBrowserAndVersion()
            );
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private DesiredCapabilities getBrowserAndVersion() {
        DesiredCapabilities browserCapabilities = new DesiredCapabilities();
        browserCapabilities.setCapability("enableVNC", true);
        String browserAndVersion = System.getProperty("browser.version");

        switch (browserAndVersion.split(":")[0]) {
            case "edge":
                browserCapabilities.setBrowserName("MicrosoftEdge");
                browserCapabilities.setVersion(browserAndVersion.split(":")[1]);
                break;
            case "firefox":
                browserCapabilities.setBrowserName("firefox");
                browserCapabilities.setVersion(browserAndVersion.split(":")[1]);
                break;
            case "safari":
                browserCapabilities.setBrowserName("safari");
                browserCapabilities.setVersion(browserAndVersion.split(":")[1]);
                break;
            case "chrome":
                browserCapabilities.setCapability(ChromeOptions.CAPABILITY, setDefaultChromeOptions());
                Configuration.browserCapabilities = browserCapabilities;
                browserCapabilities.setBrowserName("chrome");
                browserCapabilities.setVersion(browserAndVersion.split(":")[1]);
                break;
            default:
                browserCapabilities.setCapability(ChromeOptions.CAPABILITY, setDefaultChromeOptions());
                Configuration.browserCapabilities = browserCapabilities;
                browserCapabilities.setBrowserName("chrome");
                browserCapabilities.setVersion("89.0");
                break;
        }
        return browserCapabilities;
    }

    private ChromeOptions setDefaultChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-web-security");
        return options;
    }

}
