package com.epam.integration.testRail.service.runs;

import com.epam.integration.AssertableResponse;
import com.epam.integration.testRail.enumProperty.ProjectId;
import com.epam.integration.testRail.model.runs.addOrUpdateRuns.AddOrUpdateRunsModel;
import com.epam.integration.testRail.service.TestRailAPIService;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class RunsService extends TestRailAPIService {

    @Step("Creates a new test run.")
    public AssertableResponse postAddTestRun(ProjectId projectId, AddOrUpdateRunsModel createTestRunModel) {
        Response register =
                baseSetupHeaders()
                        .when()
                        .body(createTestRunModel)
                        .queryParam("api/v2/add_run/" + projectId.getProjectId())
                        .post("")
                        .then().extract().response();
        return new AssertableResponse(register);
    }

    @Step("Update a test run.")
    public AssertableResponse postUpdateTestRun(int testRunId, AddOrUpdateRunsModel updateTestRunModel) {
        Response register =
                baseSetupHeaders()
                        .when()
                        .body(updateTestRunModel)
                        .queryParam("/api/v2/update_run/" + testRunId)
                        .post("")
                        .then().extract().response();
        return new AssertableResponse(register);
    }

}
