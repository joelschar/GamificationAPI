package io.gametown.api.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.gametown.api.ApiException;
import io.gametown.api.ApiResponse;
import io.gametown.api.api.DefaultApi;
import io.gametown.api.api.spec.helpers.Environment;
import io.gametown.api.api.dto.PointScale;
import io.gametown.api.api.dto.Rule;
import io.gametown.api.api.dto.Badge;
import io.gametown.api.api.dto.User;
import io.gametown.api.api.dto.Event;

import java.util.List;

import static org.junit.Assert.*;

public class UserSteps {

    private Environment environment;
    private DefaultApi api;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private Badge badge;
    private PointScale pointScale;
    private Rule rule;
    private User user;
    private Event event;

    public UserSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^There is an api server with a /users endpoint$")
    public void there_is_an_api_server_with_a_users_endpoint() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have a badge creation payload from User$")
    public void i_have_a_badge_creation_payload_from_User() throws Throwable {
        badge = new Badge();
        badge.setName("FirstPost" + System.currentTimeMillis());
        badge.setActive(true);
    }

    @Given("^I POST a badge to endpoint /badges and an api token from User$")
    public void i_POST_a_badge_to_endpoint_badges_and_an_api_token_from_User() throws Throwable {
        try {
            lastApiResponse = api.createBadgeWithHttpInfo(environment.getApiKey(), badge);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
            List<Badge> badges = api.getBadges(environment.getApiKey());
            for (Badge mybadge : badges) {
                if (mybadge.getName().equals(badge.getName()))
                    badge.setId(mybadge.getId());
            }
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Given("^I have a pointScale creation payload from User$")
    public void i_have_a_pointScale_creation_payload_from_User() throws Throwable {
        pointScale = new PointScale();
        pointScale.setActive(true);
        pointScale.setName("Post" + System.currentTimeMillis());
    }

    @Given("^I POST a PointScale to endpoint /pointScale from User$")
    public void i_POST_a_PointScale_to_endpoint_pointScale_from_User() throws Throwable {
        try {
            lastApiResponse = api.createPointScaleWithHttpInfo(environment.getApiKey(), pointScale);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
            List<PointScale> pointScales = api.getPointScales(environment.getApiKey());
            for (PointScale mypointScale : pointScales) {
                if (mypointScale.getName().equals(pointScale.getName()))
                    pointScale.setId(mypointScale.getId());
            }
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Given("^I have a Rule creation payload from User$")
    public void i_have_a_Rule_creation_payload_from_User() throws Throwable {
        rule = new Rule();
        rule.setActive(true);
        rule.setBadge(badge);
        rule.setNbrPoints(50);
        rule.setPointScale(pointScale);
        rule.setValue("publishFirstNewPost");
    }

    @Given("^I POST a rule with PointScale and Badge to endpoint /rules and an api token from User$")
    public void i_POST_a_rule_with_PointScale_and_Badge_to_endpoint_rules_and_an_api_token_from_User() throws Throwable {
        try {
            lastApiResponse = api.createRuleWithHttpInfo(environment.getApiKey(), rule);
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

    @Given("^I have a User creation payload$")
    public void i_have_a_User_creation_payload() throws Throwable {
        user = new User();
        long timestamp = System.currentTimeMillis();
        user.setEmail("myemail_" + timestamp + "@filou.com");
        user.setFirstname("myname" + timestamp);
        user.setLastname("myLastName" + timestamp);
    }

    @Given("^I have a Event creation payload$")
    public void i_have_a_Event_creation_payload() throws Throwable {
        event = new Event();
        event.setEvent(rule.getValue());
        event.setUser(user);
    }

    @Given("^I POST it the endpoind /event$")
    public void i_POST_it_the_endpoind_event() throws Throwable {
        try {
            lastApiResponse = api.newEventWithHttpInfo(environment.getApiKey(), event);
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

    @When("^I GET a user with endpoint /users and an api token$")
    public void i_GET_a_user_with_endpoint_users_and_an_api_token() throws Throwable {
        try {
            lastApiResponse = api.getUsersWithHttpInfo(environment.getApiKey());
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

    @Then("^I receive a (\\d+) status code for User$")
    public void i_receive_a_status_code_for_User(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }

    @Then("^I can find the user in the GET response$")
    public void i_can_find_the_user_in_the_GET_response() throws Throwable {
        List<User> users = api.getUsers(environment.getApiKey());
        boolean isInResponse = false;
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                isInResponse = true;
                break;
            }
        }
        assertTrue(isInResponse);
    }

    @Then("^I can find the user in the GET response only once fro Event$")
    public void i_can_find_the_user_in_the_GET_response_only_once_fro_Event() throws Throwable {
        List<User> users = api.getUsers(environment.getApiKey());
        int countUsers = 0;
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                countUsers++;
            }
        }
        assertEquals(1, countUsers);
    }
}
