Feature: Users

  Background:
    Given There is an api server with a /users endpoint

#   Scenario: Get a badge
#
  Scenario: Get a User
    Given I have a badge creation payload from User
    Given I POST a badge to endpoint /badges and an api token from User
    Given I have a pointScale creation payload from User
    Given I POST a PointScale to endpoint /pointScale from User
    Given I have a Rule creation payload from User
    Given I POST a rule with PointScale and Badge to endpoint /rules and an api token from User
    Given I have a User creation payload
    Given I have a Event creation payload
    Given I POST it the endpoind /event
    When I GET a user with endpoint /users and an api token
    Then I receive a 200 status code for User
    Then I can find the user in the GET response

