Feature: Sell in decreasing

  Scenario Outline: At the end of each day our system lowers the SellIn value for every item
    Given a <Type> "foo" with quality <Q0> to be sold in <S0>
    When the day ends
    Then it's to be sold in <S1>
    Examples:
      | Type           | Q0 | S0 | S1 |
      | item           | 5  | 5  | 4  |
      | item           | 0  | 0  | -1 |
      | item           | 13 | -5 | -6 |
      | cheese item    | 5  | 5  | 4  |
      | cheese item    | 0  | 0  | -1 |
      | cheese item    | 13 | -5 | -6 |
      | backstage pass | 5  | 5  | 4  |
      | backstage pass | 0  | 0  | -1 |
      | backstage pass | 13 | -5 | -6 |
      | anything       | 13 | -5 | -6 |

  Scenario: "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
    Given a legendary item "Sulfuras" with quality 80 to be sold in 0
    When the day ends
    But it's to be sold in 0
    And its quality becomes 80
