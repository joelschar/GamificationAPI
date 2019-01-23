package io.gametown.api.api.spec.steps;

import io.gametown.api.ApiException;
import io.gametown.api.ApiResponse;
import io.gametown.api.api.DefaultApi;
import io.gametown.api.api.spec.helpers.Environment;

public class EventSteps {

    private Environment environment;
    private DefaultApi api;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public EventSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

}
