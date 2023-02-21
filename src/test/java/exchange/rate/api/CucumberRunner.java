package exchange.rate.api;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/Destination"},
        features = {"src/test/resources/VerifyLatestEndpoint.feature"},
        glue = {"exchange.rate.api.Stepdefs"})
public class CucumberRunner {
}
