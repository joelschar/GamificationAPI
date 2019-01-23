package io.gametown.api.api.spec.steps;

import io.gametown.api.api.DefaultApi;
import io.gametown.api.api.spec.helpers.Environment;
import io.gametown.api.api.ApiException;
import io.gametown.api.api.ApiResponse;

public class BadgeSteps {

    private Environment environment;
    private DefaultApi api;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public BadgeSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

}
