package exchange.rate.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Stepdefs {
    static Map<String, String> headers = new HashMap<>();
    static Map<String, String> queryParams = new HashMap<>();
    Response latestResponse;

    @Given("user has a valid API Key")
    public void userHasAValidAPIKey() {
        headers.put("apikey", "gNPtAXObSQdXu0MccUvPjpJ0ElfRHpSf");
    }

    @When("user sends GET request to latest endpoint")
    public void userSendsGETRequestToLatestEndpoint() {
        RestAssured.baseURI = "https://api.apilayer.com";
        RestAssured.basePath = "/exchangerates_data/latest";
        latestResponse = given().headers(headers)
                .when().get("https://api.apilayer.com/exchangerates_data/latest");
//        latestResponse = given().headers("apikey", "gNPtAXObSQdXu0MccUvPjpJ0ElfRHpSf")
//                .when().get();
        System.out.println("----------response---------");
        System.out.println(latestResponse.prettyPrint());
    }
    @Then("response code is {int}")
    public void responseCodeIs(int responseCode) {
        assertEquals(responseCode, latestResponse.statusCode());
    }

    @And("response returns {string} equal to {string}")
    public void responseReturnsEqualTo(String key, String value) {
        assertEquals(value, latestResponse.getBody().jsonPath().get(key));
    }

    @And("response returns {string} which is not empty")
    public void responseReturnsWhichIsNotEmpty(String key) {
        assertNotNull(latestResponse.getBody().jsonPath().get(key));
    }

    @Given("User has a valid URL")
    public void userHasAValidURL() {
        headers.clear();
        queryParams.clear();
    }
}
