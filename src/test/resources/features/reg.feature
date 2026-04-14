Feature: User Registration

  Scenario: Successful registration
    Given user is on registration page
    When user enters valid details
    And clicks register
    Then account should be created successfully

  Scenario: Missing last name
    Given user is on registration page
    When user enters details without last name
    And clicks register
    Then error message "Last Name is required" should be shown

  Scenario: Password mismatch
    Given user is on registration page
    When user enters mismatching passwords
    And clicks register
    Then error message "Password did not match" should be shown

  Scenario: Terms not accepted
    Given user is on registration page
    When user enters valid details without accepting terms
    And clicks register
    Then error message "You must confirm that you have read and accepted our Terms and Conditions" should be shown