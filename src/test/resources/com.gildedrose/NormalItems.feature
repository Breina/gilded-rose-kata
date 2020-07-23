Feature: Normal items

  Scenario Outline: At the end of each day our system lowers both values for every item
    Given an item "Elixir of the Mongoose" with quality <Q0> to be sold in <S0>
    When the day ends
    Then its quality becomes <Q1>
    Examples:
      | S0 | Q0 | Q1 |
      | 10 | 10 | 9  |
      | 5  | 5  | 4  |
      | 30 | 1  | 0  |
      | 3  | 50 | 49 |

  Scenario Outline: Once the sell by date has passed, Quality degrades twice as fast
    Given an item "Elixir of the Mongoose" with quality <Q0> to be sold in <S0>
    When the day ends
    Then its quality becomes <Q1>
    Examples:
      | S0 | Q0 | Q1 |
      | -5 | 10 | 8  |
      | -8 | 50 | 48 |
      | 0  | 3  | 1  |

  Scenario Outline: The Quality of an item is never becomes negative
    Given an item "Elixir of the Mongoose" with quality <Q0> to be sold in <S0>
    When the day ends
    Then its quality becomes <Q1>
    Examples:
      | S0 | Q0 | Q1 |
      | 3  | 0  | 0  |
      | -3 | 0  | 0  |
      | -3 | 1  | 0  |

  Scenario: The Quality of an item is never negative
    Given an item "Elixir of the Mongoose" with quality -5 to be sold in 5
    Then it fails
