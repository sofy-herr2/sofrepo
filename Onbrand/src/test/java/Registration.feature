@UserRegistration
Feature: User Registrations

  Scenario: Verify Registration page and Mandatory Fields
    Given Navigate to create account page
    When Verify registration page
    And Enter registration data
      | Field             | Value |
      | FirstName         |       |
      | LastName          |       |
      | Email             |       |
      | Password          |       |
      | ConfirmPassword   |       |
      | Job title         |       |
      | Department        |       |
      | Marketing contact |       |
      | Message           |       |
    Then Verify mandatory error messages for registration page

  Scenario: Internal User Registration
    Given Navigate to create account page
    When Enter registration data
      | Field             | Value                |
      | FirstName         | Test                 |
      | LastName          | User                 |
      | Email             | test@northplains.com |
      | Password          | Password1!           |
      | ConfirmPassword   | Password1!           |
      | Job title         | Quality Analyst      |
      | Department        | QA                   |
      | Marketing contact | qa1@vyre.com         |
      | Message           | Test                 |
    Then Verify successful user registration
    And Verify message "Application complete. Awaiting approval." in class "notification"
    And Delete user from Backend "test@northplains.com"

  Scenario: Verify Password Fields
    Given Navigate to create account page
    When Enter registration data
      | Field             | Value                |
      | FirstName         | Test                 |
      | LastName          | User                 |
      | Email             | test@northplains.com |
      | Password          |                      |
      | ConfirmPassword   | Password1!           |
      | Job title         | Quality Analyst      |
      | Department        | QA                   |
      | Marketing contact | qa1@vyre.com         |
      | Message           | Test                 |
    Then Verify message "The password doesn`t meet the minimum security requirements." in class "errorContainerClass"
    And Clear all Registration fields
    When Enter registration data
      | Field             | Value                |
      | FirstName         | Test                 |
      | LastName          | User                 |
      | Email             | test@northplains.com |
      | Password          | Password1!           |
      | ConfirmPassword   |                      |
      | Job title         | Quality Analyst      |
      | Department        | QA                   |
      | Marketing contact | qa1@vyre.com         |
      | Message           | Test                 |
    Then Verify message "field required" in class "errorContainerClass"
    And Clear all Registration fields
    When Enter registration data
      | Field             | Value                |
      | FirstName         | Test                 |
      | LastName          | User                 |
      | Email             | test@northplains.com |
      | Password          | Password1!           |
      | ConfirmPassword   | Password1            |
      | Job title         | Quality Analyst      |
      | Department        | QA                   |
      | Marketing contact | qa1@vyre.com         |
      | Message           | Test                 |
    Then Verify message "The passwords do not match." in class "errorContainerClass"
    And Clear all Registration fields
    When Enter registration data
      | Field             | Value                |
      | FirstName         | Test                 |
      | LastName          | User                 |
      | Email             | test@northplains.com |
      | Password          | pass                 |
      | ConfirmPassword   | pass                 |
      | Job title         | Quality Analyst      |
      | Department        | QA                   |
      | Marketing contact | qa1@vyre.com         |
      | Message           | Test                 |
    Then Verify message "The password doesn`t meet the minimum security requirements." in class "errorContainerClass"

  Scenario: Verify Email Fields
    Given Navigate to create account page
    When Enter registration data
      | Field             | Value           |
      | FirstName         | Test            |
      | LastName          | User            |
      | Email             | test            |
      | Password          | Password1!      |
      | ConfirmPassword   | Password1!      |
      | Job title         | Quality Analyst |
      | Department        | QA              |
      | Marketing contact | qa1@vyre.com    |
      | Message           | Test            |
    Then Verify message "email not valid" in class "errorContainerClass"
    And Clear all Registration fields
    When Enter registration data
      | Field             | Value                |
      | FirstName         | Test                 |
      | LastName          | User                 |
      | Email             | test@northplains.com |
      | Password          | Password1!           |
      | ConfirmPassword   | Password1!           |
      | Job title         | Quality Analyst      |
      | Department        | QA                   |
      | Marketing contact | qa1                  |
      | Message           | Test                 |
    Then Verify message "email not valid" in class "errorContainerClass"

  Scenario: Verify Terms checkbox
    Given Navigate to create account page
    When Enter registration data without accepting terms
      | Field             | Value                |
      | FirstName         | test@northplains.com |
      | LastName          | User                 |
      | Email             | test@northplains.com |
      | Password          | Password1!           |
      | ConfirmPassword   | Password1!           |
      | Job title         | Quality Analyst      |
      | Department        | QA                   |
      | Marketing contact | qa1@vyre.com         |
      | Message           | Test                 |
    Then Verify message "You must agree The Terms of Use." in class "errorContainerClass"
