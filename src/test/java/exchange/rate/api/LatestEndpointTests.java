package exchange.rate.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LatestEndpointTests {

    static Response response;
    static String apiKey = "gNPtAXObSQdXu0MccUvPjpJ0ElfRHpSf";

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "https://api.apilayer.com";
        RestAssured.basePath = "/exchangerates_data/latest";
    }

    @Test
    public void test1(){
        Response response = given().headers("apikey", "gNPtAXObSQdXu0MccUvPjpJ0ElfRHpSf")
                .when().get();

//        Response response = when().get("https://api.exchangeratesapi.io/v1/latest?access_key=sHsr44ejNHHc36OTdQNfFxdCYoHQsFbq");
        System.out.println(response.prettyPrint());
    }
}
