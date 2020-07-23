Feature: Conjured items

  Scenario Outline: At the end of each day our system lowers both values for every item
    Given an conjured item "Conjured" with quality <Q0> to be sold in <S0>
    When the day ends
    Then its quality becomes <Q1>
    Examples:
      | S0 | Q0 | Q1 |
      | 10 | 10 | 8  |
      | 5  | 5  | 3  |
      | 30 | 2  | 0  |
      | 30 | 1  | 0  |
      | 3  | 50 | 48 |

  Scenario Outline: Once the sell by date has passed, Quality degrades twice as fast
    Given an conjured item "Conjured" with quality <Q0> to be sold in <S0>
    When the day ends
    Then its quality becomes <Q1>
    Examples:
      | S0 | Q0 | Q1 |
      | -5 | 10 | 6  |
      | -8 | 50 | 46 |
      | 0  | 4  | 0  |

  Scenario Outline: The Quality of an item is never becomes negative
    Given an conjured item "Conjured" with quality <Q0> to be sold in <S0>
    When the day ends
    Then its quality becomes <Q1>
    Examples:
      | S0 | Q0 | Q1 |
      | 3  | 1  | 0  |
      | 3  | 0  | 0  |
      | -3 | 0  | 0  |
      | -3 | 1  | 0  |
      | -3 | 2  | 0  |
      | -3 | 3  | 0  |


  Scenario: The Quality of an item is never negative
    Given an conjured item "Conjured" with quality -5 to be sold in 5
    Then it fails
