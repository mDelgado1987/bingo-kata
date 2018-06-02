Feature: As a VP of gaming I want my game to call out Bingo numbers so that people can play with their cards

  Scenario: Check the number is between 1 and 75 inclusive
    Given I have a Bingo caller
    When I call a number
    Then the number is between 1 and 75 inclusive

  Scenario: No number has been called more than once
    Given I have a Bingo caller
    When I call a number 75 times
    Then all numbers between 1 and 75 are present
    And no number has been called more than once