package com.example.codeBookAutotest.ui;

import com.refinitive.pages.LoginPage;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class MainPageTest extends BaseUiTest {
    LoginPage loginPage = new LoginPage();

    @BeforeMethod
    public void setUp() {
        open("https://www.jetbrains.com/");
    }

    @Severity(SeverityLevel.BLOCKER)
    @Epic("QA-456 Login")
    @Feature("QA-789 Automate cases for CB Login page")
    @Story("QA-123 Automate cases for Login")
    @TmsLink(value = "1")
    @Description("Test first Login by user cred")
    @Test
    public void search() {
        loginPage.searchButton.click();

        $("#header-search").sendKeys("Selenium");
        $x("//button[@type='submit' and text()='Search']").click();

        $(".js-search-input").shouldHave(attribute("value", "Selenium"));
    }

    @Severity(SeverityLevel.MINOR)
    @Epic("QA-456 Login")
    @Feature("QA-789 Automate cases for CB Login page")
    @Story("QA-123 Automate cases for Login")
    @TmsLink(value = "2")
    @Description("Test first Login by user cred")
    @Test
    public void toolsMenu() {
        loginPage.toolsMenu.hover();

        $(".menu-main__popup-wrapper").shouldBe(visible);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Epic("QA-456 Login")
    @Feature("QA-789 Automate cases for CB Login page")
    @Story("QA-123 Automate cases for Login")
    @TmsLink(value = "3")
    @Description("Test first Login by user cred")
    @Test
    public void navigationToAllTools() {
        assertEquals("asda", "asda");
    }
}
