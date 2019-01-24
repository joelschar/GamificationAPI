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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PointscaleSteps {

    private Environment environment;
    private DefaultApi api;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private PointScale pointscale;

    public PointscaleSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^There is an api server with a /pointscales endpoint$")
    public void there_is_an_api_server_with_a_pointscales_endpoint() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have a pointScale creation payload$")
    public void i_have_a_pointScale_creation_payload() throws Throwable {
        pointscale = new PointScale();
        pointscale.setActive(true);
        pointscale.setName("PointScale_" + System.currentTimeMillis());
    }

    @Given("^I have a pointscale creation paylod from GET response$")
    public void pointscale() throws Throwable {
        List<PointScale> poinscales = api.getPointScales(environment.getApiKey());
        // we take the first on doesn't matter if it was the one created before,
        // the purpose of the previous creation is just to make sure there is at lease on
        for(PointScale p: poinscales){
            if(p.getName().equals(pointscale.getName()))
                pointscale = p;
        }
    }

    @When("^I POST it to the endpoint /pointScale with an api token$")
    public void i_POST_it_to_the_endpoint_pointScale_with_an_api_token() throws Throwable {
        try {
            lastApiResponse = api.createPointScaleWithHttpInfo(environment.getApiKey(), pointscale);
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
    @When("^I GET the endpoint /pointScales with an api token$")
    public void i_GET_the_endpoint_pointScales_with_an_api_token() throws Throwable {
        try {
            lastApiResponse = api.getPointScalesWithHttpInfo(environment.getApiKey());
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

    @When("^I UPDATE it with endpoint /pointScales and an api token$")
    public void i_UPDATE_it_with_endpoint_pointScales_and_an_api_token() throws Throwable {
        try {
            lastApiResponse = api.updatePointScaleWithHttpInfo(environment.getApiKey(), pointscale);
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

    @When("^I DELETE a pointScale with endpoint /pointScales and an api token$")
    public void i_DELETE_a_pointScale_with_endpoint_pointScales_and_an_api_token() throws Throwable {
        try {
            lastApiResponse = api.deletePointScaleWithHttpInfo(environment.getApiKey(), pointscale);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
        System.out.println(lastStatusCode);
    }


    @Then("^I receive a (\\d+) status code from /pointScale$")
    public void i_receive_a_status_code_from_pointScale(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);

    }

}
