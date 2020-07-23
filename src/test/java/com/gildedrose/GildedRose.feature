Feature: Untitled Feature

  Scenario: Untitled Scenario
    Given an item "foo"
    And the quality is 10
    And the sell in is 10
    When I update quality
    Then the item's name is "foo"
    Then the quality is updated to 42
    And the sell in is updated to 54

  Scenario: Second Untitled Scenario
    Given an item "foo" with quality 10 and sell in -10
    When I update quality
    Then the quality becomes 42 and sell in becomes -54