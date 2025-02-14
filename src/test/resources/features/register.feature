Feature: Hotels.com Registration

  @register @smoke
  Scenario: Register new user with faker data
    Given user is on Hotels.com homepage
    When user waits for the page to be fully loaded
    And user accepts cookies if present
    And user clicks on sign in button
    And user waits for the sign in page to load
    And user clicks on create account link
    And user waits for the registration form to load
    And user enters registration details with faker data
    And user clicks on create account button
    Then user should see successful registration message
