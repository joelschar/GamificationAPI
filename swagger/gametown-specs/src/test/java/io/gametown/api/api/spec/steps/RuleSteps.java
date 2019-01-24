package io.gametown.api.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.gametown.api.ApiException;
import io.gametown.api.ApiResponse;
import io.gametown.api.api.DefaultApi;
import io.gametown.api.api.spec.helpers.Environment;
import io.gametown.api.api.dto.Rule;
import io.gametown.api.api.dto.Badge;
import io.gametown.api.api.dto.PointScale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RuleSteps {

    private Environment environment;
    private DefaultApi api;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    private  Rule rule;

    public RuleSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^There is an api server with a /rules endpoint$")
    public void there_is_an_api_server() throws Throwable {
        assertNotNull(api);
        throw new PendingException();
    }

    @Given("^I have a rule creation payload$")
    public void i_have_a_rule_creation_payload() throws Throwable {
        rule = new Rule();
        rule.setValue("rule01");
        rule.setBadge(new Badge());
        rule.setNbrPoints(1);
        rule.setPointScale(new PointScale());
        throw new PendingException();
    }

    @When("^I POST a badge to endpoint /badges with an api token$")
    public void i_POST_a_badge_to_endpoint_badges_with_an_api_token() throws Throwable {
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
        throw new PendingException();
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
        throw new PendingException();
    }

}
