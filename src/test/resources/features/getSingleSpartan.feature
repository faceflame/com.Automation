@wip
Feature: User Gets Spartan Data

  Scenario: Authenticated User Should be able to get Spartan Data
    Given credentilas with USER_role is provided
    When user trys to send a get request on "/spartans/10"
    Then user should get status code 200



