Feature: User Registration

  Scenario Outline: Successful registration in different browsers
    Given user is on registration page in "<browser>"
    When user enters valid details
    And clicks register
    Then account should be created successfully

    Examples:
      | browser |
      | chrome  |
      | chrome    |

  Scenario: Missing last name
    Given user is on registration page in "chrome"
    When user enters details without last name
    And clicks register
    Then error message "Last Name is required" should be shown

  Scenario: Password mismatch
    Given user is on registration page in "chrome"
    When user enters mismatching passwords
    And clicks register
    Then error message "Password did not match" should be shown

  Scenario: Terms not accepted
    Given user is on registration page in "chrome"
    When user enters valid details without accepting terms
    And clicks register
    Then error message "You must confirm that you have read and accepted our Terms and Conditions" should be shown