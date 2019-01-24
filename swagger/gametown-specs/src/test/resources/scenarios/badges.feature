Feature: Badges

  Background:
  Given There is an api server with a /badges endpoint


#   Scenario: Create a badge
#
  Scenario: Create a Badge
    Given I have a badge creation payload
    When I POST a badge to endpoint /badges and an api token
    Then I receive a 201 status code

#   Scenario: Get a badge
#
  Scenario: Get a Badge
    Given I have a badge created and a badge getting payload
    When I GET a badge with endpoint /badges and an api token
    Then I receive a 200 status code

#  Scenario: Update a Badge
  Scenario: Update a Badge
    Given I have a badge created and a badge updating payload
    When I PUT a badge with endpoint /badges and an api token
    Then I receive a 202 status code


#  Scenario: Delete a Badge
#
  Scenario: Delete a badge
    Given I have a badge created and a badge deleting payload
    When I DELETE a badge with endpoint /badges  and an api token
    Then I receive a 204 status code
