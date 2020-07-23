Feature: Cheese items

  Scenario Outline: "Aged Brie" actually increases in Quality the older it gets
    Given a cheese item "Aged Brie" with quality <Q0> to be sold in <S0>
    When the day ends
    Then its quality becomes <Q1>
    Examples:
      | S0 | Q0 | Q1 |
      | 3  | 3  | 4  |
      | 10 | 0  | 1  |
      | 50 | 30 | 31 |

  Scenario Outline: Once the sell by date has passed, Quality degrades twice as fast
    Given a cheese item "Aged Brie" with quality <Q0> to be sold in <S0>
    When the day ends
    Then its quality becomes <Q1>
    Examples:
      | S0 | Q0 | Q1 |
      | 0  | 3  | 5  |
      | -3 | 0  | 2  |
      | -8 | 30 | 32 |

  Scenario Outline: The Quality of an item is never more than 50
    Given a cheese item "Aged Brie" with quality <Q0> to be sold in <S0>
    When the day ends
    Then its quality becomes <Q1>
    Examples:
      | S0 | Q0 | Q1 |
      | 3  | 50 | 50 |
      | -3 | 50 | 50 |
      | -8 | 49 | 50 |