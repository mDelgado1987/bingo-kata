Feature: As a VP of Gaming I want my game to check player's cards when they call Bingo So that a winner can be decided

  Scenario: Happy path: Player check the card after mark all numbers in it
    Given a player calls Bingo after all numbers on their card have been called
    When I check the card
    Then the player is the winner

  Scenario: Sad path: Player check the card after mark some numbers in it
    Given a player calls Bingo before all numbers on their card have been called
    When I check the card
    Then the player is not the winner