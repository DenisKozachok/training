package com.epam.ui;

import com.epam.pages.GoogleMainPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

public class GoogleSearchTest extends BaseUiTest {
    GoogleMainPage googleMainPage = new GoogleMainPage();

    @Severity(SeverityLevel.BLOCKER)
    @Epic("QA-456 Search")
    @Feature("QA-789 Automate cases for search page")
    @Story("QA-123 Automate cases for search page")
    @TmsLink(value = "1")
    @Description("Test google search field")
    @Test
    public void testGoogleSearch() {
        googleMainPage
                .open()
                .fillInSearchInput("epam")
                .googleSearchButtonClick();
    }

    @Severity(SeverityLevel.NORMAL)
    @Epic("QA-456 Search")
    @Feature("QA-789 Automate cases for search page")
    @Story("QA-123 Automate cases for search page")
    @TmsLink(value = "1")
    @Description("Test google search field with epmty request")
    @Test
    public void testGoogleSearchWithEmptyRequest() {
        googleMainPage
                .open()
                .fillInSearchInput("")
                .googleSearchButtonClick();
    }

    @Severity(SeverityLevel.NORMAL)
    @Epic("QA-456 Search")
    @Feature("QA-789 Automate cases for search page")
    @Story("QA-123 Automate cases for search page")
    @TmsLink(value = "1")
    @Description("Test I am Lucky Button")
    @Test
    public void testIamLuckyButton() {
        googleMainPage
                .open()
                .fillInSearchInput("rest")
                .iAmFeelingLuckyButtonClick();
    }
}
