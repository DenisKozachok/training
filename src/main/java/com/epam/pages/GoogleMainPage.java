package com.epam.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selenide.*;

public class GoogleMainPage {
    private SelenideElement searchInput = $("[name='q']");
    private SelenideElement googleSearchButton = $("[name='btnK']");
    private SelenideElement iAmFeelingLuckyButton = $("[name='btnI']");

    @Step("Open Google page")
    public static GoogleMainPage open() {
        Selenide.open("https://www.google.com.ua/");
        return new GoogleMainPage();
    }

    @Step("Fill in query field \"{query}\"")
    public GoogleMainPage fillInSearchInput(String query) {
        searchInput.setValue(query);
        return new GoogleMainPage();
    }

    @Step("Google Search Button Click")
    public GoogleMainPage googleSearchButtonClick() {
        googleSearchButton.click();
        return new GoogleMainPage();
    }

    @Step("Google I Am Feeling Lucky Button Click")
    public GoogleMainPage iAmFeelingLuckyButtonClick() {
        iAmFeelingLuckyButton.click();
        return new GoogleMainPage();
    }


}
