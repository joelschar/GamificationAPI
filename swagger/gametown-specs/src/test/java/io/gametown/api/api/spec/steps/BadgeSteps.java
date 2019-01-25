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

import static org.junit.Assert.*;

public class BadgeSteps {

    private Environment environment;
    private DefaultApi api;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private Badge badgeToCreate;
    private List<Badge> badgesList;
    private Badge badgeToUpdate;
    private Badge badgeToDelete;

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
        badgeToCreate = new Badge();
        badgeToCreate.setActive(true);
        badgeToCreate.setName("badgeCreated");
    }

    @When("^I POST a badge to endpoint /badges and an api token$")
    public void i_POST_a_badge_to_endpoint_badges_and_an_api_token() throws Throwable {
        try {
            lastApiResponse = api.createBadgeWithHttpInfo(environment.getApiKey(), badgeToCreate);
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


    @Then("^I have created a badge named \"([^\"]*)\"$")
    public void i_have_created_a_badge_named(String arg1) throws Throwable {

        List<Badge> badges = api.getBadges(environment.getApiKey());
        for (Badge badge : badges){
            if(badge.getName().equals(badgeToCreate.getName())) {
                assertTrue(badge.getActive());
                assertEquals(arg1, badge.getName());
            }
        }
    }

    @Then("^I have updated a badge where the name changed to \"([^\"]*)\"$")
    public void i_have_updated_a_badge_where_the_name_changed_to(String arg1) throws Throwable {
        List<Badge> badges = api.getBadges(environment.getApiKey());
        for (Badge badge : badges){
            if(badge.getName().equals(badgeToUpdate.getName())) {
                assertTrue(badge.getActive());
                assertEquals(arg1, badge.getName());
            }
        }
    }

    @Then("^I have received a badge named \"([^\"]*)\"$")
    public void i_have_received_a_badge_named(String arg1) throws Throwable {
        List<Badge> badges = api.getBadges(environment.getApiKey());
        for (Badge badge : badges){
            if(badge.getName().equals(badgeToCreate.getName())) {
                assertTrue(badge.getActive());
                assertEquals(arg1, badge.getName());
            }
        }
    }

    @Then("^the badge no longer exist$")
    public void theBadgeNoLongerExist() throws Throwable {
        boolean stillExist = false;
        List<Badge> badges = api.getBadges(environment.getApiKey());
        if(badges != null)
            for (Badge badge : badges){
                if(badge.getId().equals(badgeToDelete.getId())) {
                    stillExist = true;
                }

            }
        assertFalse(stillExist);
    }

    @Given("^I have a badge created and a badge getting payload$")
    public void i_have_a_badge_created_and_a_badge_getting_payload() throws Throwable {
        badgeToCreate = new Badge();
        badgeToCreate.setActive(true);
        badgeToCreate.setName("badgeToReceive");
        try {
            lastApiResponse = api.createBadgeWithHttpInfo(environment.getApiKey(), badgeToCreate);
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
        badgeToCreate = new Badge();
        badgeToCreate.setActive(true);
        badgeToCreate.setName("badgeToUpdate");
        badgeToUpdate = new Badge();
        int idBadgeToUpdate = 0;
        try {
            lastApiResponse = api.createBadgeWithHttpInfo(environment.getApiKey(), badgeToCreate);
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
            List<Badge> badges = api.getBadges(environment.getApiKey());
                for (Badge badge : badges){
                    if(badge.getName().equals("badgeToUpdate")) {
                        idBadgeToUpdate = badge.getId();
                    }

                }
        } catch (ApiException e) {
            lastApiCallThrewException = true;
        }
        badgeToUpdate.setId(idBadgeToUpdate);
        badgeToUpdate.setActive(true);
        badgeToUpdate.setName("badgeUpdated");
    }

    @When("^I PUT a badge with endpoint /badges and an api token$")
    public void i_PUT_a_badge_with_endpoint_badges_and_an_api_token() throws Throwable {
        try {
            lastApiResponse = api.updateBadgeWithHttpInfo(environment.getApiKey(), badgeToUpdate);
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
        badgeToCreate = new Badge();
        badgeToCreate.setActive(true);
        badgeToCreate.setName("badgeToDelete");
        badgeToDelete = new Badge();
        int idBadgeToDelete = 0;
        try {
            lastApiResponse = api.createBadgeWithHttpInfo(environment.getApiKey(), badgeToCreate);
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
            List<Badge> badges = api.getBadges(environment.getApiKey());
            for (Badge badge : badges){
                if(badge.getName().equals("badgeToDelete")) {
                    idBadgeToDelete = badge.getId();
                }

            }
        } catch (ApiException e) {
            lastApiCallThrewException = true;
        }
        badgeToDelete.setId(idBadgeToDelete);
    }

    @When("^I DELETE a badge with endpoint /badges  and an api token$")
    public void i_DELETE_a_badge_with_endpoint_badges_and_an_api_token() throws Throwable {
        try {
            lastApiResponse = api.deleteBadgeWithHttpInfo(environment.getApiKey(), badgeToDelete);
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
