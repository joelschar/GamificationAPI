Feature: PointScales

  Background:
    Given There is an api server with a /pointscales endpoint

#    Scenario: Create a PointScale
#
  Scenario: Create a PointScale
    Given I have a pointScale creation payload
    When I POST it to the endpoint /pointScale with an api token
    Then I receive a 201 status code from /pointScale
    Then I can find the pointscale in the GET response

#   Scenario: Get a PointScale
#
  Scenario: Get a PointScale
    Given I have a pointScale creation payload
    Given I POST it to the endpoint /pointScale with an api token
    When I GET the endpoint /pointScales with an api token
    Then I receive a 200 status code from /pointScale

#   Scenario: Update a PointScale
#
  Scenario: Update a PointScale
    Given I have a pointScale creation payload
    Given I POST it to the endpoint /pointScale with an api token
    Given I have a pointscale creation paylod from GET response
    Given I change its name
    When I UPDATE it with endpoint /pointScales and an api token
    Then I receive a 202 status code from /pointScale
    Then I can find the pointscale in the GET response

#    Scenario: Delete a PointScale
#
  Scenario: Delete a PointScale
    Given I have a pointScale creation payload
    Given I POST it to the endpoint /pointScale with an api token
    Given I have a pointscale creation paylod from GET response
    When I DELETE a pointScale with endpoint /pointScales and an api token
    Then I receive a 202 status code from /pointScale
    Then I can't find the pointscale in the GET response
