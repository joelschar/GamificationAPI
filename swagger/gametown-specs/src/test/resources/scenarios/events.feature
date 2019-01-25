Feature: Badges

  Background:
    Given There is an api server with a /badges endpoint

#Feature: Events
#
  Scenario: Create a new Event
    Given I have a Badge creation payload for my rule
    Given I POST a badge to endpoint /badges for my rule
    Given I have a rule creation payload with Badge
    Given I POST a rule with Badge to endpoint /rules and an api token
    Given I have a user creation payload for my event
    Given I have a event creation payload
    When I POST my event to endpoint /events
    Then I receive a 200 status code for Event
    Then the user exist one time
    Then the user as gain the badge

  Scenario: Create a new Event
    Given I have a Badge creation payload for my rule
    Given I POST a badge to endpoint /badges for my rule
    Given I have a rule creation payload with Badge
    Given I POST a rule with Badge to endpoint /rules and an api token
    Given I have a user creation payload for my event
    Given I have a event creation payload
    Given I POST my event to endpoint /events
    Given I get the created user ID
    Then the user exist one time
    When I POST my event to endpoint /events
    Then I receive a 200 status code for Event
    Then the user exist one time
