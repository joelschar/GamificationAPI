package io.gametown.api.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.gametown.api.api.dto.Event;
import io.gametown.api.api.dto.User;
import io.gametown.api.api.dto.Badge;
import io.gametown.api.ApiException;
import io.gametown.api.ApiResponse;
import io.gametown.api.api.DefaultApi;
import io.gametown.api.api.spec.helpers.Environment;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EventSteps {

    private Environment environment;
    private DefaultApi api;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    Event event;
    User user;

    public EventSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    //TODO Trouver moyen de tester si existe pas on créé.
    //TODO Si existe déjà on re-créée pas.
    // le problème est que lors de la création il utilise l'id pour voir si il existe déjà ou pas et dans ce cas la il assigne lui meme un nouvel ID.
    // Dans le cas si dessous, l'email unique change pas au fait qu'il va regarder si un User à l'id 2 existe ou pas et donc se base pas sur l'email.

    @Given("^There is an api server with a /events endpoint$")
    public void there_is_an_api_server() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have a event creation payload$")
    public void iHaveAEventCreationPayload() throws Throwable {
        event = new Event();
        event.setEvent("publishFirstPost"); //same name that the rule created before in the test

        // on définit l'utilisateur qui va recevoir le badge, ici il existe pas
        user = new User();
        user.setEmail("test@gmail.com" + System.currentTimeMillis());
        user.setFirstname("Roger");
        user.setLastname("Rabbit");
        user.setId(2);
        event.setUser(user);
    }

    @When("^I POST my event to endpoint /events$")
    public void iPOSTMyEventToEndpointEvents() throws Throwable {
        try{
            lastApiResponse = api.newEventWithHttpInfo(environment.getApiKey(), event);
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

    @Then("^I receive a (\\d+) status code for Event$")
    public void iReceiveAStatusCodeForEvent(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }

    @Then("^the user exist one time$")
    public void theUserExistOneTime() throws Throwable {

        int nbrSameUser = 0;
        List<User> users = api.getUsers(environment.getApiKey());
        for (User us : users){
            if(us.getEmail().equals(user.getEmail())){
                nbrSameUser++;
            }
        }

        assertEquals(nbrSameUser, 1);
        throw new PendingException();
    }

    @Then("^the user as gain the badge$")
    public void theUserAsGainTheBadge() throws Throwable {
        boolean userHasGainTheBadge = false;
        int userId = -1;
        List<User> users = api.getUsers(environment.getApiKey());
        for (User us : users){
            if(us.getEmail().equals(user.getEmail())){
                userId = us.getId();
            }
        }
        List<Badge> badges = api.getUserBadges(environment.getApiKey(), userId);
        for (Badge badge : badges){
            if (badge.getName().equals("FirstPost")){
                userHasGainTheBadge = true;
            }
        }
        assertTrue(userHasGainTheBadge);
    }
}
