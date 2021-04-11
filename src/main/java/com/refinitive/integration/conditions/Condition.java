package com.refinitive.integration.conditions;

import io.restassured.response.Response;

public interface Condition {

    void check(Response response);

}

