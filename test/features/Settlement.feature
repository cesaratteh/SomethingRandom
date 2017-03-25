Feature: Settlement

  Scenario: A game is in progress and there are existing Tiles on the board

    Given A Settlement is being founded on level 1
    When A Meeple is placed on a non-volcano Hexagon
    Then The turn ends with a legal move
    And  The Meeple is subtracted from the Player's set of Meeples

    Given A Settlement is being founded on level 1
    When A Totoro is placed on a non-volcano Hexagon
    Then The move is illegal
    And The game ends

    Given A Settlement is being founded on level 1
    When A Tiger is placed on a non-volcano Hexagon
    Then The move is illegal
    And The game ends

    Given A Settlement is creating a tiger playground on level 1 or 2
    When A Tiger is placed on a non-volcano Hexagon
    Then The move is illegal
    And The game ends

    Given A Settlement is being founded on a level other than 1
    When A Meeple is placed on a non-volcano Hexagon
    Then The move is illegal
    And The game ends

    Given The Player is out of Meeples and Totoros and Tigers
    When The Player attempts a build option
    Then The Player loses

    Given The Player has an existing Settlement
    When The Player decides to Expand their Settlement from a Hexagon
    And The Player has suffcient Meeples
    Then The Settlement Expands to all adjacent Hexagons from the chosen settlement
    And Meeples are placed on the new Settlement Hexagons
    And Meeples are subtracted from the Player's set of Meeples

    Given There are 2 existing Settlements
    When The 2 Settlements become adjacent
    Then Merge the 2 Settlements together

    Given There is a Settlement of size 5 or more without a Totoro
    When The Player attempts to place a Totoro on a non-volcano empty Hexagon
    Then The Totoro is placed otherwise the game is ended

    Given There is a unsettled Hexagon that is at level 3 or more
    And The Hexagon is connected to a settlement without a Tiger
    When The Player attempts to place a Tiger on a non-volano empty Hexagon
    Then The Tiger is placed otherwise the game is ended
