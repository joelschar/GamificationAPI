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

import java.util.List;

import static org.junit.Assert.*;

public class RuleSteps {

    private Environment environment;
    private DefaultApi api;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    private Rule ruleWithBadge;
    private Rule ruleWithPointScale;
    private Rule ruleWithPointScaleAndBadge;
    private Rule tempRule;
    private Badge badge;
    private PointScale pointScale;

    public RuleSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^There is an api server with a /rules endpoint$")
    public void there_is_an_api_server() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have a Badge creation payload for my rule$")
    public void iHaveABadgeCreationPayloadForMyRule() throws Throwable {
        badge = new Badge();
        badge.setName("FirstPost");
        badge.setActive(true);
    }

    @Given("^I POST a badge to endpoint /badges for my rule$")
    public void iPOSTABadgeToEndpointBadgesForMyRule() throws Throwable {
        try{
            lastApiResponse = api.createBadgeWithHttpInfo(environment.getApiKey(), badge);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
            List<Badge> badges = api.getBadges(environment.getApiKey());
            for (Badge mybadge : badges){
                if(mybadge.getName().equals(badge.getName()))
                    badge.setId(mybadge.getId());
            }
        } catch (ApiException e){
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }


    @Given("^I have a rule creation payload with Badge$")
    public void i_have_a_rule_creation_payload() throws Throwable {
        ruleWithBadge = new Rule();
        ruleWithBadge.setActive(true);
        ruleWithBadge.setBadge(badge);
        ruleWithBadge.setNbrPoints(0);
        ruleWithBadge.setPointScale(null);
        ruleWithBadge.setValue("publishFirstPost");
    }


    @When("^I POST a rule with Badge to endpoint /rules and an api token$")
    public void iPOSTARuleWithBadgeToEndpointRulesAndAnApiToken() throws Throwable {
        try{
            lastApiResponse = api.createRuleWithHttpInfo(environment.getApiKey(), ruleWithBadge);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e){
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }


    @Then("^I have created a rule with badge and no poinScale$")
    public void iHaveCreatedARuleWithBadgeAndNoPoinScale() throws Throwable {
        List<Rule> rules = api.getRules(environment.getApiKey());
        for (Rule rule : rules){
            if(rule.getValue().equals(ruleWithBadge.getValue())) {
                assertNotNull(rule.getBadge());
                assertEquals(rule.getBadge().getName(), badge.getName());
                assertEquals(rule.getNbrPoints(), ruleWithBadge.getNbrPoints());
                assertNull(rule.getPointScale());
            }
        }
    }

    @Given("^I have a PointScale creation payload for my rule$")
    public void iHaveAPointScaleCreationPayloadForMyRule() throws Throwable {
        pointScale = new PointScale();
        pointScale.setActive(true);
        pointScale.setName("Post");
    }

    @Given("^I POST a PointScale to endpoint /pointScale for my rule$")
    public void iPOSTAPointScaleToEndpointPointScaleForMyRule() throws Throwable {
        try{
            lastApiResponse = api.createPointScaleWithHttpInfo(environment.getApiKey(), pointScale);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
            List<PointScale> pointScales = api.getPointScales(environment.getApiKey());
            for (PointScale mypointScale : pointScales){
                if(mypointScale.getName().equals(pointScale.getName()))
                    pointScale.setId(mypointScale.getId());
            }
        } catch (ApiException e){
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Given("^I have a rule creation payload with PointScale$")
    public void iHaveARuleCreationPayloadWithPointScale() throws Throwable {
        ruleWithPointScale = new Rule();
        ruleWithPointScale.setActive(true);
        ruleWithPointScale.setBadge(null);
        ruleWithPointScale.setNbrPoints(50);
        ruleWithPointScale.setPointScale(pointScale);
        ruleWithPointScale.setValue("publishNewPost");
    }

    @When("^I POST a rule with PointScale to endpoint /rules and an api token$")
    public void iPOSTARuleWithPointScaleToEndpointRulesAndAnApiToken() throws Throwable {
        try{
            lastApiResponse = api.createRuleWithHttpInfo(environment.getApiKey(), ruleWithPointScale);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e){
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }


    @Then("^I have created a rule with poinScale and no badge$")
    public void iHaveCreatedARuleWithPoinScaleAndNoBadge() throws Throwable {
        List<Rule> rules = api.getRules(environment.getApiKey());
        for (Rule rule : rules){
            if(rule.getValue().equals(ruleWithPointScale.getValue())) {
                assertNull(rule.getBadge());
                assertEquals(rule.getNbrPoints(), ruleWithPointScale.getNbrPoints());
                assertNotNull(rule.getPointScale());
                assertEquals(rule.getPointScale().getName(), pointScale.getName());
            }
        }
    }

        @Given("^I have a rule creation payload with PointScale and Badge$")
        public void iHaveARuleCreationPayloadWithPointScaleAndBadge() throws Throwable {
            ruleWithPointScaleAndBadge = new Rule();
            ruleWithPointScaleAndBadge.setActive(true);
            ruleWithPointScaleAndBadge.setBadge(badge);
            ruleWithPointScaleAndBadge.setNbrPoints(50);
            ruleWithPointScaleAndBadge.setPointScale(pointScale);
            ruleWithPointScaleAndBadge.setValue("publishFirstNewPost");
        }

        @When("^I POST a rule with PointScale and Badge to endpoint /rules and an api token$")
        public void iPOSTARuleWithPointScaleAndBadgeToEndpointRulesAndAnApiToken() throws Throwable {
            try{
                lastApiResponse = api.createRuleWithHttpInfo(environment.getApiKey(), ruleWithPointScaleAndBadge);
                lastApiCallThrewException = false;
                lastApiException = null;
                lastStatusCode = lastApiResponse.getStatusCode();
            } catch (ApiException e){
                lastApiCallThrewException = true;
                lastApiResponse = null;
                lastApiException = e;
                lastStatusCode = lastApiException.getCode();
            }
        }


    @Then("^I have created a rule with poinScale and badge$")
    public void iHaveCreatedARuleWithPoinScaleAndBadge() throws Throwable {
        List<Rule> rules = api.getRules(environment.getApiKey());
        for (Rule rule : rules){
            if(rule.getValue().equals(ruleWithPointScaleAndBadge.getValue())) {
                assertNotNull(rule.getBadge());
                assertEquals(rule.getBadge().getName(), badge.getName());
                assertEquals(rule.getNbrPoints(), ruleWithPointScaleAndBadge.getNbrPoints());
                assertNotNull(rule.getPointScale());
                assertEquals(rule.getPointScale().getName(), pointScale.getName());
            }
        }
    }

    @Then("^I receive a (\\d+) status code for Rule$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }

    @When("^I GET a rule with endpoint /rules and an api token$")
    public void iGETARuleWithEndpointRulesAndAnApiToken() throws Throwable {
        try{
            lastApiResponse = api.getRulesWithHttpInfo(environment.getApiKey());
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e){
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }


    @Then("^I receive at least one Rule and not null$")
    public void iReceiveAtLeastOneRuleAndNotNull() throws Throwable {
        List<Rule> rules = api.getRules(environment.getApiKey());
        assertNotEquals(rules.size(),0);
        for (Rule rule : rules){
            if (rule.getValue().equals(ruleWithBadge.getValue())){
                assertNotNull(rule.getBadge());
                assertNull(rule.getPointScale());
            }
        }
    }

    @When("^I UPDATE a rule with endpoint /rules and an api token$")
    public void iUPDATEARuleWithEndpointRulesAndAnApiToken() throws Throwable {
        try{

            tempRule = new Rule();
            List<Rule> rules = api.getRules(environment.getApiKey());
            for (Rule rule : rules){
                if(rule.getValue().equals(ruleWithBadge.getValue()))
                    tempRule = rule;
            }

            tempRule.setValue("publishNewImage");

            lastApiResponse = api.updateRuleWithHttpInfo(environment.getApiKey(), tempRule );
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e){
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }


    @Then("^The rule has change$")
    public void theRuleHasChange() throws Throwable {
        List<Rule> rules = api.getRules(environment.getApiKey());
        for (Rule rule : rules){
            if(rule.getValue().equals(tempRule.getValue())) {
                assertNotEquals(ruleWithBadge.getValue(), tempRule.getValue());
            }
        }
    }

    @When("^I DELETE a rule with endpoint /rules  and an api token$")
    public void iDELETEARuleWithEndpointRulesAndAnApiToken() throws Throwable {
        try{

            Rule tempRule = new Rule();
            List<Rule> rules = api.getRules(environment.getApiKey());
            for (Rule rule : rules){
                if(rule.getValue().equals(ruleWithBadge.getValue()))
                    tempRule = rule;
            }

            lastApiResponse = api.deleteRuleWithHttpInfo(environment.getApiKey(), tempRule );
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e){
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^the rule no longer exist$")
    public void theRuleNoLongerExist() throws Throwable {
        boolean stillExist = false;
        List<Rule> rules = api.getRules(environment.getApiKey());
        if(rules != null)
        for (Rule rule : rules){
            if(rule.getId().equals(ruleWithBadge.getId())) {
                stillExist = true;
            }

        }
        assertFalse(stillExist);
    }
}
