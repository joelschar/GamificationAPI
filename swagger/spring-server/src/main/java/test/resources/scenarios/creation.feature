Feature: Creation of badges

  Scenario: Create a badge
    Given I have a badge creation payload
    When I POST a badge in endpoint /badges
    Then I receive a 201 status code