Feature: PointScales

  Background:
    Given There is an api server with a /pointScales endpoint


#   Scenario: Create a PointScale
#
  Scenario: Create a PointScale
    Given I have a pointScale creation payload
    When I POST a pointScale to endpoint /pointScale and an api token
    Then I receive a 201 status code

#   Scenario: Get a PointScale
#
  Scenario: Get a PointScale
    Given I have a pointScale created and a pointScale getting payload
    When I GET a pointScale with endpoint /pointScales and an api token
    Then I receive a 200 status code

#  Scenario: Update a PointScale
  Scenario: Update a PointScale
    Given I have a pointScale created and a pointScale updating payload
    When I PUT a pointScale with endpoint /pointScales and an api token
    Then I recieve a 200 status code


#  Scenario: Delete a PointScale
#
  Scenario: Delete a PointScale
    Given I have a pointScale created and a pointScale deleting payload
    When I DELETE a pointScale with endpoint /pointScales  and an api token
    Then I receive a 200 status code