Feature: User Registration

  Scenario: Successful registration
    Given user is on registration page
    When user enters valid details
    And clicks register
    Then account should be created successfullyully