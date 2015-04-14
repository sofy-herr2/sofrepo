@UserLogin
Feature: Login to System

  Scenario: Invalid user Logins
    Given Navigate to login page
    When Login with invalid username
      | Username    | password |
      | a1@vyre.com | Testing1 |
    Then Verify login error message "Sorry, the login info you entered isn`t valid."
    When Login with invalid password
      | Username     | password   |
      | qa1@vyre.com | Testing123 |
    Then Verify login error message "Sorry, the login info you entered isn`t valid."
    When Login with inactive username
      | Username     | password |
      | qa3@vyre.com | Testing1 |
    Then Verify login error message "Sorry, your account is inactive"

  Scenario: Missing user Logins
    Given Navigate to login page
    When Click on the login button
    Then Verify validation error for "email"
    When Enter login details
      | Username     | password |
      | qa1@vyre.com |          |
    Then Verify validation error for "password"

  Scenario: Valid user login
    Given Navigate to login page
    When Enter login details
      | Username     | password |
      | qa1@vyre.com | Testing1 |
    Then Verify Dashboard page
