package exchange.rate.api;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class Stepdefs {
    static Map<String, String> headers = new HashMap<>();
    static Map<String, String> queryParams = new HashMap<>();
    Response latestResponse;
    float savedRate;

    @Before
    public static void setup() {
        RestAssured.baseURI = "https://api.apilayer.com";
        RestAssured.basePath = "/exchangerates_data/latest";
        headers.clear();
        queryParams.clear();
    }

    @Given("user has a valid API Key")
    public void userHasAValidAPIKey() {
        headers.put("apikey", System.getenv("apikey"));
    }

    @When("user sends GET request to latest endpoint")
    public void userSendsGETRequestToLatestEndpoint() {
        latestResponse = given().headers(headers).queryParams(queryParams).when().get();
        System.out.println(latestResponse.prettyPrint());
    }

    @Then("response code is {int}")
    public void responseCodeIs(int responseCode) {
        assertEquals(responseCode, latestResponse.statusCode());
    }

    @And("response returns {string} equal to {string}")
    public void responseReturnsEqualTo(String key, String value) {
        assertEquals(value, latestResponse.getBody().jsonPath().get(key).toString());
    }

    @And("response returns {string} which is not empty")
    public void responseReturnsWhichIsNotEmpty(String key) {
        assertNotNull(latestResponse.getBody().jsonPath().get(key));
    }

    @And("user adds a quarry parameter with key {string} and value {string}")
    public void userAddsAQuarryParameterWithKeyAndValue(String key, String value) {
        queryParams.put(key, value);
    }

    @And("exchanging {string} to {string} and then back to {string} returns similar rate as on the beginning")
    public void exchangingToAndThenBackToReturnsSimilarRateAsOnTheBeginning(String currency1, String currency2, String arg2) {
        float rateCurrency2 = latestResponse.getBody().jsonPath().get("rates." + currency1);
        assertTrue( Math.abs(savedRate-rateCurrency2)  > 0.01);
    }

    @And("user saves rate for {string}")
    public void userSavesRateFor(String currency) {
        savedRate = latestResponse.getBody().jsonPath().get("rates." + currency);
    }
}
