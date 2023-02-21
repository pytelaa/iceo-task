Feature: Verify latest endpoint for Exchange Rate API

  Background: User has a valid URL
    Given User has a valid URL

  Scenario: Getting a default successful response results in 200 response code and exchange rate for EUR
    Given user has a valid API Key
    When user sends GET request to latest endpoint
    Then response code is 200
    And response returns "success" equal to "true"
    And response returns "base" equal to "EUR"
    And response returns "timestamp" which is not empty
    And response returns "date" which is not empty

#  Scenario: Getting a response for USD results in 200 response code and respective exchange rate
#    Given user has a valid API Key
#    And user adds a quarry parameter with key "base" and value "USD"
#    And user adds a quarry parameter with key "symbols" and value "EUR, PLN, GBP"
#    When user sends GET request to latest endpoint
#    Then response code is 200
#    And response returns "success" equal to "true"
#    And response returns "base" equal to "USD"
#    And response returns "timestamp" which is not empty
#    And response returns "date" which is not empty
#    And response returns "rate.EUR" which is not empty
#    And response returns "rate.PLN" which is not empty
#    And response returns "rate.GBP" which is not empty
#
#  Scenario: Getting a response without valid API KEY results in 401 response code
#    When user sends GET request to latest endpoint
#    Then response code is 401
#    And response returns "message" equal to "No API key found in request"
#
#  Scenario: Getting a response for invalid currency results in 400 response code
#    Given user has a valid API Key
#    And user adds a quarry parameter with key "base" and value "ZZZ"
#    When user sends GET request to latest endpoint
#    Then response code is 400
#    And response returns parameter "error.code" equal to "invalid_base_currency"
#    And response returns parameter "error.message" equal to "An unexpected error ocurred. [Technical Support: support@apilayer.com]"
#
#  Scenario Outline: Checking if exchange rate one way and then back gives valid data
#    Given user has a valid API Key
#    And user adds a quarry parameter with key "base" and value "<Currency1>"
#    And user adds a quarry parameter with key "symbols" and value "<Currency2>"
#    And user sends GET request to latest endpoint
#    And response code is 200
#    And user adds a quarry parameter with key "base" and value "<Currency2>"
#    And user adds a quarry parameter with key "symbols" and value "<Currency1>"
#    When user sends GET request to latest endpoint
#    Then response code is 200
#    And exchanging "<Currency1>" to "<Currency2>" and then back to "<Currency1>" returns similar rate as on the beginning
#    Examples:
#    |Currency1|Currency2|
#    |EUR      |USD      |
#    |PLN      |GBP      |
#    |GBP      |USD      |