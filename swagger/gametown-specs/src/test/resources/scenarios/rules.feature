Feature: Rules

  Background:
    Given There is an api server with a /rules endpoint


#   Scenario: Create a Rule
#
  Scenario: Create a Rule
    Given I have a rule creation payload
    Given I have a Badge created
    When I POST a rule to endpoint /rules and an api token
    Then I receive a 201 status code

#   Scenario: Get a Rule
#
  Scenario: Get a Rule
    Given I have a rule created and a rule getting payload
    When I GET a rule with endpoint /ruless and an api token
    Then I receive a 200 status code

#  Scenario: Update a Rule
  Scenario: Update a Rule
    Given I have a rule created and a rule updating payload
    When I PUT a rule with endpoint /rules and an api token
    Then I receive a 200 status code


#  Scenario: Delete a Rule
#
  Scenario: Delete a Rule
    Given I have a rule created and a rule deleting payload
    When I DELETE a rule with endpoint /rules  and an api token
    Then I receive a 200 status code