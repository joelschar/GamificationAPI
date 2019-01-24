package io.gametown.api.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.gametown.api.api.DefaultApi;
import io.gametown.api.api.spec.helpers.Environment;
import io.gametown.api.ApiException;
import io.gametown.api.ApiResponse;
import io.gametown.api.api.Badge;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class BadgeSteps {

    private Environment environment;
    private DefaultApi api;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private Badge badge;

    public BadgeSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^There is an api server with a /badges endpoint$")
    public void there_is_an_api_server() throws Throwable {
        assertNotNull(api);
        throw new PendingException();
    }

    @Given("^I have a badge creation payload$")
    public void i_have_a_badge_creation_payload() throws Throwable {
        badge = new Badge();
        badge.setActive(true);
        badge.setName("badge01");
        throw new PendingException();
    }

    @When("^I POST a badge to endpoint /badges with an api token$")
    public void i_POST_a_badge_to_endpoint_badges_with_an_api_token() throws Throwable {
        try {
            lastApiResponse = api.createBadgeWithHttpInfo(environment.getApiKey(), badge);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
        throw new PendingException();
    }

    @Given("^I GET it to /badges with an api token$")
    public void i_GET_it_to_badges_with_an_api_token() throws Throwable {
        try {
            lastApiResponse = api.getBadgesWithHttpInfo(environment.getApiKey());
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();

        }
        throw new PendingException();
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
        throw new PendingException();
    }

}
