Feature: Badges

  Background:
    Given There is an api server

  Scenario: Create a Badge
    Given I have a badge creation payload
    When I POST a badge to endpoint /badges with an api token
    Then I receive a 201 status code


  Scenario: Delete a Badge

  Scenario: Get active Badges

  Scenario: Update a Badge