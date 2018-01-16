Feature: Google page

  Scenario: search google.com from different feature file
    Given I go to google
    When I query for "cucumber spring selenium"
    And click search
    Then google page title should become "cucumber spring selenium - Google Search"
