Feature: Hotels.com Registration

  Scenario: Register new user with faker data
    Given user is on Hotels.com homepage
    When user clicks on sign in button
    And user accepts cookies if present
    And user clicks on create account link
    And user enters registration details with faker data
    And user clicks on create account button
