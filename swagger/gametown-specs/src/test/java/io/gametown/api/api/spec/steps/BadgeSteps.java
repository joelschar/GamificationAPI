package io.gametown.api.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.gametown.api.api.DefaultApi;
import io.gametown.api.api.spec.helpers.Environment;
import io.gametown.api.ApiException;
import io.gametown.api.ApiResponse;
import io.gametown.api.api.dto.Badge;

import java.util.List;

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
    private List<Badge> badgesList;
    private Badge badgetoUpdate;
    private Badge badgetoDelete;

    public BadgeSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^There is an api server with a /badges endpoint$")
    public void there_is_an_api_server() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have a badge creation payload$")
    public void i_have_a_badge_creation_payload() throws Throwable {
        badge = new Badge();
        badge.setActive(true);
        badge.setName("badge01");
    }

    @When("^I POST a badge to endpoint /badges and an api token$")
    public void i_POST_a_badge_to_endpoint_badges_and_an_api_token() throws Throwable {
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
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }

    @Given("^I have a badge created and a badge getting payload$")
    public void i_have_a_badge_created_and_a_badge_getting_payload() throws Throwable {
        badge = new Badge();
        badge.setActive(true);
        badge.setName("badge01");
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
    }

    @When("^I GET a badge with endpoint /badges and an api token$")
    public void i_GET_a_badge_with_endpoint_badges_and_an_api_token() throws Throwable {
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
    }

    @Given("^I have a badge created and a badge updating payload$")
    public void i_have_a_badge_created_and_a_badge_updating_payload() throws Throwable {
        badge = new Badge();
        badge.setActive(true);
        badge.setName("badge01");
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
        try {
            badgesList = api.getBadges(environment.getApiKey());
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
        badgetoUpdate = new Badge();
        badgetoUpdate.setId(badgesList.get(1).getId());
        badgetoUpdate.setActive(true);
        badgetoUpdate.setName("badge");
    }

    @When("^I PUT a badge with endpoint /badges and an api token$")
    public void i_PUT_a_badge_with_endpoint_badges_and_an_api_token() throws Throwable {
        try {
            lastApiResponse = api.updateBadgeWithHttpInfo(environment.getApiKey(), badgetoUpdate);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Given("^I have a badge created and a badge deleting payload$")
    public void i_have_a_badge_created_and_a_badge_deleting_payload() throws Throwable {
        badge = new Badge();
        badge.setActive(true);
        badge.setName("badge01");
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
        try {
            badgesList = api.getBadges(environment.getApiKey());
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
        badgetoDelete = new Badge();
        badgetoDelete.setId(badgesList.get(1).getId());
    }

    @When("^I DELETE a badge with endpoint /badges  and an api token$")
    public void i_DELETE_a_badge_with_endpoint_badges_and_an_api_token() throws Throwable {
        try {
            lastApiResponse = api.deleteBadgeWithHttpInfo(environment.getApiKey(), badgetoDelete);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }


}
