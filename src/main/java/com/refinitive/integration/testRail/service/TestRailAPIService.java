package com.refinitive.integration.testRail.service;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestRailAPIService {

    String logixTestRail = "https://lxw.testrail.net/index.php?";
    String userName = "teampostman@gmail.com";
    String userPass = "zOKfbsf31231331231266plfiF2Xr";

    protected RequestSpecification baseSetupHeaders() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .auth().preemptive()
                .basic(userName, userPass)
                .baseUri(logixTestRail)
                .filters(new RequestLoggingFilter(),
                        new ResponseLoggingFilter(),
                        new AllureRestAssured());
    }

}