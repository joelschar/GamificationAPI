package test.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.gametown.api.api.model.Badge;

import cucumber.api.java.en.*;
import io.gametown.api.api.BadgesApi;
import io.gametown.api.entities.BadgeEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Patrick Neto
 */
public class CreationSteps {

    private BadgesApi badgesApi = BadgeEntity();
    private Badge badge = new Badge();
    private final String API_KEY = "application01";
    private int lastStatusCode;

    public CreationSteps() {

    }

    @Given("^I have a badge creation payload$")
    public void I_have_a_badge_creation_payload() throws Throwable {
        badge.setActive(true);
        badge.setId(1);
        badge.setName("badge01");
    }

    @When("^I POST a badge in endpoint /badges$")
    public void I_POST_a_badge_in_endpoint_badges() throws Throwable {
        La = badgeApi.createBadge(API_KEY, badge);
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(201, lastStatusCode);
    }
}
