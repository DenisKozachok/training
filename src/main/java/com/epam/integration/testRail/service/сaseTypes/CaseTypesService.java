package com.epam.integration.testRail.service.сaseTypes;

import com.epam.integration.AssertableResponse;
import com.epam.integration.testRail.service.TestRailAPIService;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class CaseTypesService extends TestRailAPIService {

    @Step("Returns a list of available case types.")
    public AssertableResponse getTestCaseTypes() {
        Response register =
                baseSetupHeaders()
                        .when()
                        .param("api/v2/get_case_types", "")
                        .get()
                        .then().extract().response();
        return new AssertableResponse(register);
    }
}
