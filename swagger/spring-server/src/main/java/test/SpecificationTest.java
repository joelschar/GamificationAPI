package test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 *
 * @author Patrick Neto (patrick.neto@heig-vd.ch)
 */
@RunWith(Cucumber.class)
@CucumberOptions(features="src/main/java/test/resources/scenarios", plugin = {"pretty", "html:target/cucumber"})
public class SpecificationTest {
    public SpecificationTest() {
        System.out.println("Tests run!");
    }
}