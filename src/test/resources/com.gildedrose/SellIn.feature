Feature: Sell in descreasing

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

Feature: Normal items

  Scenario Outline: At the end of each day our system lowers both values for every item
    Given an item "Elixir of the Mongoose" with quality <Q0> to be sold in <S0>
    When the day ends
    Then its quality becomes <Q1> and it's to be sold in <S1>
    Examples:
      | Q0 | S0 | Q1 | S1 |
      | 10 | 10 | 9  | 9  |
      | 5  | 5  | 4  | 4  |
      | 1  | 30 | 0  | 29 |
      | 50 | 3  | 49 | 2  |

  Scenario Outline: Once the sell by date has passed, Quality degrades twice as fast
    Given an item "Elixir of the Mongoose" with quality <Q0> to be sold in <S0>
    When the day ends
    Then its quality becomes <Q1> and it's to be sold in <S1>
    Examples:
      | Q0 | S0 | Q1 | S1 |
      | 10 | -5 | 8  | -6 |
      | 50 | -8 | 48 | -9 |
      | 3  | 0  | 1  | -1 |

  Scenario Outline: The Quality of an item is never negative
    Given an item "Elixir of the Mongoose" with quality <Q0> to be sold in <S0>
    When the day ends
    Then its quality becomes <Q1> and it's to be sold in <S1>
    Examples:
      | Q0 | S0 | Q1 | S1 |
      | 0  | 3  | 0  | 2  |
      | 0  | -3 | 0  | -4 |
      | 1  | -3 | 0  | -4 |

Feature: Cheese items

  Scenario Outline: "Aged Brie" actually increases in Quality the older it gets
    Given a cheese item "Aged Brie" with quality <Q0> to be sold in <S0>
    When the day ends
    Then its quality becomes <Q1> and it's to be sold in <S1>
    Examples:
      | Q0 | S0 | Q1 | S1 |
      | 3  | 3  | 4  | 2  |
      | 0  | 10 | 1  | 9  |
      | 30 | -3 | 4  | -4 |
