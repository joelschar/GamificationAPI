Feature: Rules

  Background:
    Given There is an api server with a /rules endpoint


#   Scenario: Create a Rule with Badge
#
  Scenario: Create a Rule with Badge
    Given I have a Badge creation payload for my rule
    Given I POST a badge to endpoint /badges for my rule
    Given I have a rule creation payload with Badge
    When I POST a rule with Badge to endpoint /rules and an api token
    Then I receive a 201 status code for Rule
    Then I have created a rule with badge and no poinScale

#   Scenario: Create a Rule with PointScale
#
  Scenario: Create a Rule with PointScale
    Given I have a PointScale creation payload for my rule
    Given I POST a PointScale to endpoint /pointScale for my rule
    Given I have a rule creation payload with PointScale
    When I POST a rule with PointScale to endpoint /rules and an api token
    Then I receive a 201 status code for Rule
    Then I have created a rule with poinScale and no badge


#   Scenario: Create a Rule with PointScale and Badge
#
  Scenario: Create a Rule with PointScale and Badge
    Given I have a Badge creation payload for my rule
    Given I POST a badge to endpoint /badges for my rule
    Given I have a PointScale creation payload for my rule
    Given I POST a PointScale to endpoint /pointScale for my rule
    Given I have a rule creation payload with PointScale and Badge
    When I POST a rule with PointScale and Badge to endpoint /rules and an api token
    Then I receive a 201 status code for Rule
    Then I have created a rule with poinScale and badge

#   Scenario: Get a Rule
#
  Scenario: Get a Rule
    Given I have a Badge creation payload for my rule
    Given I POST a badge to endpoint /badges for my rule
    Given I have a rule creation payload with Badge
    Given I POST a rule with Badge to endpoint /rules and an api token
    When I GET a rule with endpoint /rules and an api token
    Then I receive a 200 status code for Rule
    Then I receive at least one Rule and not null

#  Scenario: Update a Rule
  Scenario: Update a Rule
    Given I have a Badge creation payload for my rule
    Given I POST a badge to endpoint /badges for my rule
    Given I have a rule creation payload with Badge
    Given I POST a rule with Badge to endpoint /rules and an api token
    When I UPDATE a rule with endpoint /rules and an api token
    Then I receive a 202 status code for Rule
    Then The rule has change


#  Scenario: Delete a Rule
#
  Scenario: Delete a Rule
    Given I have a Badge creation payload for my rule
    Given I POST a badge to endpoint /badges for my rule
    Given I have a rule creation payload with Badge
    Given I POST a rule with Badge to endpoint /rules and an api token
    When I DELETE a rule with endpoint /rules  and an api token
    Then I receive a 204 status code for Rule
    Then the rule no longer exist