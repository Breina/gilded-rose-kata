Feature: Backstage Passes

  Scenario Outline: "Backstage passes" increases in Quality as its SellIn value approaches;
    Given a backstage pass "TAFKAL80ETC concert" with quality <Q0> to be sold in <S0>
    When the day ends
    Then its quality becomes <Q1>
    Examples:
      | S0 | Q0 | Q1 |
      | 50 | 0  | 1  |
      | 12 | 10 | 11 |
      | 11 | 10 | 12 |
      | 10 | 10 | 12 |
      |  7 | 20 | 22 |
      |  6 | 20 | 23 |
      |  5 | 20 | 23 |
      |  1 | 30 | 33 |
      |  0 | 30 |  0 |
      | -1 | 30 |  0 |