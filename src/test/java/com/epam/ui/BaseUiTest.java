package com.epam.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.epam.listener.Listener;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners({Listener.class})
public class BaseUiTest {

    @BeforeSuite
    public void setUpSuite() {
        Configuration.pageLoadTimeout = 10000;
        Configuration.timeout = 10000;

        if (checkOs().equals("windows")) {
            // TODO: 06.04.2021 Discuss this point with QA team
            Configuration.browser = "chrome";
            Configuration.startMaximized = true;
        } else {
            Configuration.browserSize = "1920x1080";
            Configuration.browser = "com.epam.driverCapabilities.SelenoidWebDriverProvider";
        }


        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    private String checkOs() {
        String ocBit = System.getProperty("os.arch").contains("64") ? "64" : "32";
        String ocName = System.getProperty("os.name").split(" ")[0].toLowerCase();
        return ocName;
    }

    protected <T> T at(Class<T> pageClass){
        return Selenide.page(pageClass);
    }

}
